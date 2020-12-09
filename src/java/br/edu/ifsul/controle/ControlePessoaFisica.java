/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.CidadeDAO;
import br.edu.ifsul.dao.PessoaFisicaDAO;
import br.edu.ifsul.dao.ProdutoDAO;
import br.edu.ifsul.dao.TipoEnderecoDAO;
import br.edu.ifsul.modelo.Cidade;
import br.edu.ifsul.modelo.Endereco;
import br.edu.ifsul.modelo.PessoaFisica;
import br.edu.ifsul.modelo.Produto;
import br.edu.ifsul.modelo.TipoEndereco;
import br.edu.ifsul.util.Util;
import br.edu.ifsul.util.UtilRelatorios;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Diloan Silva
 * @email diloan.silva@gmail.com
 */
@ManagedBean(name = "controlePessoaFisica")
@ViewScoped
public class ControlePessoaFisica implements Serializable {

    private PessoaFisicaDAO<PessoaFisica> dao;
    private PessoaFisica objeto;
    private CidadeDAO<Cidade> daoCidade;
    private TipoEnderecoDAO<TipoEndereco> daoTipoEndereco;
    private Endereco endereco;
    private boolean novoEndereco;
    private ProdutoDAO<Produto> daoProduto;
    private Produto produto;

    public ControlePessoaFisica() {
        dao = new PessoaFisicaDAO<>();
        daoCidade = new CidadeDAO<>();
        daoTipoEndereco = new TipoEnderecoDAO<>();
        daoProduto = new ProdutoDAO<>();
    }

    public void imprimeProdutos() {
        HashMap paramentros = new HashMap();
        UtilRelatorios.imprimeRelatorio("relatorioProdutos", paramentros, daoProduto.getListaTodos());

    }

    public void imprimePessoa(Integer id) {
        objeto = dao.localizar(id);
        List<PessoaFisica> lista = new ArrayList<>();
        lista.add(objeto);
        HashMap paramentros = new HashMap();
        UtilRelatorios.imprimeRelatorio("relatorioPessoaFisica", paramentros, lista);

    }

    public void adicionarDesejo() {
        if (produto != null) {
            if (!objeto.getDesejos().contains(produto)) {
                objeto.getDesejos().add(produto);
                Util.mensagemInformacao("Desejo adicionado com sucesso!");
            } else {
                Util.mensagemErro("Este desejo já existe na sua lista!");
            }
        }
    }

    public void removerDesejo(int index) {
        produto = objeto.getDesejos().get(index);
        objeto.getDesejos().remove(produto);
        Util.mensagemInformacao("Desejo removido com sucesso!");
    }

    public String listar() {
        return "/privado/pessoaFisica/listar?faces-redirect=true";
    }

    public void novoEndereco() {
        endereco = new Endereco();
        novoEndereco = true;
    }

    public void alterarEndereco(int index) {
        endereco = objeto.getEnderecos().get(index);
        novoEndereco = false;
    }

    public void salvarEndereco(int index) {
        if (novoEndereco) {
            objeto.adicionarEndereco(endereco);
        }
        Util.mensagemInformacao("Endereco persistido com sucesso!");
    }

    public void removerEndereco(int index) {
        objeto.removerEndereco(index);
        Util.mensagemInformacao("Endereco removido com sucesso!");
    }

    public void novo() {
        objeto = new PessoaFisica();
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
    public PessoaFisicaDAO getDao() {
        return dao;
    }

    /**
     * @param dao the dao to set
     */
    public void setDao(PessoaFisicaDAO dao) {
        this.dao = dao;
    }

    /**
     * @return the objeto
     */
    public PessoaFisica getObjeto() {
        return objeto;
    }

    /**
     * @param objeto the objeto to set
     */
    public void setObjeto(PessoaFisica objeto) {
        this.objeto = objeto;
    }

    /**
     * @return the daoCidade
     */
    public CidadeDAO<Cidade> getDaoCidade() {
        return daoCidade;
    }

    /**
     * @param daoCidade the daoCidade to set
     */
    public void setDaoCidade(CidadeDAO<Cidade> daoCidade) {
        this.daoCidade = daoCidade;
    }

    /**
     * @return the daoTipoEndereco
     */
    public TipoEnderecoDAO<TipoEndereco> getDaoTipoEndereco() {
        return daoTipoEndereco;
    }

    /**
     * @param daoTipoEndereco the daoTipoEndereco to set
     */
    public void setDaoTipoEndereco(TipoEnderecoDAO<TipoEndereco> daoTipoEndereco) {
        this.daoTipoEndereco = daoTipoEndereco;
    }

    /**
     * @return the endereco
     */
    public Endereco getEndereco() {
        return endereco;
    }

    /**
     * @param endereco the endereco to set
     */
    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    /**
     * @return the novoEndereco
     */
    public boolean isNovoEndereco() {
        return novoEndereco;
    }

    /**
     * @param novoEndereco the novoEndereco to set
     */
    public void setNovoEndereco(boolean novoEndereco) {
        this.novoEndereco = novoEndereco;
    }

    /**
     * @return the daoProduto
     */
    public ProdutoDAO<Produto> getDaoProduto() {
        return daoProduto;
    }

    /**
     * @param daoProduto the daoProduto to set
     */
    public void setDaoProduto(ProdutoDAO<Produto> daoProduto) {
        this.daoProduto = daoProduto;
    }

    /**
     * @return the produto
     */
    public Produto getProduto() {
        return produto;
    }

    /**
     * @param produto the produto to set
     */
    public void setProduto(Produto produto) {
        this.produto = produto;
    }

}
