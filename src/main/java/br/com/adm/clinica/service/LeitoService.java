package br.com.adm.clinica.service;

import java.util.List;

import br.com.adm.clinica.model.Leito;

public interface LeitoService {
	
	public void salvar(Leito leito);
	public void deletar(Long id);
	public void alterar(Leito leito);
	public List<Leito> listar();
	public Leito buscarPorId(Long id);
	public Leito buscarLeitoPorDescricao(String descricao);
}
