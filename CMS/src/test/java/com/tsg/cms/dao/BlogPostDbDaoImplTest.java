
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.cms.dao;

import com.tsg.cms.dto.Category;
import com.tsg.cms.dto.BlogPost;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author apprentice
 */
public class BlogPostDbDaoImplTest {

    private BlogPostDbDao dao;
    private CategoryDbDao categoryDao;
    private BlogPost C1;
    private BlogPost C2;
    private BlogPost C3;

    private List<Category> cList1;
    private List<Category> cList2;
    private List<Category> cList3;

    private Category cat1;
    private Category cat2;
    private Category cat3;

    public BlogPostDbDaoImplTest() {
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
        dao = ctx.getBean("BlogPostDbDao", BlogPostDbDao.class);
        categoryDao = ctx.getBean("CategoryDbDao", CategoryDbDao.class);

        JdbcTemplate cleaner = (JdbcTemplate) ctx.getBean("jdbcTemplate");
        cleaner.execute("delete from categoriesPostsBridge");
        cleaner.execute("delete from blogPosts");
        cleaner.execute("delete from categories");

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = format.parse("12-25-2016 00:00:00");

        cList1 = new ArrayList<>();
        cat1 = new Category();
        cat1.setCategoryName("News");
        cat1 = categoryDao.addCategory(cat1);

        cat2 = new Category();
        cat2.setCategoryName("Sales");
        cat2 = categoryDao.addCategory(cat2);

        cat3 = new Category();
        cat3.setCategoryName("Cheese");
        cat3 = categoryDao.addCategory(cat3);

        cList1.add(cat1);
        cList1.add(cat2);

        C1 = new BlogPost();

        C1.setDateSubmitted(date);
        C1.setStartDate(date);
        C1.setEndDate(date);
        C1.setTitle("News");
        C1.setPostBody("Q");
        C1.setUserIdFK(11);
        C1.setStatus("Published");
        C1.setPostType("International");

        C2 = new BlogPost();

        C2.setDateSubmitted(date);
        C2.setStartDate(date);
        C2.setEndDate(date);
        C2.setTitle("Ads");
        C2.setPostBody("B");
        C2.setUserIdFK(22);
        C2.setStatus("PendingForApproval");
        C2.setPostType("Local");

        C3 = new BlogPost();

        C3.setDateSubmitted(date);
        C3.setStartDate(date);
        C3.setEndDate(date);
        C3.setTitle("News");
        C3.setPostBody("F");
        C3.setUserIdFK(33);
        C3.setStatus("Draft");
        C3.setPostType("Local");

    }

    @After
    public void tearDown() {
    }

    /**
     * Test of addBlogPost method, of class BlogPostDbDaoImpl.
     */
    @Test
    public void testAddBlogPostDuplicateCategory() {
        try {
            C1 = dao.addBlogPost(C1);
            cat1 = categoryDao.addCategory(cat1);
            cat1 = categoryDao.addCategory(cat1);
            categoryDao.addCategoryAndPostToBridge(cat1, C1.getPostId());

        } catch (DuplicateKeyException e) {
            Boolean thrown = true;
            System.out.println("Duplicate Key");
            Assert.assertTrue(thrown);
        }

    }

    @Test
    public void testAddBlogPostUniqueCategory() {
        try {
            Category Cat4 = new Category();
            Cat4.setCategoryName("Unique");

            C1 = dao.addBlogPost(C1);
            Cat4 = categoryDao.addCategory(Cat4);
            categoryDao.addCategoryAndPostToBridge(Cat4, C1.getPostId());

        } catch (DuplicateKeyException e) {
            System.out.println("Duplicate Key");
            fail();
        }

    }

    @Test
    public void testUpdateBlogPostDuplicateCategory() {
        try {

            C3 = dao.addBlogPost(C3);
            categoryDao.addCategoryAndPostToBridge(cat1, C3.getPostId());
            C3.setTitle("M");
            dao.updateBlogPost(C3);
            categoryDao.addCategoryAndPostToBridge(cat1, C3.getPostId());

        } catch (DuplicateKeyException e) {
            Boolean thrown = true;
            Assert.assertTrue(thrown);
        }

    }

    /**
     * Test of removeBlogPost method, of class BlogPostDbDaoImpl.
     */
    @Test
    public void testRemoveBlogPost() {
        dao.addBlogPost(C2);
        BlogPost fromDb = dao.getBlogPostById(C2.getPostId());
        C2.setPostId(fromDb.getPostId());
        dao.removeBlogPost(C2.getPostId());
        fromDb = dao.getBlogPostById(C2.getPostId());
        assertNull(fromDb);
    }

    /**
     * Test of updateBlogPost method, of class BlogPostDbDaoImpl.
     */
    @Test
    public void testUpdateBlogPost() {
        C3 = dao.addBlogPost(C3);

        C3.setTitle("M");
        dao.updateBlogPost(C3);

//        C3.setPostId(0);
        BlogPost fromDb = dao.getBlogPostById(C3.getPostId());
        C3.setPostId(fromDb.getPostId());

        assertEquals(C3, fromDb);
    }

    /**
     * Test of getAllBlogPost method, of class BlogPostDbDaoImpl.
     */
    @Test
    public void testGetAllBlogPost() {
        dao.addBlogPost(C1);
        dao.addBlogPost(C2);
        dao.addBlogPost(C3);

        List<BlogPost> cList = dao.getAllBlogPost();
        assertEquals(cList.size(), 3);

    }

    /**
     * Test of searchBlogPost method, of class BlogPostDbDaoImpl.
     */
    @Test
    public void testSearchBlogPost() {
        dao.addBlogPost(C1);
        dao.addBlogPost(C2);
        dao.addBlogPost(C3);

        Map<SearchTerm, String> criteria = new HashMap<>();
        criteria.put(SearchTerm.TITLE, "News");
        List<BlogPost> cList = dao.searchBlogPost(criteria);
        assertEquals(2, cList.size());

        criteria.put(SearchTerm.POSTTYPE, "International");
        cList = dao.searchBlogPost(criteria);
        BlogPost fromDb = cList.get(0);
        C1.setPostId(fromDb.getPostId());
        assertEquals(C1, fromDb);
        assertEquals(1, cList.size());

        criteria = new HashMap<>();
        criteria.put(SearchTerm.STATUS, "Draft");
        cList = dao.searchBlogPost(criteria);
        assertEquals(1, cList.size());

        criteria = new HashMap<>();
        criteria.put(SearchTerm.STATUS, "Published");
        cList = dao.searchBlogPost(criteria);
        assertEquals(C1, cList.get(0));

    }

}
