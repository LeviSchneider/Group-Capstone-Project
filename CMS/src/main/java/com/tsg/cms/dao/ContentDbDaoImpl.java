/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.cms.dao;

import com.tsg.cms.dto.Content;
import com.tsg.cms.dto.ContentState;
import java.sql.ResultSet;
import java.sql.SQLException;
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
public class ContentDbDaoImpl implements ContentDbDao{

    private static final String SQL_INSERT_CONTENT
            = "insert into blogPosts (dateSubmitted, startDate, endDate, title, postBody, userIdFK, status, postType) value(?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_DELETE_CONTENT
            = "delete from blogPosts where postId = ?";
    private static final String SQL_UPDATE_CONTENT
            = "update blogPosts set dateSubmitted = ?, startDate = ?, endDate = ?, title = ?, postBody = ?, userIdFK = ?, status = ?, postType = ?";
    private static final String SQL_SELECT_ALL_CONTENT
            = "select * from blogPosts";
    private static final String SQL_SELECT_CONTENT
            = "select * from blogPosts where postId = ?";
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Content addContent(Content content) {
        jdbcTemplate.update(SQL_INSERT_CONTENT,
                content.getDateSubmitted(),
                content.getStartDate(),
                content.getEndDate(),
                content.getTitle(),
                content.getPostBody(),
                content.getUserIdFK(),
                content.getStatus(),
                content.getPostType());
        content.setPostId(jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class));
        return content;
    }

    @Override
    public void removeContent(int postId) {
        jdbcTemplate.update(SQL_DELETE_CONTENT, postId);
    }

    @Override
    public void updateContent(Content content) {
        jdbcTemplate.update(SQL_UPDATE_CONTENT,
                content.getDateSubmitted(),
                content.getStartDate(),
                content.getEndDate(),
                content.getTitle(),
                content.getPostBody(),
                content.getUserIdFK(),
                content.getStatus(),
                content.getPostType());
    }

    @Override
    public List<Content> getAllContent() {
        return jdbcTemplate.query(SQL_SELECT_ALL_CONTENT, new ContentMapper());
    }
    
    @Override
    public Content getContentById(int postId) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_CONTENT, new ContentMapper(), postId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    
    @Override
    public List<Content> searchContent(Map<SearchTerm, String> criteria) {
        if(criteria.size() == 0)
        {
            return getAllContent();
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
            return jdbcTemplate.query(sQuery.toString(), new ContentMapper(), paramVals);
        }
    }
    
    private static final class ContentMapper implements RowMapper<Content> {

        @Override
        public Content mapRow(ResultSet rs, int i) throws SQLException {
            Content content = new Content();
            content.setDateSubmitted(rs.getDate("dateSubmitted"));
            content.setStartDate(rs.getDate("startDate"));
            content.setEndDate(rs.getDate("endDate"));
            content.setTitle(rs.getString("title"));
            content.setPostBody(rs.getString("postBody"));
            content.setUserIdFK(rs.getInt("userIdFK"));
            content.setStatus(rs.getString("status"));
            content.setPostType(rs.getString("postType"));
            
            return content;
        }
        
    }
}
