//
///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.tsg.cms.dao;
//
//import com.tsg.cms.dto.Category;
//import com.tsg.cms.dto.BlogPost;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import org.junit.After;
//import org.junit.AfterClass;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import static org.junit.Assert.*;
//import org.junit.BeforeClass;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//import org.springframework.dao.DuplicateKeyException;
//import org.springframework.jdbc.core.JdbcTemplate;
//
///**
// *
// * @author apprentice
// */
//public class BlogPostDbDaoImplTest {
//
//    private BlogPostDbDao dao;
//    private BlogPost c1;
//    private BlogPost c2;
//    private BlogPost c3;
//    private BlogPost c4;
//    private BlogPost c5;
//    private BlogPost c6;
//
//    public BlogPostDbDaoImplTest() {
//    }
//
//    @BeforeClass
//    public static void setUpClass() {
//        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
//
//        JdbcTemplate cleaner = (JdbcTemplate) ctx.getBean("jdbcTemplate");
//        cleaner.execute("delete from postHashTagBridge");
//        cleaner.execute("delete from blogPosts");
//        cleaner.execute("delete from hashTags");
//        cleaner.execute("delete from categories");
//    }
//
//    @AfterClass
//    public static void tearDownClass() {
//    }
//
//    @Before
//    public void setUp() throws ParseException {
//
//        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
//        dao = ctx.getBean("BlogPostDbDao", BlogPostDbDao.class);
//        //categoryDao = ctx.getBean("CategoryDbDao", CategoryDbDao.class);
//
//        JdbcTemplate cleaner = (JdbcTemplate) ctx.getBean("jdbcTemplate");
//        // cleaner.execute("delete from categoriesPostsBridge");
//        cleaner.execute("delete from blogPosts");
//        // cleaner.execute("delete from categories");
//
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date date = format.parse("12-25-2016 00:00:00");
//
//        c1 = new BlogPost();
//
//        c1.setTimeCreated(date);
//        c1.setTimeEdited(date);
//        c1.setStartDate(date);
//        c1.setEndDate(date);
//        c1.setTitle("News");
//        c1.setPostBody("Q");
//        c1.setUserIdFK(1111);
//        c1.setStatus(Status.PUBLISHED);
//
//        c2 = new BlogPost();
//
//        c2.setTimeCreated(date);
//        c2.setTimeEdited(date);
//        c2.setStartDate(date);
//        c2.setEndDate(date);
//        c2.setTitle("Ads");
//        c2.setPostBody("B");
//        c2.setUserIdFK(2222);
//        c2.setStatus(Status.READY_FOR_APPROVAL);
//
//        c3 = new BlogPost();
//
//        c3.setTimeCreated(date);
//        c3.setTimeEdited(date);
//        c3.setStartDate(date);
//        c3.setEndDate(date);
//        c3.setTitle("News");
//        c3.setPostBody("F");
//        c3.setUserIdFK(3333);
//        c3.setStatus(Status.DRAFT);
//
//        c4 = new BlogPost();
//
//        c4.setTimeCreated(date);
//        c4.setTimeEdited(date);
//        c4.setStartDate(date);
//        c4.setEndDate(date);
//        c4.setTitle("Sale!");
//        c4.setPostBody("This is the first post with the same title.");
//        c4.setUserIdFK(33);
//        c4.setStatus(Status.DRAFT);
//
//        c5 = new BlogPost();
//
//        c5.setTimeCreated(date);
//        c5.setTimeEdited(date);
//        c5.setStartDate(date);
//        c5.setEndDate(date);
//        c5.setTitle("Sale!");
//        c5.setPostBody("This is the second post with the same title.");
//        c5.setUserIdFK(33);
//        c5.setStatus(Status.DRAFT);
//
//        c6 = new BlogPost();
//
//        c6.setTimeCreated(date);
//        c6.setTimeEdited(date);
//        c6.setStartDate(date);
//        c6.setEndDate(date);
//        c6.setTitle("Sale!");
//        c6.setPostBody("This is the third post with the same title.");
//        c6.setUserIdFK(33);
//        c6.setStatus(Status.DRAFT);
//
//    }
//
//    @After
//    public void tearDown() {
//    }
//
//    
//    //by rights, should be moved to category tests
//    //blogposts shouldn't need category / hashtag, they can stand alone
//    
//    /**
//     * Test of addBlogPost method, of class BlogPostDbDaoImpl.
//     */
////    @Test
////    public void testAddBlogPostDuplicateCategory() {
////        try {
////            c1 = dao.addBlogPost(c1);
////            cat1 = categoryDao.addCategory(cat1);
////            cat1 = categoryDao.addCategory(cat1);
////            categoryDao.addCategoryAndPostToBridge(cat1, c1.getPostId());
////
////        } catch (DuplicateKeyException e) {
////            Boolean thrown = true;
////            System.out.println("Duplicate Key");
////            Assert.assertTrue(thrown);
////        }
////
////    }
////
////    @Test
////    public void testAddBlogPostUniqueCategory() {
////        try {
////            Category Cat4 = new Category();
////            Cat4.setCategoryName("Unique");
////
////            c1 = dao.addBlogPost(c1);
////            Cat4 = categoryDao.addCategory(Cat4);
////            categoryDao.addCategoryAndPostToBridge(Cat4, c1.getPostId());
////
////        } catch (DuplicateKeyException e) {
////            System.out.println("Duplicate Key");
////            fail();
////        }
////
////    }
////
////    @Test
////    public void testUpdateBlogPostDuplicateCategory() {
////        try {
////
////            c3 = dao.addBlogPost(c3);
////            categoryDao.addCategoryAndPostToBridge(cat1, c3.getPostId());
////            c3.setTitle("M");
////            dao.updateBlogPost(c3);
////            categoryDao.addCategoryAndPostToBridge(cat1, c3.getPostId());
////
////        } catch (DuplicateKeyException e) {
////            Boolean thrown = true;
////            Assert.assertTrue(thrown);
////        }
////
////    }
//    /**
//     * Test of removeBlogPost method, of class BlogPostDbDaoImpl.
//     */
//    @Test
//    public void testAddRemoveBlogPost() {
//        dao.addBlogPost(c2);
//        BlogPost fromDb = dao.getBlogPostById(c2.getPostId());
//        c2.setPostId(fromDb.getPostId());
//        dao.removeBlogPost(c2.getPostId());
//        fromDb = dao.getBlogPostById(c2.getPostId());
//        assertNull(fromDb);
//    }
//
//    /**
//     * Test of updateBlogPost method, of class BlogPostDbDaoImpl.
//     */
//    @Test
//    public void testUpdateBlogPost() {
//        c3 = dao.addBlogPost(c3);
//
//        c3.setTitle("M");
//        dao.updateBlogPost(c3);
//
////        C3.setPostId(0);
//        BlogPost fromDb = dao.getBlogPostById(c3.getPostId());
//        c3.setPostId(fromDb.getPostId());
//
//        assertEquals(c3, fromDb);
//    }
//
//    /**
//     * Test of getAllBlogPosts method, of class BlogPostDbDaoImpl.
//     */
//    @Test
//    public void testGetAllBlogPost() {
//        dao.addBlogPost(c1);
//        dao.addBlogPost(c2);
//        dao.addBlogPost(c3);
//
//        List<BlogPost> cList = dao.getAllBlogPosts();
//        assertEquals(cList.size(), 3);
//
//    }
//
//    /**
//     * Test of searchBlogPosts method, of class BlogPostDbDaoImpl.
//     */
//    @Test
//    public void testSearchBlogPost() {
//        dao.addBlogPost(c1);
//        dao.addBlogPost(c2);
//        dao.addBlogPost(c3);
//
//        Map<SearchTerm, String> criteria = new HashMap<>();
//        criteria.put(SearchTerm.TITLE, "News");
//        List<BlogPost> cList = dao.searchBlogPosts(criteria);
//        assertEquals(2, cList.size());
//
//        //this next
//        criteria = new HashMap<>();
//        criteria.put(SearchTerm.STATUS, "DRAFT");
//        cList = dao.searchBlogPosts(criteria);
//        assertEquals(1, cList.size());
//
//        criteria = new HashMap<>();
//        criteria.put(SearchTerm.STATUS, "PUBLISHED");
//        cList = dao.searchBlogPosts(criteria);
//        assertEquals(c1, cList.get(0));
//
//    }
//
//    @Test
//    public void testTitleNumber() {
//
//        c4.setTitle(c6.getTitle());
//        c5.setTitle(c6.getTitle());
//        //failsafe in case objects get changed in setup
//
//        Assert.assertEquals(c4.getTitle(), c5.getTitle());
//        Assert.assertEquals(c5.getTitle(), c6.getTitle());
//        //titles are indeed the same
//
//        dao.addBlogPost(c4);
//        dao.addBlogPost(c5);
//        dao.addBlogPost(c6);
//
//        List<BlogPost> sameTitle = dao.getBlogPostsByTitle(c4.getTitle());
//        Assert.assertEquals(3, sameTitle.size());
//
//        Assert.assertEquals(c4, dao.getBlogPostByTitleNumber(c4.getTitleNumber()));
//        Assert.assertEquals(c5, dao.getBlogPostByTitleNumber(c5.getTitleNumber()));
//        Assert.assertEquals(c6, dao.getBlogPostByTitleNumber(c6.getTitleNumber()));
//
//        Assert.assertNotSame(c5, dao.getBlogPostByTitleNumber(c6.getTitleNumber()));
//    }
//
//}
