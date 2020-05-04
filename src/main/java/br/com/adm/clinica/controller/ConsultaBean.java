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
import lombok.Getter;
import lombok.Setter;

@Named
@ViewScoped
@Getter
@Setter
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
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
	}

	public void salvar() {
		Consulta consulta = new Consulta();
		Paciente paciente = pacienteService.buscarPacientePorNome(nomePaciente);
		Medico medico = medicoService.buscarMedicoPorNome(nomeMedico);
		consulta.setPaciente(paciente);
		consulta.setMedico(medico);
		consulta.setData(data.replace("T", " "));
		consulta.setRealizado(false);
		consultaService.salvar(consulta);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Consulta Marcada Com Sucesso", "Consulta Marcada Com Sucesso"));
	}

	public void deletar(Long id) {
		consultaService.deletar(id);
		//FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Successful", "Consulta Cancelado Com Sucesso"));
		FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Sucesso", "Consulta Cancelado Com Sucesso"));
		consultas = consultaService.listar();

	}

	public void alterar() {
		Consulta consultaSelecionada = consultaService.buscarPorId(idConsulta);
		consultaSelecionada.setData(data);
		consultaService.alterar(consultaSelecionada);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Consulta Remarcada Com Sucesso", "Consulta Remarcada Com Sucesso"));
		consultas = consultaService.listar();

	}

	public void showPageEditar(Long id) throws IOException {
		idConsulta = id;
		//FacesContext.getCurrentInstance().getExternalContext().redirect("editarconsulta.xhtml?faces-redirect=true");
	}
	
	public void buscarConsultaPorId(Long id) {
		
	}

}
