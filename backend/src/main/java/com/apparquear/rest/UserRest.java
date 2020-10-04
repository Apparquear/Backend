package com.apparquear.rest;

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

import com.apparquear.dao.UserDAO;
import com.apparquear.model.User;

@RestController
@RequestMapping("apparquear/user")
public class UserRest {

	@Autowired
	private UserDAO userDAO;

	// Methods
	@PostMapping("/save")
	public void save(@RequestBody User user) {
		userDAO.save(user);
	}

	@GetMapping("/list")
	public List<User> list() {
		return userDAO.findAll();
	}

	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable("id") Long user_ID) {
		userDAO.deleteById(user_ID);
	}

	@PutMapping("/update")
	public void update(@RequestBody User user) {
		userDAO.save(user);
	}

}
