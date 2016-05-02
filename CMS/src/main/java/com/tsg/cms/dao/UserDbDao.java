/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.cms.dao;

import com.tsg.cms.dto.User;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author apprentice
 */
public interface UserDbDao {

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    User addUser(User user);

    List<User> getAllUsers();

    User getUserId(int userId);

    void removeUser(int userId);

    void setJdbcTemplate(JdbcTemplate jdbcTemplate);

    void updateUser(User user);

    public List<String> getRolesForUser(int userId);
    
}
