package com.apparquear.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.apparquear.model.User;

public interface UserDAO extends JpaRepository<User, Integer>{

}
