package lhos.jompscity.vestibular.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Connection {

//	private static EntityManager entityManager;
	private static EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("vestibular");;
	
	public EntityManager getEntityManager() {
		return managerFactory.createEntityManager();
	}

}
