package br.com.adm.clinica.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collections;
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
import br.com.adm.clinica.model.Evolucao;
import br.com.adm.clinica.model.Exame;
import br.com.adm.clinica.model.LeitoInternacao;
import br.com.adm.clinica.model.Paciente;
import br.com.adm.clinica.model.vo.PacienteInternadoVO;
import br.com.adm.clinica.service.ConsultaService;
import br.com.adm.clinica.service.EvolucaoService;
import br.com.adm.clinica.service.ExameService;
import br.com.adm.clinica.service.LeitoInternacaoService;
import br.com.adm.clinica.service.PacienteService;
import lombok.Getter;
import lombok.Setter;

@Named
@ViewScoped
@Getter
@Setter
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
	private EvolucaoService evolucaoService;

	@Inject
	private Paciente paciente;
	
	@Inject
	private LeitoInternacao leitoInternacao;
	
	@Inject
	private Evolucao evolucao;

	private List<Paciente> pacientes = new ArrayList<Paciente>();

	private List<PacienteInternadoVO> pacientesInternados = new ArrayList<>();

	private List<LeitoInternacao> leitosDeInternacaoOcupados = new ArrayList<>();
	
	private List<Exame> examesPacienteInternado = new ArrayList<>();
	
	private List<Evolucao> evolucoesPacientesInternados = new ArrayList<>();
	
	private boolean mostrarNovaEvolucao = false;
	
	@PostConstruct
	public void init() {
		try {
			listar();
			buscarPacientesInternados();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
	}
	
	public void listar() {
		pacientes = pacienteService.listar();
		Collections.sort(pacientes);
	}
	

	public void salvar() {
		if(validaPacienteSave())
		pacienteService.salvar(paciente);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Paciente Cadastrado Com Sucesso", "Paciente Cadastrado Com Sucesso"));
		paciente = new Paciente();
	}
	
	public boolean validaPacienteSave() {
		try {
			if (pacienteService.buscarPacientePorCpf(paciente.getCpf()) != null) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "CPF já cadastrado", "CPF já cadastrado"));
				return false;
			}
		} catch (NoResultException e) {

		}

		try {
			if (pacienteService.buscarPacientePorRg(paciente.getRg()) != null) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "RG já cadastrado", "RG já cadastrado"));
				return false;
			}
		} catch (NoResultException e) {

		}
		
		return true;
	}

	public void deletar(Long id) {
		Paciente paciente = pacienteService.buscarPorId(id);
		List<Exame> exames = exameService.buscarExamesPorPaciente(paciente);
		if (!exames.isEmpty()) {
			for (Exame exame : exames) {
				exameService.deletar(exame.getId());
			}
		}
		List<Consulta> consultas = consultaService.buscarConsultaPorPaciente(paciente);
		if (!consultas.isEmpty()) {
			for (Consulta consulta : consultas) {
				consultaService.deletar(consulta.getId());
			}
		}

		try {
			LeitoInternacao leito = leitoInternacaoService.buscarLeitoDeInternacaoPorPaciente(paciente);
			leito.setPaciente(null);
			leitoInternacaoService.alterar(leito);
		} catch (NoResultException e) {

		}

		pacienteService.deletar(id);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Paciente Deletado Com Sucesso", "Paciente Deletado Com Sucesso"));
		listar();
	}

	public void buscarPacientePorId(Long id) {
		paciente = pacienteService.buscarPorId(id);
	}

	public void buscarPacientesInternados() {
		pacientesInternados = new ArrayList<>();
		leitosDeInternacaoOcupados = new ArrayList<>();
		leitosDeInternacaoOcupados = leitoInternacaoService.buscarPacientesInternados();
		PacienteInternadoVO pacienteVO = new PacienteInternadoVO();
		for (int i = 0; i < leitosDeInternacaoOcupados.size(); i++) {
			pacienteVO.setNomePaciente(leitosDeInternacaoOcupados.get(i).getPaciente().getNome());
			pacienteVO.setCpf(leitosDeInternacaoOcupados.get(i).getPaciente().getCpf());
			pacienteVO.setLeito(leitosDeInternacaoOcupados.get(i).getLeito().getDescricao());
			pacienteVO.setLeitoInternacao(leitosDeInternacaoOcupados.get(i).getNumero().toString());
			pacientesInternados.add(pacienteVO);
			pacienteVO = new PacienteInternadoVO();

		}
		Collections.sort(pacientesInternados);
	}

	public void alterar(Paciente paciente) {
		pacienteService.alterar(paciente);
		listar();
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
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Alta Concluida", "Alta Concluida"));
		buscarPacientesInternados();
	}
	
	public void buscarExamesPorPacienteInternado(String cpf) {
		paciente = pacienteService.buscarPacientePorCpf(cpf);
		examesPacienteInternado = exameService.buscarExamesPorPaciente(paciente);
	}
	
	public void buscarEvolucoesPorPaciente(String cpf) {
		paciente = pacienteService.buscarPacientePorCpf(cpf);
		evolucoesPacientesInternados = evolucaoService.buscarEvolucoesDePaciente(paciente);
	}
	
	public void showNovaEvolucaoPage() throws IOException {
		//System.out.println(id);
//		FacesContext.getCurrentInstance().getExternalContext().redirect("novaevolucao.xhtml?faces-redirect=true&id=" + id);
		mostrarNovaEvolucao = true;
	}
	
	public void novaEvolucaoPaciente() {
		evolucao.setPaciente(paciente);
		evolucaoService.salvar(evolucao);
		buscarEvolucoesPorPaciente(paciente.getCpf());
		evolucao = new Evolucao();
		mostrarNovaEvolucao = false;
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Evolução Do Paciente Cadastrada", "Evolução Do Paciente Cadastrada"));
	}
	
}