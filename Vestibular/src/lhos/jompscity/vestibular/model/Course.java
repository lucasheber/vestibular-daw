package lhos.jompscity.vestibular.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity(name="courses")
public class Course {
	
	@Id
	@GeneratedValue(generator="course_id", strategy=GenerationType.SEQUENCE)
	@SequenceGenerator(name="course_id", sequenceName="course_seq", allocationSize=1)
	private Long id;
	
	@Column(unique=true)
	private String codeCourse;
	
	private String name;
	
	private String initials;
	
	private Integer numVacancy;
	
	private Integer totalSubscribers; 
	
	@OneToMany(cascade=CascadeType.REMOVE, mappedBy="course")
	private List<Candidate> candidates = new ArrayList<Candidate>();
	
	@OneToMany(cascade=CascadeType.REMOVE, mappedBy="course")
	private List<Classroom> classrooms = new ArrayList<Classroom>();

	public Course() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodeCourse() {
		return codeCourse;
	}

	public void setCodeCourse(String codeCourse) {
		this.codeCourse = codeCourse;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInitials() {
		return initials;
	}

	public void setInitials(String initials) {
		this.initials = initials;
	}

	public Integer getNumVacancy() {
		return numVacancy;
	}

	public void setNumVacancy(Integer numVacancy) {
		this.numVacancy = numVacancy;
	}

	public Integer getTotalSubscribers() {
		return totalSubscribers;
	}

	public void setTotalSubscribers(Integer totalSubscribers) {
		this.totalSubscribers = totalSubscribers;
	}

	public List<Candidate> getCandidates() {
		return candidates;
	}

	public void setCandidates(List<Candidate> candidates) {
		this.candidates = candidates;
	}

	public List<Classroom> getClassrooms() {
		return classrooms;
	}

	public void setClassrooms(List<Classroom> classrooms) {
		this.classrooms = classrooms;
	}
	
	public List<Candidate> getCandidatesByScore() {
		candidates.sort(new Comparator<Candidate>() {
			@Override
			public int compare(Candidate candidate, Candidate anotherCandidate) {
				if (candidate.getResult().getHits() < anotherCandidate.getResult().getHits())
					return -1;
				if (candidate.getResult().getHits() > anotherCandidate.getResult().getHits())
					return 1;
				return 0;
			}
		});
		
		return candidates;
	}
}
