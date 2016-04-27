///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.tsg.cms.dao;
//
//import com.tsg.cms.dto.StaticPage;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import org.junit.After;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import static org.junit.Assert.*;
//import org.junit.BeforeClass;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//import org.springframework.jdbc.core.JdbcTemplate;
//
///**
// *
// * @author apprentice
// */
//public class StaticPageDbDaoImplTest {
//
//    private StaticPageDbDao dao;
//    private StaticPage c1;
//    private StaticPage c2;
//    private StaticPage c3;
//    private StaticPage c4;
//    private StaticPage c5;
//    private StaticPage c6;
//
//    public StaticPageDbDaoImplTest() {
//    }
//
//    @BeforeClass
//    public static void setUpClass() {
//
//        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
//
//        JdbcTemplate cleaner = (JdbcTemplate) ctx.getBean("jdbcTemplate");
//        cleaner.execute("delete from pageHashTagBridge");
//        cleaner.execute("delete from staticPages");
//        cleaner.execute("delete from hashTags");
//        cleaner.execute("delete from categories");
//
//    }
//
//    @Before
//    public void setUp() throws ParseException {
//
//        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
//        dao = ctx.getBean("StaticPageDbDao", StaticPageDbDao.class);
//        //categoryDao = ctx.getBean("CategoryDbDao", CategoryDbDao.class);
//
//        JdbcTemplate cleaner = (JdbcTemplate) ctx.getBean("jdbcTemplate");
//        // cleaner.execute("delete from categoriesPostsBridge");
//        cleaner.execute("delete from staticPages");
//        // cleaner.execute("delete from categories");
//
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date date = format.parse("12-25-2016 00:00:00");
//
//        c1 = new StaticPage();
//
//        c1.setTimeCreated(date);
//        c1.setTimeEdited(date);
//        c1.setStartDate(date);
//        c1.setEndDate(date);
//        c1.setTitle("News");
//        c1.setPageBody("Q");
//        c1.setUserIdFK(1111);
//        c1.setStatus(Status.PUBLISHED);
//
//        c2 = new StaticPage();
//
//        c2.setTimeCreated(date);
//        c2.setTimeEdited(date);
//        c2.setStartDate(date);
//        c2.setEndDate(date);
//        c2.setTitle("Ads");
//        c2.setPageBody("B");
//        c2.setUserIdFK(2222);
//        c2.setStatus(Status.READY_FOR_APPROVAL);
//
//        c3 = new StaticPage();
//
//        c3.setTimeCreated(date);
//        c3.setTimeEdited(date);
//        c3.setStartDate(date);
//        c3.setEndDate(date);
//        c3.setTitle("News");
//        c3.setPageBody("F");
//        c3.setUserIdFK(3333);
//        c3.setStatus(Status.DRAFT);
//
//        c4 = new StaticPage();
//
//        c4.setTimeCreated(date);
//        c4.setTimeEdited(date);
//        c4.setStartDate(date);
//        c4.setEndDate(date);
//        c4.setTitle("Sale!");
//        c4.setPageBody("This is the first post with the same title.");
//        c4.setUserIdFK(33);
//        c4.setStatus(Status.DRAFT);
//
//        c5 = new StaticPage();
//
//        c5.setTimeCreated(date);
//        c5.setTimeEdited(date);
//        c5.setStartDate(date);
//        c5.setEndDate(date);
//        c5.setTitle("Sale!");
//        c5.setPageBody("This is the second post with the same title.");
//        c5.setUserIdFK(33);
//        c5.setStatus(Status.DRAFT);
//
//        c6 = new StaticPage();
//
//        c6.setTimeCreated(date);
//        c6.setTimeEdited(date);
//        c6.setStartDate(date);
//        c6.setEndDate(date);
//        c6.setTitle("Sale!");
//        c6.setPageBody("This is the third post with the same title.");
//        c6.setUserIdFK(33);
//        c6.setStatus(Status.DRAFT);
//
//    }
//
//    @After
//    public void tearDown() {
//    }
//
//    /**
//     * Test of setJdbcTemplate method, of class StaticPageDbDaoImpl.
//     */
//    @Test
//    public void testAddRemoveStaticPage() {
//        dao.addStaticPage(c2);
//        StaticPage fromDb = dao.getStaticPageById(c2.getPageId());
//        c2.setPageId(fromDb.getPageId());
//        dao.removeStaticPage(c2.getPageId());
//        fromDb = dao.getStaticPageById(c2.getPageId());
//        assertNull(fromDb);
//    }
//
//    /**
//     * Test of updateStaticPage method, of class StaticPageDbDaoImpl.
//     */
//    @Test
//    public void testUpdateStaticPage() {
//        c3 = dao.addStaticPage(c3);
//
//        c3.setTitle("M");
//        dao.updateStaticPage(c3);
//
////        C3.setPageId(0);
//        StaticPage fromDb = dao.getStaticPageById(c3.getPageId());
//        c3.setPageId(fromDb.getPageId());
//
//        assertEquals(c3, fromDb);
//    }
//
//    /**
//     * Test of getAllStaticPage method, of class StaticPageDbDaoImpl.
//     */
//    @Test
//    public void testGetAllStaticPage() {
//        dao.addStaticPage(c1);
//        dao.addStaticPage(c2);
//        dao.addStaticPage(c3);
//
//        List<StaticPage> cList = dao.getAllStaticPages();
//        assertEquals(cList.size(), 3);
//
//    }
//
//    /**
//     * Test of searchStaticPage method, of class StaticPageDbDaoImpl.
//     */
//    @Test
//    public void testSearchStaticPage() {
//        dao.addStaticPage(c1);
//        dao.addStaticPage(c2);
//        dao.addStaticPage(c3);
//
//        Map<SearchTerm, String> criteria = new HashMap<>();
//        criteria.put(SearchTerm.TITLE, "News");
//        List<StaticPage> cList = dao.searchStaticPage(criteria);
//        assertEquals(2, cList.size());
//
//        //this next
//        criteria = new HashMap<>();
//        criteria.put(SearchTerm.STATUS, "DRAFT");
//        cList = dao.searchStaticPage(criteria);
//        assertEquals(1, cList.size());
//
//        criteria = new HashMap<>();
//        criteria.put(SearchTerm.STATUS, "PUBLISHED");
//        cList = dao.searchStaticPage(criteria);
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
//        dao.addStaticPage(c4);
//        dao.addStaticPage(c5);
//        dao.addStaticPage(c6);
//
//        List<StaticPage> sameTitle = dao.getStaticPageByTitle(c4.getTitle());
//        Assert.assertEquals(3, sameTitle.size());
//
//        Assert.assertEquals(c4, dao.getStaticPageByTitleNumber(c4.getTitleNumber()));
//        Assert.assertEquals(c5, dao.getStaticPageByTitleNumber(c5.getTitleNumber()));
//        Assert.assertEquals(c6, dao.getStaticPageByTitleNumber(c6.getTitleNumber()));
//
//        Assert.assertNotSame(c5, dao.getStaticPageByTitleNumber(c6.getTitleNumber()));
//    }
//
//}
