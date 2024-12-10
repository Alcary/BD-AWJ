package com.example.Laborator_7.dao;

import com.example.Laborator_7.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class AdminDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Admin> getAllAdmins() {
        String sql = "SELECT * FROM admini";
        return jdbcTemplate.query(sql, new AdminiRowMapper());
    }

    public int saveAdmin(Admin admin) {
        String sql = "INSERT INTO admini (username, parola) VALUES (?, ?)";

        return jdbcTemplate.update(sql,
                admin.getUsername(),
                admin.getParola());
    }

    public Admin findByUsername(String username) {
        String sql = "SELECT * FROM admini WHERE username = ?";
        return jdbcTemplate.queryForObject(sql, new AdminiRowMapper(), username);
    }

    private static class AdminiRowMapper implements RowMapper<Admin> {
        @Override
        public Admin mapRow(ResultSet rs, int rowNum) throws SQLException {
            Admin admin = new Admin();
            admin.setIdAdmin(rs.getInt("id_admin"));
            admin.setUsername(rs.getString("username"));
            admin.setParola(rs.getString("parola"));
            return admin;
        }
    }
}