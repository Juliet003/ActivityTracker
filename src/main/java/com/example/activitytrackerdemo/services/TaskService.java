package com.example.activitytrackerdemo.services;

import com.example.activitytrackerdemo.dto.request.TaskRequest;
import com.example.activitytrackerdemo.utils.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

@Service
public interface TaskService {
    ApiResponse createTask(TaskRequest taskRequest, HttpServletRequest request);

    ApiResponse findTaskById(Long id, HttpServletRequest request);

    ApiResponse findAllTaskByUser(HttpServletRequest request);

    ApiResponse findTasksByStatus(HttpServletRequest request, String taskStatus);

    ApiResponse updateTaskById(TaskRequest taskRequest, Long id, HttpServletRequest request);


    ApiResponse moveTaskStatus(HttpServletRequest request, Long taskId, String taskStatus);

    ApiResponse deleteTask(Long id);
}
