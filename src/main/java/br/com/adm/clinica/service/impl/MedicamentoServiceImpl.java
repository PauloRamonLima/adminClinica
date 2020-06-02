package br.com.adm.clinica.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.adm.clinica.dao.MedicamentoDAO;
import br.com.adm.clinica.model.Medicamento;
import br.com.adm.clinica.model.Medico;
import br.com.adm.clinica.service.MedicamentoService;

public class MedicamentoServiceImpl implements MedicamentoService, Serializable {
	
	private static final long serialVersionUID = 7926927024524823388L;

	
	@Inject
	private MedicamentoDAO medicamentoDAO;


	@Override
	public void salvar(Medicamento medicamento) {
		medicamentoDAO.save(medicamento);
	}


	@Override
	public void deletar(Long id) {
		medicamentoDAO.delete(id);
	}


	@Override
	public void alterar(Medicamento medicamento) {
		medicamentoDAO.update(medicamento);
	}


	@Override
	public List<Medicamento> listar() {
		return medicamentoDAO.findAll();
	}


	@Override
	public Medicamento buscarPorId(Long id) {
		return medicamentoDAO.findById(id);
	}


	@Override
	public Medicamento buscarMedicamentoPorNome(String nome) {
		return medicamentoDAO.buscarMedicamentoPorNome(nome);
	}

}
