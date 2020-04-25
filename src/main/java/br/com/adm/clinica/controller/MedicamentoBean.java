package br.com.adm.clinica.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.com.adm.clinica.dao.MedicamentoDAO;
import br.com.adm.clinica.model.Medicamento;

@Named
@ViewScoped
public class MedicamentoBean implements Serializable {

	private static final long serialVersionUID = -2623497556875224198L;
	
	private Medicamento medicamento = new Medicamento();
	
	private MedicamentoDAO medicamentoDAO = new MedicamentoDAO();
	
	private List<Medicamento> medicamentos = new ArrayList<Medicamento>();
	
	@PostConstruct
	public void init() {
		medicamentos = medicamentoDAO.findAll();
	}
	
	public void salvar() {
		medicamentoDAO.save(medicamento);
	}
	
	public void deletar(Long id) {
		medicamentoDAO.delete(id);
	}
	
	
	// Getters e Setters
	
	public Medicamento getMedicamento() {
		return medicamento;
	}

	public void setMedicamento(Medicamento medicamento) {
		this.medicamento = medicamento;
	}

	public MedicamentoDAO getMedicamentoDAO() {
		return medicamentoDAO;
	}

	public void setMedicamentoDAO(MedicamentoDAO medicamentoDAO) {
		this.medicamentoDAO = medicamentoDAO;
	}

	public List<Medicamento> getMedicamentos() {
		return medicamentos;
	}

	public void setMedicamentos(List<Medicamento> medicamentos) {
		this.medicamentos = medicamentos;
	}
	
	
}
