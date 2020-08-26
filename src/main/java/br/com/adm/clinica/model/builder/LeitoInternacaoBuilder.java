package br.com.adm.clinica.model.builder;

import br.com.adm.clinica.model.LeitoInternacao;

public class LeitoInternacaoBuilder {
	
	private LeitoInternacao instancia;
	
	public LeitoInternacaoBuilder() {
		this.instancia = new LeitoInternacao();
	}
	
	public LeitoInternacao construir() {
		return this.instancia;
	}
}
