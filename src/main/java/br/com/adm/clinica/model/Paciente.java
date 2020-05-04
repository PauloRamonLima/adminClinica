package br.com.adm.clinica.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "adm_paciente")
@Getter
@Setter
public class Paciente implements Serializable, Comparable<Paciente> {
	
	private static final long serialVersionUID = -3228230850490987679L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "paciente_id")
	private Long id;
	
	@Column(name = "paciente_nome")
	private String nome;
	
	@Column(name = "paciente_dtNascimento")
	private String dtNascimento;
	
	@Column(name = "paciente_cpf")
	private String cpf;
	
	@Column(name = "paciente_rg")
	private String rg;
	
	@Column(name = "paciente_estCivil")
	private String EstCivil;
	
	@Column(name = "paciente_logradouro")
	private String logradouro;
	
	@Column(name = "paciente_endNumero")
	private String endNumero;
	
	@Column(name = "paciente_bairro")
	private String bairro;
	
	@Column(name = "paciente_localidade")
	private String localidade;

	@Column(name = "paciente_uf")
	private String uf;
	
	@Column(name = "paciente_cep")
	private String cep;

	@Override
	public String toString() {
		return "Paciente [id=" + id + ", nome=" + nome + ", dtNascimento=" + dtNascimento + ", cpf=" + cpf + ", rg="
				+ rg + ", EstCivil=" + EstCivil + ", logradouro=" + logradouro + ", endNumero=" + endNumero
				+ ", bairro=" + bairro + ", localidade=" + localidade + ", uf=" + uf + ", cep=" + cep + "]";
	}
	
	
	public int compareTo(Paciente o) {
        int valor = nome.compareTo(o.nome);
        return (valor != 0 ? valor : 1);
    }   
	
}
