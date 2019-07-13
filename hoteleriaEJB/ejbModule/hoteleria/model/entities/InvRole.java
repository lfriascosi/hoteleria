package hoteleria.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the inv_roles database table.
 * 
 */
@Entity
@Table(name="inv_roles")
@NamedQuery(name="InvRole.findAll", query="SELECT i FROM InvRole i")
public class InvRole implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer idrol;

	private String descripcion;

	@Temporal(TemporalType.DATE)
	private Date fechaactualizacion;

	@Temporal(TemporalType.DATE)
	private Date fechacreacion;

	private String nombrerol;

	private String nombrerolvista;

	//bi-directional many-to-one association to InvRolesusuario
	@OneToMany(mappedBy="invRole")
	private List<InvRolesusuario> invRolesusuarios;

	public InvRole() {
	}

	public Integer getIdrol() {
		return this.idrol;
	}

	public void setIdrol(Integer idrol) {
		this.idrol = idrol;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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

	public String getNombrerol() {
		return this.nombrerol;
	}

	public void setNombrerol(String nombrerol) {
		this.nombrerol = nombrerol;
	}

	public String getNombrerolvista() {
		return this.nombrerolvista;
	}

	public void setNombrerolvista(String nombrerolvista) {
		this.nombrerolvista = nombrerolvista;
	}

	public List<InvRolesusuario> getInvRolesusuarios() {
		return this.invRolesusuarios;
	}

	public void setInvRolesusuarios(List<InvRolesusuario> invRolesusuarios) {
		this.invRolesusuarios = invRolesusuarios;
	}

	public InvRolesusuario addInvRolesusuario(InvRolesusuario invRolesusuario) {
		getInvRolesusuarios().add(invRolesusuario);
		invRolesusuario.setInvRole(this);

		return invRolesusuario;
	}

	public InvRolesusuario removeInvRolesusuario(InvRolesusuario invRolesusuario) {
		getInvRolesusuarios().remove(invRolesusuario);
		invRolesusuario.setInvRole(null);

		return invRolesusuario;
	}

}