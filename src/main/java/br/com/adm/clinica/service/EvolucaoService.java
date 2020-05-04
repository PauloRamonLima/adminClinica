package br.com.adm.clinica.service;

import java.util.List;

import br.com.adm.clinica.model.Evolucao;
import br.com.adm.clinica.model.Paciente;

public interface EvolucaoService {
	
	public void salvar(Evolucao evolucao);
	public void deletar(Long id);
	public void alterar(Evolucao evolucao);
	public List<Evolucao> listar();
	public List<Evolucao> buscarEvolucoesDePaciente(Paciente paciente);

}
