package br.com.adm.clinica.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.adm.clinica.dao.FarmaciaDAO;
import br.com.adm.clinica.model.Farmacia;
import br.com.adm.clinica.service.FarmaciaService;

public class FarmaciaServiceImpl implements FarmaciaService, Serializable {

	private static final long serialVersionUID = 1423878598249955360L;
	
	@Inject
	private FarmaciaDAO farmaaciaDAO;

	@Override
	public void salvar(Farmacia farmacia) {
		
		farmaaciaDAO.save(farmacia);
	}

	@Override
	public void deletar(Long id) {
		
		farmaaciaDAO.delete(id);
	}

	@Override
	public void alterar(Farmacia farmacia) {
		
		farmaaciaDAO.update(farmacia);
	}

	@Override
	public List<Farmacia> listar() {
		return farmaaciaDAO.findAll();
	}

	@Override
	public Farmacia buscarFarmaciaPorMedicamento(String nomeMedicamento) {
		return farmaaciaDAO.buscarFarmaciaPorMedicamento(nomeMedicamento);
	}

}
