/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.hibernate.loader.custom.Return;

/**
 *
 *  @author Diloan Silva
 * @email diloan.silva@gmail.com
 */
public class Util {
    public static String getMensagemErro(Exception e){
        while(e.getCause() != null){
            e = (Exception) e.getCause();
        }
        String retorno = e.getMessage();
        if(retorno.contains("foreign key")){
            retorno = "Registro não pode ser excluido por possuir referências no sistema...";
        }
        return retorno;
    } 
    
    public static void mensagemInformacao(String msg){
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.getExternalContext().getFlash().setKeepMessages(true);
        FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, "");
        facesContext.addMessage(null, mensagem);
        
    }
    
    public static void mensagemErro(String msg){
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.getExternalContext().getFlash().setKeepMessages(true);
        FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, "");
        facesContext.addMessage(null, mensagem);
        
    }
}
