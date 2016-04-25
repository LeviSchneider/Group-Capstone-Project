/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.cms.dao;

import com.tsg.cms.dto.StaticPage;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author apprentice
 */
public interface StaticPageDao {

    public StaticPage addStaticPages(StaticPage page);

    public StaticPage getStaticPage(int pageId);

    public List<StaticPage> listAllStaticPages();

    public void removeStaticPage(int pageId);

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate);

    public StaticPage updateStaticPage(StaticPage pageId);
}
