/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.cms.dao;

import java.util.List;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author apprentice
 */
public class TagDbDaoImplTest {

    private TagDbDao dao;
    private String tag1 = "gruyere";
    private String tag2 = "kashkaval";
    private String tag3 = "sulguni";
    private String tag4 = "leicester red";
    private String tag5 = "emmentaler";

    public TagDbDaoImplTest() {

    }

    @Before
    public void setUp() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        dao = ctx.getBean("TagDbDao", TagDbDao.class);

        JdbcTemplate cleaner = (JdbcTemplate) ctx.getBean("jdbcTemplate");
        cleaner.execute("delete from hashTags");
        cleaner.execute("delete from postHashTagBridge");
    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void testAddGetTag() {
        dao.addTag(tag1, 0);
        dao.addTag(tag2, 0);
        dao.addTag(tag3, 0);

        List<String> post0tags = dao.getPostTags(0);
        Assert.assertTrue(post0tags.contains(tag1));
        Assert.assertTrue(post0tags.contains(tag2));
        Assert.assertTrue(post0tags.contains(tag3));
        Assert.assertFalse(post0tags.contains(tag4));

        dao.addTag(tag1, 1);
        dao.addTag(tag2, 1);
        List<String> post1tags = dao.getPostTags(1);
        Assert.assertTrue(post0tags.contains(tag1));
        Assert.assertTrue(post0tags.contains(tag2));

        //can't add same tag more than once to same post
        dao.addTag(tag1, 1);
        post1tags = dao.getPostTags(1);
        Assert.assertEquals(2, post1tags.size());
    }

}
