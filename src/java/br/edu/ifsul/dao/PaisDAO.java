/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Pais;
import java.io.Serializable;

/**
 *
 * @author ASUSX451
 */
public class PaisDAO<T> extends DAOGenerico<Pais> implements Serializable {

    public PaisDAO(){
        super();
        classePersistente = Pais.class;
        ordem = "nome";
      }
        }
