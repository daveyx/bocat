package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.cassandra.model.UserAccount;
import com.example.cassandra.repo.UserAccountRepository;

@Controller
@RequestMapping("/playground")
public class PlaygroundController {
	@Autowired
	private UserAccountRepository userAccountRepository;
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<UserAccount> findAll() {
//		return userAccountRepository.findAll();
		return null;
	}
}
