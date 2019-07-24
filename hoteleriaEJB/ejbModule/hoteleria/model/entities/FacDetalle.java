package hoteleria.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the fac_detalles database table.
 * 
 */
@Entity
@Table(name="fac_detalles")
@NamedQuery(name="FacDetalle.findAll", query="SELECT f FROM FacDetalle f")
public class FacDetalle implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="FAC_DETALLES_IDDETALLE_GENERATOR", sequenceName="SEQ_FAC_DETALLES",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="FAC_DETALLES_IDDETALLE_GENERATOR")
	private Integer iddetalle;

	private Integer diasestadia;

	private Integer estadoreserva;

	@Temporal(TemporalType.DATE)
	private Date fechauso;

	@Column(name="precio_unit")
	private double precioUnit;

	//bi-directional many-to-one association to FacReserva
	@ManyToOne
	@JoinColumn(name="idreserva")
	private FacReserva facReserva;

	//bi-directional many-to-one association to InvHabitacione
	@ManyToOne
	@JoinColumn(name="idhabitacion")
	private InvHabitacione invHabitacione;

	public FacDetalle() {
	}

	public Integer getIddetalle() {
		return this.iddetalle;
	}

	public void setIddetalle(Integer iddetalle) {
		this.iddetalle = iddetalle;
	}

	public Integer getDiasestadia() {
		return this.diasestadia;
	}

	public void setDiasestadia(Integer diasestadia) {
		this.diasestadia = diasestadia;
	}

	public Integer getEstadoreserva() {
		return this.estadoreserva;
	}

	public void setEstadoreserva(Integer estadoreserva) {
		this.estadoreserva = estadoreserva;
	}

	public Date getFechauso() {
		return this.fechauso;
	}

	public void setFechauso(Date fechauso) {
		this.fechauso = fechauso;
	}

	public double getPrecioUnit() {
		return this.precioUnit;
	}

	public void setPrecioUnit(double precioUnit) {
		this.precioUnit = precioUnit;
	}

	public FacReserva getFacReserva() {
		return this.facReserva;
	}

	public void setFacReserva(FacReserva facReserva) {
		this.facReserva = facReserva;
	}

	public InvHabitacione getInvHabitacione() {
		return this.invHabitacione;
	}

	public void setInvHabitacione(InvHabitacione invHabitacione) {
		this.invHabitacione = invHabitacione;
	}

}