package br.com.adm.clinica.model.builder;

import br.com.adm.clinica.model.Exame;
import br.com.adm.clinica.model.Paciente;
import br.com.adm.clinica.model.Usuario;

public class ExameBuilder {
	
	private Exame instancia;
	
	public ExameBuilder() {
		this.instancia = new Exame();
	}
	
	public Exame construir() {
		return this.instancia;
	}
	
	public ExameBuilder addPaciente(Paciente paciente) {
		this.instancia.setPaciente(paciente);
		return this;
	}
	
	public ExameBuilder addMedico(Usuario medico) {
		this.instancia.setMedico(medico);
		return this;
	}
	
	
	public ExameBuilder addNomeDataRealizado(String nome, String data, boolean realizado) {
		this.instancia.setNome(nome);
		this.instancia.setData(data);
		this.instancia.setRealizado(realizado);
		return this;
	}
}
