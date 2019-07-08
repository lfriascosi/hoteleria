package hoteleria.model.manager;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import hoteleria.model.entities.FacDetalle;
import hoteleria.model.entities.FacReserva;
import hoteleria.model.entities.InvUsuario;

/**
 * Session Bean implementation class ManagerReservas
 */
@Stateless
@LocalBean
public class ManagerReservas {
	@PersistenceContext
	private EntityManager em;
    public ManagerReservas() {
        // TODO Auto-generated constructor stub
    }
    public List<FacReserva> findAllFacReservas(){
    	String consulta1="select f from FacReserva f";
    	Query q=em.createQuery(consulta1,FacReserva.class);
    	return q.getResultList();
    }
    public List<FacDetalle> findAllFacDetalles(){
    	String consulta2="select d from FacDetalle d";
    	Query q=em.createQuery(consulta2,FacDetalle.class);
    	return q.getResultList();
    }
    public List<InvUsuario> findAllInvUsuarios(){
    	String consulta3="select u from InvUsuario u";
    	Query q=em.createQuery(consulta3,InvUsuario.class);
    	return q.getResultList();
    }

}
