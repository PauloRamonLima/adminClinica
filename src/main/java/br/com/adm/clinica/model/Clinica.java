package br.com.adm.clinica.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "adm_clinica")
public class Clinica {
	
	@Id
	@SequenceGenerator(name = "clinica_GENERATION", sequenceName = "clinica_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "clinica_GENERATION")
	@Column(name = "clinica_id")
	private Long id;
	
	@Column(name = "clinica_descricao")
	private String descricao;
	
	@Column(name = "clinica_pediatrica")
	private boolean flagPediatrica;
	
	@Column(name = "clinica_uti")
	private boolean flagUti;
	
}
