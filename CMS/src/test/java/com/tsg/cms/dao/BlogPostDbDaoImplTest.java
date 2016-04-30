/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.cms.dao;

import com.tsg.cms.dto.BlogPost;
import com.tsg.cms.dto.BlogPostContainer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author apprentice
 */
public class BlogPostDbDaoImplTest {

    ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
    private BlogPostDbDao dao = ctx.getBean("BlogPostDbDao", BlogPostDbDao.class);
    private BlogPost c1;
    private BlogPost c2;
    private BlogPost c3;
    private BlogPost c4;
    private BlogPost c5;
    private BlogPost c6;
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public BlogPostDbDaoImplTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        JdbcTemplate cleaner = (JdbcTemplate) ctx.getBean("jdbcTemplate");
        cleaner.execute("delete from postHashTagBridge");
        cleaner.execute("delete from blogPosts");
        cleaner.execute("delete from hashTags");
        cleaner.execute("delete from categories");
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws ParseException {

        JdbcTemplate cleaner = (JdbcTemplate) ctx.getBean("jdbcTemplate");
        cleaner.execute("delete from postHashTagBridge");
        cleaner.execute("delete from hashTags");
        cleaner.execute("delete from blogPosts");

        Date date = format.parse("2015-12-25 00:00:00");
        Date endDate = format.parse("2017-12-25 00:00:00");
        c1 = new BlogPost();

        c1.setTimeCreated(date);
        c1.setTimeEdited(date);
        c1.setStartDate(date);
        c1.setEndDate(endDate);
        c1.setTitle("News");
        c1.setPostBody("Q");
        c1.setUserIdFK(1111);
        c1.setStatus("PUBLISHED");

        c2 = new BlogPost();

        c2.setTimeCreated(date);
        c2.setTimeEdited(date);
        c2.setStartDate(date);
        c2.setEndDate(endDate);
        c2.setTitle("Ads");
        c2.setPostBody("B");
        c2.setUserIdFK(2222);
        c2.setStatus("READY_FOR_APPROVAL");

        c3 = new BlogPost();

        c3.setTimeCreated(date);
        c3.setTimeEdited(date);
        c3.setStartDate(date);
        c3.setEndDate(endDate);
        c3.setTitle("News");
        c3.setPostBody("F");
        c3.setUserIdFK(3333);
        c3.setStatus("DRAFT");

        c4 = new BlogPost();

        c4.setTimeCreated(date);
        c4.setTimeEdited(date);
        c4.setStartDate(date);
        c4.setEndDate(endDate);
        c4.setTitle("Sale!");
        c4.setPostBody("This is the first post with the same title.");
        c4.setUserIdFK(33);
        c4.setStatus("DRAFT");
        
        c5 = new BlogPost();

        c5.setTimeCreated(date);
        c5.setTimeEdited(date);
        c5.setStartDate(date);
        c5.setEndDate(endDate);
        c5.setTitle("Sale!");
        c5.setPostBody("This is the second post with the same title.");
        c5.setUserIdFK(33);
        c5.setStatus("DRAFT");

        c6 = new BlogPost();

        c6.setTimeCreated(date);
        c6.setTimeEdited(date);
        c6.setStartDate(date);
        c6.setEndDate(endDate);
        c6.setTitle("Sale!");
        c6.setPostBody("This is the third post with the same title.");
        c6.setUserIdFK(33);
        c6.setStatus("DRAFT");

    }

    @After
    public void tearDown() {
    }

    /**
     * Test of removeBlogPost method, of class BlogPostDbDaoImpl.
     */
    @Test
    public void testAddRemoveBlogPost() {
        dao.addBlogPost(c2);
        BlogPost fromDb = dao.getBlogPostById(c2.getPostId()).getBlogPost();
        //c2.setPostId(fromDb.getPostId());
        Assert.assertEquals(c2, fromDb);

        dao.removeBlogPost(c2.getPostId());
        fromDb = dao.getBlogPostById(c2.getPostId()).getBlogPost();
        assertNull(fromDb);
    }

