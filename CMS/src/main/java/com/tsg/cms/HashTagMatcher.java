/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.cms;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author apprentice
 */
public class HashTagMatcher {
    
    public List<String> findHashTags(String post) {
        List<String> matches = new ArrayList<>();
        
        Matcher hashTagMatcher = Pattern.compile("#\\w+").matcher(post);
        while (hashTagMatcher.find()) {
            matches.add(hashTagMatcher.group());
        }
        
        return matches;
    }
    
}
