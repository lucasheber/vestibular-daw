package lhos.jompscity.vestibular.beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import lhos.jompscity.vestibular.dao.DAO;
import lhos.jompscity.vestibular.model.Candidate;
import lhos.jompscity.vestibular.model.Course;
import sun.util.resources.cldr.se.CurrencyNames_se;

@ViewScoped
@ManagedBean
public class CourseBeans {

	private Course course = new Course();
	private DAO<Course> dao = new DAO<>(Course.class);
	private List<Course> courses = new ArrayList<>();
	private Boolean status = null;
	private String message = "";
	private String search = "";
	
	// Chamado ao clicar no botao cadastrar.
	public void register() {
		
		if (course.getId() == null) {
			course.setTotalSubscribers(0);
			boolean result = dao.insert(course);
	
			if (result) {
				course = new Course();
				message = "Cadastro realizado com sucesso!";
				status = true;
			} else {
				message = "Ocorreu um erro na inserção dos dados!";
				status = false;
			}
			
		} else {
			dao.update(course);
		}
	}
	
	// Chamado ao clicar no botao remover
	public void delete (Course course) {
		boolean removed = dao.remove(course);
		
		if (removed) {
			message = "Curso removido com sucesso!";
			status = true;
		}
	} // delete

	public void searchBy () {
	
	}
	
	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public List<Course> getCourses() {
		DAO<Course> dao = new DAO<>(Course.class);
		courses.clear();
		
		if (search != null && !search.isEmpty()) {
			for (Course course : dao.list())
				if (course.getCodeCourse().equalsIgnoreCase(search))
					courses.add(course);
		} else {
			courses = dao.list();
		}
		
		return courses ;
	}

	public Boolean getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}
	
	public String reportApprovedCandidates() {
		StringBuffer buffer = new StringBuffer("----------------------- Lista de cursos -----------------------\n\n");
		
		for (Course course : dao.list()) 
			buffer.append(course.toString(course.getCandidatesByScore()));
		
		return buffer.toString();
	}
	
	public String report() {
		StringBuffer buffer = new StringBuffer("----------------------- Lista de cursos -----------------------\n\n");
		
		for (Course course : dao.list()) 
			buffer.append(course.toString(course.getCandidates()));
		
		return buffer.toString();
	}
}
