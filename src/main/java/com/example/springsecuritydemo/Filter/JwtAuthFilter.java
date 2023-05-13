package com.example.springsecuritydemo.Filter;

import com.example.springsecuritydemo.Service.JwtService;
import com.example.springsecuritydemo.Service.UserInfoUserDetailService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/*
* use of this filter class
* 1)get bearer token
* 2)parse it and get the header and its data
* 3)validate and allow the user to access all his authorized endpoitns
* */


/*It is called when user tries to access any of the end pouints*/
@Component
public class JwtAuthFilter extends OncePerRequestFilter {
    @Autowired
    private JwtService jwtService;


    @Autowired
    private UserInfoUserDetailService userDetailService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        String token = null;
        //validating

        String username = null;
        if(authHeader != null && authHeader.startsWith("Bearer ")){
            token = authHeader.substring(7);
            username = jwtService.extractUsername(token);
        }


        //if user is valid and token is not expired:

        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
           UserDetails userDetails =  userDetailService.loadUserByUsername(username);


            //creating the new authentication obj if token is valid
            if(jwtService.validateToken(token,userDetails)){
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,null,userDetails.getAuthorities()
                );

                authToken.setDetails(
                        new WebAuthenticationDetailsSource()
                                .buildDetails(request)
                );


                SecurityContextHolder.getContext()
                        .setAuthentication(authToken);

           }
        }



        //calling filter chain

        filterChain.doFilter(request,response);
    }
}
