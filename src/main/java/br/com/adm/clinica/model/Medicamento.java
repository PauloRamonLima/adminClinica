package br.com.adm.clinica.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "adm_medicamento")
public class Medicamento implements Serializable {
	
	private static final long serialVersionUID = -285594409066347562L;

	@Id
	@SequenceGenerator(name = "medicamento_GENERATION", sequenceName = "medicamento_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "medicamento_GENERATION")
	@Column(name = "medicamento_id")
	private Long id;
	
	@Column(name = "medicamento_nome")
	private String nome;
	
	@Column(name = "medicamento_tipo")
	@Enumerated(EnumType.STRING)
	private TipoMedicamento tipoMedicamento;
	
	@Column(name = "medicamento_tipo_usuario")
	@Enumerated(EnumType.STRING)
	private TipoUsuarioMedicamento tipoUsuarioMedicamento;

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

	public TipoMedicamento getTipoMedicamento() {
		return tipoMedicamento;
	}

	public void setTipoMedicamento(TipoMedicamento tipoMedicamento) {
		this.tipoMedicamento = tipoMedicamento;
	}

	public TipoUsuarioMedicamento getTipoUsuarioMedicamento() {
		return tipoUsuarioMedicamento;
	}

	public void setTipoUsuarioMedicamento(TipoUsuarioMedicamento tipoUsuarioMedicamento) {
		this.tipoUsuarioMedicamento = tipoUsuarioMedicamento;
	}
	

}
