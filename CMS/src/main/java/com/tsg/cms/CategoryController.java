/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.cms;

import com.tsg.cms.dao.CategoryDAO;
import com.tsg.cms.dto.Category;
import java.util.List;
import javax.inject.Inject;
import org.springframework.dao.DuplicateKeyException;
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
public class CategoryController {

    private final CategoryDAO dao;

    @Inject
    public CategoryController(CategoryDAO dao) {
        this.dao = dao;
    }

    @RequestMapping(value = "/category/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Category getCategory(@PathVariable("id") int id) {
        return dao.getCategoryById(id);
    }

//    @RequestMapping(value = "/category", method = RequestMethod.POST)
//    @ResponseStatus(HttpStatus.CREATED)
//    @ResponseBody
//    public Object[category, error] createCategory(@RequestBody Category category) {
//
//        try {
//            
//            return dao.addCategory(category);
//            
//        } catch (DuplicateKeyException e) {
//
//            return category;
//            
//        }
//
//    }

    @RequestMapping(value = "/category/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategory(@PathVariable("id") int id) {
        dao.removeCategory(id);
    }

    @RequestMapping(value = "/category/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCategory(@PathVariable("id") int id, @RequestBody Category category) {
        category.setCategoryId(id);
        dao.updateCategory(category);
    }

    @RequestMapping(value = "/categories", method = RequestMethod.GET)
    @ResponseBody
    public List<Category> getAllCategories() {
        return dao.getAllCategories();
    }
}
