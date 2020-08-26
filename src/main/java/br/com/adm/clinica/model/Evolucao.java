package br.com.adm.clinica.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "adm_evolucao")
public class Evolucao implements Serializable {

	private static final long serialVersionUID = -4050484052109133500L;

	@Id
	@SequenceGenerator(name = "evolucao_GENERATION", sequenceName = "evolucao_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "evolucao_GENERATION")
	@Column(name = "evolucao_id")
	private Long id;
	
	@Column(name = "evolucao_descEvolucao")
	private String descEvoucao;
	
	@ManyToOne
	@JoinColumn(name = "paciente_id")
	private Paciente paciente;
	
}
