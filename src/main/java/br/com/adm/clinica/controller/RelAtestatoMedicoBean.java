package br.com.adm.clinica.controller;

import java.awt.Image;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import br.com.adm.clinica.model.Paciente;
import br.com.adm.clinica.model.vo.AtestadoMedicoVO;
import br.com.adm.clinica.util.RelatorioGeneric;
import br.com.adm.clinica.util.TransformaJavaEmJson;
import lombok.Getter;
import lombok.Setter;

@Named
@ViewScoped
@Getter
@Setter
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
		relatorioGeneric.gerarRelatorio(atestadosMedicoVO, "AtestadoMedico.jasper", parametros, "atestado-medico");
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Atestado Gerado Com Sucesso", "Atestado Gerado Com Sucesso"));
	}
	
	public void gerarPlanilhaAtestadoMedico() {
		atestadosMedicoVO = new ArrayList<AtestadoMedicoVO>();
	
		atestadosMedicoVO.add(atestado);
		XSSFWorkbook wb = new XSSFWorkbook();
		
		XSSFSheet folha = wb.createSheet("Atestado Medico");
		Map<String, Object[]> mapa = new TreeMap<>();
		mapa.put("1",new Object[] { "Nome", "Dias"});
		mapa.put("2",new Object[] { atestado.getNome()});
		mapa.put("3",new Object[] { atestado.getDias()});
		
		Set<String> keySet = mapa.keySet();
		int contRow = 0;
		for(String chave : keySet) {
			Row row = folha.createRow(contRow++);
			Object[] objetoAtual = mapa.get(chave);
			
			int contCell = 0;
			for(Object obj : objetoAtual) {
				Cell celula = row.createCell(contCell++);
				
				if(obj instanceof String) {
					celula.setCellValue((String) obj);
				} 
			}
		}
		try {
			FileOutputStream out = new FileOutputStream(new File("ex.atestado.xlsx"));
			wb.write(out);
			out.close();
			System.out.println("Planilha gerada");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String getRealPath(String diretorio) {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		return session.getServletContext().getRealPath(diretorio);
	}

}

