/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.cms.dao;

import com.tsg.cms.dto.BlogPost;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
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

    private static final String SQL_INSERT_BLOGPOST
            = "insert into blogPosts (timeCreated, timeEdited, startDate, endDate, title, postBody, userIdFK, titleNumber, status) value(?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_DELETE_BLOGPOST
            = "delete from blogPosts where postId = ?";
    private static final String SQL_UPDATE_BLOGPOST
            = "update blogPosts set timeCreated = ?, timeEdited = ?, startDate = ?, endDate = ?, title = ?, postBody = ?, userIdFK = ?, titleNumber = ?, status = ?";
    private static final String SQL_SELECT_ALL_BLOGPOSTS
            = "select * from blogPosts";
    private static final String SQL_SELECT_BLOGPOST_BY_ID
            = "select * from blogPosts where postId = ?";
    private static final String SQL_SELECT_BLOGPOST_BY_TITLENUMBER
            = "select * from blogPosts where titleNumber = ?";
    private static final String SQL_SELECT_BLOGPOST_BY_TITLE
            = "select * from blogPosts where title = ?";
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
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public BlogPost addBlogPost(BlogPost blogPost) {

        setTitleNumber(blogPost);

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
        return blogPost;
    }

    @Override
    public void removeBlogPost(int postId) {
        jdbcTemplate.update(SQL_DELETE_BLOGPOST, postId);
    }

    @Override
    public void updateBlogPost(BlogPost blogPost) {
        setTitleNumber(blogPost);
        jdbcTemplate.update(SQL_UPDATE_BLOGPOST,
                blogPost.getTimeCreated(),
                blogPost.getTimeEdited(),
                blogPost.getStartDate(),
                blogPost.getEndDate(),
                blogPost.getTitle(),
                blogPost.getPostBody(),
                blogPost.getUserIdFK(),
                blogPost.getTitleNumber(),
                blogPost.getStatus().toString()
        );
    }

    @Override
    public List<BlogPost> getAllBlogPost() {
        return jdbcTemplate.query(SQL_SELECT_ALL_BLOGPOSTS, new BlogPostMapper());
    }

    @Override
    public BlogPost getBlogPostById(int postId) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_BLOGPOST_BY_ID, new BlogPostMapper(), postId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public BlogPost getBlogPostByTitleNumber(String titleNumber) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_BLOGPOST_BY_TITLENUMBER, new BlogPostMapper(), titleNumber);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<BlogPost> getBlogPostByTitle(String title) {
        try {
            return jdbcTemplate.query(SQL_SELECT_BLOGPOST_BY_TITLE, new BlogPostMapper(), title);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<BlogPost> searchBlogPost(Map<SearchTerm, String> criteria) {
        if (criteria.size() == 0) {
            return getAllBlogPost();
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
            return jdbcTemplate.query(sQuery.toString(), new BlogPostMapper(), paramVals);
        }
    }

    //search for all posts with same title; if none exist, set titleNumber to title.
    //if the title already exists, extract titlenumbers from result.
    //loop through title numbers and find the lowest.
    private void setTitleNumber(BlogPost blogPost) {
        String title = blogPost.getTitle();
        title = title.replaceAll("([^a-zA-Z0-9 _]|^\\s)", "");
        title = title.replaceAll("([^a-zA-Z0-9]|^\\s)", "_");
        List<BlogPost> postsWithSameTitle = getBlogPostByTitle(title);

        if (postsWithSameTitle.isEmpty()) {
            blogPost.setTitleNumber(title);
        } else {
            List<String> titleNumbers = postsWithSameTitle.stream()
                    .map(p -> p.getTitleNumber())
                    .collect(Collectors.toList());
            for (int i = 0; i <= titleNumbers.size() + 1; i++) {
                if (!titleNumbers.contains(title + i)) {
                    blogPost.setTitleNumber(title + i);
                }
            }
        }
    }

    @Override
    public List<BlogPost> getBlogPostByTag(String tag) {
        try {
            return jdbcTemplate.query(SQL_SELECT_BLOGPOSTS_BY_HASHTAG_NAME, new BlogPostMapper(), tag);
        } catch (EmptyResultDataAccessException e) {
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
            blogPost.setTitleNumber(rs.getString("titleNumber"));
            blogPost.setStatus(Status.valueOf(rs.getString("status")));
            return blogPost;
        }

    }
}
