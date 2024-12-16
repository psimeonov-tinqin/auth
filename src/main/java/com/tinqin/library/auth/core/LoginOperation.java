package com.tinqin.library.auth.core;

import com.tinqin.library.auth.api.login.LoginInput;
import com.tinqin.library.auth.api.login.LoginResult;
import com.tinqin.library.auth.api.register.RegisterInput;
import com.tinqin.library.auth.jwtmanager.JwtManager;
import com.tinqin.library.auth.persistance.entities.User;
import com.tinqin.library.auth.persistance.repositories.UserRepository;
import io.vavr.Tuple;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginOperation {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtManager jwtManager;

    public LoginResult login(LoginInput input) {

        return userRepository
                .findUserByUsername(input.getUsername())
                .map(user -> {
                    if (!passwordEncoder.matches(input.getPassword(), user.getPassword())) {
                        throw new RuntimeException("Invalid password");
                    }
                    return user;
                })
                .map(this::generateJwt)
                .map(this::buildResult)
                .orElseThrow(() -> new RuntimeException("User not found"));

    }

    private String generateJwt(User user) {
        return jwtManager.generateJwt(user);
    }

    private LoginResult buildResult(String jwt) {
        return LoginResult
                .builder()
                .token(jwt)
                .build();
    }
}
