package com.example.minhm80.configuration;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http
                .sessionManagement(menagement -> menagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(Authorize ->
                        Authorize
//                                .requestMatchers("/api/super-admin/**").hasRole("ADMIN")
//                                .requestMatchers("/api/**").authenticated()
//                                .anyRequest().permitAll()
                                .requestMatchers(
                                        "/api/auth/**"


                                ).permitAll()

                                // 2️⃣ Role-based (QUYỀN CAO hơn đặt TRƯỚC)
                                .requestMatchers("/api/super-admin/**").hasRole("ADMIN")

                                // 3️⃣ Các API còn lại cần đăng nhập
                                .requestMatchers("/api/**").authenticated()

                                // 4️⃣ Ngoài API thì cho phép hết
                                .anyRequest().permitAll()
                ).addFilterBefore(new JwtValidator(),
                        BasicAuthenticationFilter.class)
                .csrf(AbstractHttpConfigurer::disable)
                .cors(
                        cors->cors.configurationSource(corsConfigurationSource())
                ).build();



    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }




private CorsConfigurationSource corsConfigurationSource(){
    return  new CorsConfigurationSource() {
        @Override
        public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
            CorsConfiguration cfg=new CorsConfiguration();
            cfg.setAllowedOrigins(
                    Arrays.asList(
                            "http://localhost:5173",
                            "http://localhost:3000"
                    )
            );
            cfg.setAllowedMethods(Collections.singletonList("*"));
            cfg.setAllowCredentials(true);
            cfg.setAllowedHeaders(Collections.singletonList("*"));
            cfg.setExposedHeaders(Arrays.asList("Authorization"));
            cfg.setMaxAge(3600L);
            return cfg;



        }
    };

}

}