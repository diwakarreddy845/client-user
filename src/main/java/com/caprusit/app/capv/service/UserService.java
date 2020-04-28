package com.caprusit.app.capv.service;

import java.util.List;

import com.caprusit.app.capv.model.User;

public interface UserService {
	
	User saveUser(User user);
	
	User updateUser(User user);
	
	void deleteUser(long userId);
	
	User getUserByUserName(User user);
	
	List<User> getUserListByClient(User user);
	
	User getUserById(long userId);

}
