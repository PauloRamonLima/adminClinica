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
@Table(name = "adm_exame")
@Getter
@Setter
public class Exame implements Serializable {

	private static final long serialVersionUID = 5551260347343214139L;

	@Id
	@SequenceGenerator(name = "exame_GENERATION", sequenceName = "exame_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "exame_GENERATION")
	@Column(name = "exame_id")
	private Long id;

	@Column(name = "exame_nome")
	private String nome;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "paciente_id")
	private Paciente paciente;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "medico_id", referencedColumnName = "usuario_id")
	private Usuario medico;

	@Column(name = "exame_data")
	private String data;

	@Column(name = "exame_realizado")
	private boolean realizado;

}
