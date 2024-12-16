package com.tinqin.library.auth.api.login;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class LoginInput {

    private String username;
    private String password;
}
