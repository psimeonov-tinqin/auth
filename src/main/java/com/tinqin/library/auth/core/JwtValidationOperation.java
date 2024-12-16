package com.tinqin.library.auth.core;

import com.tinqin.library.auth.api.jwtvalidation.JwtValidationInput;
import com.tinqin.library.auth.api.jwtvalidation.JwtValidationResult;
import com.tinqin.library.auth.jwtmanager.JwtManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtValidationOperation {
    private final JwtManager jwtManager;

    public JwtValidationResult validate(JwtValidationInput input) {
        return JwtValidationResult
                .builder()
                .result(jwtManager.validate(input.getJwt()))
                .build();
    }
}
