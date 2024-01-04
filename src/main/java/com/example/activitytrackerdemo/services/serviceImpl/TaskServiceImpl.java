package com.example.activitytrackerdemo.services.serviceImpl;

import com.example.activitytrackerdemo.dto.request.TaskRequest;
import com.example.activitytrackerdemo.dto.response.TaskResponse;
import com.example.activitytrackerdemo.dto.response.UserResponse;
import com.example.activitytrackerdemo.entities.Task;
import com.example.activitytrackerdemo.entities.User;
import com.example.activitytrackerdemo.enums.TaskStatus;
import com.example.activitytrackerdemo.repository.TaskRepository;
import com.example.activitytrackerdemo.repository.UserRepository;
import com.example.activitytrackerdemo.services.TaskService;
import com.example.activitytrackerdemo.utils.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor; 
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@RequiredArgsConstructor
@Service
public class TaskServiceImpl implements TaskService {
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

    @Override
    public ApiResponse createTask(TaskRequest taskRequest, HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserResponse userResponse = (UserResponse) session.getAttribute("UserResponse");
//        session.getAttribute("userId");
        Optional<User> optionalUser = userRepository.findById(userResponse.getId());
        if(optionalUser.isEmpty()){
            return new ApiResponse("01","User does not exist", HttpStatus.BAD_REQUEST);
        }
        Task newtask = new Task();
            newtask.setTitle(taskRequest.getTitle());
            newtask.setDescription(taskRequest.getDescription());
            newtask.setTaskStatus(TaskStatus.PENDING);
            newtask.setCreatedTime(LocalDateTime.now());
            newtask.setUser(optionalUser.get());

            taskRepository.save(newtask);
            return new ApiResponse("00", "Task created", HttpStatus.CREATED);
    }

    @Override
    public ApiResponse findTaskById(Long id, HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserResponse userResponse = (UserResponse) session.getAttribute("UserResponse");
        Optional<User> user1 = userRepository.findById(userResponse.getId());
        if(user1.isEmpty()){
            return new ApiResponse("01","User does not exist", HttpStatus.BAD_REQUEST);
        }
        Optional<Task> task =taskRepository.findById(id);
        if(task.isEmpty()){
            return new ApiResponse("01","Task does not exist", HttpStatus.BAD_REQUEST);
        }
        TaskResponse taskResponse = new TaskResponse();
        taskResponse.setId(task.get().getId());
        taskResponse.setTitle(task.get().getTitle());
        taskResponse.setDescription(task.get().getDescription());
        taskResponse.setCreatedTime(task.get().getCreatedTime());
        taskResponse.setUpdatedTime(task.get().getUpdatedTime());
        taskResponse.setCompletedTime(task.get().getCompletedTime());
        taskResponse.setStatus(String.valueOf(task.get().getTaskStatus()));
        return new ApiResponse("00","Request Successful",HttpStatus.CREATED,taskResponse);
    }

    @Override
    public ApiResponse findAllTaskByUser(HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserResponse userResponse = (UserResponse) session.getAttribute("UserResponse");
        Optional<User> user1 = userRepository.findById(userResponse.getId());
        if(user1.isEmpty()){
            return new ApiResponse("01","User does not exist", HttpStatus.BAD_REQUEST);
        }
        List<TaskResponse> taskResponses = new ArrayList<>();
        List<Task> taskList = taskRepository.findAllByUser(user1.get());
        taskList.forEach(task -> {
            TaskResponse newtaskResponse = new TaskResponse();
            newtaskResponse.setTitle(task.getTitle());
            newtaskResponse.setDescription(task.getDescription());
            newtaskResponse.setStatus(String.valueOf(task.getTaskStatus()));
            newtaskResponse.setCreatedTime(task.getCreatedTime());
            newtaskResponse.setUpdatedTime(task.getUpdatedTime());
            newtaskResponse.setCompletedTime(task.getCompletedTime());
            newtaskResponse.setId(task.getId());
            taskResponses.add(newtaskResponse);
        });
        return new ApiResponse("00","Request successful",HttpStatus.OK,taskResponses);
    }
    @Override
    public ApiResponse findTasksByStatus(HttpServletRequest request, String taskStatus){
        HttpSession session = request.getSession();
        UserResponse userResponse = (UserResponse) session.getAttribute("UserResponse");
        Optional<User> user1 = userRepository.findById(userResponse.getId());
        if(user1.isEmpty()){
            return new ApiResponse("01","User does not exist", HttpStatus.BAD_REQUEST);
        }
        List<TaskResponse> taskResponseList = new ArrayList<>();
        List<Task> taskList = taskRepository.findTasksByTaskStatus(taskStatus);
        taskList.forEach(task -> {
            TaskResponse taskResponse = new TaskResponse();
            taskResponse.setTitle(task.getTitle());
            taskResponse.setDescription(task.getDescription());
            taskResponse.setCreatedTime(task.getCreatedTime());
            taskResponse.setUpdatedTime(task.getUpdatedTime());
            taskResponse.setCompletedTime(task.getCompletedTime());
            taskResponse.setStatus(String.valueOf(task.getTaskStatus()));
            taskResponse.setId(task.getId());
            taskResponseList.add(taskResponse);
        });
        return new ApiResponse("00","Successful",HttpStatus.OK,taskResponseList);
    }

    @Override
    public ApiResponse updateTaskById(TaskRequest taskRequest, Long id, HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserResponse userResponse = (UserResponse) session.getAttribute("UserResponse");
        Optional<User> user1 = userRepository.findById(userResponse.getId());
        if(user1.isEmpty()){
            return new ApiResponse("01","User does not exist", HttpStatus.BAD_REQUEST);
        }
        Task task = taskRepository.findById(id).orElseThrow(()-> new RuntimeException("Task not found"));
        task.setTitle(taskRequest.getTitle());
        task.setDescription(taskRequest.getDescription());
        taskRepository.save(task);
        return new ApiResponse("00","Successful",HttpStatus.OK);
    }
    @Override
    public  ApiResponse moveTaskStatus(HttpServletRequest request, Long taskId, String taskStatus) {
        HttpSession session = request.getSession();
        UserResponse userResponse = (UserResponse) session.getAttribute("UserResponse");
        Optional<User> user = userRepository.findById(userResponse.getId());
        if (user.isEmpty()) {
            return new ApiResponse("01", "User does not exist", HttpStatus.BAD_REQUEST);
        }
        Optional<Task> task = taskRepository.findById(taskId);
        if (task.isEmpty()) {
            return new ApiResponse("01", "Task does not exist", HttpStatus.BAD_REQUEST);
        }
        if (taskStatus.equals("DONE")) {
            task.get().setTaskStatus(TaskStatus.DONE);
            task.get().setCreatedTime(LocalDateTime.now());
            return new ApiResponse("00", "Successful", HttpStatus.OK);
        }
        if (taskStatus.equals("PENDING")) {
            task.get().setTaskStatus(TaskStatus.PENDING);
            task.get().setCompletedTime(null);
        return new ApiResponse("00", "Successful", HttpStatus.OK);
    }
        if (taskStatus.equals("IN_PROGRESS")){
            task.get().setTaskStatus(TaskStatus.IN_PROGRESS);
            task.get().setCompletedTime(null);
            }
            return new ApiResponse("00","Successful",HttpStatus.OK);
        }


    @Override
    public ApiResponse deleteTask(Long id) {
        userRepository.deleteById(id);
        return new ApiResponse("00","Successful");
    }
}
