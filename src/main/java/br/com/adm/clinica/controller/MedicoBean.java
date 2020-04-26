package br.com.adm.clinica.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.adm.clinica.model.Medico;
import br.com.adm.clinica.service.MedicoService;

@Named
@ViewScoped
public class MedicoBean implements Serializable {
	
	private static final long serialVersionUID = 3585539643557964267L;

	@Inject
	private Medico medico;
	
	@Inject
	private MedicoService medicoService;
	
	private List<Medico> medicos = new ArrayList<Medico>();
	
	private static Long idMedico;
	
	@PostConstruct
	public void init() {
		medicos = medicoService.listar();
	}
	
	public void salvar(){
		if(medicoService.buscarMedicoPorCrm(medico.getCrm()) == null) {
			medicoService.salvar(medico);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Medico Cadastrado Com Sucesso", "Medico Cadastrado Com Sucasso"));
			medico = new Medico();
		}else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "CRM já cadastrado", "CRM já cadastrado"));
	
		}
	}
	
	public void deletar(Long id){
		medicoService.deletar(id);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Medico Deletado Com Sucesso", "Medico Deletado Com Sucesso"));
	}
	
	public void buscarMedicoPorId(Long id){
		medicoService.buscarPorId(id);
	}
	
	public void alterar(){
		Medico medicoSelecionado = medicoService.buscarPorId(idMedico);
		medicoSelecionado.setNome(medico.getNome());
		medicoSelecionado.setCrm(medico.getCrm());
		medicoService.alterar(medicoSelecionado);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Medico Atualizado Com Sucesso", "Medico Atualizado Com Sucesso"));		
	}
	
	public void showPageEditar(Long id) throws IOException {
		idMedico = id;
		FacesContext.getCurrentInstance().getExternalContext().redirect("editarmedico.xhtml?faces-redirect=true");
}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public List<Medico> getMedicos() {
		return medicos;
	}

	public void setMedicos(List<Medico> medicos) {
		this.medicos = medicos;
	}

	public Long getIdMedico() {
		return idMedico;
	}

	public void setIdMedico(Long idMedico) {
		this.idMedico = idMedico;
	}

}
