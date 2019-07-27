package hoteleria.model.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the inv_objetoshabitaciones database table.
 * 
 */
@Entity
@Table(name="inv_objetoshabitaciones")
@NamedQuery(name="InvObjetoshabitacione.findAll", query="SELECT i FROM InvObjetoshabitacione i")
public class InvObjetoshabitacione implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="INV_OBJETOSHABITACIONES_IDOBJETOSHABITACIONES_GENERATOR", sequenceName="SEQ_INV_OBJETOSHABITACIONES",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="INV_OBJETOSHABITACIONES_IDOBJETOSHABITACIONES_GENERATOR")
<<<<<<< Upstream, based on origin/master
	@Column(unique=true, nullable=false)
=======
>>>>>>> 0a54ac3 Reservación Cliente
	private Integer idobjetoshabitaciones;

	@Column(nullable=false)
	private Integer cantidad;

	//bi-directional many-to-one association to InvObjeto
	@ManyToOne
	@JoinColumn(name="idobjeto", nullable=false)
	private InvObjeto invObjeto;

	//bi-directional one-to-one association to InvTiposhabitacione
	@OneToOne
	@JoinColumn(name="idtipohabitacion", nullable=false)
	private InvTiposhabitacione invTiposhabitacione;

	public InvObjetoshabitacione() {
	}

	public Integer getIdobjetoshabitaciones() {
		return this.idobjetoshabitaciones;
	}

	public void setIdobjetoshabitaciones(Integer idobjetoshabitaciones) {
		this.idobjetoshabitaciones = idobjetoshabitaciones;
	}

	public Integer getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public InvObjeto getInvObjeto() {
		return this.invObjeto;
	}

	public void setInvObjeto(InvObjeto invObjeto) {
		this.invObjeto = invObjeto;
	}

	public InvTiposhabitacione getInvTiposhabitacione() {
		return this.invTiposhabitacione;
	}

	public void setInvTiposhabitacione(InvTiposhabitacione invTiposhabitacione) {
		this.invTiposhabitacione = invTiposhabitacione;
	}

}