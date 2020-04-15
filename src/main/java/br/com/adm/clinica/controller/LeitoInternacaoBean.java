package br.com.adm.clinica.controller;

import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.adm.clinica.dao.LeitoDAO;
import br.com.adm.clinica.dao.LeitoInternacaoDAO;
import br.com.adm.clinica.dao.PacienteDAO;
import br.com.adm.clinica.model.Leito;
import br.com.adm.clinica.model.LeitoInternacao;
import br.com.adm.clinica.model.Paciente;
import br.com.adm.clinica.util.TransformaJavaEmJson;

@ManagedBean
@SessionScoped
public class LeitoInternacaoBean implements Serializable {

	private static final long serialVersionUID = -2138249260881247947L;

	private LeitoInternacaoDAO leitoInternacaoDAO = new LeitoInternacaoDAO();
	
	private PacienteDAO pacienteDAO = new PacienteDAO();
	
	private LeitoDAO leitoDAO = new LeitoDAO();
	
	private LeitoInternacao leitoInternacao = new LeitoInternacao();
	
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
		Leito leito = leitoDAO.findById(id);
		leitoInternacao.setLeito(leito);
		leitoInternacaoDAO.save(leitoInternacao);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Leito de internação Cadastrado Com Sucasso", "Leito de internação Cadastrado Com Sucasso"));
	}
	
	public void InternarPaciente() throws ParseException {
		Paciente paciente = pacienteDAO.buscarPacientePorNome(nomePaciente);
		LeitoInternacao leitoInternacao = leitoInternacaoDAO.findById(idLeitoInternacao);
		String data = format.format(new Date());
		Date data2 = format.parse(data);
		leitoInternacao.setDataInternacao(data2);
		leitoInternacao.setPaciente(paciente);
		leitoInternacaoDAO.update(leitoInternacao);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Paciente Internado Com Sucesso", "Paciente Internado Com Sucesso"));
	}
	
	public void deletar(Long id){
		leitoInternacaoDAO.delete(id);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Leito de internação Deletado Com Sucasso", "Leito de internação Deletado Com Sucasso"));
	}
	
	public void alterar(Long id) {
		LeitoInternacao leito = leitoInternacaoDAO.findById(id);
		leitoInternacaoDAO.update(leito);
	}
	
	public void showLeitoInternacaoDesocupado(Leito leito) throws IOException {
	     leitosInternacaoDesocupados = leitoInternacaoDAO.buscarLeitosDeInternacaoDesocupados(leito);
		 FacesContext.getCurrentInstance().getExternalContext().redirect("listarleitointernacaodesocupado.xhtml?faces-redirect=true");

   }
	
	public void showLeitoInternacaoOcupado(Leito leito) throws IOException {
	     leitosInternacaoOcupados = leitoInternacaoDAO.buscarLeitoDeInternacaOcupados(leito);
		 FacesContext.getCurrentInstance().getExternalContext().redirect("listarleitointernacaoocupado.xhtml?faces-redirect=true");
  }
	
	public void showLeitoInternacaoEditar(Long id) throws IOException {
		idLeitoInternacao = id;
		 FacesContext.getCurrentInstance().getExternalContext().redirect("internarpaciente.xhtml?faces-redirect=true");
	}

	public LeitoInternacaoDAO getLeitoInternacaoDAO() {
		return leitoInternacaoDAO;
	}

	public void setLeitoInternacaoDAO(LeitoInternacaoDAO leitoInternacaoDAO) {
		this.leitoInternacaoDAO = leitoInternacaoDAO;
	}

	public PacienteDAO getPacienteDAO() {
		return pacienteDAO;
	}

	public void setPacienteDAO(PacienteDAO pacienteDAO) {
		this.pacienteDAO = pacienteDAO;
	}

	public LeitoDAO getLeitoDAO() {
		return leitoDAO;
	}

	public void setLeitoDAO(LeitoDAO leitoDAO) {
		this.leitoDAO = leitoDAO;
	}

	public LeitoInternacao getLeitoInternacao() {
		return leitoInternacao;
	}

	public void setLeitoInternacao(LeitoInternacao leitoInternacao) {
		this.leitoInternacao = leitoInternacao;
	}

	public List<LeitoInternacao> getLeitosInternacao() {
		return leitosInternacao;
	}

	public void setLeitosInternacao(List<LeitoInternacao> leitosInternacao) {
		this.leitosInternacao = leitosInternacao;
	}

	public Long getIdLeito() {
		return idLeito;
	}

	public void setIdLeito(Long idLeito) {
		this.idLeito = idLeito;
	}

	public String getNomePaciente() {
		return nomePaciente;
	}

	public void setNomePaciente(String nomePaciente) {
		this.nomePaciente = nomePaciente;
	}

	public List<LeitoInternacao> getLeitosInternacaoDesocupados() {
		return leitosInternacaoDesocupados;
	}

	public void setLeitosInternacaoDesocupados(List<LeitoInternacao> leitosInternacaoDesocupados) {
		this.leitosInternacaoDesocupados = leitosInternacaoDesocupados;
	}

	public TransformaJavaEmJson getTransformaJavaEmJson() {
		return transformaJavaEmJson;
	}

	public void setTransformaJavaEmJson(TransformaJavaEmJson transformaJavaEmJson) {
		this.transformaJavaEmJson = transformaJavaEmJson;
	}

	public String getNomesJson() {
		return nomesJson;
	}

	public void setNomesJson(String nomesJson) {
		this.nomesJson = nomesJson;
	}

	public static Long getIdLeitoInternacao() {
		return idLeitoInternacao;
	}

	public static void setIdLeitoInternacao(Long idLeitoInternacao) {
		LeitoInternacaoBean.idLeitoInternacao = idLeitoInternacao;
	}

	public List<LeitoInternacao> getLeitosInternacaoOcupados() {
		return leitosInternacaoOcupados;
	}

	public void setLeitosInternacaoOcupados(List<LeitoInternacao> leitosInternacaoOcupados) {
		this.leitosInternacaoOcupados = leitosInternacaoOcupados;
	}
	
}
