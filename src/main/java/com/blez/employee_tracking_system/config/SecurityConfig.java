package com.blez.employee_tracking_system.config;

import com.blez.employee_tracking_system.service.CustomOAuth2UserService;
import com.blez.employee_tracking_system.service.RoleService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;
import java.util.stream.Collectors;

@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class SecurityConfig {
    private final CustomOAuth2UserService customOAuth2UserService;
    private final RoleService roleService;


    public SecurityConfig(CustomOAuth2UserService customOAuth2UserService, RoleService roleService) {
        this.customOAuth2UserService = customOAuth2UserService;
        this.roleService = roleService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        System.out.println("Configuring http filterChain");

        http
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/hello").permitAll()
                        .requestMatchers("/api/hello").hasRole("ADMIN")
                        .requestMatchers("/departments/**", "/projects/**").hasAnyRole("ADMIN", "MANAGER")
                        .requestMatchers("/employees/**").hasAnyRole("ADMIN", "MANAGER", "EMPLOYEE")
                        .anyRequest().authenticated()
                )
                .oauth2Login(oauth2 -> oauth2
                        .userInfoEndpoint(userInfo -> userInfo
                                .userService(customOAuth2UserService)
                        )
                        .defaultSuccessUrl("/", true)
                        .failureUrl("/login?error")
                )
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter()))
                )
                .csrf(csrf -> csrf.disable()); // Disable CSRF if not needed

        return http.build();
    }

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();


        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwt -> {

            String email = jwt.getClaimAsString("email");
            if (email == null) {
                throw new RuntimeException("Email claim is missing in the JWT.");
            }


            List<String> roles = roleService.getRolesByEmail(email);

            if (roles == null || roles.isEmpty()) {
                throw new RuntimeException("No roles found for user: " + email);
            }

            return roles.stream()
                    .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                    .collect(Collectors.toList());
        });

        return jwtAuthenticationConverter;
    }


}
