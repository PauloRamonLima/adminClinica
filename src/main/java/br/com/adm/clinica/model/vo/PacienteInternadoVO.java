package br.com.adm.clinica.model.vo;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PacienteInternadoVO implements Serializable, Comparable<PacienteInternadoVO> {
	
	private static final long serialVersionUID = 4468834832033043208L;

	private String nomePaciente;
	
	private String leito;
	
	private String leitoInternacao;
	
	private String cpf;
	
	public int compareTo(PacienteInternadoVO o) {
        int valor = nomePaciente.compareTo(o.nomePaciente);
        return (valor != 0 ? valor : 1);
    }   
	

}
