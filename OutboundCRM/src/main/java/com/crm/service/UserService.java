package com.crm.service;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.crm.model.User;
import com.crm.repository.UserRepository;

@Service
@Transactional
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	
	public User createUser(User user) {
		return userRepository.save(user);
	}
	
	@Transactional
	public List<User> findAllUser() {
		return userRepository.findByRole("user");
	}
	
	public void deleteUser(Long id) {
		userRepository.deleteById(id);
	}
	
	public User getUserById(Long id) {
		return userRepository.findById(id).orElse(null);
	}
	
	 // Method to get list of users by a set of user IDs
    public List<User> getUserList(Set<Long> userIdSet) {
        // Spring Data JPA's findAllById method can take a collection of IDs
        return userRepository.findAllById(userIdSet);
    }
    
    @Transactional
    public User updateProfile(MultipartFile  file, User user, Long id) throws IOException {
    	User dbUser = userRepository.findById(id).orElse(null);
    	dbUser.setName(user.getName());
    	dbUser.setEmail(user.getEmail());
    	dbUser.setPassword(user.getPassword());
    	dbUser.setFileName(file.getOriginalFilename());
    	dbUser.setFileType(file.getContentType());
    	dbUser.setData(file.getBytes());
    	return userRepository.save(dbUser);
    }
    
    // Updating user
    public User upateUser(User user, Long id) {
    	User userdb = userRepository.findById(id).orElse(null);
    	userdb.setName(user.getName());
    	userdb.setMobile(user.getMobile());
    	userdb.setEmail(user.getEmail());
    	userdb.setPassword(user.getPassword());
    	userdb.setStatus(user.getStatus());
    	return userRepository.save(userdb);
    }
    
	
}
