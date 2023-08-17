package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.entity.AccountEntity;
import com.example.demo.service.AccountService;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/account")

@Controller

public class AccountLoginController {
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private HttpSession session;
	
	@GetMapping("/login")
	public String getAccountLoginPage() {
		return "login.html";
	}
	
	@PostMapping("/login/process")
	public String login(@RequestParam String email,@RequestParam String password) {
		AccountEntity accountList = accountService.loginAccount(email, password);
		if(accountList == null) {	
			return "redirect:/account/login";
		}else {
			session.setAttribute("account", accountList);
			return "redirect:/user/blog/list";
		}
		
		
	}
	
}
