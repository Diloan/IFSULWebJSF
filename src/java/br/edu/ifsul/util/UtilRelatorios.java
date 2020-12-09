/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author Diloan Silva <diloan.silva@gmail.com>
 */
public class UtilRelatorios {

    public static void imprimeRelatorio(String nomeRelatorio, HashMap parametros, List lista) {
        
        Date dataHoraAtual = new Date();
        
        String dataAtual = new SimpleDateFormat("dd/MM/yyyy").format(dataHoraAtual);
        
        
        try {
            
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(lista);
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.getResponseComplete();
            ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
            String path = servletContext.getRealPath("/WEB-INF/relatorios/");
            parametros.put("SUBREPORT_DIR", path + File.separator);
            JasperPrint jasperPrint = JasperFillManager.fillReport(servletContext.getRealPath(
                    "/WEB-INF/relatorios/") + nomeRelatorio + ".jasper", parametros, dataSource);
            HttpServletResponse res = (HttpServletResponse) facesContext.getExternalContext().getResponse();
            res.setContentType("application/pdf");
            int codido = (int) (Math.random() * 1000);
            res.setHeader("Content-disposition", "inline;filename=relatorio_" + dataAtual + "_" + codido + ".pdf");
            byte[] b = JasperExportManager.exportReportToPdf(jasperPrint);
            res.getOutputStream().write(b);
            res.getCharacterEncoding();
            facesContext.responseComplete();
        } catch (JRException e) {
            e.printStackTrace();

        } catch (Exception e) {
            Util.mensagemErro("Erro ao imprimir relatório: " + Util.getMensagemErro(e));
            e.printStackTrace();

        }
    }
}
