package com.web_dev_494.uGraduate.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class JwtTokenVerifier extends OncePerRequestFilter {



    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String header = request.getHeader("Authorization");
        if(!request.getHeader("Authorization").startsWith("Bearer ")
                || request.getHeader("Authorization") == null
                || request.getHeader("Authorization").isEmpty()){

            filterChain.doFilter(request,response);
            System.out.println("Fails in JWT TOKEN VERIFIER");
            System.out.println();
            return;
        }
        String token = header.replace("Bearer ", ""); // Remove Bearer from Response
        String secretKey = "securesecuresecuresecuresecuresecuresecuresecure";
        try{

            Jws<Claims> claimsJws =
                    Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes()))
                    .build()
                    .parseClaimsJws(token);

            Claims body = claimsJws.getBody();
            String username = body.getSubject();
            //System.out.println("~~~~~~~In JWT TOKEN VERIFIER~~~~~~~~~~~~~~~");
            //System.out.println("username: " + username);
            Collection authorities =
                    Arrays.stream(body.get("authorities").toString().split(","))
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());

            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    username,
                    null,
                    authorities
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
            filterChain.doFilter(request, response);
        }

        catch (JwtException e){
            throw new IllegalStateException("Error with Token ");
        }
    }
}
