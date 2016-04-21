/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.cms.dao;

import com.tsg.cms.dto.Category;
import com.tsg.cms.dto.BlogPost;
import com.tsg.cms.dto.BlogPostState;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author apprentice
 */
public class BlogPostDbDaoImpl implements BlogPostDbDao{

    private static final String SQL_INSERT_BLOGPOST
            = "insert into blogPosts (dateSubmitted, startDate, endDate, title, postBody, userIdFK, status, postType) value(?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_DELETE_BLOGPOST
            = "delete from blogPosts where postId = ?";
    private static final String SQL_UPDATE_BLOGPOST
            = "update blogPosts set dateSubmitted = ?, startDate = ?, endDate = ?, title = ?, postBody = ?, userIdFK = ?, status = ?, postType = ?";
    private static final String SQL_SELECT_ALL_BLOGPOST
            = "select * from blogPosts ORDER BY postId DESC";
    private static final String SQL_SELECT_BLOGPOST
            = "select * from blogPosts where postId = ?";
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public BlogPost addBlogPost(BlogPost blogPost) {
        
        
        jdbcTemplate.update(SQL_INSERT_BLOGPOST,
                blogPost.getDateSubmitted(),
                blogPost.getStartDate(),
                blogPost.getEndDate(),
                blogPost.getTitle(),
                blogPost.getPostBody(),
                blogPost.getUserIdFK(),
                blogPost.getStatus(),
                blogPost.getPostType());
        blogPost.setPostId(jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class));
        return blogPost;
    }

    @Override
    public void removeBlogPost(int postId) {
        jdbcTemplate.update(SQL_DELETE_BLOGPOST, postId);
    }

    @Override
    public void updateBlogPost(BlogPost blogPost) {
        jdbcTemplate.update(SQL_UPDATE_BLOGPOST,
                blogPost.getDateSubmitted(),
                blogPost.getStartDate(),
                blogPost.getEndDate(),
                blogPost.getTitle(),
                blogPost.getPostBody(),
                blogPost.getUserIdFK(),
                blogPost.getStatus(),
                blogPost.getPostType());
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
    
    @Override
    public List<BlogPost> searchBlogPost(Map<SearchTerm, String> criteria) {
        if(criteria.size() == 0)
        {
            return getAllBlogPost();
        } else {
            StringBuilder sQuery = new StringBuilder("select * from blogPosts where ");
            int numParams = criteria.size();
            int paramPosition = 0;
            String[] paramVals = new String[numParams];
            Set<SearchTerm> keySet = criteria.keySet();
            Iterator<SearchTerm> iter = keySet.iterator();
            
            while(iter.hasNext())
            {
                SearchTerm currentKey = iter.next();
                
                if(paramPosition > 0)
                {
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
            return blogPost;
        }
        
    }
}
