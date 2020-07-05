package br.com.adm.clinica.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.adm.clinica.model.Paciente;


public class PacienteDAO extends GenericDAO<Paciente> implements Serializable{
	
	private static final long serialVersionUID = -1112344105376773637L;
	
	@Inject
	private EntityManager em;
	
	public PacienteDAO() {
		super(Paciente.class);
	}
	
	public Paciente buscarPacientePorNome(String nome) {	   
		  String queryJPQL = "SELECT p FROM Paciente p WHERE p.nome = :nome";   
		  return em.createQuery(queryJPQL, Paciente.class).setParameter("nome", nome).getSingleResult();	 
	}
	
	public List<Paciente> buscarPacientesPorLetrasDoNome(String nome) {
		  String queryJPQL = "SELECT p FROM Paciente p WHERE p.nome LIKE :nome";
		  return em.createQuery(queryJPQL, Paciente.class).setParameter("nome","%" + nome.toUpperCase() + "%").getResultList();	
	}
	
	public Paciente buscarPacientePorCpf(String cpf) {
		  String queryJPQL = "SELECT p FROM Paciente p WHERE p.cpf = :cpf";
		  return em.createQuery(queryJPQL, Paciente.class).setParameter("cpf", cpf).getSingleResult();	 
	}
	
	public Paciente buscarPacientePorRg(String rg) {
		  String queryJPQL = "SELECT p FROM Paciente p WHERE p.rg = :rg";   
		  return em.createQuery(queryJPQL, Paciente.class).setParameter("rg", rg).getSingleResult();	 
	}
	
}
