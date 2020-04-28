package com.caprusit.app.capv.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.caprusit.app.capv.model.Client;

@Repository
public interface ClientRepository extends CrudRepository<Client, Long>, ClientCustomRepository{


}
