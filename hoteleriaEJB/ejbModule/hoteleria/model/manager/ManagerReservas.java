package hoteleria.model.manager;

//import java.util.ArrayList;
//import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import hoteleria.model.entities.FacDetalle;
//import hoteleria.model.entities.FacReserva;
//import hoteleria.model.entities.InvUsuario;

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

//	public List<InvUsuario> findAllUsuarios() {
//		String consulta2 = "select c from (select from InvUsuario as a join InvRolesusuario as e on e.idusuario=a.idusuario join InvRole as u on e.idrol=u.idrol where u.nombrerol='cli' )as c";
//		Query q = em.createQuery(consulta2, InvUsuario.class);
//		return q.getResultList();
//	}
//
//	public FacReserva crearFacturaTmp() {
//		FacReserva facturaCabTmp = new FacReserva();
//		facturaCabTmp.setFechareserva(new Date());
//		facturaCabTmp.setFacDetalles(new ArrayList<FacDetalle>());
//		return facturaCabTmp;
//	}
//
//	public InvUsuario findClienteById(String correo) throws Exception {
//		InvUsuario cliente = null;
//		List<InvUsuario> u;
//		try {
//			String consulta2 = "select c from (select from InvUsuario as a join InvRolesusuario as e on e.idusuario=a.idusuario join InvRole as u on e.idrol=u.idrol where u.nombrerol='cli' )as c";
//			Query q = em.createQuery(consulta2, InvUsuario.class);
//			u = q.getResultList();
//			for (InvUsuario e : u) {
//				cliente.setNombresusuario(e.getNombresusuario());
//				cliente.setApellidosusuario(e.getApellidosusuario());
//				cliente.setCorreo(e.getCorreo());
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw new Exception("Error al buscar cliente: " + e.getMessage());
//		}
//		return cliente;
//	}
//
//	public void asignarClienteFacturaTmp(FacReserva facturaCabTmp, String correo) throws Exception {
//
//		InvUsuario cliente = null;
//		if (correo == null || correo.length() == 0)
//			throw new Exception("Error debe especificar la cedula del cliente.");
//		try {
//			cliente = findClienteById(correo);
//			if (cliente == null)
//				throw new Exception("Error al asignar cliente.");
//			facturaCabTmp.setInvUsuario(cliente);
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw new Exception("Error al asignar cliente: " + e.getMessage());
//		}
//	}

}
