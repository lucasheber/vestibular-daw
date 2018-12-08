package lhos.jompscity.vestibular.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity(name="Users")
@Table(name="users")
public class User {
	
	@Id
	@GeneratedValue(generator="user_id", strategy=GenerationType.SEQUENCE)
	@SequenceGenerator(name="user_id", sequenceName="user_seq", allocationSize=1)
	private Long id;
	
	@Column(unique=true)
	private String cpf;

	private String password;
	
	private Integer type;

	public User() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
}
