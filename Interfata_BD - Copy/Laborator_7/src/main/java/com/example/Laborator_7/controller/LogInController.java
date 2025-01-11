/**Este un controller pentru pagina de Log In
 * @author Calaras Alexandru
 * @version 9 Ianuarie 2025
 */
package com.example.Laborator_7.controller;

    import com.example.Laborator_7.service.AdminService;
    import com.example.Laborator_7.entity.Admin;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Controller;
    import org.springframework.ui.Model;
    import org.springframework.web.bind.annotation.*;

    @Controller
    @RequestMapping("/login")
    public class LogInController {

        @Autowired
        private AdminService adminService;

        @GetMapping
        public String showLoginPage(@RequestParam(value = "error", required = false) String error, Model model) {
            if (error != null){
                model.addAttribute("errorMessage", "Credențialele introduse sunt incorecte");
            }
            return "login";
        }

        @PostMapping
        public String checkAdminCredentials(@RequestParam("username") String username, @RequestParam("password") String password, Model model) {
            Admin admin = adminService.findByUsername(username);

            if(admin != null && admin.getParola().equals(password)) {
                return "redirect:/index";
            }
            else{
                model.addAttribute("errorMessage", "Credențialele introduse sunt incorecte!");
                return "login";
            }
        }
    }
