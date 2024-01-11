package org.myapp.rest;

import lombok.extern.slf4j.Slf4j;
import org.myapp.auth.AuthUser;
import org.myapp.service.AuthService;
import org.myapp.service.UserDetailsCustomService;
import org.myapp.service.dto.AuthDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController()
@RequestMapping("auth")
public class AuthController {


    @Autowired
    private AuthService authService;

    @Autowired
    private AuthenticationManager authenticationManager;


    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody AuthDto.LoginRequest loginRequest){

        System.out.println("LOGIN "+loginRequest.toString());
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        AuthUser user = (AuthUser) authentication.getPrincipal();

        String token = this.authService.generateToken(authentication);

        log.info("Token requested for user :{}", authentication.getAuthorities());
        AuthDto.Response response = new AuthDto.Response("User logged in successfully", token);

        return ResponseEntity.ok(response);
    }
}
