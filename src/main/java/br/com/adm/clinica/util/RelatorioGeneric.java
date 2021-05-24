package br.com.adm.clinica.util;

import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;


public class RelatorioGeneric {	
	
	public void gerarRelatorio(List<?> lista,String caminho, Map<String, Object> parameters, String nomePdf){
		try {		
		File logo = new File(getRealPath("resources/img/logosigclean.png"));
		Image logoSistema = ImageIO.read(logo);	
		parameters.put("Logo", logoSistema);
        final HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		File jasper = new File(getRealPath("resources/relatorios/" + caminho));
		FileInputStream relatorioSource = new FileInputStream(jasper);
		JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(lista); 
		JasperPrint jp = JasperFillManager.fillReport(relatorioSource, parameters, ds);
		JasperExportManager.exportReportToPdfFile(jp,getRealPath("resources/relatorios/" + nomePdf + ".pdf"));	
		final HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.sendRedirect(request.getContextPath() + nomePdf);
			 
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
