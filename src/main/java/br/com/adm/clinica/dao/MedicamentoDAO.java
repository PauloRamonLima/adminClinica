package br.com.adm.clinica.dao;

import java.io.Serializable;

import br.com.adm.clinica.model.Medicamento;

public class MedicamentoDAO extends GenericDAO<Medicamento> implements Serializable {
	
	private static final long serialVersionUID = 5627910478994035269L;

	public MedicamentoDAO() {
		super(Medicamento.class);		
	}
}
