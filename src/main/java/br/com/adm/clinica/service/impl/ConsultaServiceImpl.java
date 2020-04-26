package br.com.adm.clinica.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.adm.clinica.dao.ConsultaDAO;
import br.com.adm.clinica.model.Consulta;
import br.com.adm.clinica.model.Paciente;
import br.com.adm.clinica.service.ConsultaService;

public class ConsultaServiceImpl implements ConsultaService, Serializable {

	private static final long serialVersionUID = -3468034349366209046L;
	
	@Inject
	private ConsultaDAO consultaDAO;

	@Override
	public void salvar(Consulta consulta) {
		consultaDAO.save(consulta);
	}

	@Override
	public void deletar(Long id) {
		consultaDAO.delete(id);
	}

	@Override
	public void alterar(Consulta consulta) {
		consultaDAO.update(consulta);
	}

	@Override
	public List<Consulta> listar() {
		return consultaDAO.findAll();
	}

	@Override
	public Consulta buscarPorId(Long id) {
		return consultaDAO.findById(id);
	}

	@Override
	public List<Consulta> buscarConsultaPorPaciente(Paciente paciente) {
		return consultaDAO.buscarConsultaPorPaciente(paciente);
	}

}
