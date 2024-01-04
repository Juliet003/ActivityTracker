package com.example.activitytrackerdemo.utils;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
@Data
@RequiredArgsConstructor
public class ApiResponse {
    private  String status;
    private String message;
    private HttpStatus httpStatus;
    private  Object data;

    public ApiResponse(String status, String message, HttpStatus httpStatus, Object data) {
        this.status = status;
        this.message = message;
        this.httpStatus = httpStatus;
        this.data = data;
    }

    public ApiResponse(String status, String message, HttpStatus httpStatus) {
        this.status = status;
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public ApiResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }
    public ApiResponse(String status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data =  data;
    }
}
