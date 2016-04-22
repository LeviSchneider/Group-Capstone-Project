/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.cms.dto;

import java.util.List;
import java.util.Map;

/**
 *
 * @author apprentice
 */
public class TagContainer {
    
    private String tagName;
    private String message;
    private Map<String, Integer> rankedTags;
    private List<String> tagList;

    public List<String> getTagList() {
        return tagList;
    }

    public void setTagList(List<String> tagList) {
        this.tagList = tagList;
    }

    public Map<String, Integer> getRankedTags() {
        return rankedTags;
    }

    public void setRankedTags(Map<String, Integer> rankedTags) {
        this.rankedTags = rankedTags;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tag) {
        this.tagName = tag;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
}
