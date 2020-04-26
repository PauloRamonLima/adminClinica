package br.com.adm.clinica.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
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

	@PostConstruct
	public void init() {
		medicamentos = medicamentoService.listar();
	}

	public void salvar() {
		medicamentoService.salvar(medicamento);
	}

	public void deletar(Long id) {
		medicamentoService.deletar(id);
		medicamentos = medicamentoService.listar();

	}

}
