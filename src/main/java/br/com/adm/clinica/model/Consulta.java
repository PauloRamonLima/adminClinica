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

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "adm_consulta")
@Getter
@Setter
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
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "medico_id", referencedColumnName = "usuario_id")
	private Usuario medico;

	
	@Column(name = "exame_realizado")
	private boolean realizado;

}
