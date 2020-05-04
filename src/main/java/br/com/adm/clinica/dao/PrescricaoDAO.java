package br.com.adm.clinica.dao;

import java.io.Serializable;

import br.com.adm.clinica.model.Prescricao;

public class PrescricaoDAO extends GenericDAO<Prescricao> implements Serializable {

	private static final long serialVersionUID = -3795072654875203980L;
	
	public PrescricaoDAO() {
		super(Prescricao.class);
	}
	
	//saçvar  deletar, atualizar, listar, buscar por id
	
		
}
