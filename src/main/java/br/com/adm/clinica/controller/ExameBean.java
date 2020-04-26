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

import br.com.adm.clinica.model.Exame;
import br.com.adm.clinica.model.Medico;
import br.com.adm.clinica.model.Paciente;
import br.com.adm.clinica.service.ExameService;
import br.com.adm.clinica.service.MedicoService;
import br.com.adm.clinica.service.PacienteService;
import br.com.adm.clinica.util.TransformaJavaEmJson;
import lombok.Getter;
import lombok.Setter;

@ViewScoped
@Named
@Getter
@Setter
public class ExameBean implements Serializable {

	private static final long serialVersionUID = -6235197230054526106L;
	
	@Inject
	private Exame exame;
	
	@Inject
	private ExameService exameService;
	
	@Inject
	private PacienteService pacienteService;
	
	@Inject
	private MedicoService medicoService;
	
	@Inject
	private Paciente paciente;
	
	private String nomePaciente;
	
	private String nome;
	
	private String nomeMedico;
	
	private String data;
	
	private List<Exame> exames = new ArrayList<Exame>();
	
	private List<Paciente> pacientes = new ArrayList<Paciente>();
		
	private List<String> nomes = new ArrayList<String>();
	
	private List<String> nomesMedicos = new ArrayList<String>();
	
	private List<Medico> medicos = new ArrayList<Medico>();
		
	private String nomesJson;
	
	private String nomesMedicosJson;
	
	private double valor;
	
	private static Long idExame;
	
	@Inject
	private TransformaJavaEmJson transformaJavaEmJson;

	@PostConstruct
	public void init() {
		try {
			nomesMedicosJson = transformaJavaEmJson.transformaJavaEmJsonMedico();
			nomesJson = transformaJavaEmJson.transformaJavaEmJsonPaciente();
			exames = exameService.listar();
		}catch (NullPointerException e) {
			e.printStackTrace();
		}
	}
	
	public void salvar() {
		Exame exame = new Exame();
		Paciente paciente = pacienteService.buscarPacientePorNome(nomePaciente);
		Medico medico = medicoService.buscarMedicoPorNome(nomeMedico);
		exame.setPaciente(paciente);
		exame.setMedico(medico);
		exame.setNome(nome);
		exame.setData(data.replace("T", " "));
		exame.setRealizado(false);
		exameService.salvar(exame);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exame Cadastrado Com Sucesso", "Exame Cadastrado Com Sucesso"));
		exame = new Exame();
	}
	
	public void listarTodos() {
		exameService.listar();
	}
	
	public void deletar(Long id) {
		exameService.deletar(id);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exame Cancelado Com Sucesso", "Exame Cancelado Com Sucesso"));
		exames = exameService.listar();

	}
	
	public void alterar() {
		Exame exameSelecionado = exameService.buscarPorId(idExame);
		Paciente paciente = pacienteService.buscarPacientePorNome(nomePaciente);
		Medico medico = medicoService.buscarMedicoPorNome(nomeMedico);
	    exameSelecionado.setPaciente(paciente);
		exameSelecionado.setMedico(medico);
		exameSelecionado.setNome(nome);
		exameSelecionado.setData(data.replace("T", " "));
		exameSelecionado.setRealizado(false);
		exameSelecionado.setNome(nome);
		
		exameService.alterar(exameSelecionado);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exame Atualizado Com Sucesso", "Exame Atualizado Com Sucesso"));
		exames = exameService.listar();

	}
	
	public void showPageEditar(Long id) throws IOException {
			idExame = id;
			 FacesContext.getCurrentInstance().getExternalContext().redirect("editarexame.xhtml?faces-redirect=true");
	}
	
}
