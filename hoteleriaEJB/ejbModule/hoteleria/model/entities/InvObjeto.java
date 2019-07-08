package hoteleria.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the inv_objetos database table.
 * 
 */
@Entity
@Table(name="inv_objetos")
@NamedQuery(name="InvObjeto.findAll", query="SELECT i FROM InvObjeto i")
public class InvObjeto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="INV_OBJETOS_IDOBJETO_GENERATOR", sequenceName="SEQ_INV_OBJETOS")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="INV_OBJETOS_IDOBJETO_GENERATOR")
	@Column(unique=true, nullable=false)
	private Integer idobjeto;

	@Column(length=50)
	private String descripcion;

	@Temporal(TemporalType.DATE)
	private Date fechaactualizacion;

	@Temporal(TemporalType.DATE)
	private Date fechacreacion;

	@Column(nullable=false, length=30)
	private String nombreobjeto;

	//bi-directional many-to-one association to InvObjetoshabitacione
	@OneToMany(mappedBy="invObjeto")
	private List<InvObjetoshabitacione> invObjetoshabitaciones;

	public InvObjeto() {
	}

	public Integer getIdobjeto() {
		return this.idobjeto;
	}

	public void setIdobjeto(Integer idobjeto) {
		this.idobjeto = idobjeto;
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

	public String getNombreobjeto() {
		return this.nombreobjeto;
	}

	public void setNombreobjeto(String nombreobjeto) {
		this.nombreobjeto = nombreobjeto;
	}

	public List<InvObjetoshabitacione> getInvObjetoshabitaciones() {
		return this.invObjetoshabitaciones;
	}

	public void setInvObjetoshabitaciones(List<InvObjetoshabitacione> invObjetoshabitaciones) {
		this.invObjetoshabitaciones = invObjetoshabitaciones;
	}

	public InvObjetoshabitacione addInvObjetoshabitacione(InvObjetoshabitacione invObjetoshabitacione) {
		getInvObjetoshabitaciones().add(invObjetoshabitacione);
		invObjetoshabitacione.setInvObjeto(this);

		return invObjetoshabitacione;
	}

	public InvObjetoshabitacione removeInvObjetoshabitacione(InvObjetoshabitacione invObjetoshabitacione) {
		getInvObjetoshabitaciones().remove(invObjetoshabitacione);
		invObjetoshabitacione.setInvObjeto(null);

		return invObjetoshabitacione;
	}

}