package blog.ex.model.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import blog.ex.model.entity.BlogEntity;


public interface BlogDao extends JpaRepository<BlogEntity, Long> { 
	
	
	List<BlogEntity> findByAccountId(Long AccountId);
	
	BlogEntity save(BlogEntity blogEntity);
	
	BlogEntity findByBlogTitleAndRegisterDate(String blogTitle,LocalDate registerDate);
	
	BlogEntity findByBlogId(Long blogId);
}
