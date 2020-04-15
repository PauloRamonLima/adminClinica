package br.com.adm.clinica.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.adm.clinica.model.Consulta;
import br.com.adm.clinica.model.Paciente;
import br.com.adm.clinica.util.JPAResourceBean;

public class ConsultaDAO extends GenericDAO<Consulta> {
	
	public ConsultaDAO() {
		super(Consulta.class);
	}
	
	public List<Consulta> buscarConsultaPorPaciente(Paciente paciente) {	   
		  EntityManager em = JPAResourceBean.getEntityManager();	   
		  String queryJPQL = "SELECT c FROM Consulta c WHERE c.paciente = :paciente";   
		  return em.createQuery(queryJPQL, Consulta.class).setParameter("paciente", paciente).getResultList();	 
	}

}
