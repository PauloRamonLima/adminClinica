package br.com.adm.clinica.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.adm.clinica.model.Leito;

public class LeitoDAO extends GenericDAO<Leito> {
	
	public LeitoDAO() {
		super(Leito.class);
	}
	
	@PersistenceContext
	private EntityManager em;
	
	public Leito buscarLeitoPorDescricao(String descricao) {
		   	   
		  String queryJPQL = "SELECT l FROM Leito l WHERE l.descricao = :descricao";   
		  return em.createQuery(queryJPQL, Leito.class).setParameter("descricao", descricao).getSingleResult();
		 
	}
}
