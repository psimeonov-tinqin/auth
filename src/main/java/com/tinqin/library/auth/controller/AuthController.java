package com.tinqin.library.auth.controller;

import com.tinqin.library.auth.api.jwtvalidation.JwtValidationInput;
import com.tinqin.library.auth.api.jwtvalidation.JwtValidationResult;
import com.tinqin.library.auth.api.login.LoginInput;
import com.tinqin.library.auth.api.login.LoginResult;
import com.tinqin.library.auth.api.register.RegisterInput;
import com.tinqin.library.auth.api.register.RegisterResult;
import com.tinqin.library.auth.core.JwtValidationOperation;
import com.tinqin.library.auth.core.LoginOperation;
import com.tinqin.library.auth.core.RegisterOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final RegisterOperation registerOperation;
    private final LoginOperation loginOperation;
    private final JwtValidationOperation jwtValidationOperation;

    @PostMapping(value = "/register")
    public ResponseEntity<RegisterResult> register(@RequestBody RegisterInput registerInput) {
        RegisterResult result = registerOperation.register(registerInput);

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<RegisterResult> login(@RequestBody LoginInput loginInput) {
        LoginResult login = loginOperation.login(loginInput);

        HttpHeaders httpHeaders = new HttpHeaders() {{
            add(HttpHeaders.AUTHORIZATION, login.getToken());
        }};

        return ResponseEntity
                .ok()
                .headers(httpHeaders)
                .build();
    }

    @GetMapping("/validate")
    public ResponseEntity<?> validate(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization) {

        JwtValidationInput input = JwtValidationInput
                .builder()
                .jwt(authorization)
                .build();

        JwtValidationResult validate = jwtValidationOperation.validate(input);

        return validate.getResult()
                ? ResponseEntity.ok().build()
                : ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
