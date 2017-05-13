package com.jhsung.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jhsung.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findById(Long id);

	User findByEmail(String email);

	List<User> findByUserName(String userName);

	List<User> findByVerified(boolean verified);
}
