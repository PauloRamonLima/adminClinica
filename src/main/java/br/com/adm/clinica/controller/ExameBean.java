package br.com.adm.clinica.controller;

import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.persistence.NoResultException;

import com.google.gson.Gson;

import br.com.adm.clinica.dao.ExameDAO;
import br.com.adm.clinica.dao.MedicoDAO;
import br.com.adm.clinica.dao.PacienteDAO;
import br.com.adm.clinica.model.Exame;
import br.com.adm.clinica.model.Medico;
import br.com.adm.clinica.model.Paciente;
import br.com.adm.clinica.util.TransformaJavaEmJson;

@ViewScoped
@ManagedBean
public class ExameBean implements Serializable {

	private static final long serialVersionUID = -6235197230054526106L;
	
	private Exame exame;
	
	private ExameDAO exameDAO = new ExameDAO();;
	
	private PacienteDAO pacienteDAO = new PacienteDAO();;
	
	private MedicoDAO medicoDAO = new MedicoDAO();
	
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
	
	private TransformaJavaEmJson transformaJavaEmJson = new TransformaJavaEmJson();

	@PostConstruct
	public void init() {
		try {
			nomesMedicosJson = transformaJavaEmJson.transformaJavaEmJsonMedico();
			nomesJson = transformaJavaEmJson.transformaJavaEmJsonPaciente();
			exames = exameDAO.findAll();
		}catch (NullPointerException e) {
			e.printStackTrace();
		}
	}
	
	public void salvar() {
		Exame exame = new Exame();
		Paciente paciente = pacienteDAO.buscarPacientePorNome(nomePaciente);
		Medico medico = medicoDAO.buscarMedicoPorNome(nomeMedico);
		exame.setPaciente(paciente);
		exame.setMedico(medico);
		exame.setNome(nome);
		exame.setData(data.replace("T", " "));
		exame.setRealizado(false);
		exameDAO.save(exame);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exame Cadastrado Com Sucesso", "Exame Cadastrado Com Sucesso"));
		exame = new Exame();
	}
	
	public void listarTodos() {
		exameDAO.findAll();
	}
	
	public void deletar(Long id) {
		exameDAO.delete(id);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exame Cancelado Com Sucesso", "Exame Cancelado Com Sucesso"));

	}
	
	public void alterar() {
		Exame exameSelecionado = exameDAO.findById(idExame);
		Paciente paciente = pacienteDAO.buscarPacientePorNome(nomePaciente);
		Medico medico = medicoDAO.buscarMedicoPorNome(nomeMedico);
	    exameSelecionado.setPaciente(paciente);
		exameSelecionado.setMedico(medico);
		exameSelecionado.setNome(nome);
		exameSelecionado.setData(data.replace("T", " "));
		exameSelecionado.setRealizado(false);
		exameSelecionado.setNome(nome);
		
		exameDAO.update(exameSelecionado);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exame Atualizado Com Sucesso", "Exame Atualizado Com Sucesso"));
	}
	
	public void showPageEditar(Long id) throws IOException {
			idExame = id;
			 FacesContext.getCurrentInstance().getExternalContext().redirect("editarexame.xhtml?faces-redirect=true");
	}

	public Exame getExame() {
		return exame;
	}

	public void setExame(Exame exame) {
		this.exame = exame;
	}

	public ExameDAO getExameDAO() {
		return exameDAO;
	}

	public void setExameDAO(ExameDAO exameDAO) {
		this.exameDAO = exameDAO;
	}

	public PacienteDAO getPacienteDAO() {
		return pacienteDAO;
	}

	public void setPacienteDAO(PacienteDAO pacienteDAO) {
		this.pacienteDAO = pacienteDAO;
	}

	public List<Exame> getExames() {
		return exames;
	}

	public void setExames(List<Exame> exames) {
		this.exames = exames;
	}

	public List<Paciente> getPacientes() {
		return pacientes;
	}

	public void setPacientes(List<Paciente> pacientes) {
		this.pacientes = pacientes;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public List<String> getNomes() {
		return nomes;
	}

	public void setNomes(List<String> nomes) {
		this.nomes = nomes;
	}

	public String getNomePaciente() {
		return nomePaciente;
	}

	public void setNomePaciente(String nomePaciente) {
		this.nomePaciente = nomePaciente;
	}

	public String getNomeMedico() {
		return nomeMedico;
	}

	public void setNomeMedico(String nomeMedico) {
		this.nomeMedico = nomeMedico;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNomesJson() {
		return nomesJson;
	}

	public void setNomesJson(String nomesJson) {
		this.nomesJson = nomesJson;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public MedicoDAO getMedicoDAO() {
		return medicoDAO;
	}

	public void setMedicoDAO(MedicoDAO medicoDAO) {
		this.medicoDAO = medicoDAO;
	}

	public String getNomesMedicosJson() {
		return nomesMedicosJson;
	}

	public void setNomesMedicosJson(String nomesMedicosJson) {
		this.nomesMedicosJson = nomesMedicosJson;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
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

	public Long getIdExame() {
		return idExame;
	}

	public void setIdExame(Long idExame) {
		this.idExame = idExame;
	}

	public TransformaJavaEmJson getTransformaJavaEmJson() {
		return transformaJavaEmJson;
	}

	public void setTransformaJavaEmJson(TransformaJavaEmJson transformaJavaEmJson) {
		this.transformaJavaEmJson = transformaJavaEmJson;
	}

	
	
}
