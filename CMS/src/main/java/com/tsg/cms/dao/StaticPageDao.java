/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.cms.dao;

import com.tsg.cms.dto.StaticPage;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface StaticPageDao {
    public StaticPage addStaticPages(StaticPage page);
    public void removeStaticPage(int pageId);
    public void updateStaticPage(int pageId);
    public List<StaticPage> listAllStaticPages();
    public StaticPage getStaticPage(int pageId);
}
