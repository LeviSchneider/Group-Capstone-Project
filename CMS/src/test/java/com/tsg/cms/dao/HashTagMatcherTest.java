/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.cms.dao;

import com.tsg.cms.HashTagMatcher;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author apprentice
 */
public class HashTagMatcherTest {
    HashTagMatcher matcher = new HashTagMatcher();
    
    String garbage = "##@#$!@#$>!@#$$#!%!#!@#$#@$!$@#$!#(#@($!5$#@%";
    String blogPost1 = "this is #vaguely representative of a #sample blog #post";
    String blogPost2 = "This #sentence includes a #period.";
    
    public HashTagMatcherTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void matchesTags() {
        List<String> post1Matches = matcher.findHashTags(blogPost1);
        List<String> post2Matches = matcher.findHashTags(blogPost2);
        
        Assert.assertEquals(3, post1Matches.size());
        Assert.assertTrue(post1Matches.contains("#vaguely"));
        Assert.assertTrue(post1Matches.contains("#sample"));
        Assert.assertTrue(post1Matches.contains("#post"));
        Assert.assertEquals(2, post2Matches.size());
        Assert.assertTrue(post2Matches.contains("#sentence"));
        Assert.assertTrue(post2Matches.contains("#period"));
    }
    
    @Test
    public void ignoresGarbage() {
        List<String> garbageMatches = matcher.findHashTags(garbage);
        
        Assert.assertEquals(0, garbageMatches.size());
    }
}
