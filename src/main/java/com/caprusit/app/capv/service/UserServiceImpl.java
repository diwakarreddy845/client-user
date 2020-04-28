package com.caprusit.app.capv.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caprusit.app.capv.model.User;
import com.caprusit.app.capv.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public User saveUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public User updateUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public void deleteUser(long userId) {
		userRepository.deleteById(userId);
	}

	@Override
	public User getUserByUserName(User user) {
		return userRepository.findByUserName(user.getUserName()).get(0);
	}

	@Override
	public List<User> getUserListByClient(User user) {
		return userRepository.findByClient(user.getClient());
	}

	@Override
	public User getUserById(long userId) {
		return userRepository.findById(userId).get();
	}

}
