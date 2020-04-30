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

import br.com.adm.clinica.model.Leito;
import br.com.adm.clinica.model.LeitoInternacao;
import br.com.adm.clinica.service.LeitoInternacaoService;
import br.com.adm.clinica.service.LeitoService;
import br.com.adm.clinica.util.TransformaJavaEmJson;
import lombok.Getter;
import lombok.Setter;

@Named
@ViewScoped
@Getter
@Setter
public class LeitoBean implements Serializable {

	private static final long serialVersionUID = -3126727348707012150L;
	
	@Inject
	private Leito leito;
	
	@Inject
	private LeitoService leitoService;
		
	private List<Leito> leitos = new ArrayList<Leito>();
	
	@Inject
	private LeitoInternacao leitoInternacao;
	
	@Inject
	private LeitoInternacaoService leitoInternacaoService;
		
	private String nomePaciente;
	
	private static Long idLeitoInternacao;
	
	private static Long idLeito;
	
	@Inject
	private TransformaJavaEmJson transformaJavaEmJson;
	
	private String nomesJson;
	
	@PostConstruct
	public void init() {
		try {
			nomesJson = transformaJavaEmJson.transformaJavaEmJsonPacienteSemInternacao();
			leitos = leitoService.listar();
		}catch (NullPointerException e) {
			// TODO: handle exception
		}
	}
	
	public void salvar() {
		leitoService.salvar(leito);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Leito Cadastrado Com Sucesso", "Leito Cadastrado Com Sucesso"));
	}
	
	public void deletar(Long id) {
		leitoService.deletar(id);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Leito Deletado Com Sucesso", "Leito Deletado Com Sucesso"));
		leitos = leitoService.listar();
	}
	
	public void alterar() {
		Leito leitoSelecionado = leitoService.buscarPorId(idLeito);
		leitoSelecionado.setDescricao(leito.getDescricao());
		leitoService.alterar(leitoSelecionado);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Leito Atualizado Com Sucesso", "Leito Atualizado Com Sucesso"));
		leitos = leitoService.listar();

	}
	
	public void salvarLeitoIntercacao(Long id) {
		Long numero;
		leitoInternacao = new LeitoInternacao();
		Leito leito = leitoService.buscarPorId(id);
		leitoInternacao.setLeito(leito);
		numero = leitoInternacaoService.buscarMaiorLeitosInternacaoPorLeito(id);
		if(numero == null) {
			numero = 0L;		
		}		
		leitoInternacao.setNumero(numero + 1);
		leitoInternacaoService.salvar(leitoInternacao);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Leito de internação Cadastrado Com Sucasso", "Leito de internação Cadastrado Com Sucasso"));
		
	}
	
	public void showPageEditar(Long id) throws IOException {
		leito = leitoService.buscarPorId(id);
		System.out.println(leito.getDescricao());
		idLeito = id;
	//	FacesContext.getCurrentInstance().getExternalContext().redirect("editarleito.xhtml?faces-redirect=true");
}
	
	public void showLeitos() throws IOException {
		 FacesContext.getCurrentInstance().getExternalContext().redirect("listarleito.xhtml?faces-redirect=true");
   }

}
