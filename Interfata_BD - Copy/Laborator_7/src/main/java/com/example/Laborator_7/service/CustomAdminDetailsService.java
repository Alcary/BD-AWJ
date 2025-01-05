package com.example.Laborator_7.service;

import com.example.Laborator_7.dao.AdminDAO;
import com.example.Laborator_7.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomAdminDetailsService implements UserDetailsService {

    @Autowired
    private AdminDAO adminDAO;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin = adminDAO.findByUsername(username);
        if (admin == null) {
            throw new UsernameNotFoundException("Admin not found");
        }

        return new User(admin.getUsername(), admin.getParola(),
                Collections.singletonList(new SimpleGrantedAuthority(admin.getRol())));
    }
}