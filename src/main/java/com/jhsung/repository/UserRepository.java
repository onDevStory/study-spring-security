package com.jhsung.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jhsung.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
