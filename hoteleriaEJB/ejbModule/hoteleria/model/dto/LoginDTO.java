package hoteleria.model.dto;
/**
 * DTO para el acceso al sistema.
 * @author RogerVaca
 *
 */
import hoteleria.model.entities.InvRolesusuario;
import java.util.List;

public class LoginDTO {
	private String usuario;
	private int idUsuario;
	private String correo;
	private String tipoUsuario;
	private String rutaAcceso;
	private List<InvRolesusuario> roles;
	
	
	
	public List<InvRolesusuario> getRoles() {
		return roles;
	}
	public void setRoles(List<InvRolesusuario> roles) {
		this.roles = roles;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public Integer getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Integer codigoUsuario) {
		this.idUsuario = codigoUsuario;
	}
	public String getTipoUsuario() {
		return tipoUsuario;
	}
	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
	public String getRutaAcceso() {
		return rutaAcceso;
	}
	public void setRutaAcceso(String rutaAcceso) {
		this.rutaAcceso = rutaAcceso;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	
}
