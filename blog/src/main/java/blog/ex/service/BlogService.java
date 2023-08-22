package blog.ex.service;


import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import blog.ex.model.dao.BlogDao;
import blog.ex.model.entity.BlogEntity;

@Service
public class BlogService {
	@Autowired
	private BlogDao blogDao;

	public List<BlogEntity> findAllBlogPost(Long accountId){
		if(accountId == null) {
			return null;
		}else {
			return blogDao.findByAccountId(accountId);
		}
	}
	public boolean createBlogPost(String blogTitle,LocalDate registerDate,String fileName,String blogDetail,String category,Long accountId){
		BlogEntity blogList = blogDao.findByBlogTitleAndRegisterDate(blogTitle,registerDate);
		if(blogList == null) {
			blogDao.save(new BlogEntity(blogTitle,registerDate,fileName,blogDetail,category,accountId));
			return true;
		}else {
			return false;
		}
	}
	
	public BlogEntity getBlogPost(Long blogId) {
		if(blogId == null) {
			return null;
		}else {
			return blogDao.findByBlogId(blogId);
		}
	}
	
	public boolean editBlogPost(String blogTitle,LocalDate registerDate,String blogDetail,String category,Long accountId,Long blogId) {
		BlogEntity blogList = blogDao.findByBlogId(blogId);
		if(accountId == null) {
			return false;
		}else {
			blogList.setBlogId(blogId);
			blogList.setBlogTitle(blogTitle);
			blogList.setRegisterDate(registerDate);
			blogList.setCategoryName(category);
			blogList.setBlogDetail(blogDetail);
			blogList.setAccountId(accountId);
			blogDao.save(blogList);
			return true;
			
		}
	}
	public boolean editBlogImage(Long blogId,String fileName,Long accountId) {
		BlogEntity blogList = blogDao.findByBlogId(blogId);
		if(fileName == null || blogList.getBlogImage().equals(fileName)) {
			return false;
		}else {
			blogList.setBlogId(blogId);
			blogList.setBlogImage(fileName);
			blogList.setAccountId(accountId);
			blogDao.save(blogList);
			return true;
		}
	}
	public boolean deleteBlog(Long blogId) {
		if(blogId == null) {
			return false;
		}else {
			blogDao.deleteByBlogId(blogId);
			return true;
		}
	}
	
}
