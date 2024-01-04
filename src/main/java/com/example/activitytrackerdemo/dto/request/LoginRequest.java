package com.example.activitytrackerdemo.dto.request;

import lombok.Data;

@Data
public class LoginRequest {
    private  String email;
    private  String password;
}
