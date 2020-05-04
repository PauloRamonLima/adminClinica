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

@Entity //mostra ao hibernate que essa classe será uma tabela no banco de dados
@Table (name = "adm_prescricao")
@Getter // biblioteca lombok cria os getters( o get pega o valor do objeto, recupera) da aplicação
@Setter // biblioteca lombok cria os setters( o set coloca valor no objeto, o faz receber) da aplicação
public class Prescricao implements Serializable {
	
	static final long serialVersionUID = -4839774397062680152L;
	
	@Id
	@SequenceGenerator(name = "prescricao_GENERATION", sequenceName = "prescricao_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "prescricao_GENERATION")
    @Column(name = "prescricao_id")
	private Long id;
	
    @Column(name = "prescricao_paciente")
	private Paciente paciente;
    
    @Column(name = "prescricao_medicamento")
    private Medicamento medicamento;
    
    @Column(name = "prescricao_periodicidade")
    private String periodicidade;
    
    @Column(name = "prescricao_dias")
    private int dias;
    

}
