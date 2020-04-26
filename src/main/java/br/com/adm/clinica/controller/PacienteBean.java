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
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.NoResultException;

import com.google.gson.Gson;

import br.com.adm.clinica.model.Consulta;
import br.com.adm.clinica.model.Exame;
import br.com.adm.clinica.model.LeitoInternacao;
import br.com.adm.clinica.model.Paciente;
import br.com.adm.clinica.model.vo.PacienteInternadoVO;
import br.com.adm.clinica.service.ConsultaService;
import br.com.adm.clinica.service.ExameService;
import br.com.adm.clinica.service.LeitoInternacaoService;
import br.com.adm.clinica.service.PacienteService;

@Named
@ViewScoped
public class PacienteBean implements Serializable {

	private static final long serialVersionUID = -7178530229889330245L;
	
	@Inject
	private PacienteService pacienteService;
	
	@Inject
	private LeitoInternacaoService leitoInternacaoService;
	
	@Inject
	private ExameService exameService;
	
	@Inject
	private ConsultaService consultaService;

	@Inject
	private Paciente paciente;
	
	@Inject
	private LeitoInternacao leitoInternacao;

	private List<Paciente> pacientes = new ArrayList<Paciente>();
	
	private List<PacienteInternadoVO> pacientesInternados = new ArrayList<>();
	
	private List<LeitoInternacao> leitosDeInternacaoOcupados = new ArrayList<>();
	

	@PostConstruct
	public void init() {
		try {
			pacientes = pacienteService.listar();
			buscarPacientesInternados();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
	}

	public void salvar() {
		
		try {	
		if(pacienteService.buscarPacientePorCpf(paciente.getCpf()) != null) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"CPF já cadastrado", "CPF já cadastrado"));
				return;
			}
		}catch (NoResultException e) {
		
		}
		
		try {	
			if(pacienteService.buscarPacientePorRg(paciente.getRg()) != null) {
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"RG já cadastrado", "RG já cadastrado"));
					return;
				}
			}catch (NoResultException e) {
			
			}
		
		pacienteService.salvar(paciente);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Paciente Cadastrado Com Sucesso", "Paciente Cadastrado Com Sucesso"));
		paciente = new Paciente();
	}

	public void deletar(Long id) {
		Paciente paciente = pacienteService.buscarPorId(id);
		List<Exame> exames = exameService.buscarExamesPorPaciente(paciente);
		if(!exames.isEmpty()) {
			for(Exame exame : exames) {
				exameService.deletar(exame.getId());
			}
		}
		List<Consulta> consultas = consultaService.buscarConsultaPorPaciente(paciente);
		if(!consultas.isEmpty()) {
			for(Consulta consulta : consultas) {
				consultaService.deletar(consulta.getId());
			}
		}
		
		try {
			LeitoInternacao leito = leitoInternacaoService.buscarLeitoDeInternacaoPorPaciente(paciente);
			leito.setPaciente(null);
			leitoInternacaoService.alterar(leito);
		}catch (NoResultException e) {
			
		}
		
		pacienteService.deletar(id);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Paciente Deletado Com Sucesso", "Paciente Deletado Com Sucesso"));
	}

	public void buscarPacientePorId(Long id) {
		paciente = pacienteService.buscarPorId(id);
	}
	
	public void buscarPacientesInternados() {
		pacientesInternados = new ArrayList<>();
		leitosDeInternacaoOcupados = new ArrayList<>();
		leitosDeInternacaoOcupados = leitoInternacaoService.buscarPacientesInternados();
		PacienteInternadoVO pacienteVO = new PacienteInternadoVO();
		for(int i = 0; i < leitosDeInternacaoOcupados.size(); i++) {
			pacienteVO.setNomePaciente(leitosDeInternacaoOcupados.get(i).getPaciente().getNome());
			pacienteVO.setCpf(leitosDeInternacaoOcupados.get(i).getPaciente().getCpf());
			pacienteVO.setLeito(leitosDeInternacaoOcupados.get(i).getLeito().getDescricao());
			pacienteVO.setLeitoInternacao(leitosDeInternacaoOcupados.get(i).getNumero().toString());
			pacientesInternados.add(pacienteVO);
			pacienteVO = new PacienteInternadoVO();
			
		}
	}

	public void alterar(Long id) {
		Paciente paciente = pacienteService.buscarPorId(id);
		pacienteService.alterar(paciente);
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
	
	public void darAltaPaciente(String leito, Long numero) {
		leitoInternacao = leitoInternacaoService.buscarLeitoDeInternacaPorLeitoENumeracao(leito, numero);
		leitoInternacao.setDataInternacao(null);
		leitoInternacao.setPaciente(null);
		leitoInternacaoService.alterar(leitoInternacao);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Alta Concluida", "Alta Concluida"));
		
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

	public List<PacienteInternadoVO> getPacientesInternados() {
		return pacientesInternados;
	}

	public void setPacientesInternados(List<PacienteInternadoVO> pacientesInternados) {
		this.pacientesInternados = pacientesInternados;
	}

	public List<LeitoInternacao> getLeitosDeInternacaoOcupados() {
		return leitosDeInternacaoOcupados;
	}

	public void setLeitosDeInternacaoOcupados(List<LeitoInternacao> leitosDeInternacaoOcupados) {
		this.leitosDeInternacaoOcupados = leitosDeInternacaoOcupados;
	}

	public LeitoInternacao getLeitoInternacao() {
		return leitoInternacao;
	}

	public void setLeitoInternacao(LeitoInternacao leitoInternacao) {
		this.leitoInternacao = leitoInternacao;
	}

}
