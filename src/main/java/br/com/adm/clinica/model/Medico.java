package br.com.adm.clinica.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "adm_medico")
@Getter
@Setter
public class Medico implements Serializable {

	private static final long serialVersionUID = -3859589623908148839L;
	
	@Id
	@SequenceGenerator(name = "medico_GENERATION", sequenceName = "medico_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "medico_GENERATION")
	@Column(name = "medico_id")
	private Long id;
	
	@Column(name = "medico_nome")
	private String nome;
	
	@Column(name = "medico_crm")
	private String crm;

}
