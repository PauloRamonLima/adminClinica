package br.com.adm.clinica.util;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import com.google.gson.Gson;

import br.com.adm.clinica.dao.LeitoInternacaoDAO;
import br.com.adm.clinica.dao.MedicoDAO;
import br.com.adm.clinica.dao.PacienteDAO;
import br.com.adm.clinica.model.Medico;
import br.com.adm.clinica.model.Paciente;

public class TransformaJavaEmJson {
	
	private PacienteDAO pacienteDAO = new PacienteDAO();
	
	private LeitoInternacaoDAO leitoInternacaoDAO = new LeitoInternacaoDAO();
	
	private MedicoDAO medicoDAO = new MedicoDAO();
	
	private List<Paciente> pacientes = new ArrayList<Paciente>();
	
	private List<Medico> medicos = new ArrayList<Medico>();
	
	private List<String> nomes = new ArrayList<String>();
	
	private List<String> nomesMedicos = new ArrayList<String>();
	
	private String nomesJson;
	
	private String nomesMedicosJson;
	
	public String transformaJavaEmJsonPaciente() {
		pacientes = pacienteDAO.findAll();
		nomes = new ArrayList<String>();
		
		for(Paciente paciente : pacientes) {
			nomes.add(paciente.getNome());
		}
		
		Gson gson = new Gson();
		
		nomesJson = gson.toJson(nomes);
		
		return nomesJson;
	}
	
	public String transformaJavaEmJsonPacienteSemInternacao() {
		pacientes = pacienteDAO.findAll();
		nomes = new ArrayList<String>();
		
		for(Paciente paciente : pacientes) {
			try {
				if(leitoInternacaoDAO.buscarLeitoDeInternacaoPorPaciente(paciente) == null); 	
			}catch (NoResultException e) {
				nomes.add(paciente.getNome());
			}
		}
		
		Gson gson = new Gson();
		
		nomesJson = gson.toJson(nomes);
		
		return nomesJson;
	}
	
	public String transformaJavaEmJsonMedico() {
		medicos = medicoDAO.findAll();
		nomesMedicos = new ArrayList<String>();
		
		for(Medico medico : medicos) {
			nomesMedicos.add(medico.getNome());
		}
		
		Gson gson = new Gson();
		
		nomesMedicosJson = gson.toJson(nomesMedicos);
			
		return nomesMedicosJson;
	}
	
	// getters e setters

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

	public List<Paciente> getPacientes() {
		return pacientes;
	}

	public void setPacientes(List<Paciente> pacientes) {
		this.pacientes = pacientes;
	}

	public List<Medico> getMedicos() {
		return medicos;
	}

	public void setMedicos(List<Medico> medicos) {
		this.medicos = medicos;
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

	public LeitoInternacaoDAO getLeitoInternacaoDAO() {
		return leitoInternacaoDAO;
	}

	public void setLeitoInternacaoDAO(LeitoInternacaoDAO leitoInternacaoDAO) {
		this.leitoInternacaoDAO = leitoInternacaoDAO;
	}

}