    /**
     * Test of updateBlogPost method, of class BlogPostDbDaoImpl.
     */
    @Test
    public void testUpdateBlogPost() throws ParseException {
        dao.addBlogPost(c3);

        c3.setPostBody("M");
        dao.updateBlogPost(c3);

        BlogPost fromDb = dao.getBlogPostById(c3.getPostId()).getBlogPost();
        c3.setPostId(fromDb.getPostId());

        Assert.assertEquals(c3.getTitle(), fromDb.getTitle());
        Assert.assertEquals(c3.getTitleNumber(), fromDb.getTitleNumber());
        Assert.assertEquals(c3.getPostBody(), fromDb.getPostBody());
        Assert.assertEquals(c3.getStatus(), fromDb.getStatus());
        Assert.assertEquals(c3.getCategoryIdFK(), fromDb.getCategoryIdFK());
    }

    /**
     * Test of getAllBlogPosts method, of class BlogPostDbDaoImpl.
     */
    @Test
    public void testGetAllBlogPost() {

        System.out.println(c1.getStartDate());
        System.out.println(c1.getEndDate());
        dao.addBlogPost(c1);
        dao.addBlogPost(c2);
        dao.addBlogPost(c3);

        List<BlogPostContainer> cList = dao.getAllBlogPosts();
        assertEquals(cList.size(), 3);

    }

    /**
     * Test of searchBlogPosts method, of class BlogPostDbDaoImpl.
     */
    @Test
    public void testSearchBlogPost() {
        dao.addBlogPost(c1);
        dao.addBlogPost(c2);
        dao.addBlogPost(c3);

        Map<SearchTerm, String> criteria = new HashMap<>();
        criteria.put(SearchTerm.TITLE, "News");
        List<BlogPostContainer> cList = dao.searchBlogPosts(criteria);
        assertEquals(2, cList.size());

        //this next
        criteria = new HashMap<>();
        criteria.put(SearchTerm.STATUS, "DRAFT");
        cList = dao.searchBlogPosts(criteria);
        assertEquals(1, cList.size());

        criteria = new HashMap<>();
        criteria.put(SearchTerm.STATUS, "PUBLISHED");
        cList = dao.searchBlogPosts(criteria);
        assertEquals(c1, cList.get(0).getBlogPost());

    }

    //simplify last test
    private void resetAndTestTitles(String newTitle,
                                    BlogPost blogPost1,
                                    BlogPost blogPost2,
                                    BlogPost blogPost3) {
        blogPost1.setTitle(newTitle);
        blogPost2.setTitle(newTitle);
        blogPost3.setTitle(newTitle);

        Assert.assertEquals(blogPost1.getTitle(), blogPost2.getTitle());
        Assert.assertEquals(blogPost2.getTitle(), blogPost3.getTitle());
        //titles are indeed the same

        dao.addBlogPost(blogPost1);
        dao.addBlogPost(blogPost2);
        dao.addBlogPost(blogPost3);

        List<BlogPost> sameTitle = dao.getBlogPostsByTitle(blogPost1.getTitle());
        Assert.assertEquals(3, sameTitle.size());

        BlogPostContainer bp1Container = dao.getBlogPostByTitleNumber(blogPost1.getTitleNumber());
        BlogPostContainer bp2Container = dao.getBlogPostByTitleNumber(blogPost2.getTitleNumber());
        BlogPostContainer bp3Container = dao.getBlogPostByTitleNumber(blogPost3.getTitleNumber());

        Assert.assertEquals(blogPost1.getTitle(), bp1Container.getBlogPost().getTitle());
        Assert.assertEquals(blogPost2.getTitle(), bp2Container.getBlogPost().getTitle());
        Assert.assertEquals(blogPost3.getTitle(), bp3Container.getBlogPost().getTitle());

        Assert.assertNotSame(blogPost1, dao.getBlogPostByTitleNumber(blogPost2.getTitleNumber()).getBlogPost());
    }

    @Test
    public void testTitleNumber() {
        resetAndTestTitles("title", c4, c5, c6);
        resetAndTestTitles("#title", c4, c5, c6);
        resetAndTestTitles("title___", c4, c5, c6);
        resetAndTestTitles("...title...", c4, c5, c6);
        resetAndTestTitles("title...", c4, c5, c6);
        resetAndTestTitles("#$%%^#$@%&(^@#$%title...", c4, c5, c6);

    }

}
