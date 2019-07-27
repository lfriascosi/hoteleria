package hoteleria.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the fac_reservas database table.
 * 
 */
@Entity
@Table(name="fac_reservas")
@NamedQuery(name="FacReserva.findAll", query="SELECT f FROM FacReserva f")
public class FacReserva implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="FAC_RESERVAS_IDRESERVA_GENERATOR", sequenceName="SEQ_FAC_RESERVAS",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="FAC_RESERVAS_IDRESERVA_GENERATOR")
	@Column(unique=true, nullable=false)
	private Integer idreserva;

	@Column(length=17)
	private String codigoreserva;

	@Column(nullable=false)
	private Integer estadopago;

	@Temporal(TemporalType.DATE)

	@Column(nullable=false)
	private Date fechaentrada;

	@Temporal(TemporalType.DATE)

	@Column(nullable=false)
	private Date fechareserva;

	@Temporal(TemporalType.DATE)
	private Date fechasalida;

	@Column(nullable=false, length=10)
	private String horareserva;

	@Column(nullable=false, precision=12, scale=2)
	private BigDecimal subtotal;

	@Column(nullable=false, precision=12, scale=2)
	private BigDecimal total;

	@Column(name="valor_iva", nullable=false, precision=12, scale=2)
	private BigDecimal valorIva;

	//bi-directional many-to-one association to FacDetalle
	@OneToMany(mappedBy="facReserva")
	private List<FacDetalle> facDetalles;

	//bi-directional many-to-one association to FacParametro
	@ManyToOne
	@JoinColumn(name="descuento", nullable=false)
	private FacParametro facParametro;

	//bi-directional many-to-one association to InvUsuario
	@ManyToOne
	@JoinColumn(name="idusuario", nullable=false)
	private InvUsuario invUsuario;

	public FacReserva() {
	}

	public Integer getIdreserva() {
		return this.idreserva;
	}

	public void setIdreserva(Integer idreserva) {
		this.idreserva = idreserva;
	}

	public String getCodigoreserva() {
		return this.codigoreserva;
	}

	public void setCodigoreserva(String codigoreserva) {
		this.codigoreserva = codigoreserva;
	}

	public Integer getEstadopago() {
		return this.estadopago;
	}

	public void setEstadopago(Integer estadopago) {
		this.estadopago = estadopago;
	}

	public Date getFechaentrada() {
		return this.fechaentrada;
	}

	public void setFechaentrada(Date fechaentrada) {
		this.fechaentrada = fechaentrada;
	}

	public Date getFechareserva() {
		return this.fechareserva;
	}

	public void setFechareserva(Date fechareserva) {
		this.fechareserva = fechareserva;
	}

	public Date getFechasalida() {
		return this.fechasalida;
	}

	public void setFechasalida(Date fechasalida) {
		this.fechasalida = fechasalida;
	}

	public String getHorareserva() {
		return this.horareserva;
	}

	public void setHorareserva(String horareserva) {
		this.horareserva = horareserva;
	}

	public BigDecimal getSubtotal() {
		return this.subtotal;
	}

	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}

	public BigDecimal getTotal() {
		return this.total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public BigDecimal getValorIva() {
		return this.valorIva;
	}

	public void setValorIva(BigDecimal valorIva) {
		this.valorIva = valorIva;
	}

	public List<FacDetalle> getFacDetalles() {
		return this.facDetalles;
	}

	public void setFacDetalles(List<FacDetalle> facDetalles) {
		this.facDetalles = facDetalles;
	}

	public FacDetalle addFacDetalle(FacDetalle facDetalle) {
		getFacDetalles().add(facDetalle);
		facDetalle.setFacReserva(this);

		return facDetalle;
	}

	public FacDetalle removeFacDetalle(FacDetalle facDetalle) {
		getFacDetalles().remove(facDetalle);
		facDetalle.setFacReserva(null);

		return facDetalle;
	}

	public FacParametro getFacParametro() {
		return this.facParametro;
	}

	public void setFacParametro(FacParametro facParametro) {
		this.facParametro = facParametro;
	}

	public InvUsuario getInvUsuario() {
		return this.invUsuario;
	}

	public void setInvUsuario(InvUsuario invUsuario) {
		this.invUsuario = invUsuario;
	}

}