package br.com.adm.clinica.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.adm.clinica.dao.ExameDAO;
import br.com.adm.clinica.model.Exame;
import br.com.adm.clinica.model.Paciente;
import br.com.adm.clinica.service.ExameService;

public class ExameServiceImpl implements ExameService, Serializable {

	private static final long serialVersionUID = -5196307295243196229L;
	
	@Inject
	private ExameDAO exameDAO;

	@Override
	public void salvar(Exame exame) {
		exameDAO.save(exame);
	}

	@Override
	public void deletar(Long id) {
		exameDAO.delete(id);
	}

	@Override
	public void alterar(Exame exame) {
		exameDAO.update(exame);
	}

	@Override
	public List<Exame> listar() {
		return exameDAO.findAll();
	}

	@Override
	public Exame buscarPorId(Long id) {
		return exameDAO.findById(id);
	}

	@Override
	public List<Exame> buscarExamesPorPaciente(Paciente paciente) {
		return exameDAO.buscarExamesPorPaciente(paciente);
	}
	

}
