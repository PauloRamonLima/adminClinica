package br.com.adm.clinica.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.adm.clinica.model.Paciente;
import br.com.adm.clinica.util.JPAResourceBean;


public class PacienteDAO extends GenericDAO<Paciente>{
	
	public PacienteDAO() {
		super(Paciente.class);
	}
	
	public Paciente buscarPacientePorNome(String nome) {	   
		  EntityManager em = JPAResourceBean.getEntityManager();	   
		  String queryJPQL = "SELECT p FROM Paciente p WHERE p.nome = :nome";   
		  return em.createQuery(queryJPQL, Paciente.class).setParameter("nome", nome).getSingleResult();	 
	}
	
	public List<Paciente> buscarPacientesPorLetrasDoNome(String nome){
		  EntityManager em = JPAResourceBean.getEntityManager();	   
		  String queryJPQL = "SELECT p FROM Paciente p WHERE p.nome LIKE :nome";   
		  return em.createQuery(queryJPQL, Paciente.class).setParameter("nome","%" + nome.toUpperCase() + "%").getResultList();	
	}
	
	public Paciente buscarPacientePorCpf(String cpf) {	   
		  EntityManager em = JPAResourceBean.getEntityManager();	   
		  String queryJPQL = "SELECT p FROM Paciente p WHERE p.cpf = :cpf";   
		  return em.createQuery(queryJPQL, Paciente.class).setParameter("cpf", cpf).getSingleResult();	 
	}
	
	public Paciente buscarPacientePorRg(String rg) {	   
		  EntityManager em = JPAResourceBean.getEntityManager();	   
		  String queryJPQL = "SELECT p FROM Paciente p WHERE p.rg = :rg";   
		  return em.createQuery(queryJPQL, Paciente.class).setParameter("rg", rg).getSingleResult();	 
	}
	
	
}
