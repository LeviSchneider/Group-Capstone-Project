/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.cms;

import com.tsg.cms.dao.UserDbDao;
import com.tsg.cms.dto.User;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author apprentice
 */
@Controller
public class UserController {

    private final UserDbDao userDbDao;

    @Inject
    public UserController(UserDbDao userDbDao) {

        this.userDbDao = userDbDao;

    }
    
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    @ResponseBody
    public List<User> getAllUsers() {

        return userDbDao.getAllUsers();

    }
    
    @RequestMapping(value = "/userRoles/{userId}", method = RequestMethod.GET)
    @ResponseBody
    public List<String> getAllUsers(@PathVariable("userId") int userId) {

        return userDbDao.getRolesForUser(userId);

    }    
}
