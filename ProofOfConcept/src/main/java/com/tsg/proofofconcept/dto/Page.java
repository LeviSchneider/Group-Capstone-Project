/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.proofofconcept.dto;

/**
 *
 * @author apprentice
 */
public class Page {
    private int pageId;
    private String title;
    private String body;
    private PageStatus PageStatus;

    
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public PageStatus getPageStatus() {
        return PageStatus;
    }

    public void setPageStatus(PageStatus PageStatus) {
        this.PageStatus = PageStatus;
    }

    public int getPageId() {
        return pageId;
    }

    public void setPageId(int pageId) {
        this.pageId = pageId;
    }
    
    
    
}
