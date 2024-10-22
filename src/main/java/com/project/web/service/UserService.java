package com.project.web.service;

import com.project.web.dto.RegistrationDto;
import com.project.web.models.UserEntity;

public interface UserService {
    void saveUser(RegistrationDto registrationDto);
    UserEntity findByEmail(String email);
    UserEntity findByUsername(String username);
}
