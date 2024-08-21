package com.example.springbootandjdbc.repository;

import com.example.springbootandjdbc.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserManager {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void addUser(User user) {
        String sql = "INSERT INTO users (name, email) VALUES (?, ?)";
        jdbcTemplate.update(sql, user.getName(), user.getEmail());
        System.out.println("User added successfully!");
    }

    private static class UserRowMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setId(rs.getInt("user_id"));
            user.setName(rs.getString("name"));
            user.setEmail(rs.getString("email"));
            return user;
        }
    }

    public List<User> findAllUsers() {
        String sql = "SELECT * FROM users";
        return jdbcTemplate.query(sql, new UserRowMapper());
    }

    public User getUserById(int id) {
        String sql = "SELECT * FROM users WHERE user_id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new UserRowMapper());
    }


    public User updateUserEmail(int id, String newEmail) {
        String sql = "UPDATE users SET email = ? WHERE user_id = ?";
        jdbcTemplate.update(sql, newEmail, id);
        return getUserById(id);
    }


    public User deleteUserById(int id) {
        User user = getUserById(id);
        String sql = "DELETE FROM users WHERE user_id = ?";
        jdbcTemplate.update(sql, id);
        return user;
    }

    public List<User> addUsers(List<User> users) {
        List<User> users1 = users;

        String sql = "INSERT INTO users (user_id, name, email) VALUES (?, ?, ?)";

        jdbcTemplate.batchUpdate(sql, users, users.size(), (ps, user) -> {
            ps.setLong(1, user.getId());
            ps.setString(2, user.getName());
            ps.setString(3, user.getEmail());
        });
        return users1;
    }

    public List<User> updateUserEmails(List<User> users) {

        List<User> users1 = users;


        String sql = "UPDATE users SET email = ? WHERE user_id = ?";
        jdbcTemplate.batchUpdate(sql, users, users.size(), (ps, user) -> {
            ps.setString(1, user.getEmail());
            ps.setLong(2, user.getId());
        });
        return users1;
    }

    public List<User> findByEmailDomain(String domain) {
        String sql = "SELECT * FROM users WHERE email LIKE ?";
        return jdbcTemplate.query(sql, new Object[]{"%" + domain}, new UserRowMapper());
    }

    public int getUserCount() {
        String sql = "SELECT COUNT(user_id) FROM users";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    public List<User> findUsersByFirstChar(char firstChar) {
        String sql = "SELECT * FROM users WHERE name LIKE ?";
        return jdbcTemplate.query(sql, new Object[]{firstChar + "%"}, new UserRowMapper());
    }

}
