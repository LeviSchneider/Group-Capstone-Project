
/*/
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
///*/
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.tsg.cms.dao;
//
//import com.tsg.cms.dto.User;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//import org.springframework.dao.EmptyResultDataAccessException;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.RowMapper;
//import org.springframework.transaction.annotation.Propagation;
//import org.springframework.transaction.annotation.Transactional;
//
///**
// *
// * @author apprentice
// */
//public class UserDbDao {
//
//    private static final String SQL_INSERT_USER
//            = "insert into users (displayName, passwordSalt, passwordHash, siteRole, username) value (?,?,?,?,?)";
//    private static final String SQL_DELETE_USER
//            = "delete from users where userId = ?";
//    private static final String SQL_UPDATE_USER
//            = "update users set displayName = ?, passwordSalt = ?, passwordHash = ?, siteRole = ?, username = ?";
//    private static final String SQL_SELECT_ALL_USERS
//            = "select * from users";
//    private static final String SQL_SELECT_USER
//            = "select * from users where userId = ?";
//    private JdbcTemplate jdbcTemplate;
//
//    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
//    public User addUser(User user) {
//        jdbcTemplate.update(SQL_INSERT_USER,
//                user.getDisplayName(),
//                user.getPasswordSalt(),
//                user.getPasswordHash(),
//                user.getSiteRole(),
//                user.getUserName());
//        user.setUserId(jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class));
//        return user;
//    }
//    
//    public void removeUser(int userId) {
//        jdbcTemplate.update(SQL_DELETE_USER, userId);
//    }
//    
//    public void updateUser(User user) {
//        jdbcTemplate.update(SQL_UPDATE_USER,
//                user.getDisplayName(),
//                user.getPasswordSalt(),
//                user.getPasswordHash(),
//                user.getSiteRole(),
//                user.getUserName(),
//                user.getUserId());
//    }
//
//    public List<User> getAllUsers() {
//        return jdbcTemplate.query(SQL_SELECT_ALL_USERS, new UserMapper());
//    }
//
//    public User getUserId(int userId) {
//        try {
//            return jdbcTemplate.queryForObject(SQL_SELECT_USER, new UserMapper(), userId);
//        } catch (EmptyResultDataAccessException e) {
//            return null;
//        }
//    }
//
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
//
//    private static final class UserMapper implements RowMapper<User>{
//
//        @Override
//        public User mapRow(ResultSet rs, int i) throws SQLException {
//            User user = new User();
//            user.setUserId(rs.getInt("userId"));
//            user.setDisplayName(rs.getString("displayName"));
//            user.setPasswordSalt(rs.getString("passwordSalt"));
//            user.setPasswordHash(rs.getString("passwordHash"));
//            user.setSiteRole(rs.getString("siteRole"));
//            user.setUserName(rs.getString("username"));
//            
//            return user;
//        }
//    
//}
//}
