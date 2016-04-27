/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.cms.dao;

import com.tsg.cms.dto.Category;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author apprentice
 */
public class CategoryDbDaoImpl implements CategoryDbDao {

    private static final String SQL_INSERT_CATEGORY
            = "insert into categories (categoryName) value(?)";
    private static final String SQL_DELETE_CATEGORY
            = "delete from categories where categoryId = ?";
    private static final String SQL_UPDATE_CATEGORY
            = "update categories set categoryName = ? where categoryId = ?";
    private static final String SQL_SELECT_ALL_CATEGORY
            = "select * from categories ORDER BY categoryId DESC";
    private static final String SQL_SELECT_CATEGORY
            = "select * from categories where categoryId = ?";
    private static final String SQL_SELECT_POST_CATEGORY
            = "select * from categories "
            + "join on categories.categoryId = staticPages.categoryIdFK "
            + "where staticPages.pageId = ?";
    private static final String SQL_ADD_CATEGORY_TO_PAGE
            = "update staticPages set categoryIdFK = ? where pageId = ?";
    private static final String SQL_REMOVE_CATEGORY_FROM_PAGE
            = "update staticPages set categoryIdFK = NULL where staticPages.pageId = ?";

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Category addCategory(Category category) throws DuplicateKeyException {

        jdbcTemplate.update(SQL_INSERT_CATEGORY,
                category.getCategoryName());
        category.setCategoryId(jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class));

        return category;
    }

    @Override
    public Category getPostCategory(int pageId) {
        Category category = jdbcTemplate.queryForObject(SQL_SELECT_POST_CATEGORY, new Object[]{pageId}, new CategoryMapper());
        return category;
    }

    @Override
    public void removeCategory(int categoryId) {
        jdbcTemplate.update(SQL_DELETE_CATEGORY, categoryId);
    }

    @Override
    public Category updateCategory(Category category) {
        jdbcTemplate.update(SQL_UPDATE_CATEGORY,
                category.getCategoryName(), category.getCategoryId());
        return category;
    }

    @Override
    public List<Category> getAllCategories() {
        return jdbcTemplate.query(SQL_SELECT_ALL_CATEGORY, new CategoryDbDaoImpl.CategoryMapper());
    }

    @Override
    public Category getCategoryById(int categoryId) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_CATEGORY, new CategoryMapper(), categoryId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Category> searchCategory(Map<CategorySearchTerm, String> criteria) {

        if (criteria.size() == 0) {
            return getAllCategories();
        } else {
            StringBuilder sQuery = new StringBuilder("select * from categories where ");
            int numParams = criteria.size();
            int paramPosition = 0;
            String[] paramVals = new String[numParams];
            Set<CategorySearchTerm> keySet = criteria.keySet();
            Iterator<CategorySearchTerm> iter = keySet.iterator();

            while (iter.hasNext()) {
                CategorySearchTerm currentKey = iter.next();

                if (paramPosition > 0) {
                    sQuery.append(" and ");
                }

                sQuery.append(currentKey);
                sQuery.append(" = ? ");
                paramVals[paramPosition] = criteria.get(currentKey);
                paramPosition++;
            }
            return jdbcTemplate.query(sQuery.toString(), new CategoryMapper(), paramVals);
        }
    }

    @Override
    public void updateBlogPostCategory(Category category, int pageId) {
        jdbcTemplate.update(SQL_ADD_CATEGORY_TO_PAGE,
                category.getCategoryId(),
                pageId);
    }

    @Override
    public void removeBlogPostCategory(int pageId) {
        jdbcTemplate.update(SQL_REMOVE_CATEGORY_FROM_PAGE, pageId);
    }

    @Override
    public Category getBlogPostCategory(int pageId) {
        return jdbcTemplate.queryForObject(SQL_SELECT_POST_CATEGORY, new Object[]{pageId}, new CategoryMapper());
    }

    private static final class CategoryMapper implements RowMapper<Category> {

        @Override
        public Category mapRow(ResultSet rs, int i) throws SQLException {
            Category category = new Category();
            category.setCategoryName(rs.getString("categoryName"));
            category.setCategoryId(rs.getInt("categoryId"));
            return category;
        }
    }

}
