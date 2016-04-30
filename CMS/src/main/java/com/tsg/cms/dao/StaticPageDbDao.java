/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.cms.dao;

import com.tsg.cms.dto.SideBarLink;
import com.tsg.cms.dto.StaticPage;
import com.tsg.cms.dto.StaticPageContainer;
import java.util.List;
import java.util.Map;

/**
 *
 * @author apprentice
 */
public interface StaticPageDbDao {

    public StaticPage addStaticPage(StaticPage staticPage);

    public void removeStaticPage(Integer pageId);

    public void updateStaticPage(StaticPage staticPage);

    public List<StaticPage> getAllStaticPages();

    public StaticPage getStaticPageById(Integer pageId);

    public List<StaticPage> searchStaticPage(Map<SearchTerm, String> criteria);

    public StaticPage getStaticPageByTitleNumber(String titleNumber);

    public List<StaticPage> getStaticPageByTitle(String title);

    public List<SideBarLink> getNavBarPages();
    
    public void updatePageNavBarPosition(int pageId, int position);

    public String getHomePageLink();
}
