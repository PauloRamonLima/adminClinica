package br.com.adm.clinica.dao;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.adm.clinica.model.Farmacia;

public class FarmaciaDAO extends GenericDAO<Farmacia> implements Serializable{

	private static final long serialVersionUID = -7816004473264480122L;
	
	@Inject
	private EntityManager em;
	
	public FarmaciaDAO() {
		super(Farmacia.class);
	}
	
	public Farmacia buscarFarmaciaPorMedicamento(String nomeMedicamento) {
		String queryJpql = "select medicamento.nome from Farmacia where medicamento.nome = :nomeMedicamento";
		return em.createQuery(queryJpql, Farmacia.class).setParameter("nomeMedicamento", nomeMedicamento).getSingleResult();
	}

}
