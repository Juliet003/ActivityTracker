package com.example.activitytrackerdemo.repository;

import com.example.activitytrackerdemo.dto.response.TaskResponse;
import com.example.activitytrackerdemo.entities.Task;
import com.example.activitytrackerdemo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository <Task, Long> {
    List<Task> findAllByUser(User user);

    List<Task> findTasksByTaskStatus(String taskStatus);
}
