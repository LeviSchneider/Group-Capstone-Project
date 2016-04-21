/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
// */
package com.tsg.cms.dao;

///**

import java.util.List;
import java.util.Map;

// *
// * @author apprentice
// */
public interface TagDbDao {
    public String addTag(String tag, int postId);
    public void removeTag(String tag);
    public void updateTag(String tag);
    //public List<String> getRelatedTags(String categories);
    public List<String> getPostTags(int postId);
    public Map<String, Integer> getNumberOfTags(int num);
    public List<String> getAllTags();
}
