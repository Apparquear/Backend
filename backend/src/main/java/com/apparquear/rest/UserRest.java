package com.apparquear.rest;

import java.util.List;

import com.apparquear.dao.ParkingDAO;
import com.apparquear.model.Parking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apparquear.dao.TokenDAO;
import com.apparquear.dao.UserDAO;
import com.apparquear.exception.ApiRequestException;
import com.apparquear.model.Token;
import com.apparquear.model.User;
import com.apparquear.SHA;

@RestController
@RequestMapping("apparquear/user")
public class UserRest {
	
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private TokenDAO tokenDAO;
	@Autowired
	private ParkingDAO parkingDAO;
	
	//Methods
	@CrossOrigin
	@PostMapping("/save")
	public void save(@RequestBody User user) {
		if (userDAO.findByEmail(user.getEmail()) == null) {
			user.setPassword(SHA.getSHA512(user.getPassword()));
			userDAO.save(user);
		}else {
			throw new ApiRequestException("Este email ya se encuentra registrado");
		}
	}
	
	@GetMapping("/findAll")
	public List<User> findAll(){
		return userDAO.findAll();
	}

	@CrossOrigin
	@PostMapping("/login")
    public Token login  (@RequestBody User user){
        User DBUser = new User();
        Token token = new Token();
        DBUser = userDAO.findByEmail(user.getEmail());
        if (DBUser != null) {
        	if (DBUser.getPassword().equals(SHA.getSHA512(user.getPassword()))) {
        		
        		String secureToken = new String();
        		secureToken = token.generateToken(DBUser);
        		token.setValid(true);
        		token.setUser_ID(DBUser.getUserID());
        		token.setToken(secureToken);
        		tokenDAO.save(token);
        	} else {
        		token.setValid(false);
        	}
        	return token;
        } else {
        	throw new ApiRequestException("Este email no se encuentra registrado");
        }
    }

	@CrossOrigin
	@PostMapping("/myParkings")
	public List<Parking> MyParkings(@RequestBody User user) {
		List<Parking> userParkings = parkingDAO.findAllByUserID(user.getUserID());
		return userParkings;
	}

	@CrossOrigin
	@PostMapping("/isOwner")
	public boolean isOwner(@RequestBody User user) {
		List<Parking> userParkings = parkingDAO.findAllByUserID(user.getUserID());
		if (!userParkings.isEmpty()) return true;
		return false;
	}

	
	public void main(String args[]) {
		Token token = new Token();
		token.setValid(true);
		token.setUser_ID((long)1);
		token.setToken("8e8ed114f33c80686856887944bd63b51f96d75978d436972fc2bd262818c3d920342c4abbaee9199076c7464d99e0b10cbb91ad939772c18e98e0ef9edc3c60");
		tokenDAO.save(token);
	}

}