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

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "adm_farmacia")
@Getter
@Setter
public class Farmacia implements Serializable {

	private static final long serialVersionUID = 8644843565961175839L;

	@Id
	@SequenceGenerator(name = "farmacia_GENERATION", sequenceName = "farmacia_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "farmacia_GENERATION")
	@Column(name = "farmacia_id")
	private Long id;
	 
	@Column(name = "farmacia_qtd_medicamento")
	private int quantidadeDoMedicamento;
    
	@OneToOne
	@JoinColumn(name = "medicamento_id")
	private Medicamento medicamento;
	
	@Column(name = "farmacia_nome")
	private String nome;

}
