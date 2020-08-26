package br.com.adm.clinica.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.adm.clinica.model.Farmacia;
import br.com.adm.clinica.model.Medicamento;
import br.com.adm.clinica.service.FarmaciaService;
import br.com.adm.clinica.service.MedicamentoService;
import br.com.adm.clinica.util.TransformaJavaEmJson;
import lombok.Getter;
import lombok.Setter;

@Named
@ViewScoped
@Getter
@Setter
public class FarmaciaBean implements Serializable{

	private static final long serialVersionUID = -5281366254378234827L;
	
	@Inject
	private Farmacia farmacia;
	
	@Inject
	private FarmaciaService farmaciaService;
	
	@Inject
	private MedicamentoService medicamentoService;
	
	@Inject
	private TransformaJavaEmJson transformaJavaEmJson;
	
	private String medicamento;
	
	private List<Farmacia> farmacias = new ArrayList<Farmacia>();
	
	private String nomeMedicamento;
	
	@PostConstruct
	public void init() {
		nomeMedicamento = transformaJavaEmJson.transformaJavaEmJsonMedicamento();
		farmacias = farmaciaService.listar();
	}
	
	public void cadastrarMedicamento() throws IOException {

		Medicamento med = medicamentoService.buscarMedicamentoPorNome(medicamento);
		farmacia.setMedicamento(med);
		farmaciaService.salvar(farmacia);		
		farmacias = farmaciaService.listar();
		this.showPageFarmaciaMedicamento();
	}
	
	public void showPageMedicamento() throws IOException {
		FacesContext.getCurrentInstance().getExternalContext().redirect("novomedicamento.xhtml?faces-redirect=true");
	}
	
	public void showPageFarmaciaMedicamento() throws IOException {
		FacesContext.getCurrentInstance().getExternalContext().redirect("cadastrarmedicamentofarmacia.xhtml?faces-redirect=true");
	}
	

}
