package com.example.activitytrackerdemo.services.serviceImpl;

import com.example.activitytrackerdemo.dto.request.LoginRequest;
import com.example.activitytrackerdemo.dto.request.UserRequest;
import com.example.activitytrackerdemo.dto.response.UserResponse;
import com.example.activitytrackerdemo.entities.User;
import com.example.activitytrackerdemo.repository.UserRepository;
import com.example.activitytrackerdemo.services.UserService;
import com.example.activitytrackerdemo.utils.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServiceImp implements UserService {
    private final UserRepository userRepository;
    @Override
    public ApiResponse createUser(UserRequest request) {
    boolean checkedUser = userRepository.existsByEmail(request.getEmail());
    if(checkedUser){
        return new ApiResponse("01","User already exist",HttpStatus.BAD_REQUEST);
    }
        User newUser = new  User();
        newUser.setAddress(request.getAddress());
        newUser.setEmail(request.getEmail());
        newUser.setName(request.getName());
        newUser.setPassword(request.getPassword());
        userRepository.save(newUser);
        UserResponse userResponse = new UserResponse();
        userResponse.setName(request.getName());
        userResponse.setEmail(request.getEmail());
        userResponse.setAddress(request.getAddress());
        userResponse.setId(userResponse.getId());
     return new ApiResponse("00","User created successfully", HttpStatus.CREATED,userResponse);
    }
    @Override
    public ApiResponse login(LoginRequest loginRequest, HttpServletRequest request){
      Optional <User> user = userRepository.findByEmailAndPassword(loginRequest.getEmail(), loginRequest.getPassword());
       if (user.isEmpty()){
        return  new ApiResponse("01","user not not exist",HttpStatus.BAD_REQUEST);
       }
       UserResponse userResponse = new UserResponse();
       userResponse.setEmail(user.get().getEmail());
       userResponse.setName(user.get().getName());
       userResponse.setAddress(user.get().getAddress());
       userResponse.setId(user.get().getId());
        HttpSession session = request.getSession();
        session.setAttribute("UserResponse",userResponse);
        return new ApiResponse("00","login successful",HttpStatus.OK);
    }
    @Transactional
    @Override
    public ApiResponse updateUser(UserRequest request, Long  id) {
       User user = userRepository.findById(id).orElseThrow(()-> new RuntimeException("user not found"));
        user.setAddress(request.getAddress());
        user.setEmail(request.getEmail());
        user.setName(request.getName());
         userRepository.save(user);
        return new ApiResponse("00","Request successful",HttpStatus.CREATED);
    }

    @Override
    public ApiResponse getUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()){
            return new ApiResponse("01","User already exist",HttpStatus.BAD_REQUEST);
        }
        UserResponse userResponse = new UserResponse();
        userResponse.setName(user.get().getName());
        userResponse.setEmail(user.get().getEmail());
        return new ApiResponse("00","Request successful",HttpStatus.OK,userResponse);
    }

    @Override
    public ApiResponse deleteUser(Long id) {
        userRepository.deleteById(id);
        return new ApiResponse("01","Successful");
    }
}
