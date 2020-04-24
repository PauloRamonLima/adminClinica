package br.com.adm.clinica.model.vo;

public class PacienteInternadoVO {
	
	private String nomePaciente;
	
	private String leito;
	
	private String leitoInternacao;
	
	private String cpf;

	public String getNomePaciente() {
		return nomePaciente;
	}

	public void setNomePaciente(String nomePaciente) {
		this.nomePaciente = nomePaciente;
	}

	public String getLeito() {
		return leito;
	}

	public void setLeito(String leito) {
		this.leito = leito;
	}

	public String getLeitoInternacao() {
		return leitoInternacao;
	}

	public void setLeitoInternacao(String leitoInternacao) {
		this.leitoInternacao = leitoInternacao;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

}
