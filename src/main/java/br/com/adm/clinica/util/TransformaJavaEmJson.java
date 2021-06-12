package br.com.adm.clinica.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.NoResultException;

import com.google.gson.Gson;

import br.com.adm.clinica.model.Medicamento;
import br.com.adm.clinica.model.Paciente;
import br.com.adm.clinica.service.LeitoInternacaoService;
import br.com.adm.clinica.service.MedicamentoService;
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
	private LeitoInternacaoService leitoInternacaoService;
	
	@Inject
	private MedicamentoService medicamentoService;	
	
	public String transformaJavaEmJsonPaciente() {
		List<Paciente> pacientes = pacienteService.listar();
		List<String> nomes = new ArrayList<String>();
		String nomesJson;
		
		for(Paciente paciente : pacientes) {
			nomes.add(paciente.getNome());
		}
		
		Gson gson = new Gson();
		
		nomesJson = gson.toJson(nomes);
		
		return nomesJson;
	}
	
	public String transformaJavaEmJsonPacienteSemInternacao() {
		List<Paciente> pacientes = pacienteService.listar();
		List<String> nomes = new ArrayList<String>();
		String nomesJson;
		
		for(Paciente paciente : pacientes) {
			try {
				if(leitoInternacaoService.buscarLeitoDeInternacaoPorPaciente(paciente) == null); 	
			}catch (NoResultException e) {
				nomes.add(paciente.getNome());
			}
		}
		Gson gson = new Gson();
		nomesJson = gson.toJson(nomes);
		return nomesJson;
	}
	
	public String transformaJavaEmJsonMedicamento() {
		List<Medicamento> medicamentos = medicamentoService.listar();
		String nomesMedicamentos;
		List<String> nomeMedicamentos = new ArrayList<>();
		medicamentos.stream().forEach(e -> nomeMedicamentos.add(e.getNome()));		
		Gson gson = new Gson();	
		nomesMedicamentos = gson.toJson(nomeMedicamentos);		
		return nomesMedicamentos;
	}

}