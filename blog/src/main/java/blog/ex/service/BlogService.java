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
	public boolean createBlogPost(String blogTitle,LocalDate registerDate,String fileName,String blogDetail,String category,Long accountId) {
		if(blogList == null) {
			blogDao.save(new BlogEntity(blogTitle,registerDate,fileName,blogDetail,category,accountId));
			return true;
		}else {
			return false;
		}
	}
	
}
