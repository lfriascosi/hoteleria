package hoteleria.model.manager;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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
    
    public List<InvUsuario> findAllInvUsuarios(){
    	String consulta="select u from InvUsuario u";
    	Query q=em.createQuery(consulta, InvUsuario.class);
    	return q.getResultList();
    }
    
    public InvUsuario findInvUsuarioById(int idusuario) {
    	return em.find(InvUsuario.class, idusuario);
    }
    public InvUsuario insertarInvUsuario(InvUsuario usuario) {
    	InvUsuario usuarios = new InvUsuario();
    	usuarios.setApellidosusuario(usuario.getApellidosusuario());
    	usuarios.setNombresusuario(usuario.getNombresusuario());
    	usuarios.setClave(usuario.getClave());
    	usuarios.setCorreo(usuario.getCorreo());
    	usuarios.setDireccion(usuario.getDireccion());
    	usuarios.setEstado(usuario.getEstado());
    	usuarios.setTelefono(usuario.getTelefono());
    	usuarios.setFechacreacion(usuario.getFechacreacion());
    	usuarios.setFechaactualizacion(usuario.getFechaactualizacion());
    	em.persist(usuarios);
    	return usuarios;
    }
    public void EliminarInvUsuario (Integer idusuario) {
    	InvUsuario usuario = findInvUsuarioById(idusuario);
    	if(usuario!=null)
    		em.remove(usuario);
    }
    

}
