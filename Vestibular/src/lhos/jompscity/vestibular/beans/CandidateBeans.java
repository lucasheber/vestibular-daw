package lhos.jompscity.vestibular.beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import lhos.jompscity.vestibular.dao.DAO;
import lhos.jompscity.vestibular.model.Candidate;
import lhos.jompscity.vestibular.model.Course;

@ViewScoped
@ManagedBean
public class CandidateBeans {
	
	private Candidate candidate = new Candidate();
	private DAO<Candidate> dao = new DAO<>(Candidate.class);
	private DAO<Course> daoCourse = new DAO<>(Course.class);
	private List<Candidate> candidatesBy = new ArrayList<>();
	
	private Long idCourse;
	private Integer vacancy;
	private String numInscricao;
	
	// Para exibir a mensagem de retorno
	private Boolean status = null;
	private String message = "";
	
	public CandidateBeans() {
		
		if (candidate.getId() != null) {
			idCourse = candidate.getCourse().getId();
			
			numVacancy();
		}
	}

	// Chamado ao clicar no botao cadastrar
	public void register () {
		
		if (idCourse == null || idCourse == 0 && candidate.getId() == null) {
			status = false;
			message = "Selecione um curso ou cadastre um antes!";
			
		} else if(candidate.getId() == null) {
			
			Course course = daoCourse.searchById(idCourse);
			
			candidate.setCourse(course);

			if (course.getNumVacancy() - course.getTotalSubscribers() > 0) {
				dao.insert(candidate);
				
				course.setTotalSubscribers(course.getTotalSubscribers() + 1);
				
				daoCourse.update(course);
				
				status = true;
				message = "Candidato cadastrado com sucesso!";
				
				setCandidate(new Candidate());
			} else {
				status = false;
				message = "O curso já atingiu o número de vagas!";
			}
		} else {
			
			if (candidate.getCourse().getId() != idCourse) {
				status = false;
				message = "O curso do candidato não poder ser alterado!";
			} else {
				dao.update(candidate);
				status = true;
				message = "Candidato alterado com sucesso!";
			}
		}
	}// register
	
	// Chamado ao clicar no botao remover
	public void delete (Candidate candidate) {
		Course course = candidate.getCourse();
		
		boolean removed = dao.remove(candidate);
		
		if (removed) {
			course.setTotalSubscribers(course.getTotalSubscribers() - 1);
			daoCourse.update(course);
		}
	} // delete
	
	// Chamado ao mudar o item do select
	public void numVacancy() {
		
		if (idCourse == 0)
			vacancy = 0;
		else {
			Course course = daoCourse.searchById(idCourse);
			vacancy = course.getNumVacancy() - course.getTotalSubscribers();
		}
	}
	
	// Chamado ao clicar no botao pesquisar.
	// 
	public void searchByCourse() {
		candidatesBy.clear();
		
		for (Candidate candidate : getCandidates())
			if (candidate.getCourse().getId() == idCourse)
				candidatesBy.add(candidate);
	}
	
	public void searchByCod() {
		candidatesBy.clear();
		
		for (Candidate candidate : getCandidates())
			if (candidate.getRegistrationNumber().equalsIgnoreCase(numInscricao))
				candidatesBy.add(candidate);
	}
	
	public Candidate getCandidate() {
		return candidate;
	}

	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}

	public Long getIdCourse() {
		return idCourse;
	}

	public void setIdCourse(Long idCourse) {
		this.idCourse = idCourse;
	}

	public Integer getVacancy() {
		return vacancy;
	}

	public void setVacancy(Integer vacancy) {
		this.vacancy = vacancy;
	}
	
	public List<Candidate> getCandidates() {
		return dao.list();
	}
	
	public List<Candidate> getCandidatesByCourse() {
		return candidatesBy;
	}
	
	public List<Candidate> getCandidatesByCod() {
		return candidatesBy;
	}

	public String getNumInscricao() {
		return numInscricao;
	}

	public void setNumInscricao(String numInscricao) {
		this.numInscricao = numInscricao;
	}

	public Boolean getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}
}
