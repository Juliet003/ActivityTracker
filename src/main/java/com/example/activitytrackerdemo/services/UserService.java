package com.example.activitytrackerdemo.services;

import com.example.activitytrackerdemo.dto.request.LoginRequest;
import com.example.activitytrackerdemo.dto.request.UserRequest;
import com.example.activitytrackerdemo.utils.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    ApiResponse createUser(UserRequest request);

    ApiResponse login (LoginRequest loginRequest, HttpServletRequest request);

    ApiResponse  updateUser(UserRequest request, Long id);

    ApiResponse getUser(Long id);

    ApiResponse deleteUser(Long id);
}
