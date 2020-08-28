package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {
	private UserRepository userRepository;
	
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public List<User> getUsers() {
		return (List<User>) userRepository.findAll();
	}
	
	public User getUser(Long id) {
		return userRepository.findById(id).get();
	}
	
	public void createOrUpdateUser(User user) {
		userRepository.save(user);
	}
	
	public void removeUser(Long id) {
		userRepository.deleteById(id);
	}
}
