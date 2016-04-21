/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.cms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.SingleColumnRowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author apprentice
 */
public class TagDbDaoImpl implements TagDbDao {

    private static final String SQL_ADD_TAG_POST_HASHTAG_BRIDGE
            = "insert into postHashTagBridge (HashTagIdFK, postIdFK) values(?,?)";
    private static final String SQL_ADD_HASHTAG
            = "insert into hashTags (hashTagName) values(?)";
    private static final String SQL_DELETE_TAG_POST_HASHTAG_BRIDGE
            = "delete from postHashTagBridge where HashTagIdFK = ?";
    private static final String SQL_DELETE_HASHTAG
            = "delete from hashTags where HashTagId = ?";
    private static final String SQL_SELECT_HASHTAG
            = "select HashTagId from hashTags where hashTagName = ?";
    private static final String SQL_UPDATE_HASHTAG
            = "update hashTags set hashTagName = ? where HashTagId = ?";
    private static final String SQL_SELECT_POST_HASHTAGS_BY_ID
            = "select hashTagName from hashTags "
            + "inner join postHashTagBridge "
            + "on hashTags.HashTagId = postHashTagBridge.HashTagIdFK "
            + "where postIdFK = ?";
    private static final String SQL_SELECT_NUMBER_OF_HASHTAGS
            = "select hashTagName, count(postHashTagBridge.HashTagIdFK) as numberofhashtags from postHashTagBridge "
            + "left join hashTags as ht "
            + "on postHashTagBridge.HashTagIdFK = ht.HashTagId "
            + "group by hashTagName "
            + "group by numberOfHashTags desc "
            + "limit ?";
    private static final String SQL_ALL_HASHTAGS
            = "select * from hashTags";
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public String addTag(String tag, int postId) {
        jdbcTemplate.update(SQL_ADD_HASHTAG,
                tag);
        int hashTagId = jdbcTemplate.queryForObject(SQL_SELECT_HASHTAG, new String[]{tag}, Integer.class);
        jdbcTemplate.update(SQL_ADD_TAG_POST_HASHTAG_BRIDGE,
                hashTagId,
                postId);
        return tag;
    }

    @Override
    public void removeTag(String tag) {
        int hashTagId = jdbcTemplate.queryForObject(SQL_SELECT_HASHTAG, new Object[]{tag}, Integer.class);
        jdbcTemplate.update(SQL_DELETE_TAG_POST_HASHTAG_BRIDGE, hashTagId);
        jdbcTemplate.update(SQL_DELETE_HASHTAG, tag);
    }

    @Override
    public void updateTag(String tag) {
        jdbcTemplate.update(SQL_UPDATE_HASHTAG, tag);
    }

    @Override
    public List<String> getPostTags(int postId) {
        return jdbcTemplate.queryForList(SQL_SELECT_POST_HASHTAGS_BY_ID, String.class, new Object[]{postId});
    }

    @Override
    public Map<String, Integer> getNumberOfTags(int num) {
        return jdbcTemplate.query(SQL_SELECT_NUMBER_OF_HASHTAGS, new ResultSetExtractor<Map>() {
            @Override
            public Map extractData(ResultSet rs) throws SQLException, DataAccessException {
                Map<String, Integer> mapRet = new HashMap<String, Integer>();
                while (rs.next()) {
                    mapRet.put(rs.getString("hashTagName"), rs.getInt("numberofhashtags"));
                }
                return mapRet;
            }
        }, new Object[] {num});

    }

    @Override
    public List<String> getAllTags() {
        return jdbcTemplate.query(SQL_ALL_HASHTAGS, new SingleColumnRowMapper<String>());
    }

// Not sure if will be necessary but kept just in case
//    private static final class TagMapper implements RowMapper {
//
//        @Override
//        public Map<String, Integer> mapRow(ResultSet rs, int i) throws SQLException {
//            Map<String, Integer> tagMap = new HashMap<>();
//            tagMap.put(rs.getString("hashTagName"), rs.getInt("numberofhashtags"));
//            return tagMap;
//        }
//
//    }

}
