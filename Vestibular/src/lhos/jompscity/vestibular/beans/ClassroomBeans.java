package lhos.jompscity.vestibular.beans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import lhos.jompscity.vestibular.dao.DAO;
import lhos.jompscity.vestibular.model.Classroom;
import lhos.jompscity.vestibular.model.Course;

@ViewScoped
@ManagedBean
public class ClassroomBeans {

	private Classroom classroom = new Classroom();
	private DAO<Classroom> dao = new DAO<>(Classroom.class);
	
	private Long idCourse;
	
	public void register() {
		
		if (classroom.getId() == null) {
			DAO<Course> daoCourse = new DAO<>(Course.class);
			Course course = daoCourse.searchById(idCourse);
			
			classroom.setId(null);
			classroom.setCourse(course);
	
			dao.insert(classroom);
			setClassroom(new Classroom());
			
		} else {
			dao.update(classroom);
		}
	}
	
	// Chamado ao clicar no botao remover
	public void delete (Classroom classroom) {

		dao.remove(classroom);
		
	} // delete
	
	
	public List<Classroom> getClassrooms() {
		return dao.list();
	}
	
	public Classroom getClassroom() {
		return classroom;
	}

	public void setClassroom(Classroom classroom) {
		this.classroom = classroom;
	}

	public Long getIdCourse() {
		return idCourse;
	}

	public void setIdCourse(Long idCourse) {
		this.idCourse = idCourse;
	}
	
	
}
