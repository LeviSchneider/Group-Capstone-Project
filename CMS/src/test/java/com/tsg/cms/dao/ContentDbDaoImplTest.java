
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.cms.dao;

import com.tsg.cms.dto.Category;
import com.tsg.cms.dto.Content;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author apprentice
 */
public class ContentDbDaoImplTest {

    private ContentDbDao Dao;
    private CategoryDAO categoryDAO;
    private Content C1;
    private Content C2;
    private Content C3;

    private List<Category> cList1;
    private List<Category> cList2;
    private List<Category> cList3;

    private Category cat1;
    private Category cat2;
    private Category cat3;

    public ContentDbDaoImplTest() {
    }

    @Before
    public void setUp() throws ParseException {

        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        Dao = ctx.getBean("ContentDbDao", ContentDbDao.class);

        JdbcTemplate cleaner = (JdbcTemplate) ctx.getBean("jdbcTemplate");
        cleaner.execute("delete from blogPosts");

        ApplicationContext ctx2 = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        categoryDAO = ctx2.getBean("CategoryDAO", CategoryDAO.class);

        JdbcTemplate cleaner2 = (JdbcTemplate) ctx2.getBean("jdbcTemplate");
        cleaner.execute("delete from categories");

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = format.parse("12-25-2016 00:00:00");

        cList1 = new ArrayList<>();
        cat1 = new Category();
        cat1.setCategoryName("News");
        cat1 = categoryDAO.addCategory(cat1);

        cat2 = new Category();
        cat2.setCategoryName("Sales");
        cat2 = categoryDAO.addCategory(cat2);

        cat3 = new Category();
        cat3.setCategoryName("Cheese");
        cat3 = categoryDAO.addCategory(cat3);

        cList1.add(cat1);
        cList1.add(cat2);

        C1 = new Content();

        C1.setDateSubmitted(date);
        C1.setStartDate(date);
        C1.setEndDate(date);
        C1.setTitle("News");
        C1.setPostBody("Q");
        C1.setUserIdFK(11);
        C1.setStatus("Published");
        C1.setPostType("International");

        C2 = new Content();

        C2.setDateSubmitted(date);
        C2.setStartDate(date);
        C2.setEndDate(date);
        C2.setTitle("Ads");
        C2.setPostBody("B");
        C2.setUserIdFK(22);
        C2.setStatus("PendingForApproval");
        C2.setPostType("Local");

        C3 = new Content();

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
     * Test of addContent method, of class ContentDbDaoImpl.
     */
    @Test
    public void testAddContentDuplicateCategory() {
        try {
            C1 = Dao.addContent(C1);
            categoryDAO.addCategory(cat1, C1.getPostId());
            Content fromDb = Dao.getContentById(C1.getPostId());
            assertEquals(C1, fromDb);
        } catch (DuplicateKeyException e) {
            System.out.println("Duplicate Key");
            fail();
        }

    }

    @Test
    public void testAddContentUniqueCategory() {
        try {
            Category Cat4 = new Category();
            Cat4.setCategoryName("Unique");
            cList1.add(Cat4);

            C1 = Dao.addContent(C1);
            Cat4 = categoryDAO.addCategory(Cat4, C1.getPostId());
            Content fromDb = Dao.getContentById(C1.getPostId());
            assertEquals(C1, fromDb);
        } catch (DuplicateKeyException e) {
            System.out.println("Duplicate Key");
            fail();
        }

    }

    /**
     * Test of removeContent method, of class ContentDbDaoImpl.
     */
    @Test
    public void testRemoveContent() {
        Dao.addContent(C2);
        Content fromDb = Dao.getContentById(C2.getPostId());
        C2.setPostId(fromDb.getPostId());
        Dao.removeContent(C2.getPostId());
        fromDb = Dao.getContentById(C2.getPostId());
        assertNull(fromDb);
    }

    /**
     * Test of updateContent method, of class ContentDbDaoImpl.
     */
    @Test
    public void testUpdateContent() {
        C3 = Dao.addContent(C3);

        C3.setTitle("M");
        Dao.updateContent(C3);

//        C3.setPostId(0);
        Content fromDb = Dao.getContentById(C3.getPostId());
        C3.setPostId(fromDb.getPostId());

        assertEquals(C3, fromDb);
    }

    /**
     * Test of getAllContent method, of class ContentDbDaoImpl.
     */
    @Test
    public void testGetAllContent() {
        Dao.addContent(C1);
        Dao.addContent(C2);
        Dao.addContent(C3);

        List<Content> cList = Dao.getAllContent();
        assertEquals(cList.size(), 3);

    }

    /**
     * Test of searchContent method, of class ContentDbDaoImpl.
     */
    @Test
    public void testSearchContent() {
        Dao.addContent(C1);
        Dao.addContent(C2);
        Dao.addContent(C3);

        Map<SearchTerm, String> criteria = new HashMap<>();
        criteria.put(SearchTerm.TITLE, "News");
        List<Content> cList = Dao.searchContent(criteria);
        assertEquals(2, cList.size());

        criteria.put(SearchTerm.POSTTYPE, "International");
        cList = Dao.searchContent(criteria);
        Content fromDb = cList.get(0);
        C1.setPostId(fromDb.getPostId());
        assertEquals(C1, fromDb);
        assertEquals(1, cList.size());

        criteria = new HashMap<>();
        criteria.put(SearchTerm.STATUS, "Draft");
        cList = Dao.searchContent(criteria);
        assertEquals(1, cList.size());

        criteria = new HashMap<>();
        criteria.put(SearchTerm.STATUS, "Published");
        cList = Dao.searchContent(criteria);
        assertEquals(C1, cList.get(0));

    }

}
