package br.com.adm.clinica.model.vo;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AtestadoMedicoVO implements Serializable {
	
	private static final long serialVersionUID = 3049920242313511430L;

	private String nome;
	
	private String dias;

}
