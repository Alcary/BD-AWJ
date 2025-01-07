//Configureaza securitatea aplicatiei
//Defineste reglile de autorizare pentru rutele aplicatiei
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
                //Dezactiveaza protectia CSRF
                .csrf(csrf -> csrf.disable())
                //Configureaza regulile de autorizare pentru rute
                .authorizeHttpRequests(authorize -> authorize
                        //Permite accesul la pagina de login tuturor
                        .requestMatchers("/login").permitAll()
                        //Permite accesul la următoarele rute doar utilizatorilor cu rolul de ADMIN
                        .requestMatchers(
                                "/pacienti/edit/**", "/pacienti/delete/**", "/pacienti/new",
                                "/medicamente/edit/**", "/medicamente/delete/**", "/medicamente/new",
                                "/medici/edit/**", "/medici/delete/**", "/medici/new",
                                "/spitale/edit/**", "/spitale/delete/**", "/spitale/new",
                                "/companii_farmaceutice/edit/**", "/companii_farmaceutice/delete/**", "/companii_farmaceutice/new",
                                "/boli_asociate/edit/**", "/boli_asociate/delete/**", "/boli_asociate/new",
                                "/apartinatori/edit/**", "/apartinatori/delete/**", "/apartinatori/new"
                        ).hasRole("ADMIN")
                        //Permite accesul la următoarele pagini utilizatorilor cu orice rol
                        .requestMatchers("/pacienti", "/medicamente", "apartinatori",
                                "/medici", "/spitale", "/companii_farmaceutice", "/boli_asociate").hasAnyRole("USER", "ADMIN")
                        //Toate celelalte cereri trebuie să fie autentificate
                        .anyRequest().authenticated()
                )
                //Configurarea login-ului
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/")
                        .permitAll()
                )
                // Configurarea logout-ului
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login")
                        .permitAll()
                )
                //Gestionarea exceptiilor pentru accesul refuzat
                .exceptionHandling(exception -> exception
                        .accessDeniedHandler(new CustomAccessDeniedHandler())
                );
        return http.build();
    }

    //Folosește un encoder de parole care nu face hashing pe parola
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
