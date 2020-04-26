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

import br.com.adm.clinica.model.Consulta;
import br.com.adm.clinica.model.Medico;
import br.com.adm.clinica.model.Paciente;
import br.com.adm.clinica.service.ConsultaService;
import br.com.adm.clinica.service.MedicoService;
import br.com.adm.clinica.service.PacienteService;
import br.com.adm.clinica.util.TransformaJavaEmJson;

@Named
@ViewScoped
public class ConsultaBean implements Serializable {

	private static final long serialVersionUID = -1289342901613744971L;
	
	@Inject
	private ConsultaService consultaService;
	
	@Inject
	private PacienteService pacienteService;
	
	@Inject
	private MedicoService medicoService;
	
	private String nomePaciente;
	
	private String nome;
	
	private String nomeMedico;
	
	private String data;
	
	@Inject
	private Consulta consulta;
	
	private List<Consulta> consultas = new ArrayList<Consulta>();
	
	private List<Paciente> pacientes = new ArrayList<Paciente>();
	
	private List<String> nomes = new ArrayList<String>();
	
	private List<String> nomesMedicos = new ArrayList<String>();
	
	private List<Medico> medicos = new ArrayList<Medico>();
		
	private String nomesJson;
	
	private String nomesMedicosJson;
	
	private static Long idConsulta;

	@Inject
	private TransformaJavaEmJson transformaJavaEmJson;

	
	@PostConstruct
	public void init() {
		try {
			nomesMedicosJson = transformaJavaEmJson.transformaJavaEmJsonMedico();
			nomesJson = transformaJavaEmJson.transformaJavaEmJsonPaciente();
			consultas = consultaService.listar();
		}catch (NullPointerException e) {
			e.printStackTrace();
		}
	}
	
	public void salvar() {
		  Consulta consulta = new Consulta(); 
		  Paciente paciente = pacienteService.buscarPacientePorNome(nomePaciente); Medico medico =
		  medicoService.buscarMedicoPorNome(nomeMedico); consulta.setPaciente(paciente);
		  consulta.setMedico(medico);
		  consulta.setData(data.replace("T", " "));
		  consulta.setRealizado(false);
		 consultaService.salvar(consulta);
		 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Consulta Marcada Com Sucesso", "Consulta Marcada Com Sucesso"));
	}
	
	public void deletar(Long id) {
		consultaService.deletar(id);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Consulta Cancelado Com Sucesso", "Consulta Cancelado Com Sucesso"));

	}
	
	public void alterar() {
		Consulta consultaSelecionada = consultaService.buscarPorId(idConsulta);
		Paciente paciente = pacienteService.buscarPacientePorNome(nomePaciente); 
		Medico medico = medicoService.buscarMedicoPorNome(nomeMedico); 
		consultaSelecionada.setPaciente(paciente);
		consultaSelecionada.setMedico(medico);
		consultaSelecionada.setData(data.replace("T", " "));
		consultaSelecionada.setRealizado(false);
		consultaService.alterar(consultaSelecionada);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Consulta Atualizada Com Sucesso", "Consulta Atualizada Com Sucesso"));
	}
	
	public void showPageEditar(Long id) throws IOException {
		idConsulta = id;
		 FacesContext.getCurrentInstance().getExternalContext().redirect("editarconsulta.xhtml?faces-redirect=true");
}


	public String getNomePaciente() {
		return nomePaciente;
	}

	public void setNomePaciente(String nomePaciente) {
		this.nomePaciente = nomePaciente;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNomeMedico() {
		return nomeMedico;
	}

	public void setNomeMedico(String nomeMedico) {
		this.nomeMedico = nomeMedico;
	}
	

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Consulta getConsulta() {
		return consulta;
	}

	public void setConsulta(Consulta consulta) {
		this.consulta = consulta;
	}

	public List<Paciente> getPacientes() {
		return pacientes;
	}

	public void setPacientes(List<Paciente> pacientes) {
		this.pacientes = pacientes;
	}

	public List<String> getNomes() {
		return nomes;
	}

	public void setNomes(List<String> nomes) {
		this.nomes = nomes;
	}

	public List<String> getNomesMedicos() {
		return nomesMedicos;
	}

	public void setNomesMedicos(List<String> nomesMedicos) {
		this.nomesMedicos = nomesMedicos;
	}

	public List<Medico> getMedicos() {
		return medicos;
	}

	public void setMedicos(List<Medico> medicos) {
		this.medicos = medicos;
	}

	public String getNomesJson() {
		return nomesJson;
	}

	public void setNomesJson(String nomesJson) {
		this.nomesJson = nomesJson;
	}

	public String getNomesMedicosJson() {
		return nomesMedicosJson;
	}

	public void setNomesMedicosJson(String nomesMedicosJson) {
		this.nomesMedicosJson = nomesMedicosJson;
	}

	public List<Consulta> getConsultas() {
		return consultas;
	}

	public void setConsultas(List<Consulta> consultas) {
		this.consultas = consultas;
	}

	public Long getIdConsulta() {
		return idConsulta;
	}

	public void setIdConsulta(Long idConsulta) {
		this.idConsulta = idConsulta;
	}

	public TransformaJavaEmJson getTransformaJavaEmJson() {
		return transformaJavaEmJson;
	}

	public void setTransformaJavaEmJson(TransformaJavaEmJson transformaJavaEmJson) {
		this.transformaJavaEmJson = transformaJavaEmJson;
	}
	
	
	

}
