package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.service.AccountService;


@RequestMapping("/account")


@Controller
public class AccountRegitserController {
	@Autowired
	private AccountService accountService;
	
	@GetMapping("/register")
	public String getAccountRegisterPage() {
		return "register.html";
	}
	
	@PostMapping("/register")
	public String register(@RequestParam String accountName,@RequestParam String email,@RequestParam String password) {
		if(accountService.createAccount(accountName, email,password)) {
			return "redirect:login.html";
		}else {
			return "register.html";
		}
		
	}
	

}
