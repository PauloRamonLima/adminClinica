package br.com.adm.clinica.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "adm_consulta")
public class Consulta implements Serializable {

	private static final long serialVersionUID = -4551323848788724456L;
	
	@Id
	@SequenceGenerator(name = "consulta_GENERATION", sequenceName = "consulta_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "consulta_GENERATION")
	@Column(name = "consulta_id")
	private Long id;
	
	@Column(name = "consulta_data")
	private String data;
	
	@OneToOne
	@JoinColumn(name = "paciente_id")
	private Paciente paciente;

	@OneToOne
	@JoinColumn(name = "medico_id")
	private Medico medico;
	
	@Column(name = "exame_realizado")
	private boolean realizado;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public boolean isRealizado() {
		return realizado;
	}

	public void setRealizado(boolean realizado) {
		this.realizado = realizado;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	
}
