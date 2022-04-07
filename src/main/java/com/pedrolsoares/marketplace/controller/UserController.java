package com.pedrolsoares.marketplace.controller;

import com.pedrolsoares.marketplace.dto.request.LoginDTO;
import com.pedrolsoares.marketplace.dto.request.UserDTO;
import com.pedrolsoares.marketplace.model.AppUser;
import com.pedrolsoares.marketplace.service.AppUserService;
import com.pedrolsoares.marketplace.util.JWTUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/v1/user")
@AllArgsConstructor
public class UserController {

    private final AppUserService userService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/register")
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<Object> create(@RequestBody @Valid UserDTO newUserDTO, UriComponentsBuilder uriBuilder){
        // TODO: Email confirmation and validation
        AppUser result = userService.createUser(newUserDTO.dtoToModel());

        URI uri = uriBuilder
                .path("/api/v1/user/{id}")
                .buildAndExpand(result.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody @Valid LoginDTO loginRequest) {
        try{
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword()));

            User user = (User) authentication.getPrincipal();

            String token = JWTUtils.generateToken(user);

            return ResponseEntity.ok().header(
                    HttpHeaders.AUTHORIZATION,
                    token
            ).body(user);

        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
