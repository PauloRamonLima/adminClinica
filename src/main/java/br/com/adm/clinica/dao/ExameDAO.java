package br.com.adm.clinica.dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.adm.clinica.model.Exame;
import br.com.adm.clinica.model.Paciente;
import br.com.adm.clinica.util.JPAResourceBean;

public class ExameDAO extends GenericDAO<Exame> {
	
	@Inject
	private EntityManager em;
	
	public ExameDAO() {
		super(Exame.class);
	}
	
	public List<Exame> buscarExamesPorPaciente(Paciente paciente) {	   
		  String queryJPQL = "SELECT e FROM Exame e WHERE e.paciente = :paciente";   
		  return em.createQuery(queryJPQL, Exame.class).setParameter("paciente", paciente).getResultList();	 
	}
	
	
	
	

}
