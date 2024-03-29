package hoteleria.model.manager;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import hoteleria.model.dto.LoginDTO;
import hoteleria.model.entities.InvRole;
import hoteleria.model.entities.InvRolesusuario;
import hoteleria.model.entities.InvUsuario;

/**
 * Session Bean implementation class ManagerSeguridad
 */
@Stateless
@LocalBean
public class ManagerSeguridad {
	@EJB
	private ManagerDAO managerDAO;
	@PersistenceContext
	private EntityManager em;
    /**
     * Default constructor. 
     */
    public ManagerSeguridad() {
        // TODO Auto-generated constructor stub
    }
    
    /**
	 * Metodo que le permite a un usuario acceder al sistema.
	 * @param codigoUsuario Identificador del usuario.
	 * @param clave Clave de acceso.
	 * @return Retorna el tipo de usuario. Puede tener dos valores:
	 * 			SP (supervisor) o VD (vendedor).
	 * @throws Exception Cuando no coincide la clave proporcionada o si ocurrio
	 * un error con la consulta a la base de datos.
	 */
	public LoginDTO accederSistema(String codigoUsuario,String clave) throws Exception{
		
		InvUsuario usuario=(InvUsuario)managerDAO.findUser(InvUsuario.class, codigoUsuario);
		System.out.println("Clave BBDD:"+usuario.getClave()+" y Clave ACCESO:"+clave);
		if(usuario==null)
			throw new Exception("Error en usuario y/o clave.");
		
		if(!usuario.getClave().equals(clave))
			throw new Exception("Error en usuario y/o clave.");
		
		LoginDTO loginDTO=new LoginDTO();
		System.out.println("metodo buscar roles");
		loginDTO.setIdUsuario(usuario.getIdusuario());
		loginDTO.setUsuario(usuario.getNombresusuario()+" "+usuario.getApellidosusuario());
		loginDTO.setCorreo(usuario.getCorreo());
		loginDTO.setRoles(usuario.getInvRolesusuarios());		
		return loginDTO;
	}
	
	public InvUsuario findUsuarioById(String codigoUsuario) throws Exception {
		InvUsuario usuario=(InvUsuario)managerDAO.findById(InvUsuario.class, codigoUsuario);
		return usuario;
	}
	
	public InvUsuario findUsuarioPerId(Integer codigoUsuario) throws Exception {
		InvUsuario usuario=(InvUsuario)managerDAO.findById(InvUsuario.class, codigoUsuario);
		return usuario;
	}
	 public InvUsuario registrarUsuario(InvUsuario usuario) throws Exception {
		 	System.out.println("-------------------------------------- Antes ----------------------------");
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
	    	InvRole rolQ=(InvRole)managerDAO.findRol(InvRole.class,"cli");
	    	InvRolesusuario rolusuario = new InvRolesusuario();
	    	rolusuario.setInvUsuario(usuarios);
	    	rolusuario.setInvRole(rolQ);
	    	em.persist(rolusuario);
	    	System.out.println("-------------------------------------- después ----------------------------");
	    	return usuarios;
	    }

}
