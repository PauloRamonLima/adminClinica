package br.com.adm.clinica.model.vo;

import java.io.Serializable;

public class AtestadoMedicoVO implements Serializable {
	
	private static final long serialVersionUID = 3049920242313511430L;

	private String nome;
	
	private String dias;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDias() {
		return dias;
	}

	public void setDias(String dias) {
		this.dias = dias;
	}

}
