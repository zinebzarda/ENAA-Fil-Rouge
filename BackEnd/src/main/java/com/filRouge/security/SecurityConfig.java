package com.filRouge.security;

import com.filRouge.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.MergedAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.http.HttpMethod.*;
import static org.springframework.security.config.Customizer.withDefaults;
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;

    public SecurityConfig(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .cors(withDefaults())
                .authorizeHttpRequests(expressionInterceptUrlRegistry ->
                                expressionInterceptUrlRegistry
                                        .requestMatchers("/auth/**").permitAll()
                                        .requestMatchers("/clients/inscription").permitAll()
                                        .requestMatchers(PUT,"/clients/**").hasRole("CLIENT")
                                        .requestMatchers("/clients/allClients").hasRole("ADMIN")
                                        .requestMatchers(DELETE,"/clients/**").hasRole("ADMIN")


                                        .requestMatchers(POST,"/services/**").hasRole("PRESTATAIRE")
                                        .requestMatchers("/services/all").hasAnyRole("ADMIN", "CLIENT")
                                        .requestMatchers(PUT,"/services/**").hasAnyRole("ADMIN","PRESTATAIRE")
                                        .requestMatchers(DELETE,"/services/**").hasAnyRole("ADMIN", "PRESTATAIRE")
                                        .requestMatchers("/services/search").hasRole("CLIENT")



                                        .requestMatchers(POST,"/feedback/**").hasRole("CLIENT")
                                        .requestMatchers(GET,"/feedback/**").hasRole("ADMIN")
                                        .requestMatchers(DELETE,"/feedback/**").hasRole("ADMIN")


                                        .requestMatchers(POST,"/contacts/**").permitAll()
                                        .requestMatchers(GET,"/contacts/**").hasRole("ADMIN")
                                        .requestMatchers(DELETE,"/contacts/**").hasRole("ADMIN")


                                        .requestMatchers("/prestataires/inscription").permitAll()
                                        .requestMatchers(PUT,"/prestataires/**").hasRole("ADMIN")
                                        .requestMatchers(DELETE,"/prestataires/**").hasRole("ADMIN")
                                        .requestMatchers(GET,"/prestataires/**").hasRole("ADMIN")



//                                // Client demande endpoints
//                                .requestMatchers(POST, "/client/demandes").hasRole("CLIENT")
//                                .requestMatchers(GET, "/client/demandes").hasRole("CLIENT")
//                                .requestMatchers(PUT, "/client/demandes/*/cancel").hasRole("CLIENT")
//                                .requestMatchers(POST, "/client/demandes/*/feedback").hasRole("CLIENT")
//                                .requestMatchers(POST, "/client/demandes/contact-support").hasRole("CLIENT")
//
//                                // Prestataire demande endpoints
//                                .requestMatchers(GET, "/prestataire/demandes").hasRole("PRESTATAIRE")
//                                .requestMatchers(PUT, "/prestataire/demandes/*/accept").hasRole("PRESTATAIRE")
//                                .requestMatchers(PUT, "/prestataire/demandes/*/refuse").hasRole("PRESTATAIRE")
//
//                                // Admin demande endpoints
//                                .requestMatchers(GET, "/admin/demandes").hasRole("ADMIN")
//                                .requestMatchers(PUT, "/admin/demandes/*/confirm").hasRole("ADMIN")
//                                .requestMatchers(DELETE, "/admin/demandes/*").hasRole("ADMIN")
//                                .requestMatchers(PUT, "/admin/demandes/*").hasRole("ADMIN")



                                        .anyRequest().authenticated()
                )
                .formLogin(formLogin -> formLogin.disable());
        http.addFilterBefore(new JwtAuthorizationFilter(customUserDetailsService), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
        return authenticationManagerBuilder.build();
    }
}