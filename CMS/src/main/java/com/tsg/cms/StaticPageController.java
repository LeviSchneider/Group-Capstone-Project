/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.cms;

import com.tsg.cms.dao.CategoryDbDao;
import com.tsg.cms.dao.StaticPageDbDao;
import com.tsg.cms.dao.TagDbDao;
import com.tsg.cms.dto.CategoryContainer;
import com.tsg.cms.dto.StaticPage;
import com.tsg.cms.dto.StaticPageContainer;
import com.tsg.cms.dto.TagContainer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
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
        
        staticPage.setUserIdFK(999);
        return staticPageDao.addStaticPage(staticPage);

    }

    @RequestMapping(value = "/staticPage/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStaticPage(@PathVariable("id") int id) {
        staticPageDao.removeStaticPage(id);
    }

    @RequestMapping(value = "/staticPage/{id}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public StaticPage updateStaticPage(@PathVariable("id") int id, @RequestBody StaticPage staticPage) {
     
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
    public List<StaticPageContainer> getAllStaticPage() {

        List<StaticPageContainer> staticPageContainerList = new ArrayList<>();
        List<StaticPage> staticPages = staticPageDao.getAllStaticPages();

        for (StaticPage staticPage : staticPages) {

            StaticPageContainer staticPageContainer = new StaticPageContainer();

          
            CategoryContainer categoryContainer = new CategoryContainer();
            //categoryContainer.setCategoryList(categoryDao.getPageCategories(staticPage.getPageId()));
            staticPageContainer.setCategoryContainer(categoryContainer);

            staticPageContainer.setStaticPage(staticPage);

            staticPageContainerList.add(staticPageContainer);
        }
        return staticPageContainerList;

    }

    @RequestMapping(value = "/pageTinyMCE", method = RequestMethod.GET)
    public String showEditor(Map<String, Object> model, HttpSession session) {

        session.setAttribute("page", "tinymce");

        session.setAttribute("js_page", "tinymce.js");
        return "home";
    }

    @RequestMapping(value = "/pageTinyMCE/{id}", method = RequestMethod.GET)
    public String showPopulatedEditor(@PathVariable("id") int id, Map<String, Object> model, HttpSession session) {

        //model.put("staticPage", dao.getStaticPageById(id));
        session.setAttribute("page", "tinymce");

        session.setAttribute("js_page", "tinymce.js");

        model.put("editStaticPageId", id);
        return "home";
    }

    @RequestMapping(value = "/pagelink/{titleNumber}", method = RequestMethod.GET)
    public String getLinkedPage(@PathVariable("titleNumber") String titleNumber, Map<String, Object> model, HttpSession session) {

        session.setAttribute("page", "singlePage");

        session.setAttribute("js_page", "singlePage.js");
        int id = staticPageDao.getStaticPageByTitleNumber(titleNumber).getPageId();
        model.put("id", id);
        return "home";
    }

  

}
