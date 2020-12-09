package br.edu.ifsul.dao;
        
import br.edu.ifsul.dao.DAOGenerico;
import br.edu.ifsul.modelo.PessoaFisica;
import java.io.Serializable;
import javax.persistence.Query;

/**
 *
 * @author diloan
 */
public class PessoaFisicaDAO<T> extends DAOGenerico<PessoaFisica> implements Serializable {

    public PessoaFisicaDAO() {
        super();
        classePersistente = PessoaFisica.class;
        ordem = "nome";
    }

public boolean login(String usuario, String senha){
    Query query = em.createQuery("from PessoaFisica where UPPER(nomeUsuario) = :usuario and UPPER(senha) = :senha");
    query.setParameter("usuario", usuario.toUpperCase());
    query.setParameter("senha", senha.toUpperCase());
    if(!query.getResultList().isEmpty()){
        return true;
    }else{
        return false; 
    }    
}    

public PessoaFisica localizaPorNomeUsuario(String usuario){
    Query query = em.createQuery("from PessoaFisica where UPPER(nomeUsuario) = :usuario");
    query.setParameter("usuario", usuario.toUpperCase());
    return (PessoaFisica) query.getSingleResult();
}

}

    
    