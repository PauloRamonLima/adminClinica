package br.com.adm.clinica.service;

import java.util.List;

import br.com.adm.clinica.model.Exame;
import br.com.adm.clinica.model.Paciente;

public interface ExameService {
	
	public void salvar(Exame exame);
	public void deletar(Long id);
	public void alterar(Exame exame);
	public List<Exame> listar();
	public Exame buscarPorId(Long id);
	public List<Exame> buscarExamesPorPaciente(Paciente paciente);

}
