/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.cms;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author apprentice
 */
@Controller
public class HomeController {

    //we are including HttpSession here so we can set variables across the entire user 
    //session, without having to pass them between models over and over
    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    public String displayHomePage(Model model, HttpSession session) {

        //this attribute is used to set the content of the middle pane
        //it refers to the JSP file, without the JSP extension
        session.setAttribute("page", "tempLandingPage");
        //this attribute is used to include any JavaScript pages associated with the above page
        //this is so we don't have to include JS pages that we don't need
        session.setAttribute("js_page", "tempLandingPage.js");
        return "home";

    }

    @RequestMapping(value = "/blog", method = RequestMethod.GET)
    public String showBlog(Map<String, Object> model, HttpSession session) {
        //this attribute is used to set the content of the middle pane
        //it refers to the JSP file, without the JSP extension
        session.setAttribute("page", "blog");
        //this attribute is used to include any JavaScript pages associated with the above page
        //this is so we don't have to include JS pages that we don't need
        session.setAttribute("js_page", "blog.js");
        return "home";
    }

    @RequestMapping(value = "/tinymce", method = RequestMethod.GET)
    public String showEditor(Map<String, Object> model, HttpSession session) {
        //this attribute is used to set the content of the middle pane
        //it refers to the JSP file, without the JSP extension
        session.setAttribute("page", "tinymce");
        //this attribute is used to include any JavaScript pages associated with the above page
        //this is so we don't have to include JS pages that we don't need
        session.setAttribute("js_page", "tinymce.js");
        return "home";
    }

    @RequestMapping(value = "/render", method = RequestMethod.POST)
    public String renderHtmlOutput(HttpServletRequest req, Model model, HttpSession session) {
        String htmlOutput = req.getParameter("htmlOutput");
        model.addAttribute("htmlOutput", htmlOutput);
        //this attribute is used to set the content of the middle pane
        //it refers to the JSP file, without the JSP extension
        session.setAttribute("page", "render");
        //this attribute is used to include any JavaScript pages associated with the above page
        //this is so we don't have to include JS pages that we don't need
        //session.setAttribute("js_page", "tempLandingPage.js");
        return "home";
    }

}
