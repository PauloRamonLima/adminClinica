package br.com.adm.clinica.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "adm_paciente")
public class Paciente implements Serializable {
	
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
	
	
	
	
	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getEstCivil() {
		return EstCivil;
	}

	public void setEstCivil(String estCivil) {
		EstCivil = estCivil;
	}

	

	public String getEndNumero() {
		return endNumero;
	}

	public void setEndNumero(String endNumero) {
		this.endNumero = endNumero;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDtNascimento() {
		return dtNascimento;
	}

	public void setDtNascimento(String dtNascimento) {
		this.dtNascimento = dtNascimento;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	@Override
	public String toString() {
		return "Paciente [id=" + id + ", nome=" + nome + ", dtNascimento=" + dtNascimento + ", cpf=" + cpf + ", rg="
				+ rg + ", EstCivil=" + EstCivil + ", logradouro=" + logradouro + ", endNumero=" + endNumero
				+ ", bairro=" + bairro + ", localidade=" + localidade + ", uf=" + uf + ", cep=" + cep + "]";
	}

	


}
