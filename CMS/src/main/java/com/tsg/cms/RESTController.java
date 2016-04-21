/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.cms;

import com.tsg.cms.dao.ContentDbDao;
import com.tsg.cms.dto.Content;
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

/**
 *
 * @author apprentice
 */

//It may be more desirable to bifurcate content into
//"static" pages and blog posts.

@Controller
public class RESTController {
    
    private final ContentDbDao dao;
    
    @Inject
    public RESTController (ContentDbDao dao) {
        this.dao = dao;
    }
    
    //We'll need methods to
    //-add content
    //-delete content
    //-update a particular piece of content
    //-get published content depending on whether it's a blog post or static page
    //-get ALL content for editing purposes
    //
    
    @RequestMapping(value = "/content/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Content getContent(@PathVariable("id") int id) {
        return dao.getContentById(id);
    }
    
    @RequestMapping(value = "/content", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Content createContent(@RequestBody Content content) {
        content.setUserIdFK(999);
        return dao.addContent(content);
    }
    
    @RequestMapping(value = "/content/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteContent(@PathVariable("id") int id) {
        dao.removeContent(id);
    }
    
    @RequestMapping(value = "/content/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateContent(@PathVariable("id") int id, @RequestBody Content content) {
        content.setPostId(id);
        dao.updateContent(content);
    }
//        
//    @RequestMapping(value = "/pubcontent", method = RequestMethod.GET)
//    @ResponseBody
//    public List<Content> getPublishedContent () {
//        return dao.getPublishedContent();
//    }
    
    @RequestMapping(value = "/allcontent", method = RequestMethod.GET)
    @ResponseBody
    public List<Content> getAllContent () {
        return dao.getAllContent();
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
