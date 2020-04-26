package br.com.adm.clinica.model.vo;

import java.io.Serializable;

public class ReceituarioVO implements Serializable {
	
	private static final long serialVersionUID = 8429310552290977889L;
	
	private String paciente;

	public String getPaciente() {
		return paciente;
	}

	public void setPaciente(String paciente) {
		this.paciente = paciente;
	}

}
