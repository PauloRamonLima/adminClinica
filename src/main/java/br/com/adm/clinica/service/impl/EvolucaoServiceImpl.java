package br.com.adm.clinica.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.adm.clinica.dao.EvolucaoDAO;
import br.com.adm.clinica.model.Evolucao;
import br.com.adm.clinica.model.Paciente;
import br.com.adm.clinica.service.EvolucaoService;

public class EvolucaoServiceImpl implements Serializable, EvolucaoService {

	private static final long serialVersionUID = -1769899865038661026L;
	
	@Inject
	private EvolucaoDAO evolucaoDAO;

	@Override
	public void salvar(Evolucao evolucao) {
		evolucaoDAO.save(evolucao);
	}

	@Override
	public void deletar(Long id) {
		evolucaoDAO.delete(id);
	}

	@Override
	public void alterar(Evolucao evolucao) {
		evolucaoDAO.update(evolucao);
	}

	@Override
	public List<Evolucao> listar() {
		return evolucaoDAO.findAll();
	}

	@Override
	public List<Evolucao> buscarEvolucoesDePaciente(Paciente paciente) {
		return evolucaoDAO.buscarEvolucoesDePaciente(paciente);
	}
	
}
