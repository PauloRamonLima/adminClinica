package br.com.adm.clinica.util;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAResourceBean {

	private static EntityManagerFactory emFactory;

	/*
	 * @Produces
	 * 
	 * @ApplicationScoped public static EntityManagerFactory getEMF() { if
	 * (emFactory == null) { emFactory =
	 * Persistence.createEntityManagerFactory("medpoint"); } return emFactory; }
	 */

	@Produces
	@ApplicationScoped
	public EntityManagerFactory getEMF() {
		return Persistence.createEntityManagerFactory("medpoint");
	}

	/*
	 * @Produces
	 * 
	 * @RequestScoped public static EntityManager getEntityManager() { return
	 * getEMF().createEntityManager(); }
	 */

	@Produces
	@RequestScoped
	public EntityManager getEntityManager(EntityManagerFactory factory) {
		return factory.createEntityManager();
	}

	public void fecharEntityManager(@Disposes EntityManager manager) {
		if (manager.isOpen()) {
			manager.close();
		}
	}

}
