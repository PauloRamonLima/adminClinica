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
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import br.com.adm.clinica.model.Paciente;
import br.com.adm.clinica.model.vo.AtestadoMedicoVO;
import br.com.adm.clinica.service.PacienteService;
import br.com.adm.clinica.util.RelatorioGeneric;
import br.com.adm.clinica.util.TransformaJavaEmJson;

@Named
@ViewScoped
public class RelAtestatoMedicoBean implements Serializable {
	
	private static final long serialVersionUID = 5766601780803929072L;

	private RelatorioGeneric relatorioGeneric = new RelatorioGeneric();
	
	private String nomePaciente;
	
	private String dias;
	
	@Inject
	private AtestadoMedicoVO atestado;
	
	private List<AtestadoMedicoVO> atestadosMedicoVO = new ArrayList<AtestadoMedicoVO>();
	
	private List<Paciente> pacientes = new ArrayList<Paciente>();
	
	private List<String> nomes = new ArrayList<String>();
	
	/*
	 * @Inject private PacienteService pacienteService;
	 */
	private String nomesJson;
	
	@Inject
	private TransformaJavaEmJson transformaJavaEmJson;

	
	@PostConstruct
	public void init() {
		nomesJson = transformaJavaEmJson.transformaJavaEmJsonPaciente();
	}
	
	public void gerarRelatorioAtestadoMedico() throws IOException {
		Map<String, Object> parametros = new HashMap<String, Object>();
		File logo = new File(getRealPath("resources/img/logosigclean.png"));
		Image logoSistema = ImageIO.read(logo);
		atestadosMedicoVO = new ArrayList<AtestadoMedicoVO>();
		parametros.put("Logo", logoSistema);
		atestadosMedicoVO.add(atestado);
		relatorioGeneric.gerarRelatorio(atestadosMedicoVO,"AtestadoMedico.jasper", parametros, "atestado-medico");
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Atestado Gerado Com Sucesso", "Atestado Gerado Com Sucesso"));
	}
	
	private String getRealPath(String diretorio) {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(false);
		return session.getServletContext().getRealPath(diretorio);
	}

	public String getNomePaciente() {
		return nomePaciente;
	}

	public void setNomePaciente(String nomePaciente) {
		this.nomePaciente = nomePaciente;
	}

	public String getDias() {
		return dias;
	}

	public void setDias(String dias) {
		this.dias = dias;
	}

	public RelatorioGeneric getRelatorioGeneric() {
		return relatorioGeneric;
	}

	public void setRelatorioGeneric(RelatorioGeneric relatorioGeneric) {
		this.relatorioGeneric = relatorioGeneric;
	}

	public AtestadoMedicoVO getAtestado() {
		return atestado;
	}

	public void setAtestado(AtestadoMedicoVO atestado) {
		this.atestado = atestado;
	}

	public List<AtestadoMedicoVO> getAtestadosMedicoVO() {
		return atestadosMedicoVO;
	}

	public void setAtestadosMedicoVO(List<AtestadoMedicoVO> atestadosMedicoVO) {
		this.atestadosMedicoVO = atestadosMedicoVO;
	}

	public List<Paciente> getPacientes() {
		return pacientes;
	}

	public void setPacientes(List<Paciente> pacientes) {
		this.pacientes = pacientes;
	}

	public List<String> getNomes() {
		return nomes;
	}

	public void setNomes(List<String> nomes) {
		this.nomes = nomes;
	}

	public String getNomesJson() {
		return nomesJson;
	}

	public void setNomesJson(String nomesJson) {
		this.nomesJson = nomesJson;
	}
	
	

}
