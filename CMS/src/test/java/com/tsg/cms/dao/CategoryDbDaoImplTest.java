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
import static java.util.Date.from;
import java.util.List;

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
public class CategoryDbDaoImplTest {

    private CategoryDbDao Dao;
    private BlogPostDbDao blogDao;

    private Category C1;
    private Category C2;
    private Category C3;
    private BlogPost blogPost1, blogPost2, blogPost3;

    public CategoryDbDaoImplTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");

        JdbcTemplate cleaner = (JdbcTemplate) ctx.getBean("jdbcTemplate");
        cleaner.execute("delete from blogPosts");
        cleaner.execute("delete from categories");
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws ParseException {

        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        Dao = ctx.getBean("CategoryDbDao", CategoryDbDao.class);
        blogDao = ctx.getBean("BlogPostDbDao", BlogPostDbDao.class);

        JdbcTemplate cleaner = (JdbcTemplate) ctx.getBean("jdbcTemplate");
        cleaner.execute("delete from blogPosts");
        cleaner.execute("delete from categories");

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = format.parse("12-25-2016 00:00:00");

        blogPost1 = new BlogPost();
        blogPost1.setTimeCreated(date);
        blogPost1.setTimeEdited(date);
        blogPost1.setStartDate(date);
        blogPost1.setEndDate(date);
        blogPost1.setTitle("News");
        blogPost1.setPostBody("Q");
        blogPost1.setUserIdFK(1111);
        blogPost1.setStatus(Status.PUBLISHED);

        blogPost2 = new BlogPost();
        blogPost2.setTimeCreated(date);
        blogPost2.setTimeEdited(date);
        blogPost2.setStartDate(date);
        blogPost2.setEndDate(date);
        blogPost2.setTitle("Ads");
        blogPost2.setPostBody("B");
        blogPost2.setUserIdFK(2222);
        blogPost2.setStatus(Status.READY_FOR_APPROVAL);

        blogPost3 = new BlogPost();
        blogPost3.setTimeCreated(date);
        blogPost3.setTimeEdited(date);
        blogPost3.setStartDate(date);
        blogPost3.setEndDate(date);
        blogPost3.setTitle("News");
        blogPost3.setPostBody("F");
        blogPost3.setUserIdFK(3333);
        blogPost3.setStatus(Status.DRAFT);

        blogDao.addBlogPost(blogPost1);
        blogDao.addBlogPost(blogPost2);
        blogDao.addBlogPost(blogPost3);

        C1 = new Category();
        C1.setCategoryName("category1");

        C2 = new Category();
        C2.setCategoryName("category2");

        C3 = new Category();
        C3.setCategoryName("category3");
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of addCategory method, of class CategoryDAOImpl.
     */
    @Test
    public void testAddCategory() {

        C1 = Dao.addCategory(C1);
        Dao.addCategoryAndPostToBridge(C1, blogPost1.getPostId());
        Category fromDb = Dao.getCategoryById(C1.getCategoryId());
        C1.setCategoryId(fromDb.getCategoryId());
        assertEquals(C1, fromDb);

    }

    /**
     *
     * Test of removeCategory method, of class CategoryDAOImpl.
     */
    @Test
    public void testRemoveCategory() {

        C2 = Dao.addCategory(C2);
        Dao.addCategoryAndPostToBridge(C2, blogPost2.getPostId());
        Category fromDb = Dao.getCategoryById(C2.getCategoryId());
        C2.setCategoryId(fromDb.getCategoryId());

        Dao.removeCategory(C2.getCategoryId());
        fromDb = Dao.getCategoryById(C2.getCategoryId());
        assertNull(fromDb);

    }

    /**
     * Test of updateCategory method, of class CategoryDAOImpl.
     */
    @Test
    public void testUpdateCategory() {

        C3 = Dao.addCategory(C3);
        Dao.addCategoryAndPostToBridge(C3, blogPost3.getPostId());

        C3.setCategoryName("CategoryTest");
        Dao.updateCategory(C3);

        Category fromDb = Dao.getCategoryById(C3.getCategoryId());
        C3.setCategoryId(fromDb.getCategoryId());
        assertEquals(C3, fromDb);

    }

    /**
     * Test of getAllCategories method, of class CategoryDAOImpl.
     */
    @Test
    public void testGetAllCategories() {

        C1 = Dao.addCategory(C1);
        Dao.addCategoryAndPostToBridge(C1, blogPost1.getPostId());
        C2 = Dao.addCategory(C2);
        Dao.addCategoryAndPostToBridge(C2, blogPost2.getPostId());
        C3 = Dao.addCategory(C3);
        Dao.addCategoryAndPostToBridge(C3, blogPost3.getPostId());

        List<Category> cList = Dao.getAllCategories();
        assertEquals(3, cList.size());

    }

    /**
     *
     * Test of searchCategory method, of class CategoryDAOImpl.
     */
    @Test
    public void testSearchCategory() {
//        Dao.addCategoryAndPostToBridge(C1, blogPost1.getPostId());
//        Dao.addCategoryAndPostToBridge(C2, blogPost2.getPostId());
//        Dao.addCategoryAndPostToBridge(C3, blogPost3.getPostId());
    }

    from blogpostdao tests
    
    /**
     * Test of addBlogPost method, of class BlogPostDbDaoImpl.
     */
    @Test

    public void testAddBlogPostDuplicateCategory() {
        try {
            c1 = dao.addBlogPost(c1);
            cat1 = categoryDao.addCategory(cat1);
            cat1 = categoryDao.addCategory(cat1);
            categoryDao.addCategoryAndPostToBridge(cat1, c1.getPostId());

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

            c1 = dao.addBlogPost(c1);
            Cat4 = categoryDao.addCategory(Cat4);
            categoryDao.addCategoryAndPostToBridge(Cat4, c1.getPostId());

        } catch (DuplicateKeyException e) {
            System.out.println("Duplicate Key");
            fail();
        }

    }

    @Test
    public void testUpdateBlogPostDuplicateCategory() {
        try {

            c3 = dao.addBlogPost(c3);
            categoryDao.addCategoryAndPostToBridge(cat1, c3.getPostId());
            c3.setTitle("M");
            dao.updateBlogPost(c3);
            categoryDao.addCategoryAndPostToBridge(cat1, c3.getPostId());

        } catch (DuplicateKeyException e) {
            Boolean thrown = true;
            Assert.assertTrue(thrown);
        }

    }
}
