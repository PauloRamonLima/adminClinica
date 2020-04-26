package br.com.adm.clinica.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "adm_leitoInternacao")
@Getter
@Setter
public class LeitoInternacao implements Serializable {

	private static final long serialVersionUID = 4374106940293223559L;
	
	@Id
	@SequenceGenerator(name = "leitoInternacao_GENERATION", sequenceName = "leitoInternacao_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "leitoInternacao_GENERATION")	@Column(name = "leitoInternacao_id")
	private Long id;
	
	@Column(name = "leitoInternacao_numero")
	private Long numero;
	
	@ManyToOne()
	@JoinColumn(name = "leito_id")
	private Leito leito;
	
	@OneToOne()
	@JoinColumn(name = "paciente_id")
	private Paciente paciente;
	
	@Column(name = "leitoInternacao_dataInternacao")
	private Date dataInternacao;
	
}
