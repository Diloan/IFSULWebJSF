/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.PaisDAO;
import br.edu.ifsul.modelo.Pais;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Diloan Silva
 * @email diloan.silva@gmail.com
 */
@ManagedBean(name = "controlePais")
@SessionScoped
public class ControlePais implements Serializable {

    private PaisDAO<Pais> dao;
    private Pais objeto;

    public ControlePais() {
        dao = new PaisDAO<>();
    }

    public String listar() {
        return "/privado/pais/listar?faces-redirect=true";
    }

    public String novo() {
        objeto = new Pais();
        return "formulario?faces-redirect=true";
    }

    public String salvar() {
        boolean persistiu = false;
        if (objeto.getId() == null) {
            persistiu = dao.persistir(objeto);
        } else {
            persistiu = dao.merge(objeto);
        }
        if (persistiu) {
            Util.mensagemInformacao(dao.getMensagem());
            return "listar?faces-redirect=true";
        } else {
            Util.mensagemErro(dao.getMensagem());
            return "formulario?faces-redirect=true";
        }
    }

    public String cancelar() {
        return "/privado/pais/listar?faces-rediret=true";
    }

    public String editar(Integer id) {
        objeto = dao.localizar(id);
        return "formulario?faces-redirect=true";
    }

    public void remover(Integer id) {
        objeto = dao.localizar(id);
        if (dao.remove(objeto)) {
            Util.mensagemInformacao(dao.getMensagem());
        } else {
            Util.mensagemErro(dao.getMensagem());
        }
    }

    /**
     * @return the dao
     */
    public PaisDAO getDao() {
        return dao;
    }

    /**
     * @param dao the dao to set
     */
    public void setDao(PaisDAO dao) {
        this.dao = dao;
    }

    /**
     * @return the objeto
     */
    public Pais getObjeto() {
        return objeto;
    }

    /**
     * @param objeto the objeto to set
     */
    public void setObjeto(Pais objeto) {
        this.objeto = objeto;
    }

}
