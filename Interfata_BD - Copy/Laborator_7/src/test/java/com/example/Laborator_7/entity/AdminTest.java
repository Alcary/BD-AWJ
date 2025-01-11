/** Test pentru entitatea Admin
 * @author Calaras Alexandru
 * @version 11 Ianuarie 2025
 */
package com.example.Laborator_7.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AdminTest{

    @Test
    public void testAdmin() {
        //Creare obiect de tip Admin
        Admin admin = new Admin();
        admin.setUsername("testUser");
        admin.setParola("testPassword");
        admin.setRol("ROLE_ADMIN");

        //Testare getteri
        assertEquals("testUser", admin.getUsername());
        assertEquals("testPassword", admin.getParola());
        assertEquals("ROLE_ADMIN", admin.getRol());
    }
}