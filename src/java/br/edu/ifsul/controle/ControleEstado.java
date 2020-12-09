/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.EstadoDAO;
import br.edu.ifsul.dao.PaisDAO;
import br.edu.ifsul.modelo.Estado;
import br.edu.ifsul.modelo.Pais;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Diloan Silva
 * @email diloan.silva@gmail.com
 */
@ManagedBean(name = "controleEstado")
@ViewScoped
public class ControleEstado implements Serializable {

    private EstadoDAO<Estado> dao;
    private Estado objeto;
    private PaisDAO<Pais> daoPais;
    

    public ControleEstado() {
        dao = new EstadoDAO<>();
        daoPais = new PaisDAO<>();
    }

    public String listar() {
        return "/privado/estado/listar?faces-redirect=true";
    }

    public void novo() {
        objeto = new Estado();
    }

    public void salvar() {
        boolean persistiu = false;
        if (objeto.getId() == null) {
            persistiu = dao.persistir(objeto);
        } else {
            persistiu = dao.merge(objeto);
        }
        if (persistiu) {
            Util.mensagemInformacao(dao.getMensagem());
        } else {
            Util.mensagemErro(dao.getMensagem());
        }
    }

    public void editar(Integer id) {
        objeto = dao.localizar(id);
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
    public EstadoDAO getDao() {
        return dao;
    }

    /**
     * @param dao the dao to set
     */
    public void setDao(EstadoDAO dao) {
        this.dao = dao;
    }

    /**
     * @return the objeto
     */
    public Estado getObjeto() {
        return objeto;
    }

    /**
     * @param objeto the objeto to set
     */
    public void setObjeto(Estado objeto) {
        this.objeto = objeto;
    }

    /**
     * @return the daoPais
     */
    public PaisDAO<Pais> getDaoPais() {
        return daoPais;
    }

    /**
     * @param daoPais the daoPais to set
     */
    public void setDaoPais(PaisDAO<Pais> daoPais) {
        this.daoPais = daoPais;
    }

}
