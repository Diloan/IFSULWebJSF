/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.TipoEndereco;
import java.io.Serializable;

/**
 *
 * @author Diloan
 */
public class TipoEnderecoDAO<T> extends DAOGenerico<TipoEndereco> implements Serializable{
    
    public TipoEnderecoDAO(){
        super();
        classePersistente = TipoEndereco.class;
        ordem = "descricao";
    }
}
