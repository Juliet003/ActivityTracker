package com.example.activitytrackerdemo.controller;

import com.example.activitytrackerdemo.dto.request.TaskRequest;
import com.example.activitytrackerdemo.services.TaskService;
import com.example.activitytrackerdemo.utils.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/")
public class TaskController {
private final TaskService taskService;
    @PostMapping("create-task")
    public ResponseEntity <ApiResponse> createTask (@RequestBody TaskRequest taskRequest, HttpServletRequest httpServletRequest){
        System.out.println("the user session "  +  httpServletRequest);
        ApiResponse apiResponse = taskService.createTask(taskRequest,httpServletRequest);
       return new  ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }
    @PutMapping("update-task/{id}")
    public ResponseEntity<ApiResponse> updateTask(@RequestBody TaskRequest taskRequest, HttpServletRequest request,@PathVariable Long id){
        ApiResponse apiResponse = taskService.updateTaskById(taskRequest, id, request);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("view-task/{id}")
    public ResponseEntity<ApiResponse>viewTaskById(@PathVariable Long id, HttpServletRequest request){
        ApiResponse apiResponse = taskService.findTaskById(id,request );
        return  new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }
    @GetMapping("view-all-task")
    public ResponseEntity<ApiResponse>viewAllTaskByUser(HttpServletRequest request){
        ApiResponse apiResponse = taskService.findAllTaskByUser(request);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
    @GetMapping("view-by-status")
    public ResponseEntity<ApiResponse>viewAllTaskByStatus(HttpServletRequest request,@RequestParam("status")  String TaskStatus){
        ApiResponse apiResponse = taskService.findTasksByStatus(request, TaskStatus);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @DeleteMapping("delete-task/{id}")
    public ResponseEntity<ApiResponse>deleteTask(@PathVariable Long id){
        ApiResponse apiResponse = taskService.deleteTask(id);
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }



}
