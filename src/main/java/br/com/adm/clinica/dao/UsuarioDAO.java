package br.com.adm.clinica.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.adm.clinica.model.Usuario;

public class UsuarioDAO extends GenericDAO<Usuario> implements Serializable {
	
	private static final long serialVersionUID = 4844343325711167352L;
	
	@Inject
	private EntityManager em;
	
	public UsuarioDAO() {
		super(Usuario.class);
	}
	
	public Usuario logar(String login, String senha) {
		  String jpql = "SELECT u FROM Usuario u WHERE u.login = :login AND u.senha = :senha";
		  return em.createQuery(jpql, Usuario.class).setParameter("login", login).setParameter("senha", senha).getSingleResult();
	}
	
	public Usuario buscarUsuarioPorLogin(String login) {
		  String jpql = "SELECT u FROM Usuario u WHERE u.login = :login";
		  return em.createQuery(jpql, Usuario.class).setParameter("login", login).getSingleResult();
	}
	
	public List<Usuario> buscarMedicos(){
		String jpql = "SELECT u FROM Usuario u WHERE u.usuario_tipo = :MEDICO";
		return em.createQuery(jpql, Usuario.class).getResultList();
	}
	
	public Usuario buscarMedicoPorNome(String nome){
		String jpql = "SELECT u FROM Usuario u WHERE u.usuario_tipo = :MEDICO AND u.nome = :nome";
		return em.createQuery(jpql, Usuario.class).getSingleResult();
	}
}