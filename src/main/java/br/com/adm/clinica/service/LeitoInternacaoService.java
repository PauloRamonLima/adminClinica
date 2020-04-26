package br.com.adm.clinica.service;

import java.util.List;

import br.com.adm.clinica.model.Leito;
import br.com.adm.clinica.model.LeitoInternacao;
import br.com.adm.clinica.model.Paciente;

public interface LeitoInternacaoService {
	
	public void salvar(LeitoInternacao leitoInternacao);
	public void deletar(Long id);
	public void alterar(LeitoInternacao leitoInternacao);
	public List<LeitoInternacao> listar();
	public LeitoInternacao buscarPorId(Long id);
	public List<LeitoInternacao> buscarLeitosInternacaoPorLeito(Leito leito);
	public Long buscarMaiorLeitosInternacaoPorLeito(Long leito);
	public LeitoInternacao buscarLeitoDeInternacaoPorPaciente(Paciente paciente);
	public List<LeitoInternacao> buscarLeitoDeInternacaOcupados(Leito leito);
	public LeitoInternacao buscarLeitoDeInternacaPorLeitoENumeracao(String leito, Long numero);
	public List<LeitoInternacao> buscarLeitosDeInternacaoDesocupados(Leito leito);
	public List<LeitoInternacao> buscarPacientesInternados();

}
