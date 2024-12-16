package com.tinqin.library.auth.api.login;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class LoginResult {
    private String token;
}
