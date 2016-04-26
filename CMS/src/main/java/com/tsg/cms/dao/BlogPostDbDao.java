/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.cms.dao;

import com.tsg.cms.dto.BlogPost;
import java.util.List;
import java.util.Map;
import org.springframework.dao.EmptyResultDataAccessException;

/**
 *
 * @author apprentice
 */
public interface BlogPostDbDao {

    public BlogPost addBlogPost(BlogPost blogPost);
    public void removeBlogPost(int postId);
    public void updateBlogPost(BlogPost blogPost);
    public List<BlogPost> getAllBlogPost();
    public BlogPost getBlogPostById(int postId);
    public List<BlogPost> searchBlogPost(Map<SearchTerm, String> criteria);
    public BlogPost getBlogPostByTitleNumber(String titleNumber);
    public List<BlogPost> getBlogPostByTitle(String title);
    public List<BlogPost> getBlogPostByTag(String tag);
    
}
