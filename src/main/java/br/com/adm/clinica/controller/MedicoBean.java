package br.com.adm.clinica.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.adm.clinica.dao.MedicoDAO;
import br.com.adm.clinica.model.Medico;

@ManagedBean
@ViewScoped
public class MedicoBean implements Serializable {
	
	private static final long serialVersionUID = 3585539643557964267L;

	private Medico medico = new Medico();
	
	private MedicoDAO medicoDAO = new MedicoDAO();
	
	private List<Medico> medicos = new ArrayList<Medico>();
	
	private static Long idMedico;
	
	@PostConstruct
	public void init() {
		medicos = new ArrayList<Medico>();
		medicos = medicoDAO.findAll();
	}
	
	public void salvar(){
		if(medicoDAO.buscarMedicoPorCrm(medico.getCrm()) == null) {
			medicoDAO.save(medico);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Medico Cadastrado Com Sucesso", "Medico Cadastrado Com Sucasso"));
			medico = new Medico();
		}else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "CRM já cadastrado", "CRM já cadastrado"));
	
		}
	}
	
	public void deletar(Long id){
		medicoDAO.delete(id);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Medico Deletado Com Sucesso", "Medico Deletado Com Sucesso"));
	}
	
	public void buscarMedicoPorId(Long id){
		medicoDAO.findById(id);
	}
	
	public void alterar(){
		Medico medicoSelecionado = medicoDAO.findById(idMedico);
		medicoSelecionado.setNome(medico.getNome());
		medicoSelecionado.setCrm(medico.getCrm());
		medicoDAO.update(medicoSelecionado);
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

	public MedicoDAO getMedicoDAO() {
		return medicoDAO;
	}

	public void setMedicoDAO(MedicoDAO medicoDAO) {
		this.medicoDAO = medicoDAO;
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
