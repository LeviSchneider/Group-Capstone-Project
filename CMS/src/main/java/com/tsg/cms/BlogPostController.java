/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.cms;

import com.tsg.cms.dto.BlogPost;
import java.util.List;
import javax.inject.Inject;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.tsg.cms.dao.BlogPostDbDao;

import com.tsg.cms.dto.BlogPostContainer;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author apprentice
 */
//It may be more desirable to bifurcate blogPost into
//"static" pages and blog posts.
@Controller
public class BlogPostController {

    private final BlogPostDbDao blogPostDao;

    @Inject
    public BlogPostController(BlogPostDbDao blogPostDao) {

        this.blogPostDao = blogPostDao;

    }

    @RequestMapping(value = "/blogPost/{id}", method = RequestMethod.GET)
    @ResponseBody
    public BlogPostContainer getBlogPost(@PathVariable("id") int id) {

        return blogPostDao.getBlogPostById(id);

    }

    @RequestMapping(value = "/blogPost", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public BlogPostContainer createBlogPost(@RequestBody BlogPost blogPost) {

        blogPost.setUserIdFK(999);
        return blogPostDao.addBlogPost(blogPost);

    }

    @RequestMapping(value = "/blogPost/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBlogPost(@PathVariable("id") int id) {
        blogPostDao.removeBlogPost(id);
    }

    @RequestMapping(value = "/blogPost/{id}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public BlogPostContainer updateBlogPost(@PathVariable("id") int id, @RequestBody BlogPost blogPost) {

        blogPost.setPostId(id);
        return blogPostDao.updateBlogPost(blogPost);

    }

    @RequestMapping(value = "/blogPosts", method = RequestMethod.GET)
    @ResponseBody
    public List<BlogPostContainer> getAllBlogPost() {

        return blogPostDao.getAllBlogPosts();

    }

    @RequestMapping(value = "/link/{titleNumber}", method = RequestMethod.GET)
    public String getLinkedPost(@PathVariable("titleNumber") String titleNumber, Map<String, Object> model, HttpSession session) {

        int id = blogPostDao.getBlogPostByTitleNumber(titleNumber).getBlogPost().getPostId();
        model.put("id", id);
        session.setAttribute("page", "singlePost");
        session.setAttribute("js_page", "singlePost.js");

        return "home";
    }

    @RequestMapping(value = "/taggedPosts/{tag}", method = RequestMethod.GET)
    @ResponseBody
    public List<BlogPostContainer> getPostsByTag(@PathVariable("tag") String tag) {

        return blogPostDao.getBlogPostsByTag(tag);

    }

}
