package com.groundbooking.groundbookingmonolythicapp.Components;

import com.groundbooking.groundbookingmonolythicapp.ExceptionHandling.CustomExceptions.LoginExpiredException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
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
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.sql.SQLOutput;


@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {

//    private LoggerContext context = new LoggerContext();
//    Logger logger = context.getLogger("testLogger");

    @Autowired
        private JWTHelper jwtHelper;

        @Autowired
        private UserDetailsService userDetailsService;

        @Override
        protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {


            String requestHeader = request.getHeader("Authorization");

            String username = null;
            String token = null;

            if (requestHeader != null && requestHeader.startsWith("Bearer")) {

                token = requestHeader.substring(7);
                try {

                    username = this.jwtHelper.getUsernameFromToken(token);


                } catch (IllegalArgumentException e) {
                    System.out.println("Illegal Argument while fetching the username !!");
                    e.printStackTrace();
                } catch (ExpiredJwtException e) {
                    System.out.println("Given jwt token is expired !!");
                    e.printStackTrace();
                } catch (MalformedJwtException e) {
                    System.out.println("Some changed has done in token !! Invalid Token");
                    e.printStackTrace();
                }catch (SignatureException e){
                        System.out.println("Sinature exception Occured in JWTAuntentication filter but it was handled and this is caused as wrong token was passed in header when accessing urls");
                        //ex.printStackTrace();
                }
                catch (Exception e) {
                    e.printStackTrace();

                }


            } else {
                System.out.println("Invalid Header Value !! occured in JWTAuthenticationFilter during login as there would be no header when login or adding user ");
            }


            //
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {


                //fetch user detail from username
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

                Boolean validateToken = this.jwtHelper.validateToken(token, userDetails);

                if (validateToken) {

                    //set the authentication
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(authentication);


                } else {
                    System.out.println("Validation fails !!");
                }


            }

            filterChain.doFilter(request, response);


        }



}
