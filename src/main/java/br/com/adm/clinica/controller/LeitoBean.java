package br.com.adm.clinica.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import br.com.adm.clinica.dao.LeitoDAO;
import br.com.adm.clinica.dao.LeitoInternacaoDAO;
import br.com.adm.clinica.model.Leito;
import br.com.adm.clinica.model.LeitoInternacao;
import br.com.adm.clinica.util.TransformaJavaEmJson;

@ManagedBean
@RequestScoped
public class LeitoBean implements Serializable {

	private static final long serialVersionUID = -3126727348707012150L;
	
	private Leito leito = new Leito();
	
	private LeitoDAO leitoDAO = new LeitoDAO();
		
	private List<Leito> leitos = new ArrayList<Leito>();
	
	private LeitoInternacao leitoInternacao;
	
	private LeitoInternacaoDAO leitoInternacaoDAO = new LeitoInternacaoDAO();
		
	private String nomePaciente;
	
	private static Long idLeitoInternacao;
	
	private static Long idLeito;
	
	private TransformaJavaEmJson transformaJavaEmJson = new TransformaJavaEmJson();
	
	private String nomesJson;
	
	@PostConstruct
	public void init() {
		try {
			nomesJson = transformaJavaEmJson.transformaJavaEmJsonPaciente();
			System.out.println("chamou");
			leitos = leitoDAO.findAll();
		}catch (NullPointerException e) {
			// TODO: handle exception
		}
	}
	
	public void salvar() {
		leitoDAO.save(leito);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Leito Cadastrado Com Sucasso", "Leito Cadastrado Com Sucasso"));
	}
	
	public void deletar(Long id) {
		leitoDAO.delete(id);
		leitos = leitoDAO.findAll();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Leito Deletado Com Sucasso", "Leito Deletado Com Sucasso"));

	}
	
	public void alterar() {
		Leito leitoSelecionado = leitoDAO.findById(idLeito);
		leitoSelecionado.setDescricao(leito.getDescricao());
		leitoDAO.update(leitoSelecionado);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Leito Atualizado Com Sucasso", "Leito Atualizado Com Sucasso"));
	}
	
	public void salvarLeitoIntercacao(Long id) {
		Long numero;
		leitoInternacao = new LeitoInternacao();
		Leito leito = leitoDAO.findById(id);
		leitoInternacao.setLeito(leito);
		numero = leitoInternacaoDAO.buscarMaiorLeitosInternacaoPorLeito(id);
		if(numero == null) {
			numero = 0L;		
		}		
		leitoInternacao.setNumero(numero + 1);
		leitoInternacaoDAO.save(leitoInternacao);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Leito de internação Cadastrado Com Sucasso", "Leito de internação Cadastrado Com Sucasso"));
		
	}
	
	public void showPageEditar(Long id) throws IOException {
		idLeito = id;
		FacesContext.getCurrentInstance().getExternalContext().redirect("editarleito.xhtml?faces-redirect=true");
}
	
	public void showLeitos() throws IOException {
		 FacesContext.getCurrentInstance().getExternalContext().redirect("listarleito.xhtml?faces-redirect=true");
   }

	public Leito getLeito() {
		return leito;
	}

	public void setLeito(Leito leito) {
		this.leito = leito;
	}

	public LeitoDAO getLeitoDAO() {
		return leitoDAO;
	}

	public void setLeitoDAO(LeitoDAO leitoDAO) {
		this.leitoDAO = leitoDAO;
	}

	public List<Leito> getLeitos() {
		return leitos;
	}

	public void setLeitos(List<Leito> leitos) {
		this.leitos = leitos;
	}

	public LeitoInternacao getLeitoInternacao() {
		return leitoInternacao;
	}

	public void setLeitoInternacao(LeitoInternacao leitoInternacao) {
		this.leitoInternacao = leitoInternacao;
	}

	public LeitoInternacaoDAO getLeitoInternacaoDAO() {
		return leitoInternacaoDAO;
	}

	public void setLeitoInternacaoDAO(LeitoInternacaoDAO leitoInternacaoDAO) {
		this.leitoInternacaoDAO = leitoInternacaoDAO;
	}

	public String getNomePaciente() {
		return nomePaciente;
	}

	public void setNomePaciente(String nomePaciente) {
		this.nomePaciente = nomePaciente;
	}

	public Long getIdLeitoInternacao() {
		return idLeitoInternacao;
	}

	public void setIdLeitoInternacao(Long idLeitoInternacao) {
		this.idLeitoInternacao = idLeitoInternacao;
	}

	public Long getIdLeito() {
		return idLeito;
	}

	public void setIdLeito(Long idLeito) {
		this.idLeito = idLeito;
	}

	public String getNomesJson() {
		return nomesJson;
	}

	public void setNomesJson(String nomesJson) {
		this.nomesJson = nomesJson;
	}
	
}
