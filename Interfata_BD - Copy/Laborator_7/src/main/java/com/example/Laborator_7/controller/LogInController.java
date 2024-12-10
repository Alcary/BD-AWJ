    package com.example.Laborator_7.controller;

    import com.example.Laborator_7.entity.Apartinator;
    import com.example.Laborator_7.service.AdminService;
    import com.example.Laborator_7.entity.Admin;
    import com.example.Laborator_7.service.ApartinatorService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Controller;
    import org.springframework.ui.Model;
    import org.springframework.web.bind.annotation.*;

    import java.util.List;

    @Controller
    @RequestMapping("/login")
    public class LogInController {

        @Autowired
        private AdminService adminService;

        @GetMapping
        public String showLoginPage() {
            return "login";
        }

        @PostMapping
        public String checkAdminCredentials(@RequestParam("username") String username, @RequestParam("password") String password, Model model) {
            Admin admin = adminService.findByUsername(username);

            if(admin != null && admin.getParola().equals(password)) {
                return "redirect:/admin";
            }
            else{
                model.addAttribute("error", "Credențialele introduse sunt incorecte");
                return "login";
            }
        }
    }
