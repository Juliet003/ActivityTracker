package com.example.activitytrackerdemo.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TaskResponse {
    private Long id;
    private String title;
    private String description;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
    private LocalDateTime completedTime;
    private String status;
}
