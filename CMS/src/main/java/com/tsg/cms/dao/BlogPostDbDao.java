/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.cms.dao;

import com.tsg.cms.dto.BlogPost;
import com.tsg.cms.dto.BlogPostContainer;
import java.util.List;
import java.util.Map;

/**
 *
 * @author apprentice
 */
public interface BlogPostDbDao {
    public BlogPostContainer addBlogPost(BlogPost blogPost);
    public void removeBlogPost(Integer postId);
    public BlogPostContainer updateBlogPost(BlogPost blogPost);
    public List<BlogPostContainer> getAllBlogPosts();
    public BlogPostContainer getBlogPostById(Integer postId);
    public List<BlogPostContainer> searchBlogPosts(Map<SearchTerm, String> criteria);
    public BlogPostContainer getBlogPostByTitleNumber(String titleNumber);
    public List<BlogPost> getBlogPostsByTitle(String title);
    public List<BlogPostContainer> getBlogPostsByTag(String tag);
    
}
