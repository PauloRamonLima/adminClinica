package br.com.adm.clinica.service;

import java.util.List;

import br.com.adm.clinica.model.Medico;

public interface MedicoService {
	
	public void salvar(Medico medico);
	public void deletar(Long id);
	public void alterar(Medico medico);
	public List<Medico> listar();
	public Medico buscarPorId(Long id);
	public Medico buscarMedicoPorNome(String nome);
	public String buscarMedicoPorCrm(String crm);



}
