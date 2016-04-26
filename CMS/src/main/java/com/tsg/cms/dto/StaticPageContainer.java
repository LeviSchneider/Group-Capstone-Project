/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.cms.dto;

/**
 *
 * @author apprentice
 */
public class StaticPageContainer {

    private StaticPage staticPage;
    private String message;
    private TagContainer tagContainer;
    private CategoryContainer categoryContainer;

    public StaticPage getStaticPage() {
        return staticPage;
    }

    public void setStaticPage(StaticPage staticPage) {
        this.staticPage = staticPage;
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

    public CategoryContainer getCategoryContainer() {
        return categoryContainer;
    }

    public void setCategoryContainer(CategoryContainer categoryContainer) {
        this.categoryContainer = categoryContainer;
    }

}
