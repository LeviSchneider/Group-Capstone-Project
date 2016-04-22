/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.cms.dto;

import java.util.List;
import java.util.Objects;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author apprentice
 */
public class Category {
    private int categoryId;
    @NotEmpty(message="You must supply a value for Category Name")
    @Length(max=50, message="Category Name must be no more then 50 characters in length.")
    private String categoryName;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + this.categoryId;
        hash = 47 * hash + Objects.hashCode(this.categoryName);
        hash = 47 * hash + Objects.hashCode(this.categoryPosts);
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
        final Category other = (Category) obj;
        if (this.categoryId != other.categoryId) {
            return false;
        }
        if (!Objects.equals(this.categoryName, other.categoryName)) {
            return false;
        }
        if (!Objects.equals(this.categoryPosts, other.categoryPosts)) {
            return false;
        }
        return true;
    }
    private List<BlogPost> categoryPosts;

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<BlogPost> getCategoryPosts() {
        return categoryPosts;
    }

    public void setCategoryPosts(List<BlogPost> categoryPosts) {
        this.categoryPosts = categoryPosts;
    }
    
    
}
