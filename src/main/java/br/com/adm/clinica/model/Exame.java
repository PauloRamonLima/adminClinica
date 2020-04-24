package br.com.adm.clinica.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "adm_exame")
public class Exame implements Serializable {

	private static final long serialVersionUID = 5551260347343214139L;

	@Id
	@SequenceGenerator(name = "exame_GENERATION", sequenceName = "exame_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "exame_GENERATION")
	@Column(name = "exame_id")
	private Long id;

	@Column(name = "exame_nome")
	private String nome;

	@OneToOne
	@JoinColumn(name = "paciente_id")
	private Paciente paciente;

	@OneToOne
	@JoinColumn(name = "medico_id")
	private Medico medico;

	@Column(name = "exame_data")
	private String data;

	@Column(name = "exame_realizado")
	private boolean realizado;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public boolean isRealizado() {
		return realizado;
	}

	public void setRealizado(boolean realizado) {
		this.realizado = realizado;
	}

}
