package com.example.activitytrackerdemo.controller;

import com.example.activitytrackerdemo.dto.request.LoginRequest;
import com.example.activitytrackerdemo.dto.request.UserRequest;
import com.example.activitytrackerdemo.services.UserService;
import com.example.activitytrackerdemo.utils.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("signup")
    public ResponseEntity<ApiResponse> userSignUp (@RequestBody UserRequest request){
        System.out.println(" Details of the user" + request);
        ApiResponse apiResponse = userService.createUser(request);
       return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
}
    @PostMapping("login")
    public ResponseEntity<ApiResponse> userLogin (@RequestBody LoginRequest loginRequest, HttpServletRequest httpServletRequest){
        ApiResponse apiResponse = userService.login(loginRequest, httpServletRequest);
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }
    @GetMapping("view-user/{id}")
    public ResponseEntity<ApiResponse> findUser (@PathVariable Long id){
        ApiResponse apiResponse = userService.getUser(id);
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }
    @PutMapping("update/{id}")
    public ResponseEntity<ApiResponse> updateUser(@RequestBody UserRequest request, @PathVariable Long id){
        ApiResponse apiResponse = userService.updateUser(request,id );
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }
    @DeleteMapping("delete-user/{id}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Long id){
        ApiResponse apiResponse = userService.deleteUser(id);
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }
}
