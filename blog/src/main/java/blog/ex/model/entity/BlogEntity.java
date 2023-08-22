package blog.ex.model.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "blog")
public class BlogEntity {

	@Id
	
	@Column(name = "blog_id")
	
	@GeneratedValue(strategy = GenerationType.AUTO)

	private Long blogId;
	
	@NonNull	
	@Column(name = "blog_title")
	private String blogTitle;
	
	@NonNull	
	@Column(name = "category_name")
	private String categoryName;
	
	@NonNull	
	@Column(name = "blog_image")
	private String blogImage;
	
	@NonNull	
	@Column(name = "article")
	private String blogDetail;
	
	@NonNull	
	@DateTimeFormat(pattern ="yyyy-MM-dd")
	@Column(name = "blog_created_at")
	private LocalDate registerDate;
	
	@NonNull	
	@Column(name = "account_id")
	private Long accountId;
	
	public BlogEntity(@Nonnull String blogTitle,@Nonnull LocalDate registerDate,@Nonnull String blogImage,
			@Nonnull String blogDetail,@Nonnull String categoryName,Long accountId) {
		this.blogTitle = blogTitle;
		this.categoryName = categoryName;
		this.blogImage = blogImage;
		this.blogDetail = blogDetail;
		this.registerDate = registerDate;
		this.accountId = accountId;
	}

}
