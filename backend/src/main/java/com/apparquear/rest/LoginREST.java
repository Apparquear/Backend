package com.apparquear.rest;


import com.apparquear.dao.UserDAO;
import com.apparquear.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("apparquear/login")
public class LoginREST {

    @Autowired
    private UserDAO userDAO;

    //Methods
    @PostMapping("/user")
    public boolean login  (@RequestBody User user){
        List<User> userList;
        userList=userDAO.findAll();
        //System.out.println(userList.get(0).getUser_name());
        //return user;
        User DBUser= userList.stream()
                .filter(userAux -> userAux.getUser_name().equals(user.getUser_name()))
                .findAny()
                .orElse(null);
        if (DBUser!=null){
            return DBUser.getUser_password().equals(user.getUser_password());
        }
        return false;
    }


}
