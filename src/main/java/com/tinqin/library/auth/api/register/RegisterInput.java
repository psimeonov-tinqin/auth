package com.tinqin.library.auth.api.register;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class RegisterInput {

    private String username;
    private String password;
}
