/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.cms.dao;

import com.tsg.cms.dto.SideBarLink;
import com.tsg.cms.dto.StaticPage;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
public class StaticPageDbDaoImpl implements StaticPageDbDao {

    private static final String SQL_INSERT_STATICPAGE
            = "insert into staticPages (timeCreated, timeEdited, startDate, endDate, title, pageBody, userIdFK, categoryIdFK, titleNumber, status, sideBarPosition) value(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_DELETE_STATICPAGE
            = "delete from staticPages where pageId = ?";
    private static final String SQL_UPDATE_STATICPAGE
            = "update staticPages set timeCreated = ?, timeEdited = ?, startDate = ?, endDate = ?, title = ?, pageBody = ?, userIdFK = ?, categoryIdFK = ?, titleNumber = ?, status = ?, sideBarPosition = ? where pageId = ?";
    private static final String SQL_SELECT_ALL_STATICPAGES
            = "select * from staticPages ORDER by pageId DESC";
    private static final String SQL_SELECT_STATICPAGE_BY_ID
            = "select * from staticPages where pageId = ?";
    private static final String SQL_SELECT_STATICPAGE_BY_TITLENUMBER
            = "select * from staticPages where titleNumber = ?";
    private static final String SQL_SELECT_STATICPAGE_BY_TITLE
            = "select * from staticPages where title = ?";
    private static final String SQL_SELECT_TITLENUMBERS
            = "SELECT titleNumber FROM staticPages where titleNumber rlike ?";
    private static final String SQL_SELECT_STATICPAGES_BY_SIDEBAR_POSITION
            = "select title, titleNumber, sideBarPosition from staticPages where sideBarPosition > 0 order by sideBarPosition asc";
    private static final String SQL_UPDATE_STATICPAGE_SIDEBAR_POSITION
            = "update staticPages set sideBarPosition = ? where titleNumber = ?";
    private static final String SQL_SELECT_HOMEPAGE_LINK
            = "SELECT titleNumber FROM `staticPages` WHERE `title` = 'Home' OR `title` = 'home'";
    private static final String SQL_QUICK_PUBLISH_STATICPAGE
            = "update staticPages set status = ? where pageId = ?";
    private static final String SQL_SELECT_STATICPAGE_BY_ID_ADMIN_UNPUBLISHED
            = "select * from staticPages WHERE `status` != 'PUBLISHED' ORDER BY pageId DESC";
    
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public StaticPage addStaticPage(StaticPage staticPage) {

        setTitleNumber(staticPage);
        Date date = new Date();

        staticPage.setTimeCreated(date);
        staticPage.setTimeEdited(date);
        staticPage.setSideBarPosition(0);
        jdbcTemplate.update(SQL_INSERT_STATICPAGE,
                            staticPage.getTimeCreated(),
                            staticPage.getTimeEdited(),
                            staticPage.getStartDate(),
                            staticPage.getEndDate(),
                            staticPage.getTitle(),
                            staticPage.getPageBody(),
                            staticPage.getUserIdFK(),
                            staticPage.getCategoryIdFK(),
                            staticPage.getTitleNumber(),
                            staticPage.getStatus().toString(),
                            staticPage.getSideBarPosition()
        );

        staticPage.setPageId(jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class));
        return staticPage;
    }

    @Override
    public void removeStaticPage(Integer pageId) {
        jdbcTemplate.update(SQL_DELETE_STATICPAGE, pageId);
    }

    @Override
    public void updateStaticPage(StaticPage updatedPage) {

        //if title has changed set new titleNumber
        StaticPage oldPage = jdbcTemplate.queryForObject(SQL_SELECT_STATICPAGE_BY_ID, new StaticPageMapper(), updatedPage.getPageId());
        if (!oldPage.getTitle().equals(updatedPage.getTitle())) {
            setTitleNumber(updatedPage);
        } else {
            updatedPage.setTitleNumber(oldPage.getTitleNumber());
        }
        //updatedPage.setCategoryIdFK(oldPage.getCategoryIdFK());
        updatedPage.setSideBarPosition(oldPage.getSideBarPosition());
        updatedPage.setUserIdFK(oldPage.getUserIdFK());

        jdbcTemplate.update(SQL_UPDATE_STATICPAGE,
                            updatedPage.getTimeCreated(),
                            updatedPage.getTimeEdited(),
                            updatedPage.getStartDate(),
                            updatedPage.getEndDate(),
                            updatedPage.getTitle(),
                            updatedPage.getPageBody(),
                            updatedPage.getUserIdFK(),
                            updatedPage.getCategoryIdFK(),
                            updatedPage.getTitleNumber(),
                            updatedPage.getStatus().toString(),
                            updatedPage.getSideBarPosition(),
                            updatedPage.getPageId()
        );
    }

    @Override
    public List<StaticPage> getAllStaticPages() {
        return jdbcTemplate.query(SQL_SELECT_ALL_STATICPAGES, new StaticPageMapper());
    }

    @Override
    public StaticPage getStaticPageById(Integer pageId) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_STATICPAGE_BY_ID, new StaticPageMapper(), pageId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<StaticPage> searchStaticPage(Map<SearchTerm, String> criteria) {
        if (criteria.size() == 0) {
            return getAllStaticPages();
        } else {
            StringBuilder sQuery = new StringBuilder("select * from staticPages where ");
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
            return jdbcTemplate.query(sQuery.toString(), new StaticPageMapper(), paramVals);
        }
    }

    @Override
    public StaticPage getStaticPageByTitleNumber(String titleNumber) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_STATICPAGE_BY_TITLENUMBER, new StaticPageMapper(), titleNumber);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<StaticPage> getStaticPageByTitle(String title) {
        try {
            return jdbcTemplate.query(SQL_SELECT_STATICPAGE_BY_TITLE, new StaticPageMapper(), title);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public String getHomePageLink() {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_HOMEPAGE_LINK, String.class);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<StaticPage> getAllStaticPagesAdminUnpublished() {

        return jdbcTemplate.query(SQL_SELECT_STATICPAGE_BY_ID_ADMIN_UNPUBLISHED, new StaticPageMapper());

    }

    @Override
    public void adminQuickChangeStaticPageStatus(int id, String status) {

        jdbcTemplate.update(SQL_QUICK_PUBLISH_STATICPAGE, status, id);

    }

    private static final class StaticPageMapper implements RowMapper<StaticPage> {

        @Override
        public StaticPage mapRow(ResultSet rs, int i) throws SQLException {
            //insert simpledateformat parser to maintain timestamp information
            //currently stripped by mapper after it goes in the db
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            StaticPage staticPage = new StaticPage();
            staticPage.setPageId(rs.getInt("pageId"));
            staticPage.setTimeCreated(rs.getTimestamp("timeCreated"));
            staticPage.setTimeEdited(rs.getTimestamp("timeEdited"));
            staticPage.setStartDate(rs.getTimestamp("startDate"));
            staticPage.setEndDate(rs.getTimestamp("endDate"));
            staticPage.setTitle(rs.getString("title"));
            staticPage.setPageBody(rs.getString("pageBody"));
            staticPage.setUserIdFK(rs.getInt("userIdFK"));
            staticPage.setTitleNumber(rs.getString("titleNumber"));
            int value = rs.getInt("categoryIdFK");
            staticPage.setCategoryIdFK(rs.wasNull() ? null : value);
            staticPage.setStatus(Status.valueOf(rs.getString("status")));
            staticPage.setSideBarPosition(rs.getInt("sideBarPosition"));
            return staticPage;
        }
    }

    private static final class SideBarLinkMapper implements RowMapper<SideBarLink> {

        @Override
        public SideBarLink mapRow(ResultSet rs, int i) throws SQLException {
            //insert simpledateformat parser to maintain timestamp information
            //currently stripped by mapper after it goes in the db
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            SideBarLink link = new SideBarLink();
            link.setSideBarLinkName(rs.getString("title"));
            link.setSideBarLinkPosition(rs.getInt("sideBarPosition"));
            link.setSideBarLinkUrl(rs.getString("titleNumber"));

            return link;
        }
    }

    private List<String> getTitleNumbersLikeTitle(String title) {
        title = title + "\\d*";
        try {
            return jdbcTemplate.queryForList(SQL_SELECT_TITLENUMBERS, String.class, new Object[]{title});
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    private void setTitleNumber(StaticPage staticPage) {
        String title = staticPage.getTitle();
        title = title.replaceAll("([^a-zA-Z0-9 _]|^\\s)", "");
        title = title.replaceAll("([^a-zA-Z0-9]|^\\s)", "_");
        List<String> titleNumbers = getTitleNumbersLikeTitle(title);

        if (titleNumbers.isEmpty()) {
            staticPage.setTitleNumber(title);
        } else {
            for (Integer i = 0; i <= titleNumbers.size() + 1; i++) {
                if (!titleNumbers.contains(title + i.toString())) {
                    staticPage.setTitleNumber(title + i.toString());
                }
            }
        }
    }

    @Override
    public List<SideBarLink> getNavBarPages() {

        return jdbcTemplate.query(SQL_SELECT_STATICPAGES_BY_SIDEBAR_POSITION, new SideBarLinkMapper());

    }

    @Override
    public void updatePageNavBarPosition(int position, String titleNumber) {

        jdbcTemplate.update(SQL_UPDATE_STATICPAGE_SIDEBAR_POSITION, position, titleNumber);
    }

}
