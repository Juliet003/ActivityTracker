package com.example.activitytrackerdemo.entities;

import com.example.activitytrackerdemo.enums.TaskStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="task")
public class Task {
    @Id
    @SequenceGenerator(name = "id",
            sequenceName = "id", allocationSize = 1)
    @GeneratedValue(generator = "id",
            strategy = GenerationType.SEQUENCE)
    private Long id;
    private String title;
    private String description;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
    private LocalDateTime completedTime;
    @Enumerated(EnumType.STRING)
    private TaskStatus taskStatus;

    @ManyToOne
    private User user;

    public Task(Long id,String title,String description,LocalDateTime createdTime,
                LocalDateTime updatedTime,LocalDateTime completedTime, TaskStatus taskStatus){
        this.id=id;
        this.title=title;
        this.description=description;
        this.createdTime=createdTime;
        this.updatedTime=updatedTime;
        this.completedTime=completedTime;
        this.taskStatus=taskStatus;
    }
    public Task(){

    }
    public void setId(Long id){
        this.id=id;
    }
    public Long getId(){
        return id;
    }
    public void setTitle(String title){
        this.title=title;
    }
    public String getTitle(){
        return title;
    }
    public void setDescription(String description){
        this.description=description;
    }
    public String getDescription(){
        return description;
    }
    public void setCreatedTime(LocalDateTime createdTime){
        this.createdTime=createdTime;
    }
    public LocalDateTime getCreatedTime(){
        return createdTime;
    }
    public void setUpdatedTime(LocalDateTime updatedTime){
        this.updatedTime=updatedTime;
    }
    public LocalDateTime getUpdatedTime(){
        return  updatedTime;
    }
    public void setCompletedTime(LocalDateTime completedTime){
        this.completedTime=completedTime;
    }
    public LocalDateTime getCompletedTime(){
        return completedTime;
    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }

    public Task(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

