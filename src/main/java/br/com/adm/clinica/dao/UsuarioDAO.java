package br.com.adm.clinica.dao;

import javax.persistence.EntityManager;

import br.com.adm.clinica.model.Usuario;
import br.com.adm.clinica.util.JPAResourceBean;

public class UsuarioDAO extends GenericDAO<Usuario> {
	
	public UsuarioDAO() {
		super(Usuario.class);
	}
	
	public Usuario logar(String login, String senha) {
		  EntityManager em = JPAResourceBean.getEntityManager();
		  String jpql = "SELECT u FROM Usuario u WHERE u.login = :login AND u.senha = :senha";
		  return em.createQuery(jpql, Usuario.class).setParameter("login", login).setParameter("senha", senha).getSingleResult();

	}
	
	public Usuario buscarUsuarioPorLogin(String login) {
		  EntityManager em = JPAResourceBean.getEntityManager();
		  String jpql = "SELECT u FROM Usuario u WHERE u.login = :login";
		  return em.createQuery(jpql, Usuario.class).setParameter("login", login).getSingleResult();
	}

}
