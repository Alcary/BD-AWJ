package com.example.Laborator_7.service;

import com.example.Laborator_7.dao.AdminDAO;
import com.example.Laborator_7.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    private AdminDAO adminDAO;

    public List<Admin> getAllAdmins() {
        return adminDAO.getAllAdmins();
    }

    public Admin findByUsername(String username) {
        return adminDAO.findByUsername(username);
    }

    public int saveAdmin(Admin admin) {
        return adminDAO.saveAdmin(admin);
    }

}
