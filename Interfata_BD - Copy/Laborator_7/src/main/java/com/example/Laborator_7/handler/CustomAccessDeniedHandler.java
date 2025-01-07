//Handler personalizat pentru gestionarea cazurilor in care accesul este refuzat
package com.example.Laborator_7.handler;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAccessDeniedHandler implements AccessDeniedHandler, AuthenticationFailureHandler {

    //Suprascrie metoda handle pentru a personaliza raspunsul in cazul unui AccessDeniedException
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException, ServletException {
        //Obtine referinta URL-ului anterior din header-ul cererii
        String referer = request.getHeader("Referer");

        //Daca URL-ul anterior exista, redirectioneaza utilizatorul inapoi
        if (referer != null) {
            response.sendRedirect(referer);
        } else {
            //Daca URL-ul anterior nu este disponibil, redirectioneaza utilizatorul la pagina principala
            response.sendRedirect("/");
        }
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        response.sendRedirect("/login?error=true");
    }
}