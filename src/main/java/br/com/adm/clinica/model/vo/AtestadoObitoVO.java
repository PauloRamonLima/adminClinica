package br.com.adm.clinica.model.vo;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AtestadoObitoVO implements Serializable{
	
	private static final long serialVersionUID = -6039648599560888491L;

	private String nome;
	
	private String horario;
	
	private String cpf;
	
	private String rg;
	
	private String nascimento;
	
	private String municipio;
	
	private String uf;
	
	private String motivo;

}
