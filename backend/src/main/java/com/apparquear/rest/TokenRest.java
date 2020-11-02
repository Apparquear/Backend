package com.apparquear.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apparquear.dao.TokenDAO;
import com.apparquear.model.Token;

@RestController
@RequestMapping("apparquear/token")
public class TokenRest {

	@Autowired
	private TokenDAO tokenDAO;
	
	@CrossOrigin
	@PostMapping("/invalidate")
	public void invalidate(@RequestBody Token secureToken) {
		System.out.println(secureToken);
		Token token = new Token();
		token = tokenDAO.findByToken(secureToken.getToken());
		token.setValid(false);
		tokenDAO.save(token);
	}
}
