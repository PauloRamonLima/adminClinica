package br.com.adm.clinica.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.adm.clinica.model.Consulta;
import br.com.adm.clinica.model.Paciente;

public class ConsultaDAO extends GenericDAO<Consulta> implements Serializable {
	
	private static final long serialVersionUID = -1221393016954393940L;
	
	@Inject
	private EntityManager em;
	
	public ConsultaDAO() {
		super(Consulta.class);
	}
	
	public List<Consulta> buscarConsultaPorPaciente(Paciente paciente) {	   
		  String queryJPQL = "SELECT c FROM Consulta c WHERE c.paciente = :paciente";   
		  return em.createQuery(queryJPQL, Consulta.class).setParameter("paciente", paciente).getResultList();	 
	}

}
