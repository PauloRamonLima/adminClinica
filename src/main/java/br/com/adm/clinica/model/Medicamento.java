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
@Table(name = "adm_medicamento")
@Getter
@Setter
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
	private String tipoMedicamento;
	
	@Column(name = "medicamento_tipo_usuario")
	private String tipoUsuarioMedicamento;
	
	@Column(name = "medicamento_quantidade")
	private int quantidadeDoMedicamento;
	
	public Medicamento() {
		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Medicamento other = (Medicamento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
