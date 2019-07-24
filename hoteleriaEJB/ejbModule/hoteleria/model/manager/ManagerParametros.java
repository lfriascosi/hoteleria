package hoteleria.model.manager;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.wildfly.security.sasl.util.UsernamePasswordHashUtil;

import hoteleria.model.entities.FacParametro;

/**
 * Session Bean implementation class ManagerParametros
 */
@Stateless
@LocalBean
public class ManagerParametros {
	@PersistenceContext
	private EntityManager em;
	@EJB
    private ManagerDAO managerDAO;
	
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
    
    public void actualizarFacParametro(FacParametro parametro) throws Exception {
    	FacParametro h = findFacParametroById(parametro.getIdparametro());
    	if(h==null)
    		throw new Exception("No existe el parametro con el id especificado.");
    	h.setNombreparametro(parametro.getNombreparametro());
    	h.setValorparametro(parametro.getValorparametro());
    	h.setDescripcion(parametro.getDescripcion());
    	em.merge(h);
    }

	public double getValorParametroDouble(String nombreParametro) throws Exception {
  		return (findParametroById(nombreParametro).getValorparametro());
  	}

	public FacParametro findParametroById(String nombreParametro) throws Exception{
		FacParametro p=(FacParametro)managerDAO.findById(FacParametro.class, nombreParametro);
  		if(p==null)
  			throw new Exception("No existe el parametro "+nombreParametro);
  		return p;
  	}

}
