/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.cms.dao;

import com.tsg.cms.dto.SideBarLink;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author apprentice
 */
public class SideBarLinksDbDaoImpl implements SideBarLinksDbDao {

    private static final String SQL_INSERT_SIDEBAR_LINK
            = "insert into sideBarLinks (staticPageName, url, jspPage, javaScriptPage, position) values(?,?,?,?,?)";
    private static final String SQL_DELETE_SIDEBAR_LINK
            = "delete from sideBarLinks where sideBarLinksId = ?";
    private static final String SQL_UPDATE_SIDEBAR_LINK
            = "update sideBarLinks set staticPageName = ?, url = ?, jspPage = ?, javaScriptPage = ?, position = ? where sideBarLinksId = ?";
    private static final String SQL_SELECT_SIDEBAR_LINK
            = "select * from sideBarLinks where sideBarLinksId = ?";
    private static final String SQL_SELECT_ALL_SIDEBAR_LINKS
            = "select * from sideBarLinks";
    private static final String SQL_SELECT_SIDEBAR_LINK_ID
            = "select sideBarLinksId from static_pages where staticPageName = ?";
    private JdbcTemplate jdbcTemplate;

    @Override
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public SideBarLink addSideBarLink(SideBarLink link) {
        jdbcTemplate.update(SQL_INSERT_SIDEBAR_LINK,
                            link.getSideBarLinkName(),
                            link.getSideBarLinkUrl(),
                            link.getSideBarLinkJsp(),
                            link.getSideBarLinkJavaScript(),
                            link.getSideBarLinkPosition());
        link.setLinkId(jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class));

        return link;
    }

    @Override
    public void removeSideBarLink(int linkId) {
        jdbcTemplate.update(SQL_DELETE_SIDEBAR_LINK, linkId);

    }

    @Override
    public SideBarLink updateSideBarLink(SideBarLink link) {
        jdbcTemplate.update(SQL_UPDATE_SIDEBAR_LINK,
                            link.getSideBarLinkName(),
                            link.getSideBarLinkUrl(),
                            link.getSideBarLinkJsp(),
                            link.getSideBarLinkJavaScript(),
                            link.getSideBarLinkPosition());

        return link;
    }

    @Override
    public List<SideBarLink> getAllSideBarLinks() {

        return jdbcTemplate.query(SQL_SELECT_ALL_SIDEBAR_LINKS, new SideBarLinkMapper());

    }

    @Override
    public SideBarLink getSideBarLink(int linkId) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_SIDEBAR_LINK, new SideBarLinkMapper(), linkId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    private static final class SideBarLinkMapper implements RowMapper<SideBarLink> {

        @Override
        public SideBarLink mapRow(ResultSet rs, int i) throws SQLException {

            SideBarLink link = new SideBarLink();
            link.setLinkId(rs.getInt("sideBarLinksId"));
            link.setSideBarLinkName(rs.getString("staticPageName"));
            link.setSideBarLinkUrl(rs.getString("url"));
            link.setSideBarLinkJsp(rs.getString("jspPage"));
            link.setSideBarLinkJavaScript(rs.getString("JavaScriptPage"));
            link.setSideBarLinkPosition(rs.getInt("position"));
          
            return link;
        }
    }

}
