package br.com.adm.clinica.controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.view.ViewScoped;
import javax.persistence.NoResultException;

import com.google.gson.Gson;

import br.com.adm.clinica.dao.ConsultaDAO;
import br.com.adm.clinica.dao.ExameDAO;
import br.com.adm.clinica.dao.LeitoInternacaoDAO;
import br.com.adm.clinica.dao.PacienteDAO;
import br.com.adm.clinica.model.Consulta;
import br.com.adm.clinica.model.Exame;
import br.com.adm.clinica.model.LeitoInternacao;
import br.com.adm.clinica.model.Paciente;

@ManagedBean
@ViewScoped
public class PacienteBean implements Serializable {

	private static final long serialVersionUID = -7178530229889330245L;

	private PacienteDAO pacienteDAO = new PacienteDAO();
	
	private LeitoInternacaoDAO leitoInternacaoDAO = new LeitoInternacaoDAO();
	
	private ExameDAO exameDAO = new ExameDAO();
	
	private ConsultaDAO consultaDAO = new ConsultaDAO();

	private Paciente paciente = new Paciente();

	private List<Paciente> pacientes = new ArrayList<Paciente>();
	

	@PostConstruct
	public void init() {
		try {
			pacientes = pacienteDAO.findAll();
		} catch (NullPointerException e) {
			// TODO: handle exception
			System.out.println("Lista Vazia");
		}
	}

	public void salvar() {
		
		try {	
		if(pacienteDAO.buscarPacientePorCpf(paciente.getCpf()) != null) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"CPF já cadastrado", "CPF já cadastrado"));
				return;
			}
		}catch (NoResultException e) {
		
		}
		
		try {	
			if(pacienteDAO.buscarPacientePorRg(paciente.getRg()) != null) {
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"RG já cadastrado", "RG já cadastrado"));
					return;
				}
			}catch (NoResultException e) {
			
			}
		
		pacienteDAO.save(paciente);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Paciente Cadastrado Com Sucesso", "Paciente Cadastrado Com Sucesso"));
		paciente = new Paciente();
	}

	public void deletar(Long id) {
		Paciente paciente = pacienteDAO.findById(id);
		List<Exame> exames = exameDAO.buscarExamesPorPaciente(paciente);
		if(!exames.isEmpty()) {
			for(Exame exame : exames) {
				exameDAO.delete(exame.getId());
			}
		}
		List<Consulta> consultas = consultaDAO.buscarConsultaPorPaciente(paciente);
		if(!consultas.isEmpty()) {
			for(Consulta consulta : consultas) {
				consultaDAO.delete(consulta.getId());
			}
		}
		
		try {
			LeitoInternacao leito = leitoInternacaoDAO.buscarLeitoDeInternacaoPorPaciente(paciente);
			leito.setPaciente(null);
			leitoInternacaoDAO.update(leito);
		}catch (NoResultException e) {
			
		}
		
		pacienteDAO.delete(id);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Paciente Deletado Com Sucasso", "Paciente Deletado Com Sucasso"));
	}

	public void buscarPacientePorId(Long id) {
		pacienteDAO.findById(id);
	}

	public void alterar(Long id) {
		Paciente paciente = pacienteDAO.findById(id);
		pacienteDAO.update(paciente);
	}

	public void pesquisaCep(AjaxBehaviorEvent event) {

		try {

			URL url = new URL("https://viacep.com.br/ws/" + paciente.getCep() + "/json/");
			URLConnection connection = url.openConnection();
			InputStream is = connection.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));

			String cep = "";
			StringBuilder jsonCep = new StringBuilder();
			while ((cep = br.readLine()) != null) {
				jsonCep.append(cep);

			}
			Paciente gsonAux = new Gson().fromJson(jsonCep.toString(), Paciente.class);

			paciente.setCep(gsonAux.getCep());
			paciente.setLogradouro(gsonAux.getLogradouro());
			paciente.setBairro(gsonAux.getBairro());
			paciente.setLocalidade(gsonAux.getLocalidade());
			paciente.setUf(gsonAux.getUf());
			// System.out.println(gsonAux);

		} catch (Exception e) {
			mostrarMsg("Erro!");
		}

	}

	private void mostrarMsg(String msg) {
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage message = new FacesMessage(msg);
		context.addMessage(null, message);

	}

	// getters e setters

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public List<Paciente> getPacientes() {
		return pacientes;
	}

	public void setPacientes(List<Paciente> pacientes) {
		this.pacientes = pacientes;
	}

}
