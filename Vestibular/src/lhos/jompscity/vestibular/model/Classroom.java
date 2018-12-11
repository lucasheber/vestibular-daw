package lhos.jompscity.vestibular.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity(name="Classrooms")
@Table(name="classrooms")
public class Classroom {

	@Id
	@GeneratedValue(generator="classroom_id", strategy=GenerationType.SEQUENCE)
	@SequenceGenerator(name="classroom_id", sequenceName="classroom_seq", allocationSize=1)
	private Long id;

	private Integer capacity;
	
	@ManyToOne
	private Course course;
	
	@OneToMany(cascade=CascadeType.REMOVE, mappedBy="classroom")
	private List<Candidate> candidates = new ArrayList<Candidate>();

	public Classroom() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodeClass() {
		return String.format("%06d", id);
	}

	public Integer getCapacity() {
		return capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}
	
	public List<Candidate> getCandidates() {
		candidates.sort(null);
		return candidates;
	}
	
	public void setCandidates(List<Candidate> candidates) {
		this.candidates = candidates;
	}
}
