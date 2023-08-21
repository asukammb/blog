package blog.ex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import blog.ex.model.entity.AccountEntity;
import blog.ex.service.AccountService;
import jakarta.servlet.http.HttpSession;

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
			return "redirect:/login";
		}else {
			session.setAttribute("account", accountList);
			return "redirect:/blog";
		}
		
		
	}
	
}