package lhos.jompscity.vestibular.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity(name="results")
public class Result {
	
	@Id
	@GeneratedValue(generator="result_id", strategy=GenerationType.SEQUENCE)
	@SequenceGenerator(name="result_id", sequenceName="result_seq", allocationSize=1)
	private Long id;
	
	@ManyToOne
	private Candidate candidate;
	
	private Integer hits;

	public Result() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Candidate getCandidate() {
		return candidate;
	}

	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}

	public Integer getHits() {
		return hits;
	}

	public void setHits(Integer hits) {
		this.hits = hits;
	}
}
