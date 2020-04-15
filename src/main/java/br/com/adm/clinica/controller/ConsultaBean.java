package br.com.adm.clinica.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

import com.google.gson.Gson;

import br.com.adm.clinica.dao.ConsultaDAO;
import br.com.adm.clinica.dao.MedicoDAO;
import br.com.adm.clinica.dao.PacienteDAO;
import br.com.adm.clinica.model.Consulta;
import br.com.adm.clinica.model.Exame;
import br.com.adm.clinica.model.Medico;
import br.com.adm.clinica.model.Paciente;
import br.com.adm.clinica.util.TransformaJavaEmJson;

@ManagedBean
@ViewScoped
public class ConsultaBean implements Serializable {

	private static final long serialVersionUID = -1289342901613744971L;
	
	private ConsultaDAO consultaDAO = new ConsultaDAO();
	
	private PacienteDAO pacienteDAO = new PacienteDAO();
	
	private MedicoDAO medicoDAO = new MedicoDAO();
	
	private String nomePaciente;
	
	private String nome;
	
	private String nomeMedico;
	
	private String data;
	
	private Consulta consulta;
	
	private List<Consulta> consultas = new ArrayList<Consulta>();
	
	private List<Paciente> pacientes = new ArrayList<Paciente>();
	
	private List<String> nomes = new ArrayList<String>();
	
	private List<String> nomesMedicos = new ArrayList<String>();
	
	private List<Medico> medicos = new ArrayList<Medico>();
		
	private String nomesJson;
	
	private String nomesMedicosJson;
	
	private static Long idConsulta;

	private TransformaJavaEmJson transformaJavaEmJson = new TransformaJavaEmJson();

	
	@PostConstruct
	public void init() {
		try {
			nomesMedicosJson = transformaJavaEmJson.transformaJavaEmJsonMedico();
			nomesJson = transformaJavaEmJson.transformaJavaEmJsonPaciente();
			consultas = consultaDAO.findAll();
		}catch (NullPointerException e) {
			e.printStackTrace();
		}
	}
	
	public void salvar() {
		  Consulta consulta = new Consulta(); 
		  Paciente paciente = pacienteDAO.buscarPacientePorNome(nomePaciente); Medico medico =
		  medicoDAO.buscarMedicoPorNome(nomeMedico); consulta.setPaciente(paciente);
		  consulta.setMedico(medico);
		  consulta.setData(data.replace("T", " "));
		  consulta.setRealizado(false);
		 consultaDAO.save(consulta);
		 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Consulta Marcada Com Sucesso", "Consulta Marcada Com Sucesso"));
	}
	
	public void deletar(Long id) {
		consultaDAO.delete(id);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Consulta Cancelado Com Sucesso", "Consulta Cancelado Com Sucesso"));

	}
	
	public void alterar() {
		Consulta consultaSelecionada = consultaDAO.findById(idConsulta);
		Paciente paciente = pacienteDAO.buscarPacientePorNome(nomePaciente); 
		Medico medico = medicoDAO.buscarMedicoPorNome(nomeMedico); 
		consultaSelecionada.setPaciente(paciente);
		consultaSelecionada.setMedico(medico);
		consultaSelecionada.setData(data.replace("T", " "));
		consultaSelecionada.setRealizado(false);
		consultaDAO.update(consultaSelecionada);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Consulta Atualizada Com Sucesso", "Consulta Atualizada Com Sucesso"));
	}
	
	public void showPageEditar(Long id) throws IOException {
		idConsulta = id;
		 FacesContext.getCurrentInstance().getExternalContext().redirect("editarconsulta.xhtml?faces-redirect=true");
}

	
	public ConsultaDAO getConsultaDAO() {
		return consultaDAO;
	}

	public void setConsultaDAO(ConsultaDAO consultaDAO) {
		this.consultaDAO = consultaDAO;
	}

	public PacienteDAO getPacienteDAO() {
		return pacienteDAO;
	}

	public void setPacienteDAO(PacienteDAO pacienteDAO) {
		this.pacienteDAO = pacienteDAO;
	}

	public MedicoDAO getMedicoDAO() {
		return medicoDAO;
	}

	public void setMedicoDAO(MedicoDAO medicoDAO) {
		this.medicoDAO = medicoDAO;
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
