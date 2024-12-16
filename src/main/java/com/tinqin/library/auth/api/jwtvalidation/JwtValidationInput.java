package com.tinqin.library.auth.api.jwtvalidation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@Builder
public class JwtValidationInput {

    private String jwt;
}
