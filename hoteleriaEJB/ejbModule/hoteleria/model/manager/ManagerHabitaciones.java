package hoteleria.model.manager;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import hoteleria.model.entities.InvTiposhabitacione;


/**
 * Session Bean implementation class ManagerHabitaciones
 */
@Stateless
@LocalBean
public class ManagerHabitaciones {
	@PersistenceContext
	private EntityManager em;
    /**
     * Default constructor. 
     */
    public ManagerHabitaciones() {
        // TODO Auto-generated constructor stub
    }
    public List<InvTiposhabitacione> findAllHabitaciones() {
		String consulta = "select o from InvTiposhabitacione o order by o.fechacreacion";
		Query q = em.createQuery(consulta, InvTiposhabitacione.class);
		return q.getResultList();

	}
    public InvTiposhabitacione findHabitacionById(int idtipohabitacion) {
		return em.find(InvTiposhabitacione.class, idtipohabitacion);
	}

	public InvTiposhabitacione findHabitacionByNombre(String nombretipohabitacion) {
		return em.find(InvTiposhabitacione.class, nombretipohabitacion);
	}
	
public  InvTiposhabitacione InsertarHabitacion(InvTiposhabitacione habitacion)  {
	InvTiposhabitacione habitaciones = new InvTiposhabitacione();
	habitaciones.setNombretipohabitacion(habitacion.getNombretipohabitacion());
	habitaciones.setFechacreacion(habitacion.getFechacreacion());
	habitaciones.setFechaactualizacion(habitacion.getFechaactualizacion());
	em.persist(habitaciones);
	return habitaciones;
	

		
		}

	
	public void eliminarHabitacion(Integer idtipohabitacion) {
		InvTiposhabitacione habitacion=findHabitacionById(idtipohabitacion);
		if(habitacion!=null)
			em.remove(habitacion);
	}
	public void actualizarHabitacion(InvTiposhabitacione habitacion) throws Exception {
		InvTiposhabitacione h=findHabitacionById(habitacion.getIdtipohabitacion());
		if(h==null)
			throw new Exception("NO existe la habitacion con el id especificada.");
		h.setNombretipohabitacion(habitacion.getNombretipohabitacion());
		h.setFechacreacion(habitacion.getFechacreacion());
		h.setFechaactualizacion(habitacion.getFechaactualizacion());
		em.merge(h);
			}
	
	
}
