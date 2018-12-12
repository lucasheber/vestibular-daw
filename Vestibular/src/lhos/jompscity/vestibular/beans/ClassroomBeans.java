package lhos.jompscity.vestibular.beans;

import java.util.ArrayList;
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
	private DAO<Candidate> daoCandidate = new DAO<>(Candidate.class);
	
	private Long idCourse;
	private Long idClass;
	private Long idCandidate;
	
	private Boolean status = null;
	private String message = "";
	private String search = "";
	
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
	
	// Atualiza todos dos candidatos com o codClass passado para null
	private void updateCandidates(String codClass) {
		EntityManager entityManager = new Connection().getEntityManager(); 
		
		entityManager.getTransaction().begin();
		
		Query query = entityManager.createQuery("update Cadidates set codClassroom = :codEmpty where codClassroom = :cod ");
		
		query.setParameter("codEmpty", null);
		query.setParameter("cod", codClass);
		
		int result = query.executeUpdate();
		
		entityManager.getTransaction().commit();
		entityManager.close();
		
		if (result == 1)
		 System.out.println("## UPDATE " + result);
	}
	
	public void distribute () {
		
		if (idClass == null || idClass == 0) {
			this.status = false;
			this.message = "Escolha uma sala ou cadastre uma!";
			
			return;
		}
		
		Classroom classroom = dao.searchById(idClass);
		Candidate candidate = daoCandidate.searchById(idCandidate);
		
		if (candidate.getClassroom() != null) {
			this.status = false;
			this.message = "O candidato já possui uma sala cadastrada!";
			
			return;
		}
		
		if (candidate.getCourse().getId() != classroom.getCourse().getId()) {
			this.status = false;
			this.message = "O curso do candidato é diferente do curso da sala!";
			
		} else if ( classroom.getCapacity() > 0){
			classroom.setCapacity(classroom.getCapacity() - 1);
			candidate.setClassroom(classroom);
			
			dao.update(classroom);
			daoCandidate.update(candidate);
			
			this.status = true;
			this.message = "Distribção feita com sucesso!";
		} else {
			this.status = false;
			this.message = "A sala já atingiu sua capacidade!";
		}
	}// distribute
	
	public List<Classroom> getClassrooms() {
		
		DAO<Classroom> dao = new DAO<>(Classroom.class);
		List<Classroom> courses = new ArrayList<>();
		
		if (search != null && !search.isEmpty()) {
			for (Classroom classroom : dao.list())
				if (classroom.getCodeClass().equalsIgnoreCase(search))
					courses.add(classroom);
		} else {
			courses = dao.list();
		}
		
		return courses ;
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

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}
	
	public String report() {
		StringBuffer buffer = new StringBuffer("----------------------- Lista de salas -----------------------\n\n");
		
		buffer.append("Código\tCurso\tCapacidade\tVagas disponíveis\n");
		
		for (Classroom classroom : dao.list()) 
			buffer.append(classroom.toString() + "\n");
		
		return buffer.toString();
	}
	
	public String reportCandidatesByClassroom() {
		List<Candidate> candidates;
		StringBuffer buffer = new StringBuffer("----------------------- Lista de candidatso por sala -----------------------\n\n");
		
		for (Classroom classroom : dao.list()) {
			buffer.append(String.format("Sala: %s\n", classroom.getCodeClass()));
			
			candidates = classroom.getCandidates();
			candidates.sort(null);
			
			buffer.append("Número de Inscrição\tData de Nascimento\tColocação\t\tNome\n");
			
			for (Candidate candidate : candidates) 
				buffer.append(candidate.toString() + "\n");
			
			buffer.append(String.format("Total de candidatos: %d\n\n", candidates.size()));	
		}
		
		return buffer.toString();
	}
}
