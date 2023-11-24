package ru.Bogachev.task_list.service.impl;

import org.springframework.stereotype.Service;
import ru.Bogachev.task_list.service.AuthService;
import ru.Bogachev.task_list.web.dto.auth.JwtRequest;
import ru.Bogachev.task_list.web.dto.auth.JwtResponse;

@Service
public class AuthServiceImpl implements AuthService {

    @Override
    public JwtResponse login(JwtRequest loginRequest) {
        return null;
    }

    @Override
    public JwtResponse refresh(String refreshToken) {
        return null;
    }

}
