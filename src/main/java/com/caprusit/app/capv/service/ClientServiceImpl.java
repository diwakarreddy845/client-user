package com.caprusit.app.capv.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caprusit.app.capv.model.Client;
import com.caprusit.app.capv.repository.ClientRepository;
@Service
public class ClientServiceImpl implements ClientService{

	@Autowired
	private ClientRepository clientRepository;
	
	@Override
	public Client saveClient(Client client) {
		return clientRepository.save(client);
	}

	@Override
	public Client updateClient(Client client) {
		return clientRepository.save(client);
	}

	@Override
	public void deleteClient(long clientId) {
		 clientRepository.deleteById(clientId);		
	}

	@Override
	public Client getClientByUserName(Client client) {
		return clientRepository.findByUserName(client.getUserName());
	}

	@Override
	public List<Client> getAllClients() {
		List<Client> clientList = (List<Client>) clientRepository.findAll();
		clientList.remove(0);
		return clientList;
	}

}
