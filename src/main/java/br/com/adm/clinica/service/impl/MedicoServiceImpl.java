package br.com.adm.clinica.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.adm.clinica.dao.MedicoDAO;
import br.com.adm.clinica.model.Medico;
import br.com.adm.clinica.service.MedicoService;

public class MedicoServiceImpl implements MedicoService, Serializable {

	private static final long serialVersionUID = -8117153318284873518L;
	
	@Inject
	private MedicoDAO medicoDAO;

	@Override
	public void salvar(Medico medico) {
		medicoDAO.save(medico);
	}

	@Override
	public void deletar(Long id) {
		medicoDAO.delete(id);
	}

	@Override
	public void alterar(Medico medico) {
		medicoDAO.update(medico);
	}

	@Override
	public List<Medico> listar() {
		return medicoDAO.findAll();
	}

	@Override
	public Medico buscarPorId(Long id) {
		return medicoDAO.findById(id);
	}

	@Override
	public Medico buscarMedicoPorNome(String nome) {
		return medicoDAO.buscarMedicoPorNome(nome);
	}

	@Override
	public String buscarMedicoPorCrm(String crm) {
		return medicoDAO.buscarMedicoPorCrm(crm);
	}

}
