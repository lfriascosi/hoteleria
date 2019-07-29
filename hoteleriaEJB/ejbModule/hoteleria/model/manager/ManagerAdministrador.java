package hoteleria.model.manager;

import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import hoteleria.model.entities.InvHabitacione;
import hoteleria.model.entities.InvObjeto;
import hoteleria.model.entities.InvTiposhabitacione;

@Stateless
@LocalBean
public class ManagerAdministrador {
	@PersistenceContext
	private EntityManager em;

	public ManagerAdministrador() {
		// TODO Auto-generated constructor stub
	}

	public List<InvObjeto> findAllObjetos() {
		String consulta = "Select e from InvObjeto e order by e.idobjeto";
		Query q = em.createQuery(consulta, InvObjeto.class);
		return q.getResultList();
	}

	public InvObjeto findObjetoById(int idobjeto) {
		return em.find(InvObjeto.class, idobjeto);
	}

	public void insertarObjeto(InvObjeto objeto) throws Exception {
		objeto.setFechacreacion(new Date());
		objeto.setFechaactualizacion(new Date());
		em.persist(objeto);
	}

	public void eliminarObjeto(int idobjeto) {
		InvObjeto objeto = findObjetoById(idobjeto);
		if (objeto != null)
			em.remove(objeto);
	}

	public void actualizarObjeto(InvObjeto objeto) throws Exception {
		InvObjeto e = findObjetoById(objeto.getIdobjeto());
		if (e == null)
			throw new Exception("No existe el objeto");
		e.setNombreobjeto(objeto.getNombreobjeto());
		e.setDescripcion(objeto.getDescripcion());
		e.setFechaactualizacion(new Date());
		em.merge(e);

	}
//  Tipos de habitaciones

	public List<InvTiposhabitacione> finAllTiposhabitaciones() {
		String consulta = "Select e from InvTiposhabitacione e order by e.idtipohabitacion";
		Query q = em.createQuery(consulta, InvTiposhabitacione.class);
		return q.getResultList();
	}

	public InvTiposhabitacione finTipoHabitacionById(int idtipohabitacion) {
		return em.find(InvTiposhabitacione.class, idtipohabitacion);
	}

	public void insertarTipoHabitacion(InvTiposhabitacione tipohabitacion) throws Exception {
		tipohabitacion.setFechacreacion(new Date());
		tipohabitacion.setFechaactualizacion(new Date());
		em.persist(tipohabitacion);
	}

	public void eliminarTipohabitacion(int idtipohabitacion) {
		InvTiposhabitacione tipohabitacion = finTipoHabitacionById(idtipohabitacion);
		if (tipohabitacion != null)
			em.remove(tipohabitacion);
	}

	public void actualizarTipoHabitacion(InvTiposhabitacione tipohabitacion) throws Exception {
		InvTiposhabitacione e = finTipoHabitacionById(tipohabitacion.getIdtipohabitacion());
		if (e == null)
			throw new Exception("No existe el tipo de habitación");
		e.setNombretipohabitacion(tipohabitacion.getNombretipohabitacion());
		e.setFechaactualizacion(new Date());
		em.merge(e);
	}

//	habitaciones

	public List<InvHabitacione> findAllInvHabitacione() {
		String consulta = "select u from InvHabitacione u order by u.numerohabitacion";
		Query q = em.createQuery(consulta, InvHabitacione.class);
		return q.getResultList();
	}

	public InvHabitacione findInvHabitacioneById(int idhabitacion) {
		return em.find(InvHabitacione.class, idhabitacion);
	}

	public void insertarHabitacion(InvHabitacione habitacion,int idtipohabitacion) throws Exception {
		InvTiposhabitacione t =new InvTiposhabitacione();
		t=finTipoHabitacionById(idtipohabitacion);
		habitacion.setInvTiposhabitacione(t);
		habitacion.setEstado(1);
		habitacion.setFechacreacion(new Date());
		habitacion.setFechaactualizacion(new Date());
		em.persist(habitacion);
	}

	public void eliminarhabitacion(int idhabitacion) {
		InvHabitacione habitacion = findInvHabitacioneById(idhabitacion);
		if (habitacion != null)
			em.remove(habitacion);
	}

	public void actualizarHabitacion(InvHabitacione habitacion, int idtipohabitacion) throws Exception {
		InvHabitacione h = findInvHabitacioneById(habitacion.getIdhabitacion());
		if (h == null)
			throw new Exception("No existe habitación");
		InvTiposhabitacione t = new InvTiposhabitacione();
		t = finTipoHabitacionById(idtipohabitacion);
		h.setInvTiposhabitacione(t);
		h.setFechaactualizacion(new Date());
		em.merge(h);
	}

}
