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
            = "insert into blogPosts (dateSubmitted, startDate, endDate, title, postBody, userIdFK, status, postType, titleNumber) values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_DELETE_BLOGPOST
            = "delete from blogPosts where postId = ?";
    private static final String SQL_UPDATE_BLOGPOST
            = "update blogPosts set dateSubmitted = ?, startDate = ?, endDate = ?, title = ?, postBody = ?, userIdFK = ?, status = ?, postType = ?, titleNumber = ? where postId = ?";
    private static final String SQL_SELECT_ALL_BLOGPOST
            = "select * from blogPosts ORDER BY postId DESC";
    private static final String SQL_SELECT_BLOGPOST
            = "select * from blogPosts where postId = ?";
    private static final String SQL_SELECT_BLOGPOST_BY_TITLENUMBER
            = "select * from blogPosts where titleNumber = ?";
    private static final String SQL_SELECT_BLOGPOST_BY_TITLE
            = "select * from blogPosts where title = ?";
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public BlogPost addBlogPost(BlogPost blogPost) {

        setTitleNumber(blogPost);

        jdbcTemplate.update(SQL_INSERT_BLOGPOST,
                            blogPost.getDateSubmitted(),
                            blogPost.getStartDate(),
                            blogPost.getEndDate(),
                            blogPost.getTitle(),
                            blogPost.getPostBody(),
                            blogPost.getUserIdFK(),
                            blogPost.getStatus(),
                            blogPost.getPostType(),
                            blogPost.getTitleNumber());

        blogPost.setPostId(jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class));
        return blogPost;
    }

    @Override
    public void removeBlogPost(int postId) {
        jdbcTemplate.update(SQL_DELETE_BLOGPOST, postId);
    }

    //need to ensure urls work here!!!
    @Override
    public void updateBlogPost(BlogPost blogPost) {
        setTitleNumber(blogPost);
        jdbcTemplate.update(SQL_UPDATE_BLOGPOST,
                            blogPost.getDateSubmitted(),
                            blogPost.getStartDate(),
                            blogPost.getEndDate(),
                            blogPost.getTitle(),
                            blogPost.getPostBody(),
                            blogPost.getUserIdFK(),
                            blogPost.getStatus(),
                            blogPost.getPostType(),
                            blogPost.getTitleNumber(),
                            blogPost.getPostId()
        );
    }

    @Override
    public List<BlogPost> getAllBlogPost() {
        return jdbcTemplate.query(SQL_SELECT_ALL_BLOGPOST, new BlogPostMapper());
    }

    @Override
    public BlogPost getBlogPostById(int postId) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_BLOGPOST, new BlogPostMapper(), postId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    //add to interface
    //maybe come up with better name for this; it's a local resource locator
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
            // ^ check this, not sure if args / sql arg is correct
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

    private void setTitleNumber(BlogPost blogPost) {
        String title = blogPost.getTitle();

        List<BlogPost> postsWithSameTitle = getBlogPostByTitle(title);

        if (postsWithSameTitle.isEmpty()) {
            blogPost.setTitleNumber(blogPost.getTitle());
        } else {
            List<String> titleNumbers = postsWithSameTitle.stream()
                    .map(p -> p.getTitleNumber())
                    .collect(Collectors.toList());
            for (int i = 0; i <= titleNumbers.size() + 1; i++) {
                if (!titleNumbers.contains("title" + i)) {
                    blogPost.setTitleNumber(title + i);
                }
            }
        }
    }

    //search for all posts with same title; if none exist, set titleNumber to title.
    //if the title already exists, extract titlenumbers from result.
    //loop through title numbers and find the lowest.
    private static final class BlogPostMapper implements RowMapper<BlogPost> {

        @Override
        public BlogPost mapRow(ResultSet rs, int i) throws SQLException {
            BlogPost blogPost = new BlogPost();
            blogPost.setDateSubmitted(rs.getDate("dateSubmitted"));
            blogPost.setStartDate(rs.getDate("startDate"));
            blogPost.setEndDate(rs.getDate("endDate"));
            blogPost.setTitle(rs.getString("title"));
            blogPost.setPostBody(rs.getString("postBody"));
            blogPost.setUserIdFK(rs.getInt("userIdFK"));
            blogPost.setStatus(rs.getString("status"));
            blogPost.setPostType(rs.getString("postType"));
            blogPost.setPostId(rs.getInt("postId"));
            blogPost.setTitleNumber("titleNumber");
            return blogPost;
        }

    }
}
