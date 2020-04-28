package com.caprusit.app.capv.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.caprusit.app.capv.model.Response;
import com.caprusit.app.capv.model.User;
import com.caprusit.app.capv.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private UserService userService;

	@PutMapping("/saveUser")
	public User save(@RequestBody User user) {
		try {
			executeSaveCommand(user);
			return this.userService.saveUser(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	@PostMapping("/updateUser")
	public User update(@RequestBody User user) {
		try {
			executeSaveCommand(user);
			return this.userService.updateUser(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	@DeleteMapping("/deleteUser/{userId}")
	public Response delete(@PathVariable String userId) {
		Response response = new Response();
		try {
			User user = this.userService.getUserById(Long.parseLong(userId));
			executeDeleteCommand(user);
			this.userService.deleteUser(Long.parseLong(userId));
			response.setResult("success");
		} catch (Exception e) {
			response.setResult("failure");
			e.printStackTrace();
		} finally {
			return response;
		}
	}

	@PostMapping("/getByUserName")
	public Response getUserByUserName(@RequestBody User user) {
		User uf = this.userService.getUserByUserName(user);
		Response response = new Response();
		if (uf == null) {
			response.setResult("no User");
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

	
	@PostMapping("/userListByClient")
    public List<User> getUserListByClient(@RequestBody User user) {
        List<User> hotels = this.userService.getUserListByClient(user);
        return hotels;
    }

	
	private void executeSaveCommand(User user) {
		// String command = "prosodyctl register diwakar jitsidemo1.capvonline.com
		// abc@1234";

		StringBuilder comnd = new StringBuilder("prosodyctl register ");
		comnd.append(user.getUserName() + " ");
		comnd.append(user.getDomainName() + " ");
		comnd.append(user.getPassword() + " ");
		try {
			System.out.println(comnd.toString());
			Process process = Runtime.getRuntime().exec(comnd.toString());
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line;
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void executeDeleteCommand(User user) {
		// String command = "prosodyctl deluser diwakar@jitsidemo1.capvonline.com "

		StringBuilder comnd = new StringBuilder("prosodyctl deluser ");
		comnd.append(user.getUserName());
		comnd.append("@");
		comnd.append(user.getDomainName());
		try {
			System.out.println(comnd.toString());
			Process process = Runtime.getRuntime().exec(comnd.toString());
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line;
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
