package br.com.adm.clinica.controller;

import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.adm.clinica.model.Leito;
import br.com.adm.clinica.model.LeitoInternacao;
import br.com.adm.clinica.model.Paciente;
import br.com.adm.clinica.service.LeitoInternacaoService;
import br.com.adm.clinica.service.LeitoService;
import br.com.adm.clinica.service.PacienteService;
import br.com.adm.clinica.util.TransformaJavaEmJson;
import lombok.Getter;
import lombok.Setter;

@Named
@SessionScoped
@Getter
@Setter
public class LeitoInternacaoBean implements Serializable {

	private static final long serialVersionUID = -2138249260881247947L;

	@Inject
	private LeitoInternacaoService leitoInternacaoService;
	
	@Inject
	private PacienteService pacienteService;
	
	@Inject
	private LeitoService leitoService;
	
	@Inject
	private LeitoInternacao leitoInternacao;
	
	private List<LeitoInternacao> leitosInternacao = new ArrayList<LeitoInternacao>();
	
	private List<LeitoInternacao> leitosInternacaoDesocupados = new ArrayList<LeitoInternacao>();
	
	private List<LeitoInternacao> leitosInternacaoOcupados = new ArrayList<LeitoInternacao>();
	
	private static Long idLeito;
	
	private String nomePaciente;
				
	private TransformaJavaEmJson transformaJavaEmJson = new TransformaJavaEmJson();
	
	private String nomesJson;
		
	private static Long idLeitoInternacao;
	
	private SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm");


	@PostConstruct
	public void init() {
		try {
			nomesJson = transformaJavaEmJson.transformaJavaEmJsonPacienteSemInternacao();
		}catch (NullPointerException e) {
			// TODO: handle exception
		}
	}
	
	public void salvar(Long id) {
		leitoInternacao = new LeitoInternacao();
		Leito leito = leitoService.buscarPorId(id);
		leitoInternacao.setLeito(leito);
		leitoInternacaoService.salvar(leitoInternacao);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Leito de internação Cadastrado Com Sucasso", "Leito de internação Cadastrado Com Sucasso"));
	}
	
	public void InternarPaciente() throws ParseException {
		Paciente paciente = pacienteService.buscarPacientePorNome(nomePaciente);
		LeitoInternacao leitoInternacao = leitoInternacaoService.buscarPorId(idLeitoInternacao);
		String data = format.format(new Date());
		Date data2 = format.parse(data);
		leitoInternacao.setDataInternacao(data2);
		leitoInternacao.setPaciente(paciente);
		leitoInternacaoService.alterar(leitoInternacao);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Paciente Internado Com Sucesso", "Paciente Internado Com Sucesso"));
		nomePaciente = "";
	}
	
	public void deletar(Long id){
		leitoInternacaoService.deletar(id);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Leito de internação Deletado Com Sucasso", "Leito de internação Deletado Com Sucasso"));
	}
	
	public void alterar(Long id) {
		LeitoInternacao leito = leitoInternacaoService.buscarPorId(id);
		leitoInternacaoService.alterar(leito);
	}
	
	public void showLeitoInternacaoDesocupado(Leito leito) throws IOException {
	     leitosInternacaoDesocupados = leitoInternacaoService.buscarLeitosDeInternacaoDesocupados(leito);
		 FacesContext.getCurrentInstance().getExternalContext().redirect("listarleitointernacaodesocupado.xhtml?faces-redirect=true");

   }
	
	public void showLeitoInternacaoOcupado(Leito leito) throws IOException {
	     leitosInternacaoOcupados = leitoInternacaoService.buscarLeitoDeInternacaOcupados(leito);
		 FacesContext.getCurrentInstance().getExternalContext().redirect("listarleitointernacaoocupado.xhtml?faces-redirect=true");
  }
	
	public void showLeitoInternacaoEditar(Long id) throws IOException {
		idLeitoInternacao = id;
		 FacesContext.getCurrentInstance().getExternalContext().redirect("internarpaciente.xhtml?faces-redirect=true");
	}
}
