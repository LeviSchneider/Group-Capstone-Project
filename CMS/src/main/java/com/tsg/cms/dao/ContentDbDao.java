/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.cms.dao;

import com.tsg.cms.dto.Content;
import java.util.List;
import java.util.Map;

/**
 *
 * @author apprentice
 */
public interface ContentDbDao {
    public Content addContent(Content content);
    public void removeContent(int postId);
    public void updateContent(Content content);
    public List<Content> getAllContent();
    public Content getContentById(int postId);
    public List<Content> searchContent(Map<SearchTerm, String> criteria);
}
