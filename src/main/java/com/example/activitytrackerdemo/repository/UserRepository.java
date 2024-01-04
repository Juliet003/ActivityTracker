package com.example.activitytrackerdemo.repository;

import com.example.activitytrackerdemo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  boolean existsByEmail (String email);
  Optional<User> findByEmailAndPassword(String email, String password);

  Optional<User>findById(Long userId);
}
