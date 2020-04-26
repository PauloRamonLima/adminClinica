package br.com.adm.clinica.service;

import java.util.List;

import br.com.adm.clinica.model.Paciente;

public interface PacienteService {
	
	public void salvar(Paciente paciente);
	public void deletar(Long id);
	public Paciente buscarPacientePorCpf(String cpf);
	public void alterar(Paciente paciente);
	public List<Paciente> listar();
	public Paciente buscarPorId(Long id);
	public Paciente buscarPacientePorNome(String nome);
	public List<Paciente> buscarPacientesPorLetrasDoNome(String nome);
	public Paciente buscarPacientePorRg(String rg);
	

}
