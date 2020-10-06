package com.apparquear.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apparquear.dao.UserDAO;
import com.apparquear.model.User;
import com.apparquear.SHA;

@RestController
@RequestMapping("apparquear/user")
public class UserRest {
	
	@Autowired
	private UserDAO userDAO;
	
	//Methods
	@PostMapping("/save")
	public void save(@RequestBody User user) {
		user.setUser_password(SHA.getSHA512(user.getUser_password()));
		userDAO.save(user);
	}
	
	@GetMapping("/findAll")
	public List<User> findAll(){
		return userDAO.findAll();
	}
	
	@PostMapping("/login")
    public boolean login  (@RequestBody User user){
        List<User> userList;
        userList=userDAO.findAll();
        //System.out.println(userList.get(0).getUser_name());
        //return user;
        User DBUser= userList.stream()
                .filter(userAux -> userAux.getUser_email().equals(user.getUser_email()))
                .findAny()
                .orElse(null);
        if (DBUser!=null){
            return DBUser.getUser_password().equals(SHA.getSHA512(user.getUser_password()));
        }
        return false;
    }

}
