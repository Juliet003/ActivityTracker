package com.example.activitytrackerdemo.dto.response;

import lombok.Data;
import lombok.Getter;

@Data
public class UserResponse {
    private String name;
    private String email;
    private String address;
    private Long id;
}
