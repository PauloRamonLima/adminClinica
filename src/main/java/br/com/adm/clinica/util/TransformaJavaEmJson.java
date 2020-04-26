package br.com.adm.clinica.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.NoResultException;

import com.google.gson.Gson;

import br.com.adm.clinica.model.Medico;
import br.com.adm.clinica.model.Paciente;
import br.com.adm.clinica.service.LeitoInternacaoService;
import br.com.adm.clinica.service.MedicoService;
import br.com.adm.clinica.service.PacienteService;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransformaJavaEmJson implements Serializable {
	
	private static final long serialVersionUID = 4000011349335176603L;
	
	@Inject
	private PacienteService pacienteService;
	
	@Inject
	private LeitoInternacaoService leitoInternacaoDAO;
	
	@Inject
	private MedicoService medicoService;
	
	private List<Paciente> pacientes = new ArrayList<Paciente>();
	
	private List<Medico> medicos = new ArrayList<Medico>();
	
	private List<String> nomes = new ArrayList<String>();
	
	private List<String> nomesMedicos = new ArrayList<String>();
	
	private String nomesJson;
	
	private String nomesMedicosJson;
	
	public String transformaJavaEmJsonPaciente() {
		pacientes = pacienteService.listar();
		nomes = new ArrayList<String>();
		
		for(Paciente paciente : pacientes) {
			nomes.add(paciente.getNome());
		}
		
		Gson gson = new Gson();
		
		nomesJson = gson.toJson(nomes);
		
		return nomesJson;
	}
	
	public String transformaJavaEmJsonPacienteSemInternacao() {
		pacientes = pacienteService.listar();
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
		medicos = medicoService.listar();
		nomesMedicos = new ArrayList<String>();
		
		for(Medico medico : medicos) {
			nomesMedicos.add(medico.getNome());
		}
		
		Gson gson = new Gson();
		
		nomesMedicosJson = gson.toJson(nomesMedicos);
			
		return nomesMedicosJson;
	}

}