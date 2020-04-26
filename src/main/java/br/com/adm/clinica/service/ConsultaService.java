package br.com.adm.clinica.service;

import java.util.List;

import br.com.adm.clinica.model.Consulta;
import br.com.adm.clinica.model.Paciente;

public interface ConsultaService {
	
	public void salvar(Consulta consulta);
	public void deletar(Long id);
	public void alterar(Consulta consulta);
	public List<Consulta> listar();
	public Consulta buscarPorId(Long id);
	public List<Consulta> buscarConsultaPorPaciente(Paciente paciente);

}
