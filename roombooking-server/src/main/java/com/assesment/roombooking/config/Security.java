//package com.assesment.roombooking.config;
//
//import com.assesment.roombooking.Filter.AuthEntryPointJwt;
//import com.assesment.roombooking.Filter.JwtAuthFilter;
//import jakarta.servlet.Filter;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//
//
//@Configuration
//@EnableMethodSecurity
//public class Security{
//
//    @Autowired
//    private AuthEntryPointJwt unauthorizedHandler;
//
//    @Bean
//    public Filter authenticationJwtTokenFilter() {
//        return new JwtAuthFilter();
//    }
//
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .cors(cors -> cors.disable())
//                .csrf(csrf -> csrf.disable())
//                .exceptionHandling(exception -> exception.authenticationEntryPoint(unauthorizedHandler))
//                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers(new AntPathRequestMatcher("/auth/api/login")).permitAll()
//                );
//
//        return http.build();
//    }

//}