/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.cms.dao;

import com.tsg.cms.dto.BlogPost;
import com.tsg.cms.dto.Category;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import junit.framework.Assert;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
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

    private BlogPostDbDao blogDao;

//    private CategoryDbDao categoryDao;
    private String tag1 = "gruyere";
    private String tag2 = "kashkaval";
    private String tag3 = "sulguni";
    private String tag4 = "leicester red";
    private String tag5 = "emmentaler";

    private BlogPost blogPost1;
    private BlogPost blogPost2;
    private BlogPost blogPost3;

//    private List<Category> cList1;
//    private List<Category> cList2;
//    private List<Category> cList3;
    public TagDbDaoImplTest() {

    }

    @BeforeClass
    public static void setUpClass() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");

        JdbcTemplate cleaner = (JdbcTemplate) ctx.getBean("jdbcTemplate");
        cleaner.execute("delete from postHashTagBridge");
        cleaner.execute("delete from categoriesPostsBridge");
        cleaner.execute("delete from blogPosts");
        cleaner.execute("delete from hashTags");
        cleaner.execute("delete from categories");
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws ParseException {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        dao = ctx.getBean("TagDbDao", TagDbDao.class);
        blogDao = ctx.getBean("BlogPostDbDao", BlogPostDbDao.class);

        JdbcTemplate cleaner = (JdbcTemplate) ctx.getBean("jdbcTemplate");
        cleaner.execute("delete from postHashTagBridge");
        cleaner.execute("delete from hashTags");
        cleaner.execute("delete from blogPosts");

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = format.parse("12-25-2016 00:00:00");
        blogPost1 = new BlogPost();

        blogPost1.setDateSubmitted(date);
        blogPost1.setStartDate(date);
        blogPost1.setEndDate(date);
        blogPost1.setTitle("News");
        blogPost1.setPostBody("Q");
        blogPost1.setUserIdFK(11);
        blogPost1.setStatus("Published");
        blogPost1.setPostType("International");

        blogPost2 = new BlogPost();

        blogPost2.setDateSubmitted(date);
        blogPost2.setStartDate(date);
        blogPost2.setEndDate(date);
        blogPost2.setTitle("Ads");
        blogPost2.setPostBody("B");
        blogPost2.setUserIdFK(22);
        blogPost2.setStatus("PendingForApproval");
        blogPost2.setPostType("Local");

        blogPost3 = new BlogPost();

        blogPost3.setDateSubmitted(date);
        blogPost3.setStartDate(date);
        blogPost3.setEndDate(date);
        blogPost3.setTitle("News");
        blogPost3.setPostBody("F");
        blogPost3.setUserIdFK(33);
        blogPost3.setStatus("Draft");
        blogPost3.setPostType("Local");

        blogDao.addBlogPost(blogPost1);
        blogDao.addBlogPost(blogPost2);
        blogDao.addBlogPost(blogPost3);

    }

    @After
    public void tearDown() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        JdbcTemplate cleaner = (JdbcTemplate) ctx.getBean("jdbcTemplate");
        //cleaner.execute("delete from categoriesPostsBridge");
        cleaner.execute("delete from postHashTagBridge");
        cleaner.execute("delete from hashTags");
        cleaner.execute("delete from blogPosts");
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void testAddGetTag() {

        //posts with no tags have no tags
        List<String> blogPost1Tags = dao.getPostTags(0);
        Assert.assertEquals(0, blogPost1Tags.size());

        //we can add multiple tags to a single post
        dao.addTag(tag1, blogPost1.getPostId());
        dao.addTag(tag2, blogPost1.getPostId());
        dao.addTag(tag3, blogPost1.getPostId());

        //and we get three tags back, and only the three we added
        blogPost1Tags = dao.getPostTags(blogPost1.getPostId());
        Assert.assertEquals(3, blogPost1Tags.size());
        Assert.assertTrue(blogPost1Tags.contains(tag1));
        Assert.assertTrue(blogPost1Tags.contains(tag2));
        Assert.assertTrue(blogPost1Tags.contains(tag3));
        Assert.assertFalse(blogPost1Tags.contains(tag4));

        //tags can have multiple posts
        dao.addTag(tag1, blogPost2.getPostId());
        dao.addTag(tag2, blogPost2.getPostId());
        dao.addTag(tag4, blogPost2.getPostId());
        List<String> blogPost2tags = dao.getPostTags(blogPost2.getPostId());
        Assert.assertEquals(3, blogPost2tags.size());
        Assert.assertTrue(blogPost2tags.contains(tag1));
        Assert.assertTrue(blogPost2tags.contains(tag2));
        Assert.assertTrue(blogPost2tags.contains(tag4));

        //but if we try to add a duplicate tag to a post it fails
        dao.addTag(tag1, blogPost2.getPostId());
        Assert.assertEquals(3, blogPost2tags.size());
        Assert.assertTrue(blogPost2tags.contains(tag1));
        Assert.assertTrue(blogPost2tags.contains(tag2));
        Assert.assertTrue(blogPost2tags.contains(tag4));
    }

    @Test
    public void testRemoveTag() {
        dao.addTag(tag1, blogPost1.getPostId());
        dao.addTag(tag2, blogPost1.getPostId());
        dao.addTag(tag3, blogPost1.getPostId());

        List<String> blogPost1Tags = dao.getPostTags(blogPost1.getPostId());
        Assert.assertEquals(3, blogPost1Tags.size());

        //gotta maintain consistency
        dao.removeTag(tag1);

    }

}
