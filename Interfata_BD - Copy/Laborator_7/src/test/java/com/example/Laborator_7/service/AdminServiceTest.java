/** Test pentru serviciul AdminService
 * @author Calaras Alexandru
 * @version 11 Ianuarie 2025
 */
package com.example.Laborator_7.service;

import com.example.Laborator_7.entity.Admin;
import com.example.Laborator_7.dao.AdminDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AdminServiceTest {

    //Creeaza un inlocuitor al clasei AdminDAO
    @Mock
    private AdminDAO adminDAO;

    @InjectMocks
    private AdminService adminService;

    //Metoda de initializare ce configureaza inlocuitorii si obiectul testat
    //inainte de fiecare test
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    //Testeaza metoda FindByUsername
    @Test
    public void testFindByUsername() {
        //Creeaza un obiect de tip Admin
        Admin admin = new Admin();
        admin.setUsername("Test");

        //Cand se apeleaza metoda findByUsername, returneaza obiectul creat anterior
        when(adminDAO.findByUsername("Test")).thenReturn(admin);

        //Apeleaza metoda findByUsername din AdminService
        Admin foundAdmin = adminService.findByUsername("Test");

        //Verifica daca obiectul returnat este cel asteptat
        assertNotNull(foundAdmin);
        assertEquals("Test", foundAdmin.getUsername());
    }
}