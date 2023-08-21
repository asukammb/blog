package blog.ex.model.entity;

import java.time.LocalDateTime;

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
@Table(name = "account")
public class AccountEntity {

	@Id
	
	@Column(name = "account_id")
	
	@GeneratedValue(strategy = GenerationType.AUTO)

	private Long accountId;
	
	@NonNull	
	@Column( name = "account_name")
	private String accountName;
	
	@NonNull
	@Column(name = "account_email")
	private String email;
	
	@NonNull	
	@Column( name = "password")
	private String password;
	
	@NonNull	
	@Column( name = "created_at")
	private LocalDateTime registerDate;
}
	

