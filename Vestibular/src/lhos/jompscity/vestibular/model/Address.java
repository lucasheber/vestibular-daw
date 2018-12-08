package lhos.jompscity.vestibular.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity(name="Address")
@Table(name="address")
public class Address {

	@Id
	@GeneratedValue(generator="address_id", strategy=GenerationType.SEQUENCE)
	@SequenceGenerator(name="address_id", sequenceName="address_seq", allocationSize=1)
	private Long id;
	
	@NotEmpty
	private String street;
	
	@NotEmpty
	private String neighborhood;
	
	private Integer number;
	
	@NotEmpty
	private String city;
	
	@NotEmpty
	private String state;
	
	@NotEmpty
	private String cep;
	
	private String complement;
	
	@OneToOne(cascade=CascadeType.PERSIST)
	private Candidate candidate;

	public Address() {}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Candidate getCandidate() {
		return candidate;
	}

	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}

	public String getNeighborhood() {
		return neighborhood;
	}

	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
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

	@Override
	public String toString() {
		return String.format(
				"Address [id=%s, street=%s, neighborhood=%s, number=%s, city=%s, state=%s, cep=%s, complement=%s, candidate=%s]",
				id, street, neighborhood, number, city, state, cep, complement, candidate);
	}
	
	
}
