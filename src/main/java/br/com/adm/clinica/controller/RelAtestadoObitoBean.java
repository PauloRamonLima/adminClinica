package br.com.adm.clinica.controller;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

import br.com.adm.clinica.dao.PacienteDAO;
import br.com.adm.clinica.model.Paciente;
import br.com.adm.clinica.model.vo.AtestadoObitoVO;
import br.com.adm.clinica.util.RelatorioGeneric;
import br.com.adm.clinica.util.TransformaJavaEmJson;

@ManagedBean
@ViewScoped
public class RelAtestadoObitoBean implements Serializable {
	
	private static final long serialVersionUID = -4487089058590375789L;

	private RelatorioGeneric relatorioGeneric = new RelatorioGeneric();
	
	private String nomePaciente;
	
	private AtestadoObitoVO atestado = new AtestadoObitoVO();
	
	private List<AtestadoObitoVO> atestadosObitoVO = new ArrayList<AtestadoObitoVO>();
	
	private List<Paciente> pacientes = new ArrayList<Paciente>();
	
	private List<String> nomes = new ArrayList<String>();
	
	private PacienteDAO pacienteDAO = new PacienteDAO();
	
	private String nomesJson;
	
	private String data;
	
	private String motivo;
	
	private TransformaJavaEmJson transformaJavaEmJson = new TransformaJavaEmJson();
	
	@PostConstruct
	public void init() {
		nomesJson = transformaJavaEmJson.transformaJavaEmJsonPaciente();
	}
	
	public String formataData() {
		
		try {
			SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			Date dataFormatada;
			dataFormatada = formatDate.parse(data.replace("T", ""));
			String dataObito = dataFormatada.toString();
			System.out.println(dataObito);
			return dataObito;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			return null;
		}
	}
	
	public void gerarRelatorioAtestadoObito() throws IOException, ParseException{
		Map<String, Object> parametros = new HashMap<String, Object>();
		File logo = new File(getRealPath("resources/img/logosigclean.png"));
		Image logoSistema = ImageIO.read(logo);
		atestadosObitoVO = new ArrayList<AtestadoObitoVO>();
		Paciente paciente = pacienteDAO.buscarPacientePorNome(atestado.getNome());
		atestado.setHorario(data);
		atestado.setCpf(paciente.getCpf());
		atestado.setMunicipio(paciente.getLocalidade());
		atestado.setNascimento(paciente.getDtNascimento());
		atestado.setRg(paciente.getRg());
		atestado.setUf(paciente.getUf());
		atestado.setMotivo(motivo);
		parametros.put("Logo", logoSistema);
		atestadosObitoVO.add(atestado);
		relatorioGeneric.gerarRelatorio(atestadosObitoVO,"AtestadoObito.jasper", parametros, "atestado-obito");
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Atestado Gerado Com Sucesso", "Atestado Gerado Com Sucesso"));
	}
	
	private String getRealPath(String diretorio) {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(false);
		return session.getServletContext().getRealPath(diretorio);
	}
	
	public RelatorioGeneric getRelatorioGeneric() {
		return relatorioGeneric;
	}

	public void setRelatorioGeneric(RelatorioGeneric relatorioGeneric) {
		this.relatorioGeneric = relatorioGeneric;
	}

	public String getNomePaciente() {
		return nomePaciente;
	}

	public void setNomePaciente(String nomePaciente) {
		this.nomePaciente = nomePaciente;
	}

	public AtestadoObitoVO getAtestado() {
		return atestado;
	}

	public void setAtestado(AtestadoObitoVO atestado) {
		this.atestado = atestado;
	}

	public List<AtestadoObitoVO> getAtestadosObitoVO() {
		return atestadosObitoVO;
	}

	public void setAtestadosObitoVO(List<AtestadoObitoVO> atestadosObitoVO) {
		this.atestadosObitoVO = atestadosObitoVO;
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

	public PacienteDAO getPacienteDAO() {
		return pacienteDAO;
	}

	public void setPacienteDAO(PacienteDAO pacienteDAO) {
		this.pacienteDAO = pacienteDAO;
	}

	public String getNomesJson() {
		return nomesJson;
	}

	public void setNomesJson(String nomesJson) {
		this.nomesJson = nomesJson;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	

	
}
