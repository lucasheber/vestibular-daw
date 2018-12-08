package lhos.jompscity.vestibular.beans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import lhos.jompscity.vestibular.dao.DAO;
import lhos.jompscity.vestibular.model.Course;

@ViewScoped
@ManagedBean
public class CourseBeans {

	private Course course = new Course();
	private DAO<Course> dao = new DAO<>(Course.class);
	
	// Chamado ao clicar no botao cadastrar.
	public void register() {
		
		if (course.getId() == null) {
			course.setTotalSubscribers(0);
			boolean result = dao.insert(course);
	
			if (result)
				course = new Course();
			
		} else {
			dao.update(course);
		}
	}
	
	// Chamado ao clicar no botao remover
	public void delete (Course course) {
		boolean removed = dao.remove(course);
		
		if (removed) {
			System.out.println("## Removido!");
		}
	} // delete

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public List<Course> getCourses() {
		DAO<Course> dao = new DAO<>(Course.class);

		return dao.list();
	}

}
