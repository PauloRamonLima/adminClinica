package br.com.adm.clinica.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.adm.clinica.model.Exame;
import br.com.adm.clinica.model.Paciente;

public class ExameDAO extends GenericDAO<Exame> implements Serializable {
	
	private static final long serialVersionUID = 3836610515784819946L;
	
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
