package br.com.adm.clinica.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.adm.clinica.model.Evolucao;
import br.com.adm.clinica.model.Paciente;

public class EvolucaoDAO extends GenericDAO<Evolucao> implements Serializable {

	private static final long serialVersionUID = 61246751347427624L;
	
	@Inject
	private EntityManager em;
	
	public EvolucaoDAO() {
		super(Evolucao.class);
	}
	
	public List<Evolucao> buscarEvolucoesDePaciente(Paciente paciente) {
		  String queryJPQL = "SELECT e FROM Evolucao e WHERE e.paciente = :paciente";
		  return em.createQuery(queryJPQL, Evolucao.class).setParameter("paciente", paciente).getResultList();	 
	}

}
