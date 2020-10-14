package br.com.adm.clinica.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.adm.clinica.model.Medicamento;
import br.com.adm.clinica.service.MedicamentoService;
import lombok.Getter;
import lombok.Setter;

@Named
@ViewScoped
@Getter
@Setter
public class MedicamentoBean implements Serializable {

	private static final long serialVersionUID = -2623497556875224198L;

	@Inject
	private Medicamento medicamento;

	@Inject
	private MedicamentoService medicamentoService;
	
	private List<Medicamento> medicamentos = new ArrayList<Medicamento>();
	
	private int quantidadeMedicamento;
	
	@PostConstruct
	public void init() {
		try {
			listar();
		}catch (Exception e) {
			medicamentos = new ArrayList<>();
		}
	}
	
	public void listar() {
		medicamentos = medicamentoService.listar();
	}
	
	public void salvar() throws IOException {
		medicamentoService.salvar(medicamento);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Medicamento Cadastrado Com Sucesso", "Medicamento Cadastrado Com Sucesso"));
		this.showPagefarmacia();
	}

	public void deletar(Long id) {
		medicamentoService.deletar(id);
	}
	
	public void retirarMedicamento() {
		if(verificarSeValorRetiradoEMenorQueQuantidadeTotal(medicamento.getQuantidadeDoMedicamento())) {
			medicamento.setQuantidadeDoMedicamento(medicamento.getQuantidadeDoMedicamento() - quantidadeMedicamento);
			medicamentoService.alterar(medicamento);
			listar();
			quantidadeMedicamento = 0;
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Medicamento Retirado Com Sucesso", "Medicamento Retirado Com Sucesso"));
		}else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Quantidade de medicamento insuficiente", "Quantidade de medicamento insuficiente"));
		}
	}
	
	public void adicionarMedicamento() {
		medicamento.setQuantidadeDoMedicamento(medicamento.getQuantidadeDoMedicamento() + quantidadeMedicamento);
		medicamentoService.alterar(medicamento);
		listar();
		quantidadeMedicamento = 0;
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Medicamento Adicionado Com Sucesso", "Medicamento Adicionado Com Sucesso"));
	}
	
	public boolean verificarSeValorRetiradoEMenorQueQuantidadeTotal(int quantidade) {
		if(this.quantidadeMedicamento > quantidade) {
			return false;
		}else {
			return true;
		}
	}
	
	public void showPageMedicamento() throws IOException {
		FacesContext.getCurrentInstance().getExternalContext().redirect("novomedicamento.xhtml?faces-redirect=true");
	}
	
	public void showPagefarmacia() throws IOException {
		FacesContext.getCurrentInstance().getExternalContext().redirect("farmacia.xhtml?faces-redirect=true");
	}

}
