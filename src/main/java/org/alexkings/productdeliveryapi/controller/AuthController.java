package org.alexkings.productdeliveryapi.controller;

import jakarta.validation.Valid;
import org.alexkings.productdeliveryapi.auth.JwtUtil;
import org.alexkings.productdeliveryapi.model.*;
import org.alexkings.productdeliveryapi.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/rest/auth")
public class AuthController {

    private static final Logger log = LoggerFactory.getLogger(AuthController.class);
    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    private JwtUtil jwtUtil;

    public AuthController(AuthenticationManager authenticationManager, UserService userService, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtUtil = jwtUtil;

    }

    @PostMapping(value = "/login")
    public ResponseEntity login(@RequestBody LoginReq loginReq) {

        try {
            Authentication authentication =
                    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginReq.getUsername(), loginReq.getPassword()));
            CustomUser u = (CustomUser) authentication.getPrincipal();
            UserDto user;
            if (u.getUserId() != null) {

                user = userService.getUserById(u.getUserId() != null ? u.getUserId() : 1L);
            } else {
                user = new UserDto();
            }
            user.setId(u.getUserId());
            String token = jwtUtil.createToken(user);
            LoginRes loginRes = new LoginRes(user.getUserName(), token);

            return ResponseEntity.ok(loginRes);

        } catch (BadCredentialsException e) {
            ErrorRes errorResponse = new ErrorRes(HttpStatus.BAD_REQUEST, "Invalid username or password");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        } catch (Exception e) {
            ErrorRes errorResponse = new ErrorRes(HttpStatus.BAD_REQUEST, e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> signIn(@RequestBody @Valid RegisterReq registerReq) {
        return ResponseEntity.ok(userService.createUser(registerReq));
    }
}