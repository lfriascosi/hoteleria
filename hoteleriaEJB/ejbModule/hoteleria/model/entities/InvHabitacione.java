package hoteleria.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the inv_habitaciones database table.
 * 
 */
@Entity
@Table(name="inv_habitaciones")
@NamedQuery(name="InvHabitacione.findAll", query="SELECT i FROM InvHabitacione i")
public class InvHabitacione implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer idhabitacion;

	private String descripcion;

	private Integer estado;

	@Temporal(TemporalType.DATE)
	private Date fechaactualizacion;

	@Temporal(TemporalType.DATE)
	private Date fechacreacion;

	private String fotografia;

	private Integer idtipohabitacion;

	private String numerohabitacion;

	private String numeropiso;

	private double precio;

	//bi-directional many-to-one association to FacDetalle
	@OneToMany(mappedBy="invHabitacione")
	private List<FacDetalle> facDetalles;

	//bi-directional one-to-one association to InvTiposhabitacione
	@OneToOne
	@JoinColumn(name="idhabitacion")
	private InvTiposhabitacione invTiposhabitacione;

	public InvHabitacione() {
	}

	public Integer getIdhabitacion() {
		return this.idhabitacion;
	}

	public void setIdhabitacion(Integer idhabitacion) {
		this.idhabitacion = idhabitacion;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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

	public String getFotografia() {
		return this.fotografia;
	}

	public void setFotografia(String fotografia) {
		this.fotografia = fotografia;
	}

	public Integer getIdtipohabitacion() {
		return this.idtipohabitacion;
	}

	public void setIdtipohabitacion(Integer idtipohabitacion) {
		this.idtipohabitacion = idtipohabitacion;
	}

	public String getNumerohabitacion() {
		return this.numerohabitacion;
	}

	public void setNumerohabitacion(String numerohabitacion) {
		this.numerohabitacion = numerohabitacion;
	}

	public String getNumeropiso() {
		return this.numeropiso;
	}

	public void setNumeropiso(String numeropiso) {
		this.numeropiso = numeropiso;
	}

	public double getPrecio() {
		return this.precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public List<FacDetalle> getFacDetalles() {
		return this.facDetalles;
	}

	public void setFacDetalles(List<FacDetalle> facDetalles) {
		this.facDetalles = facDetalles;
	}

	public FacDetalle addFacDetalle(FacDetalle facDetalle) {
		getFacDetalles().add(facDetalle);
		facDetalle.setInvHabitacione(this);

		return facDetalle;
	}

	public FacDetalle removeFacDetalle(FacDetalle facDetalle) {
		getFacDetalles().remove(facDetalle);
		facDetalle.setInvHabitacione(null);

		return facDetalle;
	}

	public InvTiposhabitacione getInvTiposhabitacione() {
		return this.invTiposhabitacione;
	}

	public void setInvTiposhabitacione(InvTiposhabitacione invTiposhabitacione) {
		this.invTiposhabitacione = invTiposhabitacione;
	}

}