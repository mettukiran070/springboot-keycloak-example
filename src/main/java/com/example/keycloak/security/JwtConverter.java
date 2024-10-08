package com.example.keycloak.security;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimNames;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.stereotype.Component;

/**
 * the jwt token has configuration object, which contains all the predefined configurations
 * within this default configuration, there is an object/ class which converts, which converts the
 * incoming jwt/ acces token to Spring boot specific format. that cnverter is JwtAutheticationConverter
 */

@Component
public class JwtConverter implements Converter<Jwt, AbstractAuthenticationToken> {

  private final JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();

  private final JwtConverterProperties properties;

  public JwtConverter(JwtConverterProperties properties) {
    this.properties = properties;
  }

  @Override
  public AbstractAuthenticationToken convert(Jwt source) {
    Collection<GrantedAuthority> authorities = Stream.concat(
        jwtGrantedAuthoritiesConverter.convert(source).stream(),
        extractResourceRoles(source).stream()).collect(Collectors.toSet());
    return new JwtAuthenticationToken(source, authorities, getPrincipalClaimName(source));
  }

  private String getPrincipalClaimName(Jwt jwt) {
    String claimName = JwtClaimNames.SUB;
    if (properties.getPrincipalAttribute() != null) {
      claimName = properties.getPrincipalAttribute();
    }
    return jwt.getClaim(claimName);
  }

  private Collection<? extends GrantedAuthority> extractResourceRoles(Jwt jwt) {
    Map<String, Object> resourceAccess = jwt.getClaim("resource_access");
    Map<String, Object> resource;
    Collection<String> resourceRoles;

    if (resourceAccess == null
        || (resource = (Map<String, Object>) resourceAccess.get(properties.getResourceId())) == null
        || (resourceRoles = (Collection<String>) resource.get("roles")) == null) {
      return Set.of();
    }
    return resourceRoles.stream()
        .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
        .collect(Collectors.toSet());
  }
}
