package com.tinqin.library.auth.core;

import com.tinqin.library.auth.api.register.RegisterInput;
import com.tinqin.library.auth.api.register.RegisterResult;
import com.tinqin.library.auth.persistance.entities.User;
import com.tinqin.library.auth.persistance.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterOperation {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public RegisterResult register(RegisterInput input) {

        User user = User
                .builder()
                .username(input.getUsername())
                .password(passwordEncoder.encode(input.getPassword()))
                .build();

        userRepository.save(user);

        return RegisterResult
                .builder()
                .build();
    }
}
