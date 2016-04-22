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

/**
 *
 * @author apprentice
 */

//It may be more desirable to bifurcate blogPost into
//"static" pages and blog posts.

@Controller
public class RESTController {
    
    private final BlogPostDbDao dao;
    
    @Inject
    public RESTController (BlogPostDbDao dao) {
        this.dao = dao;
    }
    
    //We'll need methods to
    //-add blogPost
    //-delete blogPost
    //-update a particular piece of blogPost
    //-get published blogPost depending on whether it's a blog post or static page
    //-get ALL blogPost for editing purposes
    //
    
    @RequestMapping(value = "/blogPost/{id}", method = RequestMethod.GET)
    @ResponseBody
    public BlogPost getBlogPost(@PathVariable("id") int id) {
        return dao.getBlogPostById(id);
    }
    
    @RequestMapping(value = "/blogPost", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public BlogPost createBlogPost(@RequestBody BlogPost blogPost) {
        blogPost.setUserIdFK(999);
        return dao.addBlogPost(blogPost);
    }
    
    @RequestMapping(value = "/blogPost/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBlogPost(@PathVariable("id") int id) {
        dao.removeBlogPost(id);
    }
    
    @RequestMapping(value = "/blogPost/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateBlogPost(@PathVariable("id") int id, @RequestBody BlogPost blogPost) {
        blogPost.setPostId(id);
        dao.updateBlogPost(blogPost);
    }
//        
//    @RequestMapping(value = "/pubblogPost", method = RequestMethod.GET)
//    @ResponseBody
//    public List<BlogPost> getPublishedBlogPost () {
//        return dao.getPublishedBlogPost();
//    }
    
    @RequestMapping(value = "/blogPosts", method = RequestMethod.GET)
    @ResponseBody
    public List<BlogPost> getAllBlogPost () {
        return dao.getAllBlogPost();
        //Could be problematic.
        //Certainly won't scale well.
        //Might want to 
    }
    
//    @RequestMapping(value = "/tags", method=RequestMethod.GET)
//    public List<String> getAllTags()
//    {
//        return dao.getAllTags();
//    }
        
}
