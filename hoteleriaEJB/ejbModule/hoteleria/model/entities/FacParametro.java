package hoteleria.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the fac_parametros database table.
 * 
 */
@Entity
@Table(name="fac_parametros")
@NamedQuery(name="FacParametro.findAll", query="SELECT f FROM FacParametro f")
public class FacParametro implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="FAC_PARAMETROS_IDPARAMETRO_GENERATOR", sequenceName="SEQ_FAC_PARAMETROS")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="FAC_PARAMETROS_IDPARAMETRO_GENERATOR")
	@Column(unique=true, nullable=false)
	private Integer idparametro;

	@Column(length=30)
	private String descripcion;

	@Column(nullable=false, length=20)
	private String nombreparametro;

	@Column(nullable=false)
	private double valorparametro;

	//bi-directional many-to-one association to FacReserva
	@OneToMany(mappedBy="facParametro")
	private List<FacReserva> facReservas;

	public FacParametro() {
	}

	public Integer getIdparametro() {
		return this.idparametro;
	}

	public void setIdparametro(Integer idparametro) {
		this.idparametro = idparametro;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNombreparametro() {
		return this.nombreparametro;
	}

	public void setNombreparametro(String nombreparametro) {
		this.nombreparametro = nombreparametro;
	}

	public double getValorparametro() {
		return this.valorparametro;
	}

	public void setValorparametro(double valorparametro) {
		this.valorparametro = valorparametro;
	}

	public List<FacReserva> getFacReservas() {
		return this.facReservas;
	}

	public void setFacReservas(List<FacReserva> facReservas) {
		this.facReservas = facReservas;
	}

	public FacReserva addFacReserva(FacReserva facReserva) {
		getFacReservas().add(facReserva);
		facReserva.setFacParametro(this);

		return facReserva;
	}

	public FacReserva removeFacReserva(FacReserva facReserva) {
		getFacReservas().remove(facReserva);
		facReserva.setFacParametro(null);

		return facReserva;
	}

}