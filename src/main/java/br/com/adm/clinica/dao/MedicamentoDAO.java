package br.com.adm.clinica.dao;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.adm.clinica.model.Medicamento;

public class MedicamentoDAO extends GenericDAO<Medicamento> implements Serializable {
	
	private static final long serialVersionUID = 5627910478994035269L;

	@Inject
	private EntityManager em;
	
	public MedicamentoDAO() {
		super(Medicamento.class);		
	}
	
	public Medicamento buscarMedicamentoPorNome(String nome) {
		String queryJpql = "select m from Medicamento m where m.nome = :nome";
		return em.createQuery(queryJpql, Medicamento.class).setParameter("nome", nome).getSingleResult();
	}
}
