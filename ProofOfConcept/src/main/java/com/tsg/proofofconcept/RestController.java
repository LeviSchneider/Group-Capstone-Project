/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.proofofconcept;

import com.tsg.proofofconcept.dao.InMemDAO;
import com.tsg.proofofconcept.dto.Page;
import java.util.List;
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
public class RestController {

    InMemDAO dao = new InMemDAO();
    
    

    @RequestMapping(value = "/page/title", method = RequestMethod.GET)
    @ResponseBody
    public Page getPageByTitle(@PathVariable("title") String title) {
        return dao.getPageByTitle(title);
    }
    
    @RequestMapping(value = "/pages", method = RequestMethod.GET)
    @ResponseBody
    public List<Page> getPublicPages() {
        return dao.getAllPages();
    }
    
    @RequestMapping(value = "/page", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Page createPage(@RequestBody Page page) {
        dao.addPage(page);
        return page;
    }
    
}
