package com.example.keycloak.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {

  public static final String ADMIN = "admin";
  public static final String USER = "user";
  private final JwtConverter jwtConverter;
  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.csrf(AbstractHttpConfigurer::disable);
    http.authorizeHttpRequests((auth) ->
//        auth.requestMatchers(HttpMethod.GET, "/api/hello").permitAll()
//            .requestMatchers(HttpMethod.GET, "/api/admin/**").hasRole(ADMIN)
//            .requestMatchers(HttpMethod.GET, "/api/user/**").hasRole(USER)
            auth.requestMatchers("/products/**").hasAnyRole(ADMIN,USER)
                .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html", "/swagger-resources/**", "/webjars/**").permitAll()
            .anyRequest().authenticated());

    http.sessionManagement(sess -> sess.sessionCreationPolicy(
        SessionCreationPolicy.STATELESS));
    // the below is for jwt token validation with in the resource server
    http.oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt -> jwt.jwtAuthenticationConverter(jwtConverter)));
//    http.oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()));

    // for opaque token validation refer the below code snippet
//    http.oauth2ResourceServer(oauth2 -> oauth2.opaqueToken(Customizer.withDefaults()));

    return http.build();
  }

}
