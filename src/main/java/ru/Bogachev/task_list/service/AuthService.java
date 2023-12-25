package ru.Bogachev.task_list.service;

import ru.Bogachev.task_list.web.dto.auth.JwtRequest;
import ru.Bogachev.task_list.web.dto.auth.JwtResponse;

public interface AuthService {

    JwtResponse login(JwtRequest loginRequest);

    JwtResponse refresh(String refreshToken);
}
