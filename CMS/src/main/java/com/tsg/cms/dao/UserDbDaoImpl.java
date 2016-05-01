
/*
 * /
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 /*
 * /
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.cms.dao;

import com.tsg.cms.dto.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author apprentice
 */
public class UserDbDaoImpl implements UserDbDao {

    private static final String SQL_INSERT_USER
            = "insert into users (username, password, enabled) value (?,?,?)";
    private static final String SQL_DELETE_USER
            = "delete from users where user_Id = ?";
    private static final String SQL_UPDATE_USER
            = "update users set username = ?, password = ?, enabled = ? where user_Id = ?";
    private static final String SQL_SELECT_ALL_USERS
            = "select * from users";
    private static final String SQL_SELECT_USER_ROLES
            = "select users.*, authorities.authority from users join authorities on authorities.username = users.username where user_Id = ?";
    private static final String SQL_SELECT_USER
            = "select * from users where user_Id = ?";
    private JdbcTemplate jdbcTemplate;

    @Override
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    @Override
    public User addUser(User user) {
        jdbcTemplate.update(SQL_INSERT_USER,
                            user.getUserName(),
                            user.getPassword(),
                            1);
        user.setUserId(jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class));
        return user;
    }

    @Override
    public void removeUser(int userId) {
        jdbcTemplate.update(SQL_DELETE_USER, userId);
    }

    @Override
    public void updateUser(User user) {
        jdbcTemplate.update(SQL_UPDATE_USER,
                            user.getUserName(),
                            user.getPassword(),
                            1,
                            user.getUserId());
    }

    @Override
    public List<User> getAllUsers() {
        return jdbcTemplate.query(SQL_SELECT_ALL_USERS, new UserMapper());
    }

    @Override
    public User getUserId(int userId) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_USER, new UserMapper(), userId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<String> getRolesForUser(int userId) {

        List<User> users = jdbcTemplate.query(SQL_SELECT_USER_ROLES, new UserMapper(), userId);

        List<String> roles = new ArrayList<>();
        
        for (User u : users) {
            
            roles.add(u.getSiteRole());
        }
        
        return roles;
    }

//    public List<User> searchUsers(Map<SearchTerm, String> criteria) {
//        if (criteria.size() == 0) {
//            return getAllUsers();
//        } else {
//            StringBuilder sQuery = new StringBuilder("select * from user where ");
//            int numParams = criteria.size();
//            int paramPosition = 0;
//            String[] paramVals = new String[numParams];
//            Set<SearchTerm> iter = criteria.keySet();
//            Iterator<SearchTerm> iter = keySet.iterator();
//
//            while (iter.hasNext()) {
//                SearchTerm currentKey = iter.next();
//
//                if (paramPosition > 0) {
//                    sQuery.append(" and ");
//                }
//
//                sQuery.append(currentKey);
//                sQuery.append(" = ? ");
//                paramVals[paramPosition] = criteria.get(currentKey);
//                paramPosition++;
//            }
//            return jdbcTemplate.query(sQuery.toString(), new UserMapper(), paramVals);
//        }
//    }
    private static final class UserMapper implements RowMapper<User> {

        @Override
        public User mapRow(ResultSet rs, int i) throws SQLException {
            User user = new User();
            user.setUserId(rs.getInt("user_Id"));
            user.setPassword(rs.getString("password"));

            try {
                user.setSiteRole(rs.getString("authority"));

            } catch (SQLException e) {

            }
            user.setUserName(rs.getString("username"));

            return user;
        }

    }
}
