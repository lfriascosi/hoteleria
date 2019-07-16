package hoteleria.model.manager;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import hoteleria.model.entities.FacParametro;

/**
 * Session Bean implementation class ManagerParametros
 */
@Stateless
@LocalBean
public class ManagerParametros {
	@PersistenceContext
	private EntityManager em;
	
    public ManagerParametros() {
        // TODO Auto-generated constructor stub
    }
    
    public List<FacParametro> findAllFacParametro(){
    	String consulta="select u from FacParametro u";
    	Query q = em.createQuery(consulta, FacParametro.class);
    	return q.getResultList();
    }
    
    public FacParametro findFacParametroById(int idparametro) {
    	return em.find(FacParametro.class, idparametro);
    }
    
    public FacParametro insertarFacParametro(FacParametro parametro) {
    	FacParametro parametros = new FacParametro();
    	parametros.setNombreparametro(parametro.getNombreparametro());
    	parametros.setValorparametro(parametro.getValorparametro());
    	parametros.setDescripcion(parametro.getDescripcion());
    	em.persist(parametros);
    	return parametros;
    }
    
    public void EliminarFacParametro(Integer idparametro) {
    	FacParametro parametro = findFacParametroById(idparametro);
    	if(parametro != null)
    		em.remove(parametro);
    }

}
