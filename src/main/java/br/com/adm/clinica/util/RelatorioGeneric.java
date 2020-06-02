package br.com.adm.clinica.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;


public class RelatorioGeneric {	
	
	public void gerarRelatorio(List<?> lista,String caminho, Map<String, Object> parameters, String nomePdf){
		try {		
		
		File jasper = new File(getRealPath("resources/relatorios/" + caminho));
		FileInputStream relatorioSource = new FileInputStream(jasper);
		JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(lista); 
		JasperPrint jp = JasperFillManager.fillReport(relatorioSource, parameters, ds);
		JasperExportManager.exportReportToPdfFile(jp,getRealPath("resources/relatorios/" + nomePdf + ".pdf"));	
		JasperViewer viewer = new JasperViewer(jp, false); 
		viewer.setTitle("Visualizador De PDF");
	    viewer.show();
			 
		} catch (JRException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	private String getRealPath(String diretorio) {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(false);
		return session.getServletContext().getRealPath(diretorio);
	}

}
