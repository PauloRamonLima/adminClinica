package br.com.adm.clinica.controller;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;

import br.com.adm.clinica.model.vo.AtestadoMedicoVO;
import br.com.adm.clinica.model.vo.ReceituarioVO;
import br.com.adm.clinica.util.RelatorioGeneric;
import br.com.adm.clinica.util.TransformaJavaEmJson;

@ManagedBean
@ViewScoped
public class RelReceituarioBean implements Serializable {

	private static final long serialVersionUID = -4922485544590919885L;
	
	private ReceituarioVO receituarioVO = new ReceituarioVO();
	
	private List<ReceituarioVO> receituariosVO = new ArrayList<ReceituarioVO>();
	
	private TransformaJavaEmJson transformaJavaEmJson = new TransformaJavaEmJson();
	
	private RelatorioGeneric relatorioGeneric = new RelatorioGeneric();
	
	private String nomesJson;
	
	@PostConstruct
	public void init() {
		nomesJson = transformaJavaEmJson.transformaJavaEmJsonPaciente();
	}
	
	public void gerarRelatorioReceituario() throws IOException {
		Map<String, Object> parametros = new HashMap<String, Object>();
		File logo = new File(getRealPath("resources/img/logosigclean.png"));
		Image logoSistema = ImageIO.read(logo);
		receituariosVO = new ArrayList<ReceituarioVO>();
		parametros.put("Logo", logoSistema);
		receituariosVO.add(receituarioVO);
		relatorioGeneric.gerarRelatorio(receituariosVO,"receita.jasper", parametros, "receituario-medico");
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Receituario Gerado Com Sucesso", "Receituario Gerado Com Sucesso"));
	}
	
	private String getRealPath(String diretorio) {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(false);
		return session.getServletContext().getRealPath(diretorio);
	}

	public ReceituarioVO getReceituarioVO() {
		return receituarioVO;
	}

	public void setReceituarioVO(ReceituarioVO receituarioVO) {
		this.receituarioVO = receituarioVO;
	}

	public List<ReceituarioVO> getReceituariosVO() {
		return receituariosVO;
	}

	public void setReceituariosVO(List<ReceituarioVO> receituariosVO) {
		this.receituariosVO = receituariosVO;
	}

	public TransformaJavaEmJson getTransformaJavaEmJson() {
		return transformaJavaEmJson;
	}

	public void setTransformaJavaEmJson(TransformaJavaEmJson transformaJavaEmJson) {
		this.transformaJavaEmJson = transformaJavaEmJson;
	}

	public RelatorioGeneric getRelatorioGeneric() {
		return relatorioGeneric;
	}

	public void setRelatorioGeneric(RelatorioGeneric relatorioGeneric) {
		this.relatorioGeneric = relatorioGeneric;
	}

	public String getNomesJson() {
		return nomesJson;
	}

	public void setNomesJson(String nomesJson) {
		this.nomesJson = nomesJson;
	}
}
