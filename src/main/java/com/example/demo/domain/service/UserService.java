package com.example.demo.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.dao.UserDao;

@Service
public class UserService {

	@Autowired
	UserDao userdao;

	public String getUser(int id) throws Exception {
		return userdao.getUser(id);
	}
}
