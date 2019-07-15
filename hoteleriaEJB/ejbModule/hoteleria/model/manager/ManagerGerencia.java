package hoteleria.model.manager;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import hoteleria.model.entities.FacDetalle;

/**
 * Session Bean implementation class ManagerGerencia
 */
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
    
}
