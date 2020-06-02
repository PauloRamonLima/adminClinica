package br.com.adm.clinica.service;

import java.util.List;

import br.com.adm.clinica.model.Farmacia;

public interface FarmaciaService {
	
	public void salvar(Farmacia farmacia);
	public void deletar(Long id);
	public void alterar(Farmacia farmacia);
	public List<Farmacia> listar();
	public Farmacia buscarFarmaciaPorMedicamento(String nomeMedicamento);
}
