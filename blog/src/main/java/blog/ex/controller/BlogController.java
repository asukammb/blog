package blog.ex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import blog.ex.service.BlogService;
import jakarta.servlet.http.HttpSession;

@Controller
public class BlogController {
	@Autowired
	private BlogService blogService;
	
	@Autowired
	private HttpSession session;
	
	@GetMapping("/list")
	public String getBlogListPage(Model,model) {
		AccountEntity AccountList = (AccountEntity) session.getAttribute("account");
		Long accountId = accountList.getAccountId();
		
		String accountName = accountList.getAccountName();
		
		model.addAttribute("accountName",accountName);
		model.addAttribute("blogList",blogList);
		return "blog-list.html";
	}

}
