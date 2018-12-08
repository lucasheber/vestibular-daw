package lhos.jompscity.vestibular.dao;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import lhos.jompscity.vestibular.model.User;

public class UserDAO {

	private EntityManager entityManager;
	
	public Boolean exists(User user) {
		this.entityManager = new Connection().getEntityManager(); 
		
		entityManager.getTransaction().begin();
		
		Query query = entityManager.createQuery("from Users u where u.cpf = :pCpf and u.password = :pPass");
		
		query.setParameter("pCpf", user.getCpf());
		query.setParameter("pPass", user.getPassword());
		
		boolean existe = !query.getResultList().isEmpty();
		
		entityManager.getTransaction().commit();
		entityManager.close();
		
		return existe;
	}
}
