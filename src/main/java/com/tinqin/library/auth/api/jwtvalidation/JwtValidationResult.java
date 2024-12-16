package com.tinqin.library.auth.api.jwtvalidation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class JwtValidationResult {
    private Boolean result;
}
