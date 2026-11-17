package com.uma.security.api.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static com.uma.security.api.Constants.*;

@Slf4j
public class RoleBasedAuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var requestInitiator = request.getHeader(X_REQUEST_INITIATOR);

        if (StringUtils.hasText(requestInitiator)) {
            log.info("Request initiator: {}", requestInitiator);

            if (DXP_GATEWAY.equals(requestInitiator)) {
                var roles = Optional.ofNullable(request.getHeader(X_AUTH_ROLES));
                addAuthoritiesAndDetails(getAutorities(roles.orElse(EMPTY)), extractUserDetails(request));
            } else if (DXP.equalsIgnoreCase(requestInitiator)) {
                var username = SecurityContextHolder.getContext().getAuthentication().getName();
                addAuthoritiesAndDetails(getAutorities(TME_ADMIN_USERS), Map.of("userid", username + "@umarica.com"));
            } else if (DPA.equalsIgnoreCase(requestInitiator)) {
                addAuthoritiesAndDetails(getAutorities(DPA_API_USERS), Collections.emptyMap());
            }
        }
        filterChain.doFilter(request, response);
    }

    private Map<String, String> extractUserDetails(HttpServletRequest request) {
        var userDetails = new HashMap<String, String>();
        userDetails.put("username", request.getHeader(X_AUTH_USERNAME));
        userDetails.put("userid", request.getHeader(X_AUTH_USERID));
        userDetails.put("email", request.getHeader(X_AUTH_EMAIL));
        userDetails.put("roles", request.getHeader(X_AUTH_ROLES));

        return userDetails;
    }

    private void addAuthoritiesAndDetails(Set<SimpleGrantedAuthority> authorities, Map<String, String> userDetails) {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var authWithAuthorities = new UsernamePasswordAuthenticationToken(authentication.getPrincipal(),
                                                                            authentication.getCredentials(), authorities);
        authWithAuthorities.setDetails(userDetails);
        SecurityContextHolder.getContext().setAuthentication(authWithAuthorities);
    }

    private Set<SimpleGrantedAuthority> getAutorities(String roles) {
        return Arrays.stream(roles.split(","))
                .map(role -> new SimpleGrantedAuthority(ROLE_PREFIX + role))
                .collect(Collectors.toSet());
    }
}
