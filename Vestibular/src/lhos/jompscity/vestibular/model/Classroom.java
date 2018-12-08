package lhos.jompscity.vestibular.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
	
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private Course course;

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
}
