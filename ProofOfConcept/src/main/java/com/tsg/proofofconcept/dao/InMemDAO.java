/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.proofofconcept.dao;

import com.tsg.proofofconcept.dto.Page;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * @author apprentice
 */
public class InMemDAO {

    private Map<String, Page> posts = new HashMap<>();

    public Page addPage(Page page) {
        posts.put(page.getTitle(), page);
        return page;
    }

    public void deletePage(String title) {
        posts.remove(title);
    }

    public void updatePage(Page page, String title) {
        if (posts.containsKey(title)) {
            posts.put(title, page);
        }
    }

    public List<Page> getAllPages() {
        return posts.values().stream().collect(Collectors.toList());
    }

    public Page getPageByTitle(String title) {
        return posts.get(title);
    }

}
