package com.smart.dao;

import com.smart.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class UserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final static String UPDATE_LOGIN_INFO_SQL = " UPDATE t_user SET last_visit = ?, last_ip = ?, credits = ? WHERE user_id = ?";

    public int getMatchCount(String userName, String password){
        String sqlStr = " SELECT count(*) FROM t_user where user_name = ? and password= ?";
        return jdbcTemplate.queryForObject(sqlStr, new Object[]{userName, password}, Integer.class);
    }

    public User findUserByUserName(final String userName){
        final  User user = new User();
        jdbcTemplate.query("SELECT user_id, user_name, credits FROM t_user WHERE user_name = ?", new Object[]{userName}, new RowCallbackHandler() {
            public void processRow(ResultSet rs) throws SQLException {
                user.setUserId(rs.getInt("user_id"));
                user.setUserName(userName);
                user.setCredits(rs.getInt("credits"));
            }
        });
        return user;
    }

    public void updateLoginInfo(User user){
        jdbcTemplate.update(UPDATE_LOGIN_INFO_SQL, new Object[]{user.getLastVisit(), user.getLastIp(), user.getCredits(), user.getUserId()});
    }

}
