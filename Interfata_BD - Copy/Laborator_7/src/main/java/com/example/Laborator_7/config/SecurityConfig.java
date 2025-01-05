package com.example.Laborator_7.config;

import com.example.Laborator_7.handler.CustomAccessDeniedHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/login").permitAll()
                        .requestMatchers(
                                "/pacienti/edit/**", "/pacienti/delete/**", "/pacienti/new",
                                "/medicamente/edit/**", "/medicamente/delete/**", "/medicamente/new",
                                "/medici/edit/**", "/medici/delete/**", "/medici/new",
                                "/spitale/edit/**", "/spitale/delete/**", "/spitale/new",
                                "/companii_farmaceutice/edit/**", "/companii_farmaceutice/delete/**", "/companii_farmaceutice/new",
                                "/boli_asociate/edit/**", "/boli_asociate/delete/**", "/boli_asociate/new",
                                "/apartinatori/edit/**", "/apartinatori/delete/**", "/apartinatori/new"
                        ).hasRole("ADMIN")
                        .requestMatchers("/pacienti", "/medicamente", "apartinatori",
                                "/medici", "/spitale", "/companii_farmaceutice", "/boli_asociate").hasAnyRole("USER", "ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/")
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login")
                        .permitAll()
                )
                .exceptionHandling(exception -> exception
                        .accessDeniedHandler(new CustomAccessDeniedHandler())
                );
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
