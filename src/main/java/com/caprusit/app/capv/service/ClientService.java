package com.caprusit.app.capv.service;

import java.util.List;

import com.caprusit.app.capv.model.Client;

public interface ClientService {

	Client saveClient(Client client);

	Client updateClient(Client client);

	void deleteClient(long userId);

	Client getClientByUserName(Client client);

	List<Client> getAllClients();

}
