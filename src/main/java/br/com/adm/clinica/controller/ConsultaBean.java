package br.com.adm.clinica.controller;

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
import br.com.adm.clinica.model.Paciente;
import br.com.adm.clinica.model.Usuario;
import br.com.adm.clinica.service.ConsultaService;
import br.com.adm.clinica.service.PacienteService;
import br.com.adm.clinica.service.UsuarioService;
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
	private UsuarioService usuarioService;

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

	private List<Usuario> medicos = new ArrayList<Usuario>();

	private String nomesJson;

	private String nomesMedicosJson;
	
	@Inject
	private Consulta consultaSelecionada;

	@Inject
	private TransformaJavaEmJson transformaJavaEmJson;

	@PostConstruct
	public void init() {
		try {
			nomesJson = transformaJavaEmJson.transformaJavaEmJsonPaciente();
			consultas = consultaService.listar();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
	}

	public void salvar() {
		Consulta consulta = new Consulta();
		Paciente paciente = pacienteService.buscarPacientePorNome(nomePaciente);
		Usuario medico = usuarioService.buscarMedicoPorNome(nomeMedico);
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
		FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Sucesso", "Consulta Cancelado Com Sucesso"));
		consultas = consultaService.listar();

	}

	public void alterar() {
		consultaService.alterar(consultaSelecionada);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Consulta Remarcada Com Sucesso", "Consulta Remarcada Com Sucesso"));
		consultas = consultaService.listar();

	}

}
