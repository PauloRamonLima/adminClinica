package br.com.adm.clinica.service;

import java.util.List;

import br.com.adm.clinica.model.Medicamento;

public interface MedicamentoService {
	
	public void salvar(Medicamento medicamento);
	public void deletar(Long id);
	public void alterar(Medicamento medicamento);
	public List<Medicamento> listar();
	public Medicamento buscarPorId(Long id);
	public Medicamento buscarMedicamentoPorNome(String nome);

}
