/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.cms.dao;

import com.tsg.cms.dto.Category;
import java.util.List;
import java.util.Map;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author apprentice
 */
public class CategoryDAOImplTest {
    
    private CategoryDAO Dao;
    private Category C1;
    private Category C2;
    private Category C3;
    int P1, P2, P3;
    
    public CategoryDAOImplTest() {
    }
    
    @Before
    public void setUp() {
        
        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        Dao = ctx.getBean("CategoryDAO", CategoryDAO.class);
        
        JdbcTemplate cleaner = (JdbcTemplate) ctx.getBean("jdbcTemplate");
        cleaner.execute("delete from categories");
        P1 = 1111;
        C1 = new Category();
        C1.setCategoryName("category1");
        
        P2 = 2222;
        C2 = new Category();
        C2.setCategoryName("category2");
        
        P3 = 3333;
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
        
        C1 = Dao.addCategory(C1, P1);
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
        
        Dao.addCategory(C2, P2);
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
        
        C3 = Dao.addCategory(C3, P3);
        
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
        
        Dao.addCategory(C1, P1);
        Dao.addCategory(C2, P2);
        Dao.addCategory(C3, P3);
        
        List<Category> cList = Dao.getAllCategories();
        assertEquals(cList.size(), 3);
        
    }

    /**
     *
     * Test of searchCategory method, of class CategoryDAOImpl.
     */
    @Test
    public void testSearchCategory() {
        
        Dao.addCategory(C1, P1);
        Dao.addCategory(C2, P2);
        Dao.addCategory(C3, P3);
        
        
        
    }
    
}
