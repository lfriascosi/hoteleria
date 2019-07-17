package hoteleria.model.manager;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import hoteleria.model.entities.FacDetalle;

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

	public List<FacDetalle> findAllFacDetalles() {
		String consulta2 = "select d from FacDetalle d";
		Query q = em.createQuery(consulta2, FacDetalle.class);
		return q.getResultList();
	}

}
