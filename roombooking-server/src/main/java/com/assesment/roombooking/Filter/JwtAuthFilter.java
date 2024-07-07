//package com.assesment.roombooking.Filter;
//
//import com.assesment.roombooking.util.JwtService;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//
//public class JwtAuthFilter extends OncePerRequestFilter {
//
//    @Autowired
//    private JwtService jwtService;
//
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        String authHeader = request.getHeader("Authorization");
//        String requestURI = request.getRequestURI();
//
//        try {
//            if (authHeader != null && authHeader.startsWith("Bearer ")) {
//                String jwt = authHeader.substring(7);
//                if (jwtService.validateToken(jwt)) {
//                    filterChain.doFilter(request, response);
//                } else {
//                    logger.warn("Invalid JWT token");
//                }
//            }
//        } catch (Exception e) {
//            logger.error("Cannot set user authentication: {}", e);
//        }
//
//
//    }
//}
//
//
