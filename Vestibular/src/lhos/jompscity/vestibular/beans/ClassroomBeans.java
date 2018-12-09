package lhos.jompscity.vestibular.beans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import lhos.jompscity.vestibular.dao.Connection;
import lhos.jompscity.vestibular.dao.DAO;
import lhos.jompscity.vestibular.model.Candidate;
import lhos.jompscity.vestibular.model.Classroom;
import lhos.jompscity.vestibular.model.Course;

@ViewScoped
@ManagedBean
public class ClassroomBeans {

	private Classroom classroom = new Classroom();
	private DAO<Classroom> dao = new DAO<>(Classroom.class);
//	private DAO<Course> daoCourse = new DAO<>(Course.class);
	private DAO<Candidate> daoCandidate = new DAO<>(Candidate.class);
	
	private Long idCourse;
	private Long idClass;
	private Long idCandidate;
	
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

		updateCandidates(classroom.getCodeClass());
		
		dao.remove(classroom);
		
	} // delete
	
	private void updateCandidates(String codClass) {
		EntityManager entityManager = new Connection().getEntityManager(); 
		
		entityManager.getTransaction().begin();
		
		Query query = entityManager.createQuery("update Cadidates set codClassroom = :codEmpty where codClassroom = :cod ");
		
		query.setParameter("codEmpty", null);
		query.setParameter("cod", codClass);
		
		int result = query.executeUpdate();
		
		entityManager.getTransaction().commit();
		entityManager.close();
		
		System.out.println("## UPDATE " + result);
	}
	
	public void distribute () {
		Classroom classroom = dao.searchById(idClass);
		Candidate candidate = daoCandidate.searchById(idCandidate);
		
		if (candidate.getCodClassroom() != null) {
			System.out.println("Já cadastrado!");
			
			return;
		}
		
		if (candidate.getCourse().getId() != classroom.getCourse().getId()) {
			System.out.println("## Curso diferente!");
			
		} else {
			
			candidate.setCodClassroom(classroom.getCodeClass());
			classroom.setCapacity(classroom.getCapacity() - 1);
			
			dao.update(classroom);
			daoCandidate.update(candidate);
	
		}
	}
	
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

	public DAO<Classroom> getDao() {
		return dao;
	}

	public void setDao(DAO<Classroom> dao) {
		this.dao = dao;
	}

	public Long getIdClass() {
		return idClass;
	}

	public void setIdClass(Long idClass) {
		this.idClass = idClass;
	}

	public Long getIdCandidate() {
		return idCandidate;
	}

	public void setIdCandidate(Long idCandidate) {
		this.idCandidate = idCandidate;
	}
}
