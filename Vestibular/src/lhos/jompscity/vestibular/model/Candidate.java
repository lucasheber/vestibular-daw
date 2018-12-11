package lhos.jompscity.vestibular.model;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.br.CPF;

@Entity(name="Cadidates")
@Table(name="cadidates")
public class Candidate implements Comparable<Candidate> {

	@Id
	@GeneratedValue(generator="candidate_id", strategy=GenerationType.SEQUENCE)
	@SequenceGenerator(name="candidate_id", sequenceName="candidate_seq", allocationSize=1)
	private Long id;
	
	@Column(unique=true)
	private String registrationNumber;
	
	@Column(unique=true)
	@CPF(message="O CPF não é válido!")
	private String cpf;
	
	@NotEmpty(message="O campo nome não pode ficar vazio!")
	private String name;
	
	@Temporal(TemporalType.DATE)
	private Calendar birthday = Calendar.getInstance(); 
	
	private String birthdayStr;
	
	@ManyToOne
	private Course course;

	private String answer;
	
	@NotEmpty(message="Forneça a rua")
	private String street;
	
	@NotEmpty(message="Forneça o bairro")
	private String neighborhood;
	
	private Integer numberHouse;
	
	@NotEmpty(message="Forneça a cidade")
	private String city;
	
	@NotEmpty(message="Foneça o estado")
	private String state;
	
	@NotEmpty(message="Forneca o CEP")
	private String cep;
	
	private String codClassroom;
	
	private String complement;
	
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

	
	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getNeighborhood() {
		return neighborhood;
	}

	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}

	public Integer getNumberHouse() {
		return numberHouse;
	}

	public void setNumberHouse(Integer number) {
		this.numberHouse = number;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getComplement() {
		return complement;
	}

	public void setComplement(String complement) {
		this.complement = complement;
	}

	public String getCodClassroom() {
		return codClassroom;
	}

	public void setCodClassroom(String codClassroom) {
		this.codClassroom = codClassroom;
	}

	@Override
	public int compareTo(Candidate anotherCandidate) {
		return name.compareToIgnoreCase(anotherCandidate.name);
	}
}
