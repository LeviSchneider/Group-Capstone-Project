/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.cms.dao;

import com.tsg.cms.dto.Category;
import java.util.List;
import java.util.Map;
import org.springframework.dao.DuplicateKeyException;

/**
 *
 * @author apprentice
 */
public interface CategoryDAO {
    
    public Category addCategory(Category category) throws DuplicateKeyException;
    public Category addCategory(Category category, int blogPostIdFK) throws DuplicateKeyException;
    public void removeCategory(int categoryId);
    public void updateCategory(Category category);
    public List<Category> getAllCategories();
    public Category getCategoryById(int categoryId);
    public List<Category> searchCategory(Map<CategorySearchTerm, String> criteria);
    
}
