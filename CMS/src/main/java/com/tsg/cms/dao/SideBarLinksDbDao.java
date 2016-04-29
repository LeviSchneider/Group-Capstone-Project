/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.cms.dao;

import com.tsg.cms.dto.SideBarLink;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author apprentice
 */
public interface SideBarLinksDbDao {

    public SideBarLink addSideBarLink(SideBarLink link);

    public SideBarLink getSideBarLink(int linkId);

    public List<SideBarLink> getAllSideBarLinks();

    public void removeSideBarLink(int linkId);

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate);

    public SideBarLink updateSideBarLink(SideBarLink link);
}
