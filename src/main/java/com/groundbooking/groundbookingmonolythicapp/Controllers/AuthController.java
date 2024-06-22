package com.groundbooking.groundbookingmonolythicapp.Controllers;

import com.groundbooking.groundbookingmonolythicapp.Components.JWTHelper;
import com.groundbooking.groundbookingmonolythicapp.Entities.JWTRequest;
import com.groundbooking.groundbookingmonolythicapp.Entities.JWTResponse;
import com.groundbooking.groundbookingmonolythicapp.ExceptionHandling.CustomExceptions.BadCredentialsException;
import com.groundbooking.groundbookingmonolythicapp.Services.UserCredentialsService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserDetailsService userDetailsService;
    //This UserDetails service instance is of security class that provided by spring framework

    @Autowired
    private AuthenticationManager manager;


    @Autowired
    private JWTHelper helper;

//    private Logger logger = (Logger) LoggerFactory.getLogger(AuthController.class);

   // @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/login")
    public ResponseEntity<JWTResponse> login(@RequestBody JWTRequest request) {

        this.doAuthenticate(request.getUsername(), request.getPassword());


        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        String token = this.helper.generateToken(userDetails);

        JWTResponse response = JWTResponse.builder()
                .token(token)
                .username(userDetails.getUsername())
                .role(userDetails.getAuthorities().toString()).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private void doAuthenticate(String username, String password) {

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, password);
        try {
            manager.authenticate(authentication);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(" Invalid Username or Password  !!"); //this("Invalid Username or Password  !!") doesnt print,just put to get rid of error
        }

    }
}
