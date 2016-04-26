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
public interface CategoryDbDao {
    
    public Category addCategory(Category category) throws DuplicateKeyException;
    public Category getPostCategory(int postId);
    public void removeCategory(int categoryId);
    public Category updateCategory(Category category);
    public List<Category> getAllCategories();
    public Category getCategoryById(int categoryId);
    public List<Category> searchCategory(Map<CategorySearchTerm, String> criteria);
    public void updateBlogPostCategory(Category category, int postId);
    public void removeBlogPostCategory(int postId);
    public Category getBlogPostCategory(int postId);
    
}
