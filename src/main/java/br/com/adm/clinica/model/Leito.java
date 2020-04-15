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

@Entity
@Table(name = "adm_leito")
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
