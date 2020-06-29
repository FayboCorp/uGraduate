package com.web_dev_494.uGraduate.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.web_dev_494.uGraduate.entity.Authorities;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.stream.Collectors;


// added into websecurityconfig as a filter
public class JwtUsernameAndPasswordAuthFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    // Don't autowire because you're getting the instance by extending websecurity in SecurityConfig.java
    public JwtUsernameAndPasswordAuthFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        // TODO: trying to figure out the CORS to expose headers

        try {
            //System.out.println("******* IN JWTFILTER *******");
            // POST request on localhost:8080/login with json body of just username/password
            UsernameAndPasswordRequest authRequest =
                    new ObjectMapper().readValue(request.getInputStream(), UsernameAndPasswordRequest.class);

            // Auth object that will later be passed into authentication manager
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    authRequest.getUsername(),
                    authRequest.getPassword()
            );
            System.out.println("Got " + authRequest.getUsername() + " and " + authRequest.getPassword());

            Authentication authenticated = authenticationManager.authenticate(authentication);

            return authenticated;

        }
        catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    // On successful authentication..
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

        final String ROLES =  authResult.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        // Build JWT Token that is passed in the headers.
        String token = Jwts.builder()
                .setSubject(authResult.getName()) // Subject is username
                .claim("authorities", ROLES) // Gives you the roles
                .setIssuedAt(new Date()) // Issue date
                .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusWeeks(2))) // JWT expires when?
                .signWith(Keys
                        .hmacShaKeyFor("securesecuresecuresecuresecuresecuresecuresecure".getBytes()))
                        // Obviously, This is not a good secret key
                .compact();

        response.addHeader("Authorization", "Bearer " + token);
        //Cookie cookie = new Cookie("Authorization", "Bearer" + token);
        //response.addCookie(cookie);

    }
}
