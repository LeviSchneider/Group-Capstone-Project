/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.cms.dao;

import com.tsg.cms.HashTagMatcher;
import com.tsg.cms.dto.BlogPost;
import com.tsg.cms.dto.BlogPostContainer;
import com.tsg.cms.dto.CategoryContainer;
import com.tsg.cms.dto.StaticPage;
import com.tsg.cms.dto.TagContainer;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import javax.inject.Inject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author apprentice
 */
public class BlogPostDbDaoImpl implements BlogPostDbDao {

    private TagDbDao tagDao;

    private HashTagMatcher hashTagMatcher;

    @Inject
    public BlogPostDbDaoImpl(TagDbDao tagDao, HashTagMatcher hashTagMatcher) {

        this.tagDao = tagDao;
        this.hashTagMatcher = hashTagMatcher;
    }

    private static final String SQL_INSERT_BLOGPOST
            = "insert into blogPosts (timeCreated, timeEdited, startDate, endDate, title, postBody, userIdFK, titleNumber, status) value(?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_DELETE_BLOGPOST
            = "delete from blogPosts where postId = ?";
    private static final String SQL_UPDATE_BLOGPOST
            = "update blogPosts set timeCreated = ?, timeEdited = ?, startDate = ?, endDate = ?, title = ?, postBody = ?, userIdFK = ?, titleNumber = ?, status = ? where postId = ?";
    private static final String SQL_SELECT_ALL_BLOGPOSTS
            = "select * from blogPosts ORDER BY postId DESC";
    private static final String SQL_SELECT_BLOGPOST_BY_ID
            = "select * from blogPosts where postId = ?";
    private static final String SQL_SELECT_BLOGPOST_TITLE_BY_ID
            = "select title from blogPosts where postId = ?";
    private static final String SQL_SELECT_BLOGPOST_BY_TITLENUMBER
            = "select * from blogPosts where titleNumber = ?";
    private static final String SQL_SELECT_BLOGPOST_BY_TITLE
            = "select * from blogPosts where title = ?";
    private static final String SQL_SELECT_TITLENUMBERS
            = "SELECT titleNumber FROM blogPosts where titleNumber rlike ?";
    private static final String SQL_SELECT_BLOGPOSTS_BY_HASHTAG_NAME
            = "select blogPosts.*, hashTags.hashTagName "
            + "from blogPosts "
            + "join postHashTagBridge "
            + "on blogPosts.postId = postHashTagBridge.postIdFK "
            + "join hashTags "
            + "on postHashTagBridge.HashTagIdFK = hashTags.hashTagId "
            + "where hashTagName = ?";

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public BlogPostContainer getBlogPostById(Integer postId) {

        BlogPostContainer container = new BlogPostContainer();
        BlogPost blogPost = new BlogPost();
        try {

            blogPost = jdbcTemplate.queryForObject(SQL_SELECT_BLOGPOST_BY_ID, new BlogPostMapper(), postId);
            container.setBlogPost(blogPost);
        } catch (EmptyResultDataAccessException e) {
            container.setMessage("No blogPost with that id.");
        }
        TagContainer tagContainer = new TagContainer();
        tagContainer.setTagList(tagDao.getPostTags(blogPost.getPostId()));
        container.setTagContainer(tagContainer);

        return container;

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public BlogPostContainer addBlogPost(BlogPost blogPost) {

        setTitleNumber(blogPost);

        Date date = new Date();
        blogPost.setTimeCreated(date);
        blogPost.setTimeEdited(date);

        jdbcTemplate.update(SQL_INSERT_BLOGPOST,
                blogPost.getTimeCreated(),
                blogPost.getTimeEdited(),
                blogPost.getStartDate(),
                blogPost.getEndDate(),
                blogPost.getTitle(),
                blogPost.getPostBody(),
                blogPost.getUserIdFK(),
                blogPost.getTitleNumber(),
                blogPost.getStatus().toString());

        blogPost.setPostId(jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class));

        String body = blogPost.getPostBody();
        List<String> tags = hashTagMatcher.findHashTags(body);

        for (String tag : tags) {

            tagDao.addTag(tag, blogPost.getPostId());

        }

        BlogPostContainer container = new BlogPostContainer();
        container.setBlogPost(blogPost);

        TagContainer tagContainer = new TagContainer();
        //we are not re-getting the list of hashTags so we can make sure we
        //have valid tags (ie, the tags have been processed)
        tagContainer.setTagList(tagDao.getPostTags(blogPost.getPostId()));
        container.setTagContainer(tagContainer);

        return container;

    }
    //= "insert into blogPosts (timeCreated, timeEdited, startDate, endDate, title, postBody, userIdFK, titleNumber, status) value(?, ?, ?, ?, ?, ?, ?, ?, ?)";

