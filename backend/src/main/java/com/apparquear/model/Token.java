package com.apparquear.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.apparquear.SHA;

@Entity
public class Token {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long token_ID;
	
	@Column
	private Long user_ID;
	
	@ManyToOne
	@JoinColumn(name = "user_ID", referencedColumnName = "user_ID", insertable = false, updatable = false)
	private User user;
	
	@Column
	private String token;
	
	@Column
	private Boolean valid;
	
	public Long getToken_ID() {
		return token_ID;
	}

	public void setToken_ID(Long token_ID) {
		this.token_ID = token_ID;
	}

	public Long getUser_ID() {
		return user_ID;
	}

	public void setUser_ID(Long user_ID) {
		this.user_ID = user_ID;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Boolean getValid() {
		return valid;
	}

	public void setValid(Boolean valid) {
		this.valid = valid;
	}

	public String generateToken(User user) {
		String token = new String();
		token = SHA.getSHA512(String.valueOf(Math.random()) + user.getEmail() + String.valueOf(Math.random()) + user.getName() + String.valueOf(Math.random()));
		return token;
	}
}
