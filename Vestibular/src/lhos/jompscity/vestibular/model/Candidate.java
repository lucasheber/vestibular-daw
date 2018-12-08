package lhos.jompscity.vestibular.model;

import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.NotEmpty;

@Entity(name="Cadidates")
@Table(name="cadidates")
public class Candidate {

	@Id
	@GeneratedValue(generator="candidate_id", strategy=GenerationType.SEQUENCE)
	@SequenceGenerator(name="candidate_id", sequenceName="candidate_seq")
	private Long id;
	
	@Column(unique=true)
	private String registrationNumber;
	
	@Column(unique=true)
	private String cpf;
	
	@NotEmpty
	private String name;
	
	@Temporal(TemporalType.DATE)
	private Calendar birthday = Calendar.getInstance(); 
	
	private String birthdayStr;
	
	@OneToOne(cascade=CascadeType.ALL)
	private Address address = new Address();
	
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private Course course;
	
	private String answer;
	
	private Integer score;

	public Candidate() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRegistrationNumber() {
		return String.format("%s%05d", course.getInitials().toUpperCase(), id );
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Calendar getBirthday() {
		return birthday;
	}

	public void setBirthday(Calendar birthday) {
		this.birthday = birthday;
	}

	public String getBirthdayStr() {
		return birthdayStr;
	}

	public void setBirthdayStr(String birthdayStr) {
		this.birthdayStr = birthdayStr;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return String.format(
				"Candidate [id=%s, registrationNumber=%s, cpf=%s, name=%s, birthday=%s, birthdayStr=%s, address=%s, answer=%s, score=%s]",
				id, registrationNumber, cpf, name, birthday, birthdayStr, address, answer, score);
	}

	
	
	
}
