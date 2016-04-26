/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.cms.dao;

import com.tsg.cms.dto.StaticPage;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author apprentice
 */
public class SideBarLinksDbDaoImpl implements SideBarLinksDao {

    private static final String SQL_INSERT_STATIC_PAGE
            = "insert into static_pages (staticPageName, url, jspPage, javaScriptPage, position) values(?,?,?,?,?)";
    private static final String SQL_INSERT_INTO_CATEGORY_PAGE_BRIDGE
            = "insert into staticPageCategoryBridge (static_pagesIdFK, categoryIdFK) values(?,?)";
    private static final String SQL_DELETE_STATIC_PAGE
            = "delete from static_pages where static_PagesId = ?";
    private static final String SQL_DELETE_FROM_CATEGORY_PAGE_BRIDGE
            = "delete from staticPageCategoryBridge where static_pagesIdFK = ?";
    private static final String SQL_UPDATE_STATIC_PAGE
            = "update static_pages set staticPageName = ?, url = ?, jspPage = ?, javaScriptPage = ?, position = ?";
    private static final String SQL_SELECT_STATIC_PAGE
            = "select * from static_pages where static_pagesId = ?";
    private static final String SQL_SELECT_ALL_STATIC_PAGES
            = "select * from static_pages";
    private static final String SQL_SELECT_STATIC_PAGE_ID
            = "select static_pagesId from static_pages where staticPageName = ?";
    private JdbcTemplate jdbcTemplate;

    @Override
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public StaticPage addStaticPages(StaticPage page) {
        jdbcTemplate.update(SQL_INSERT_STATIC_PAGE,
                            page.getStaticPageName(),
                            page.getStaticPageUrl(),
                            page.getStaticPageJsp(),
                            page.getStaticPageJavaScript(),
                            page.getStaticPagePosition());
        page.setPageId(jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class));
        int categoryId = jdbcTemplate.queryForObject(SQL_SELECT_STATIC_PAGE_ID, new String[]{page.getStaticPageName()}, Integer.class);

        jdbcTemplate.update(SQL_INSERT_INTO_CATEGORY_PAGE_BRIDGE, page.getPageId(), categoryId);
        return page;
    }

    @Override
    public void removeStaticPage(int pageId) {
        jdbcTemplate.update(SQL_DELETE_STATIC_PAGE, pageId);

    }

    @Override
    public StaticPage updateStaticPage(StaticPage staticPage) {
        jdbcTemplate.update(SQL_UPDATE_STATIC_PAGE,
                            staticPage.getStaticPageName(),
                            staticPage.getStaticPageUrl(),
                            staticPage.getStaticPageJsp(),
                            staticPage.getStaticPageJavaScript(),
                            staticPage.getStaticPagePosition());

        return staticPage;
    }

    @Override
    public List<StaticPage> listAllStaticPages() {

        return jdbcTemplate.query(SQL_SELECT_ALL_STATIC_PAGES, new StaticPageMapper());

    }

    @Override
    public StaticPage getStaticPage(int pageId) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_STATIC_PAGE, new StaticPageMapper(), pageId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    private static final class StaticPageMapper implements RowMapper<StaticPage> {

        @Override
        public StaticPage mapRow(ResultSet rs, int i) throws SQLException {

            throw new UnsupportedOperationException("Need the new database schema!!"); //To change body of generated methods, choose Tools | Templates.

        }
    }

}
