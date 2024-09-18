package com.crm.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.model.User;
import com.crm.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	
	public User createUser(User user) {
		return userRepository.save(user);
	}
	
	public List<User> findAllUser() {
		return userRepository.findByIsAdmin(false);
	}
	
	public void deleteUser(Long id) {
		userRepository.deleteById(id);
	}
	
	 // Method to get list of users by a set of user IDs
    public List<User> getUserList(Set<Long> userIdSet) {
        // Spring Data JPA's findAllById method can take a collection of IDs
        return userRepository.findAllById(userIdSet);
    }
	
}
