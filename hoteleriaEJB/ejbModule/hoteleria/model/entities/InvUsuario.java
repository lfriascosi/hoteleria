package hoteleria.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the inv_usuarios database table.
 * 
 */
@Entity
@Table(name="inv_usuarios")
@NamedQuery(name="InvUsuario.findAll", query="SELECT i FROM InvUsuario i")
public class InvUsuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer idusuario;

	private String apellidosusuario;

	private String clave;

	private String correo;

	private String direccion;

	private Integer estado;

	@Temporal(TemporalType.DATE)
	private Date fechaactualizacion;

	@Temporal(TemporalType.DATE)
	private Date fechacreacion;

	private String nombresusuario;

	private String telefono;

	@Column(name="tipo_usuario")
	private String tipoUsuario;

	public InvUsuario() {
	}

	public Integer getIdusuario() {
		return this.idusuario;
	}

	public void setIdusuario(Integer idusuario) {
		this.idusuario = idusuario;
	}

	public String getApellidosusuario() {
		return this.apellidosusuario;
	}

	public void setApellidosusuario(String apellidosusuario) {
		this.apellidosusuario = apellidosusuario;
	}

	public String getClave() {
		return this.clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getCorreo() {
		return this.correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Integer getEstado() {
		return this.estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public Date getFechaactualizacion() {
		return this.fechaactualizacion;
	}

	public void setFechaactualizacion(Date fechaactualizacion) {
		this.fechaactualizacion = fechaactualizacion;
	}

	public Date getFechacreacion() {
		return this.fechacreacion;
	}

	public void setFechacreacion(Date fechacreacion) {
		this.fechacreacion = fechacreacion;
	}

	public String getNombresusuario() {
		return this.nombresusuario;
	}

	public void setNombresusuario(String nombresusuario) {
		this.nombresusuario = nombresusuario;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getTipoUsuario() {
		return this.tipoUsuario;
	}

	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

}