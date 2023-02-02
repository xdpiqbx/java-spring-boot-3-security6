package com.dpiqb.security.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.http.HttpHeaders;

import java.io.IOException;


@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private final JwtService jwtService;
  @Override
  protected void doFilterInternal(
    @NonNull HttpServletRequest request,
    @NonNull HttpServletResponse response,
    @NonNull FilterChain filterChain)
    throws ServletException, IOException {
    final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
    final String jwt;
    final String userEmail;
    if(authHeader == null || !authHeader.startsWith("Bearer ")){
      filterChain.doFilter(request, response);
      return;
    }
//    jwt = authHeader.substring(7);
    jwt = authHeader.split(" ")[1];
    userEmail = jwtService.extractUserName(jwt);
  }
}
