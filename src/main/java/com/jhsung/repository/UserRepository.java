package com.jhsung.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jhsung.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	List<User> findById(Long id);

	List<User> findByEmail(String email);

	List<User> findByUserName(String userName);

	List<User> findByVerified(boolean verified);
}
