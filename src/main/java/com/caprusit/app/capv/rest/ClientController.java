package com.caprusit.app.capv.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.caprusit.app.capv.model.Client;
import com.caprusit.app.capv.model.Response;
import com.caprusit.app.capv.service.ClientService;

@RestController
@RequestMapping("/client")
public class ClientController {

	@Autowired
	private ClientService clientService;

	@PutMapping("/saveClient")
	public Client save(@RequestBody Client user) {
		return this.clientService.saveClient(user);
	}

	@PostMapping("/updateClient")
	public Client update(@RequestBody Client user) {
		return this.clientService.updateClient(user);
	}

	@DeleteMapping("/deleteClient/{userId}")
	public Response delete(@PathVariable String userId) {
		Response response = new Response();
		try {
			this.clientService.deleteClient(Long.parseLong(userId));
			response.setResult("success");
		} catch (Exception e) {
			response.setResult("failure");
			e.printStackTrace();
		} finally {
			return response;
		}
	}
	
	@GetMapping("/getAllClientList")
	public List<Client> getAllClientList(){
		return this.clientService.getAllClients();
	}

	@PostMapping("/getByUserName")
	public Response getClientByUserName(@RequestBody Client user) {
		Client uf = this.clientService.getClientByUserName(user);
		Response response = new Response();
		if (uf == null) {
			response.setResult("no Client");
			return response;
		} else if (uf != null && uf.getPassword().equals(user.getPassword())) {
			response.setResult("success");
			response.setDomainName(uf.getDomainName());
			return response;
		} else {
			response.setResult("failure");
			return response;
		}
	}

	/*
	 * private void executeSaveCommand(User user) { // String command = "prosodyctl
	 * register diwakar jitsidemo1.capvonline.com // abc@1234";
	 * 
	 * StringBuilder comnd = new StringBuilder("prosodyctl register ");
	 * comnd.append(user.getUserName() + " "); comnd.append(user.getDomainName() +
	 * " "); comnd.append(user.getPassword() + " "); try {
	 * System.out.println(comnd.toString()); Process process =
	 * Runtime.getRuntime().exec(comnd.toString()); BufferedReader reader = new
	 * BufferedReader(new InputStreamReader(process.getInputStream())); String line;
	 * while ((line = reader.readLine()) != null) { System.out.println(line); }
	 * reader.close(); } catch (IOException e) { e.printStackTrace(); } }
	 * 
	 * private void executeDeleteCommand(User user) { // String command =
	 * "prosodyctl deluser diwakar@jitsidemo1.capvonline.com "
	 * 
	 * StringBuilder comnd = new StringBuilder("prosodyctl deluser ");
	 * comnd.append(user.getUserName()); comnd.append("@");
	 * comnd.append(user.getDomainName()); try {
	 * System.out.println(comnd.toString()); Process process =
	 * Runtime.getRuntime().exec(comnd.toString()); BufferedReader reader = new
	 * BufferedReader(new InputStreamReader(process.getInputStream())); String line;
	 * while ((line = reader.readLine()) != null) { System.out.println(line); }
	 * reader.close(); } catch (IOException e) { e.printStackTrace(); } }
	 */
}
