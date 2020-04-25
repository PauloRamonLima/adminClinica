package br.com.adm.clinica.dao;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.adm.clinica.model.Medico;
import br.com.adm.clinica.util.JPAResourceBean;

public class MedicoDAO extends GenericDAO<Medico> {
	
	@Inject
	private EntityManager em;
	
	public MedicoDAO(){
		super(Medico.class);
	}
	
	public Medico buscarMedicoPorNome(String nome) {	   
		  String queryJPQL = "SELECT m FROM Medico m WHERE m.nome = :nome";   
		  return em.createQuery(queryJPQL, Medico.class).setParameter("nome", nome).getSingleResult();	 
	}
	
	public String buscarMedicoPorCrm(String crm) {	   
		  String queryJPQL = "SELECT m.crm FROM Medico m WHERE m.crm = :crm";   
		  return em.createQuery(queryJPQL, String.class).setParameter("crm", crm).getSingleResult();	 
	}

}
