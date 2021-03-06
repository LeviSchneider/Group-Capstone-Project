/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.cms;

import com.tsg.cms.dao.StaticPageDbDao;
import java.util.Map;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author apprentice
 */
@Controller
public class HomeController {

    private final StaticPageDbDao staticPageDao;

    @Inject
    public HomeController(StaticPageDbDao staticPageDao) {

        this.staticPageDao = staticPageDao;

    }

    //we are including HttpSession here so we can set variables across the entire user 
    //session, without having to pass them between models over and over
    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    public String displayHomePage(Model model, HttpSession session) {

        String homePage = staticPageDao.getHomePageLink();
        if (homePage == null) {
            session.setAttribute("page", "blog");
            session.setAttribute("js_page", "blog.js");
            return "home";

        } else {

            return "redirect:/pagelink/" + homePage;
        }

    }

    @RequestMapping(value = "/blog", method = RequestMethod.GET)
    public String showBlog(Map<String, Object> model, HttpSession session) {

        session.setAttribute("page", "blog");
        session.setAttribute("js_page", "blog.js");

        return "home";
    }

    @RequestMapping(value = "/articles", method = RequestMethod.GET)
    public String showArticles(Map<String, Object> model, HttpSession session) {

        session.setAttribute("page", "articles");
        session.setAttribute("js_page", "articles.js");

        return "home";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String showAdminPage(HttpServletRequest req, Model model, HttpSession session) {

        String htmlOutput = req.getParameter("htmlOutput");
        model.addAttribute("htmlOutput", htmlOutput);
        session.setAttribute("page", "admin");
        session.setAttribute("js_page", "admin.js");

        return "home";
    }

    @RequestMapping(value = "/tinymce", method = RequestMethod.GET)
    public String showEditor(Map<String, Object> model, HttpSession session) {

        session.setAttribute("page", "tinymce");
        session.setAttribute("js_page", "tinymce.js");
        return "home";
    }

    @RequestMapping(value = "/tinymce/{id}", method = RequestMethod.GET)
    public String showPopulatedEditor(@PathVariable("id") int id, Map<String, Object> model, HttpSession session) {

        session.setAttribute("page", "tinymce");
        session.setAttribute("js_page", "tinymce.js");
        model.put("editBlogPostId", id);

        return "home";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String showLoginPage(Map<String, Object> model, HttpSession session) {

        session.setAttribute("page", "login");
        session.setAttribute("js_page", "login.js");

        return "home";
    }
}