    @Override
    public void removeBlogPost(Integer postId) {
        jdbcTemplate.update(SQL_DELETE_BLOGPOST, postId);
    }

    @Override
    public BlogPostContainer updateBlogPost(BlogPost updatedPost) {

//                    timeCreated: "2016-04-27",
//            timeEdited: "2016-04-27",
//            startDate: $('#start-date').val(),
//            endDate: $('#end-date').val(),
//            title: $('#post-title').val(),
//            postBody: tinyMCE.activeEditor.getContent(),
//            status: $('#post-status').val()
        
        //could be made to fetch just the title, but probably better to reuse existing method
        //Check to see if title has changed; if it has, set a new titlenumber
        BlogPost oldPost = jdbcTemplate.queryForObject(SQL_SELECT_BLOGPOST_BY_ID, new BlogPostMapper(), updatedPost.getPostId());
        if (!oldPost.getTitle().equals(updatedPost.getTitle())) {
            setTitleNumber(updatedPost);
        }
        updatedPost.setUserIdFK(oldPost.getUserIdFK());

        //TODO: make sure removed hashTags are properly deleted
        
        //getting any new HashTags
        String body = updatedPost.getPostBody();
        List<String> tags = hashTagMatcher.findHashTags(body);
        for (String tag : tags) {
            tagDao.addTag(tag, updatedPost.getPostId());
        }
        Date date = new Date();
        updatedPost.setTimeEdited(date);

        jdbcTemplate.update(SQL_UPDATE_BLOGPOST,
                updatedPost.getTimeCreated(),
                updatedPost.getTimeEdited(),
                updatedPost.getStartDate(),
                updatedPost.getEndDate(),
                updatedPost.getTitle(),
                updatedPost.getPostBody(),
                updatedPost.getUserIdFK(),
                updatedPost.getTitleNumber(),
                updatedPost.getStatus().toString(),
                updatedPost.getPostId()
        );

        BlogPostContainer container = new BlogPostContainer();
        container.setBlogPost(updatedPost);

        TagContainer tagContainer = new TagContainer();
        //we are not re-getting the list of hashTags so we can make sure we
        //have valid tags (ie, the tags have been processed)
        tagContainer.setTagList(tagDao.getPostTags(updatedPost.getPostId()));
        container.setTagContainer(tagContainer);

        return container;

    }

    @Override
    public List<BlogPostContainer> getAllBlogPosts() {

        List<BlogPostContainer> blogPostContainerList = new ArrayList<>();

        List<BlogPost> blogPostList = jdbcTemplate.query(SQL_SELECT_ALL_BLOGPOSTS, new BlogPostMapper());

        for (BlogPost b : blogPostList) {

            BlogPostContainer container = new BlogPostContainer();
            container.setBlogPost(b);
            TagContainer tagContainer = new TagContainer();
            tagContainer.setTagList(tagDao.getPostTags(b.getPostId()));
            container.setTagContainer(tagContainer);
            blogPostContainerList.add(container);

        }

        return blogPostContainerList;

    }

