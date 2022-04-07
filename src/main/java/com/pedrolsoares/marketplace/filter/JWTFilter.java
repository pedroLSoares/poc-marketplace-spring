package com.pedrolsoares.marketplace.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@Component
public class JWTFilter extends OncePerRequestFilter {

    public static final String HEADER_ATTR = "Authorization";
    public static final String HEADER_ATTR_PREFIX = "Bearer ";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String attribute = request.getHeader(HEADER_ATTR);

        if (attribute == null || !attribute.startsWith(HEADER_ATTR_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }

        String token = attribute.replace(HEADER_ATTR_PREFIX, "");

        UsernamePasswordAuthenticationToken authenticationToken = getAuthenticationToken(token);
        if(authenticationToken != null){
            // Verify
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        }


        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        chain.doFilter(request, response);
    }


    private UsernamePasswordAuthenticationToken getAuthenticationToken(String token) {
        Algorithm algorithm = Algorithm.HMAC512("secret".getBytes());

        String userEmail = JWT.require(algorithm).build().verify(token).getSubject();

        if (userEmail == null) {
            return null;
        }

        return new UsernamePasswordAuthenticationToken(userEmail, null, new ArrayList<>());

    }
}