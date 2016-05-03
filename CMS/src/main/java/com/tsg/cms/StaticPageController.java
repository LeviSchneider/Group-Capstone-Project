/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.cms;

import com.tsg.cms.dao.CategoryDbDao;
import com.tsg.cms.dao.StaticPageDbDao;
import com.tsg.cms.dao.Status;
import com.tsg.cms.dto.SideBarLink;
import com.tsg.cms.dto.StaticPage;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author apprentice
 */
@Controller
public class StaticPageController {

    private final StaticPageDbDao staticPageDao;
    private final CategoryDbDao categoryDao;

    @Inject
    public StaticPageController(StaticPageDbDao dao, CategoryDbDao categoryDao) {
        this.staticPageDao = dao;
        this.categoryDao = categoryDao;
    }

    @RequestMapping(value = "/staticPage/{id}", method = RequestMethod.GET)
    @ResponseBody
    public StaticPage getStaticPage(@PathVariable("id") int id) {

        return staticPageDao.getStaticPageById(id);
    }

    @RequestMapping(value = "/staticPage", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public StaticPage createStaticPage(@RequestBody StaticPage staticPage) {

        if (staticPage.getCategoryIdFK() == -1) {
            staticPage.setCategoryIdFK(null);
        }
        if (staticPage.getStatus() == null) {
            staticPage.setStatus(Status.DRAFT);
        }

        staticPage.setUserIdFK(999);
        return staticPageDao.addStaticPage(staticPage);

    }

    @RequestMapping(value = "/staticPage/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStaticPage(@PathVariable("id") int id) {
        staticPageDao.removeStaticPage(id);
    }

    @RequestMapping(value = "/staticPage/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public StaticPage updateStaticPage(@PathVariable("id") int id, @RequestBody StaticPage staticPage) {

        if (staticPage.getCategoryIdFK() == -1) {
            staticPage.setCategoryIdFK(null);
        }
        if (staticPage.getStatus() == null) {
            staticPage.setStatus(Status.DRAFT);
        }
        staticPage.setPageId(id);
        staticPageDao.updateStaticPage(staticPage);
        return staticPage;
    }
//        
//    @RequestMapping(value = "/pubstaticPage", method = RequestMethod.GET)
//    @ResponseBody
//    public List<StaticPage> getPublishedStaticPage () {
//        return dao.getPublishedStaticPage();
//    }

    @RequestMapping(value = "/staticPages", method = RequestMethod.GET)
    @ResponseBody
    public List<StaticPage> getAllStaticPages() {

        List<StaticPage> staticPages = staticPageDao.getAllStaticPages();

        return staticPages;

    }

    @RequestMapping(value = "/staticPages/{categoryId}", method = RequestMethod.GET)
    public String getAllStaticPagesByCategory(@PathVariable("categoryId") int categoryId, Map<String, Object> model, HttpSession session) {

        List<StaticPage> staticPages = staticPageDao.getAllStaticPagesByCategory(categoryId);

        model.put("staticPages", staticPages);
        model.put("category", categoryDao.getCategoryById(categoryId));
        session.setAttribute("page", "articlesByCategory");
        session.setAttribute("js_page", "articlesByCategory.js");

        return "home";

    }

    @RequestMapping(value = "/staticPageOrder/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public List<StaticPage> updatePageOrder(@PathVariable("id") int id, @RequestBody StaticPage staticPage) {
        staticPage.setSideBarPosition(id);
        staticPageDao.updateStaticPage(staticPage);

        return staticPageDao.getAllStaticPages();
    }

    @RequestMapping(value = "/pageTinyMCE", method = RequestMethod.GET)
    public String showEditor(Map<String, Object> model, HttpSession session) {

        session.setAttribute("page", "pageTinyMCE");

        session.setAttribute("js_page", "pageTinyMCE.js");
        return "home";
    }

    @RequestMapping(value = "/pageTinyMCE/{id}", method = RequestMethod.GET)
    public String showPopulatedEditor(@PathVariable("id") int id, Map<String, Object> model, HttpSession session) {

        //model.put("staticPage", dao.getStaticPageById(id));
        session.setAttribute("page", "pageTinyMCE");

        session.setAttribute("js_page", "pageTinyMCE.js");

        model.put("editStaticPageId", id);
        return "home";
    }

    @RequestMapping(value = "/pagelink/{titleNumber}", method = RequestMethod.GET)
    public String getLinkedPage(@PathVariable("titleNumber") String titleNumber, Map<String, Object> model, HttpSession session) {

        session.setAttribute("page", "singleStaticPage");

        session.setAttribute("js_page", "singleStaticPage.js");
        int id = staticPageDao.getStaticPageByTitleNumber(titleNumber).getPageId();
        model.put("id", id);
        return "home";
    }

    @RequestMapping(value = "/staticPage/{sideBarPosition}/{titleNumber}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.CREATED)
    public void updatePageSideBarPosition(@PathVariable("sideBarPosition") int sideBarPosition, @PathVariable("titleNumber") String titleNumber) {

        //can use position 0 to remove staticPage from sideBar
        staticPageDao.updatePageNavBarPosition(sideBarPosition, titleNumber);

    }

    @RequestMapping(value = {"/sideBarLinks"}, method = RequestMethod.GET)
    @ResponseBody
    public List<SideBarLink> getSideBarLinks() {
        List<SideBarLink> newList = staticPageDao.getNavBarPages();
        return staticPageDao.getNavBarPages();
    }

    @RequestMapping(value = "/staticPagesAdminUnpublished", method = RequestMethod.GET)
    @ResponseBody
    public List<StaticPage> getAllStaticPagesAdminUnpublished() {

        return staticPageDao.getAllStaticPagesAdminUnpublished();

    }

    @RequestMapping(value = "/adminQuickChangeStaticPageStatus/{id}/{status}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public void adminQuickChangeStaticPageStatus(@PathVariable("id") int id, @PathVariable("status") String status) {

        staticPageDao.adminQuickChangeStaticPageStatus(id, status);

    }
}
