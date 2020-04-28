package com.caprusit.app.capv.repository;

import java.util.List;

import com.caprusit.app.capv.model.Client;
import com.caprusit.app.capv.model.User;

public interface UserCustomRepository {

	User findByUserName(String name);

	List<User> findByClient(String clientDomain);

}
