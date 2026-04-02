package com.janaprasath.scholarstack.security;

import com.janaprasath.scholarstack.util.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        final String header = request.getHeader("Authorization");
        final String token;
        final String email;

        // 1. Check if the header exists and starts with Bearer
        if (header == null || !header.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        token = header.substring(7);

        try {

            email = jwtUtils.extractEmail(token);
            System.out.println("DEBUG: Extracted Email from Token: " + email);

            if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        email,
                        null,
                        new ArrayList<>()
                );

                // 4. Link the request details to the token
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // 5. Tell Spring Security: "This user is valid"
                SecurityContextHolder.getContext().setAuthentication(authToken);
                System.out.println("DEBUG: SecurityContext successfully set for: " + email);
            }
        } catch (Exception e) {
            System.out.println("DEBUG: JWT Validation failed: " + e.getMessage());
        }

        filterChain.doFilter(request, response);
    }
}