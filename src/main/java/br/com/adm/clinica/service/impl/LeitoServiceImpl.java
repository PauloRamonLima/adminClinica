package br.com.adm.clinica.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.adm.clinica.dao.LeitoDAO;
import br.com.adm.clinica.model.Leito;
import br.com.adm.clinica.service.LeitoService;

public class LeitoServiceImpl implements LeitoService, Serializable {

	private static final long serialVersionUID = -7309210826459001738L;
	
	@Inject
	private LeitoDAO leitoDAO;

	@Override
	public void salvar(Leito leito) {
		leitoDAO.save(leito);
	}

	@Override
	public void deletar(Long id) {
		leitoDAO.delete(id);
	}

	@Override
	public void alterar(Leito leito) {
		leitoDAO.update(leito);
	}

	@Override
	public List<Leito> listar() {
		return leitoDAO.findAll();
	}

	@Override
	public Leito buscarPorId(Long id) {
		return leitoDAO.findById(id);
	}

	@Override
	public Leito buscarLeitoPorDescricao(String descricao) {
		return leitoDAO.buscarLeitoPorDescricao(descricao);
	}

}
