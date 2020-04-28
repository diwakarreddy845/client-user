package com.caprusit.app.capv.repository;

import com.caprusit.app.capv.model.Client;

public interface ClientCustomRepository {

	Client findByUserName(String name);


}
