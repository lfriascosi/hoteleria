package hoteleria.model.manager;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import hoteleria.model.entities.FacDetalle;
import hoteleria.model.entities.InvHabitacione;
import hoteleria.model.entities.InvRolesusuario;
import hoteleria.model.entities.InvTiposhabitacione;
import hoteleria.model.entities.InvUsuario;
 
@Stateless
@LocalBean
public class ManagerGerencia {
	@PersistenceContext
	private EntityManager em;

    /**
     * Default constructor. 
     */
    public ManagerGerencia() {
        // TODO Auto-generated constructor stub
    }
    public List<FacDetalle> findAllGerencia() {
		String consulta = "select o from InvTiposhabitacione o order by o.fechacreacion";
		Query q = em.createQuery(consulta, FacDetalle.class);
		return q.getResultList();

	}
    
    

    public List<InvRolesusuario> findAllClientes(){
    	
    	List<InvRolesusuario> listaroles = em.createQuery("select o from InvRolesusuario o WHERE o.invRole.nombrerol='cli'").getResultList();
		return listaroles;
    }
    
    public List<InvTiposhabitacione> findAllPrecios(){
    	List<InvTiposhabitacione> listaprecios = em.createQuery("select o from InvTiposhabitacione o order by o.idtipohabitacion").getResultList();
    	return listaprecios;
    }
    
    public List<InvHabitacione> findAllPreciosg(){
    	List<InvHabitacione> listaprecios = em.createQuery("select o from InvHabitacione o order by o.idhabitacion").getResultList();
    	return listaprecios;
    }
    
    public InvHabitacione findPrecioById(int idhabitacion) {
    	InvHabitacione precio = (InvHabitacione)em.find(InvHabitacione.class, idhabitacion);
		return precio;
	}
  
    
   
    
    public void actualizarPrecios(InvHabitacione precio) throws Exception{
    	InvHabitacione e=findPrecioById(precio.getIdhabitacion());
    	if(e==null)
    		throw new Exception("No existe la habitación.");
    	e.setPrecio(precio.getPrecio());
    	em.merge(e); 
    }
    
    
}
