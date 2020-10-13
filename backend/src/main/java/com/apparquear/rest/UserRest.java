package com.apparquear.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apparquear.dao.UserDAO;
import com.apparquear.exception.ApiRequestException;
import com.apparquear.model.User;
import com.apparquear.SHA;

@RestController
@RequestMapping("apparquear/user")
public class UserRest {
	
	@Autowired
	private UserDAO userDAO;
	
	//Methods
	@CrossOrigin
	@PostMapping("/save")
	public void save(@RequestBody User user) {
		System.out.println(userDAO.findByEmail(user.getEmail()));
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
    public boolean login  (@RequestBody User user){
        List<User> userList;
        userList=userDAO.findAll();
        //System.out.println(userList.get(0).getUser_name());
        //return user;
        User DBUser= userList.stream()
                .filter(userAux -> userAux.getEmail().equals(user.getEmail()))
                .findAny()
                .orElse(null);
        if (DBUser!=null){
            return DBUser.getPassword().equals(SHA.getSHA512(user.getPassword()));
        }
        return false;
    }

}