    @Override
    public BlogPostContainer getBlogPostByTitleNumber(String titleNumber) {

        try {

            BlogPost blogPost = jdbcTemplate.queryForObject(SQL_SELECT_BLOGPOST_BY_TITLENUMBER, new BlogPostMapper(), titleNumber);

            BlogPostContainer container = new BlogPostContainer();
            container.setBlogPost(blogPost);
            TagContainer tagContainer = new TagContainer();
            tagContainer.setTagList(tagDao.getPostTags(blogPost.getPostId()));
            container.setTagContainer(tagContainer);

            return container;

        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<BlogPostContainer> searchBlogPosts(Map<SearchTerm, String> criteria) {
        if (criteria.size() == 0) {

            return getAllBlogPosts();
        } else {
            StringBuilder sQuery = new StringBuilder("select * from blogPosts where ");
            int numParams = criteria.size();
            int paramPosition = 0;
            String[] paramVals = new String[numParams];
            Set<SearchTerm> keySet = criteria.keySet();
            Iterator<SearchTerm> iter = keySet.iterator();

            while (iter.hasNext()) {
                SearchTerm currentKey = iter.next();

                if (paramPosition > 0) {
                    sQuery.append(" and ");
                }

                sQuery.append(currentKey);
                sQuery.append(" = ? ");
                paramVals[paramPosition] = criteria.get(currentKey);
                paramPosition++;
            }

            List<BlogPost> blogPostList = jdbcTemplate.query(sQuery.toString(), new BlogPostMapper(), paramVals);
            List<BlogPostContainer> blogPostContainerList = new ArrayList<>();

            for (BlogPost b : blogPostList) {

                BlogPostContainer container = new BlogPostContainer();
                container.setBlogPost(b);
                TagContainer tagContainer = new TagContainer();
                tagContainer.setTagList(tagDao.getPostTags(b.getPostId()));
                container.setTagContainer(tagContainer);
                blogPostContainerList.add(container);

            }

            return blogPostContainerList;

        }
    }

    @Override
    public List<BlogPost> getBlogPostsByTitle(String title) {
        try {
            return jdbcTemplate.query(SQL_SELECT_BLOGPOST_BY_TITLE, new BlogPostMapper(), title);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    //search for all posts with same title; if none exist, set titleNumber to title.
    //if the title already exists, extract titlenumbers from result.
    //loop through title numbers and find the lowest.
//    private void setTitleNumber(BlogPost blogPost) {
//        String title = blogPost.getTitle();
//        List<BlogPost> pagesWithSameTitle = getBlogPostsByTitle(title);
//
//        if (pagesWithSameTitle.isEmpty()) {
//            title = title.replaceAll("([^a-zA-Z0-9 _]|^\\s)", "");
//            title = title.replaceAll("([^a-zA-Z0-9]|^\\s)", "_");
//            blogPost.setTitleNumber(title);
//        } else {
//            title = title.replaceAll("([^a-zA-Z0-9 _]|^\\s)", "");
//            title = title.replaceAll("([^a-zA-Z0-9]|^\\s)", "_");
//            List<String> titleNumbers = pagesWithSameTitle.stream()
//                    .map(p -> p.getTitleNumber())
//                    .collect(Collectors.toList());
//            for (int i = 0; i <= titleNumbers.size() + 1; i++) {
//                if (!titleNumbers.contains(title + i)) {
//                    blogPost.setTitleNumber(title + i);
//                }
//            }
//        }
//    }
    private List<String> getTitleNumbersLikeTitle(String title) {
        title = title + "\\d*";
        try {
            return jdbcTemplate.queryForList(SQL_SELECT_TITLENUMBERS, String.class, new Object[]{title});
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    private void setTitleNumber(BlogPost blogPost) {
        String title = blogPost.getTitle();
        title = title.replaceAll("([^a-zA-Z0-9 _]|^\\s)", "");
        title = title.replaceAll("([^a-zA-Z0-9]|^\\s)", "_");
        List<String> titleNumbers = getTitleNumbersLikeTitle(title);

        if (titleNumbers.isEmpty()) {
            blogPost.setTitleNumber(title);
        } else {
            for (Integer i = 0; i <= titleNumbers.size() + 1; i++) {
                if (!titleNumbers.contains(title + i.toString())) {
                    blogPost.setTitleNumber(title + i.toString());
                }
            }
        }
    }

    @Override
    public List<BlogPostContainer> getBlogPostsByTag(String tag) {
        try {

            List<BlogPost> blogPostList = jdbcTemplate.query(SQL_SELECT_BLOGPOSTS_BY_HASHTAG_NAME, new BlogPostMapper(), tag);
            List<BlogPostContainer> blogPostContainerList = new ArrayList<>();

            for (BlogPost b : blogPostList) {

                BlogPostContainer container = new BlogPostContainer();
                container.setBlogPost(b);
                TagContainer tagContainer = new TagContainer();
                tagContainer.setTagList(tagDao.getPostTags(b.getPostId()));
                container.setTagContainer(tagContainer);
                blogPostContainerList.add(container);

            }

            return blogPostContainerList;

        } catch (EmptyResultDataAccessException | NullPointerException e) {
            return null;
        }
    }

    //search for all posts with same title; if none exist, set titleNumber to title.
    //if the title already exists, extract titlenumbers from result.
    //loop through title numbers and find the lowest.
    private static final class BlogPostMapper implements RowMapper<BlogPost> {

        @Override
        public BlogPost mapRow(ResultSet rs, int i) throws SQLException {
            BlogPost blogPost = new BlogPost();
            blogPost.setPostId(rs.getInt("postId"));
            blogPost.setTimeCreated(rs.getDate("timeCreated"));
            blogPost.setTimeEdited(rs.getDate("timeEdited"));
            blogPost.setStartDate(rs.getDate("startDate"));
            blogPost.setEndDate(rs.getDate("endDate"));
            blogPost.setTitle(rs.getString("title"));
            blogPost.setPostBody(rs.getString("postBody"));
            blogPost.setUserIdFK(rs.getInt("userIdFK"));
            int value = rs.getInt("categoryIdFK");
            blogPost.setCategoryIdFK(rs.wasNull() ? null : value);
            blogPost.setTitleNumber(rs.getString("titleNumber"));
            blogPost.setStatus((rs.getString("status")));
            return blogPost;
        }

    }
}
