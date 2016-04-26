/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.cms.dto;

import java.util.Objects;

/**
 *
 * @author apprentice
 */
public class BlogPostContainer {
    
    private BlogPost blogPost;
    private String message;
    private TagContainer tagContainer;

    public BlogPost getBlogPost() {
        return blogPost;
    }

    public void setBlogPost(BlogPost blogPost) {
        this.blogPost = blogPost;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public TagContainer getTagContainer() {
        return tagContainer;
    }

    public void setTagContainer(TagContainer tagContainer) {
        this.tagContainer = tagContainer;
    }


    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.blogPost);
        hash = 47 * hash + Objects.hashCode(this.message);
        hash = 47 * hash + Objects.hashCode(this.tagContainer);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BlogPostContainer other = (BlogPostContainer) obj;
        if (!Objects.equals(this.message, other.message)) {
            return false;
        }
        if (!Objects.equals(this.blogPost, other.blogPost)) {
            return false;
        }
        if (!Objects.equals(this.tagContainer, other.tagContainer)) {
            return false;
        }
        return true;
    }
    
    

   
    
}
