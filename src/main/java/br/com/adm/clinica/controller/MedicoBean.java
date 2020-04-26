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
import javax.persistence.NoResultException;

import br.com.adm.clinica.model.Medico;
import br.com.adm.clinica.service.MedicoService;
import lombok.Getter;
import lombok.Setter;

@Named
@ViewScoped
@Getter
@Setter
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

	public void salvar() {
		try {
			medicoService.buscarMedicoPorCrm(medico.getCrm());
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "CRM já cadastrado", "CRM já cadastrado"));
		}catch (NoResultException e) {
			medicoService.salvar(medico);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Medico Cadastrado Com Sucesso", "Medico Cadastrado Com Sucasso"));
			medico = new Medico();
			// TODO: handle exception
		}
	}

	public void deletar(Long id) {
		medicoService.deletar(id);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Medico Deletado Com Sucesso", "Medico Deletado Com Sucesso"));
		medicos = medicoService.listar();

	}

	public void buscarMedicoPorId(Long id) {
		medicoService.buscarPorId(id);
	}

	public void alterar() {
		Medico medicoSelecionado = medicoService.buscarPorId(idMedico);
		medicoSelecionado.setNome(medico.getNome());
		medicoSelecionado.setCrm(medico.getCrm());
		medicoService.alterar(medicoSelecionado);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Medico Atualizado Com Sucesso", "Medico Atualizado Com Sucesso"));
		medicos = medicoService.listar();

	}

	public void showPageEditar(Long id) throws IOException {
		idMedico = id;
		FacesContext.getCurrentInstance().getExternalContext().redirect("editarmedico.xhtml?faces-redirect=true");
	}

}
