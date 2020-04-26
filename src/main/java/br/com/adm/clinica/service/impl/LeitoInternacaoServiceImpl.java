package br.com.adm.clinica.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.adm.clinica.dao.LeitoInternacaoDAO;
import br.com.adm.clinica.model.Leito;
import br.com.adm.clinica.model.LeitoInternacao;
import br.com.adm.clinica.model.Paciente;
import br.com.adm.clinica.service.LeitoInternacaoService;

public class LeitoInternacaoServiceImpl implements LeitoInternacaoService, Serializable {

	private static final long serialVersionUID = -7657174924483757439L;
	
	@Inject
	private LeitoInternacaoDAO leitoInternacaoDAO;

	@Override
	public void salvar(LeitoInternacao leitoInternacao) {
		leitoInternacaoDAO.save(leitoInternacao);
	}

	@Override
	public void deletar(Long id) {
		leitoInternacaoDAO.delete(id);
		
	}

	@Override
	public void alterar(LeitoInternacao leitoInternacao) {
		leitoInternacaoDAO.update(leitoInternacao);
		
	}

	@Override
	public List<LeitoInternacao> listar() {
		return leitoInternacaoDAO.findAll();
	}

	@Override
	public LeitoInternacao buscarPorId(Long id) {
		return leitoInternacaoDAO.findById(id);
	}

	@Override
	public List<LeitoInternacao> buscarLeitosInternacaoPorLeito(Leito leito) {
		return leitoInternacaoDAO.buscarLeitosInternacaoPorLeito(leito);
	}

	@Override
	public Long buscarMaiorLeitosInternacaoPorLeito(Long leito) {
		return leitoInternacaoDAO.buscarMaiorLeitosInternacaoPorLeito(leito);
	}

	@Override
	public LeitoInternacao buscarLeitoDeInternacaoPorPaciente(Paciente paciente) {
		return leitoInternacaoDAO.buscarLeitoDeInternacaoPorPaciente(paciente);
	}

	@Override
	public List<LeitoInternacao> buscarLeitoDeInternacaOcupados(Leito leito) {
		return leitoInternacaoDAO.buscarLeitoDeInternacaOcupados(leito);
	}

	@Override
	public LeitoInternacao buscarLeitoDeInternacaPorLeitoENumeracao(String leito, Long numero) {
		return leitoInternacaoDAO.buscarLeitoDeInternacaPorLeitoENumeracao(leito, numero);
	}

	@Override
	public List<LeitoInternacao> buscarLeitosDeInternacaoDesocupados(Leito leito) {
		return leitoInternacaoDAO.buscarLeitosDeInternacaoDesocupados(leito);
	}

	@Override
	public List<LeitoInternacao> buscarPacientesInternados() {
		return leitoInternacaoDAO.buscarPacientesInternados();
	}
}
