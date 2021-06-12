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
import br.com.adm.clinica.model.Paciente;
import br.com.adm.clinica.model.Usuario;
import br.com.adm.clinica.model.builder.ExameBuilder;
import br.com.adm.clinica.service.ExameService;
import br.com.adm.clinica.service.PacienteService;
import br.com.adm.clinica.service.UsuarioService;
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
	private UsuarioService usuarioService;
	
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
	
	private List<Usuario> medicos = new ArrayList<Usuario>();
		
	private String nomesJson;
	
	private String nomesMedicosJson;
	
	private double valor;
	
	private static Long idExame;
	
	@Inject
	private TransformaJavaEmJson transformaJavaEmJson;

	@PostConstruct
	public void init() {
		try {
			nomesJson = transformaJavaEmJson.transformaJavaEmJsonPaciente();
			exames = exameService.listar();
		}catch (NullPointerException e) {
			e.printStackTrace();
		}
	}
	
	public void salvar() {
		Paciente paciente = pacienteService.buscarPacientePorNome(nomePaciente);
		Usuario medico = usuarioService.buscarMedicoPorNome(nomeMedico);
		Exame exame = new ExameBuilder()
				.addPaciente(paciente)
				.addMedico(medico)
				.addNomeDataRealizado(nome, data, false)
				.construir();
		exameService.salvar(exame);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exame Cadastrado Com Sucesso", "Exame Cadastrado Com Sucesso"));
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
		Usuario medico = usuarioService.buscarMedicoPorNome(nomeMedico);
		exameSelecionado = new ExameBuilder()
				.addPaciente(paciente)
				.addMedico(medico)
				.addNomeDataRealizado(nome, data, false)
				.construir();
		
		exameService.alterar(exameSelecionado);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exame Atualizado Com Sucesso", "Exame Atualizado Com Sucesso"));
		exames = exameService.listar();

	}
	
	public void showPageEditar(Long id) throws IOException {
			idExame = id;
			FacesContext.getCurrentInstance().getExternalContext().redirect("editarexame.xhtml?faces-redirect=true");
	}
	
}
