package hoteleria.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the inv_tiposhabitaciones database table.
 * 
 */
@Entity
@Table(name="inv_tiposhabitaciones")
@NamedQuery(name="InvTiposhabitacione.findAll", query="SELECT i FROM InvTiposhabitacione i")
public class InvTiposhabitacione implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="INV_TIPOSHABITACIONES_IDTIPOHABITACION_GENERATOR", sequenceName="SEQ_INV_TIPOSHABITACIONES",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="INV_TIPOSHABITACIONES_IDTIPOHABITACION_GENERATOR")
	@Column(unique=true, nullable=false)
	private Integer idtipohabitacion;

	@Temporal(TemporalType.DATE)
	private Date fechaactualizacion;

	@Temporal(TemporalType.DATE)
	private Date fechacreacion;

	@Column(nullable=false, length=50)
	private String nombretipohabitacion;

	//bi-directional one-to-one association to InvHabitacione
	@OneToOne(mappedBy="invTiposhabitacione")
	private InvHabitacione invHabitacione;

	//bi-directional one-to-one association to InvObjetoshabitacione
	@OneToOne(mappedBy="invTiposhabitacione")
	private InvObjetoshabitacione invObjetoshabitacione;

	public InvTiposhabitacione() {
	}

	public Integer getIdtipohabitacion() {
		return this.idtipohabitacion;
	}

	public void setIdtipohabitacion(Integer idtipohabitacion) {
		this.idtipohabitacion = idtipohabitacion;
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

	public String getNombretipohabitacion() {
		return this.nombretipohabitacion;
	}

	public void setNombretipohabitacion(String nombretipohabitacion) {
		this.nombretipohabitacion = nombretipohabitacion;
	}

	public InvHabitacione getInvHabitacione() {
		return this.invHabitacione;
	}

	public void setInvHabitacione(InvHabitacione invHabitacione) {
		this.invHabitacione = invHabitacione;
	}

	public InvObjetoshabitacione getInvObjetoshabitacione() {
		return this.invObjetoshabitacione;
	}

	public void setInvObjetoshabitacione(InvObjetoshabitacione invObjetoshabitacione) {
		this.invObjetoshabitacione = invObjetoshabitacione;
	}

}