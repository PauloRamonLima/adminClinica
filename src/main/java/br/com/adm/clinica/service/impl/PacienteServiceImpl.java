package br.com.adm.clinica.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.adm.clinica.dao.PacienteDAO;
import br.com.adm.clinica.model.Paciente;
import br.com.adm.clinica.service.PacienteService;

public class PacienteServiceImpl implements PacienteService, Serializable {

	private static final long serialVersionUID = 3412134457830652674L;
	
	@Inject
	private PacienteDAO pacienteDAO;

	@Override
	public void salvar(Paciente paciente) {
		pacienteDAO.save(paciente);
	}

	@Override
	public void deletar(Long id) {
		pacienteDAO.delete(id);
	}

	@Override
	public Paciente buscarPacientePorCpf(String cpf) {
		return pacienteDAO.buscarPacientePorCpf(cpf);
	}

	@Override
	public void alterar(Paciente paciente) {
		pacienteDAO.update(paciente);
		
	}

	@Override
	public List<Paciente> listar() {
		return pacienteDAO.findAll();
	}

	@Override
	public Paciente buscarPorId(Long id) {
		return pacienteDAO.findById(id);
	}

	@Override
	public Paciente buscarPacientePorNome(String nome) {
		return pacienteDAO.buscarPacientePorNome(nome);
	}

	@Override
	public List<Paciente> buscarPacientesPorLetrasDoNome(String nome) {
		return pacienteDAO.buscarPacientesPorLetrasDoNome(nome);
	}

	@Override
	public Paciente buscarPacientePorRg(String rg) {
		return pacienteDAO.buscarPacientePorRg(rg);
	}
	
}
