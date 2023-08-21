package blog.ex.controller;


import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import blog.ex.model.entity.AccountEntity;
import blog.ex.model.entity.BlogEntity;
import blog.ex.service.BlogService;
import jakarta.servlet.http.HttpSession;

@Controller
public class BlogController {
	@Autowired
	private BlogService blogService;
	
	@Autowired
	private HttpSession session;
	
	@GetMapping("/list")
	public String getBlogListPage(Model model) {
		AccountEntity accountList = (AccountEntity) session.getAttribute("account");
		Long accountId = accountList.getAccountId();
		
		String accountName = accountList.getAccountName();
		List<BlogEntity>blogList = blogService.findAllBlogPost(accountId);
		
		model.addAttribute("accountName",accountName);
		model.addAttribute("blogList",blogList);
		return "blog-list.html";
	}
	
	@GetMapping("/blog-register")
	public String getBlogRegisterPage(Model model) {
		AccountEntity accountList = (AccountEntity) session.getAttribute("account");
		String accountName = accountList.getAccountName();
		model.addAttribute("accountName",accountName);
		model.addAttribute("registerMessage","新規記事追加");
		return "blog_register.html";
	}
	
	@PostMapping("/register/process")
	public String blogRegister(@RequestParam String blogTitle,
			@RequestParam LocalDate registerDate,
			@RequestParam String category,
			@RequestParam MultipartFile blogImage,
			@RequestParam String blogDetail,Model model) {
		AccountEntity accountList = (AccountEntity) session.getAttribute("account");
		Long accountId = accountList.getAccountId();
		
		String fileName = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-").format(new Date()) + blogImage.getOriginalFilename();
		try {
			Files.copy(blogImage.getInputStream(),Path.of("src/main/resources/static/blog-image/" + fileName));
		}catch(Exception e) {
		e.printStackTrace();
		}
		if(blogService.createBlogPost(blogTitle, registerDate, fileName, blogDetail, category, accountId)) {
			return "blog_register_fix.html";
		}else {
			model.addAttribute("registerMessage", "既に登録済みです");
			return "blog_register.html";
		}
	}
	@GetMapping("/edit/{blogId}")
	public String getBlogEditPage(@PathVariable Long blogId,Model model) {
		AccountEntity accountList = (AccountEntity) session.getAttribute("account");
		String accountName = accountList.getAccountName();
		model.addAttribute("accountName", accountName);
		
		BlogEntity blogList = blogService.getBlogPost(blogId);
		if(blogList == null) {
			return "redirecr:/blog";
		}else {
			model.addAttribute("blogList", blogList);
			model.addAttribute("editMessage", "記事編集");
			return "blog-edit.html";
		}
	}
	
	@PostMapping("/update")
	public String blogUpdate(@RequestParam String blogTitle,
			@RequestParam String category,
			@RequestParam String blogDetail,
			@RequestParam Long blogId,Model model) {
		
		AccountEntity accountList = (AccountEntity) session.getAttribute("account");
		Long accountId = accountList.getAccountId();
		
		
		if(blogService.editBlogPost(blogTitle, registerDate, blogDetail, category, accountId, blogId)) {
			return "blog_edit_fix.html";
		}else {
			model.addAttribute("registerMessage", "更新失敗しました");
			return "blog-edit.html";
		}
	}
	@GetMapping("/image/edit/{blogId}")
	public String getBlogEditImagePage(@PathVariable Long blogId,Model model) {

		AccountEntity accountList = (AccountEntity) session.getAttribute("account");
		
		String accountName = accountList.getAccountName();
		model.addAttribute("accountName", accountName);
		
		BlogEntity blogList = blogService.getBlogPost(blogId);
		if(blogList == null) {
			return "redirecr:/blog";
		}else {
			model.addAttribute("blogList", blogList);
			model.addAttribute("editImageMessage", "画像編集");
			return "blog_img_edit.html";
		}		
	}
	@PostMapping("/image/update")
	
	public String blogImgUpdate(
			@RequestParam MultipartFile blogImage,
			@RequestParam Long blogId,Model model) {
		
		AccountEntity accountList = (AccountEntity) session.getAttribute("account");
		Long accountId = accountList.getAccountId();
		
		String fileName = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-").format(new Date()) + blogImage.getOriginalFilename();
		try {
			
			Files.copy(blogImage.getInputStream(), Path.of("src/main/resources/static/blog-image/" + fileName));
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		if(blogService.editBlogImage(blogId, fileName, accountId)) {
			return "blog_edit_fix.html";
		}else {
			BlogEntity blogList = blogService.getBlogPost(blogId);
			model.addAttribute("blogList",blogList);
			model.addAttribute("editImageMessage", "更新失敗");
			return "blog_img_edit.html";
		}

	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
