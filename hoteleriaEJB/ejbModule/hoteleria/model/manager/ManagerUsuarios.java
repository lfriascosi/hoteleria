package hoteleria.model.manager;

import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import hoteleria.model.entities.InvRole;
import hoteleria.model.entities.InvRolesusuario;
import hoteleria.model.entities.InvUsuario;

/**
 * Session Bean implementation class ManagerUsuarios
 */
@Stateless
@LocalBean
public class ManagerUsuarios {
	@PersistenceContext
	private EntityManager em;

	public ManagerUsuarios() {
		// TODO Auto-generated constructor stub
	}

	public List<InvUsuario> findAllInvUsuarios() {
		String consulta = "select u from InvUsuario u";
		Query q = em.createQuery(consulta, InvUsuario.class);
		return q.getResultList();
	}

	public List<InvRole> findAllInvRole() {
		String consulta = "select u from InvRole u";
		Query q = em.createQuery(consulta, InvRole.class);
		return q.getResultList();
	}

	public List<InvRolesusuario> findAllInvRolesUsuario() {
		String consulta = "select u from InvRolesusuario u ORDER BY u.idrolusuario ";
		Query q = em.createQuery(consulta, InvRolesusuario.class);
		
		return q.getResultList();
	}

	public InvUsuario findInvUsuarioById(int idusuario) {
		return em.find(InvUsuario.class, idusuario);
	}

	public InvRolesusuario findInvRolesusuarioById(int idrolusuario) {
		return em.find(InvRolesusuario.class, idrolusuario);
	}

	public InvRole findInvRolById(int idrol) {
		return em.find(InvRole.class, idrol);
	}

	public InvUsuario insertarInvUsuario(InvUsuario usuario, int idrolusuario) {
		InvUsuario usuarios = new InvUsuario();
		usuarios.setApellidosusuario(usuario.getApellidosusuario());
		usuarios.setNombresusuario(usuario.getNombresusuario());
		usuarios.setClave(usuario.getClave());
		usuarios.setCorreo(usuario.getCorreo());
		usuarios.setDireccion(usuario.getDireccion());
		usuarios.setEstado(usuario.getEstado());
		usuarios.setTelefono(usuario.getTelefono());
		usuarios.setFechacreacion(new Date());
		usuarios.setFechaactualizacion(new Date());
		em.persist(usuarios);
		InvUsuario u = new InvUsuario();
		u = findInvUsuarioById(usuarios.getIdusuario());

		InvRole r = new InvRole();
		r = findInvRolById(idrolusuario);

		InvRolesusuario ru = new InvRolesusuario();
		ru.setInvRole(r);
		ru.setInvUsuario(u);

		em.persist(ru);
		return usuarios;
	}

	public InvRolesusuario insertarInvRolUsuario(InvRolesusuario usuario, int idrolusuario) {

		InvRole r = new InvRole();
		r = findInvRolById(idrolusuario);

		InvRolesusuario ru = new InvRolesusuario();
		ru.setInvRole(r);
		ru.setInvUsuario(usuario.getInvUsuario());

		em.persist(ru);
		return ru;
	}

	public void EliminarInvUsuario(Integer idusuario) {
		InvUsuario usuario = findInvUsuarioById(idusuario);
		if (usuario != null)
			em.remove(usuario);
	}

	public void actualizarInvRolesusuario(InvRolesusuario rolesusuario, int idrol) throws Exception {
		InvRolesusuario r = findInvRolesusuarioById(rolesusuario.getIdrolusuario());
		if (r == null)
			throw new Exception("No existe el usuario con el id especificado");
		InvUsuario h = findInvUsuarioById(rolesusuario.getInvUsuario().getIdusuario());

		if (h == null)
			throw new Exception("No existe el usuario con el id especificado");
		InvRole rl = findInvRolById(idrol);
		if (rl == null)
			throw new Exception("No existe el usuario con el id especificado");
		h.setApellidosusuario(rolesusuario.getInvUsuario().getApellidosusuario());
		h.setNombresusuario(rolesusuario.getInvUsuario().getNombresusuario());
		h.setClave(rolesusuario.getInvUsuario().getClave());
		h.setCorreo(rolesusuario.getInvUsuario().getCorreo());
		h.setDireccion(rolesusuario.getInvUsuario().getDireccion());
		h.setEstado(rolesusuario.getInvUsuario().getEstado());
		h.setTelefono(rolesusuario.getInvUsuario().getTelefono());
		h.setFechaactualizacion(new Date());
		em.merge(h);
		r.setInvUsuario(h);
		r.setInvRole(rl);
		em.merge(r);
	}

}
