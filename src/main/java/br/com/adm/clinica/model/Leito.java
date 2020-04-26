package br.com.adm.clinica.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "adm_leito")
@Getter
@Setter
public class Leito implements Serializable {
	
	private static final long serialVersionUID = 6628052714973825174L;

	@Id
	@SequenceGenerator(name = "leito_GENERATION", sequenceName = "leito_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "leito_GENERATION")
	@Column(name = "leito_id")
	private Long id;
	
	@Column(name = "leito_descricao")
	private String descricao;
	
	@OneToMany(
			mappedBy = "leito",
			cascade = CascadeType.ALL,
			orphanRemoval = true
			)
	private List<LeitoInternacao> leitosInternacao;

}
