package hoteleria.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the bitacora database table.
 * 
 */
@Entity
@Table(name="bitacora")
@NamedQuery(name="Bitacora.findAll", query="SELECT b FROM Bitacora b")
public class Bitacora implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="BITACORA_IDBITACORA_GENERATOR", sequenceName="SEQ_BITACORA",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="BITACORA_IDBITACORA_GENERATOR")
	@Column(unique=true, nullable=false)
	private Integer idbitacora;

	@Column(nullable=false, length=40)
	private String descripcionevento;

	@Temporal(TemporalType.DATE)
	@Column(nullable=false)
	private Date fechaevento;

	@Column(nullable=false, length=10)
	private String horaevento;

	@Column(nullable=false, length=50)
	private String ipusuario;


	@Column(nullable=false, length=100)
	private String metodo;

	//bi-directional many-to-one association to InvUsuario
	@ManyToOne
	@JoinColumn(name="idusuario", nullable=false)
	private InvUsuario invUsuario;

	public Bitacora() {
	}

	public Integer getIdbitacora() {
		return this.idbitacora;
	}

	public void setIdbitacora(Integer idbitacora) {
		this.idbitacora = idbitacora;
	}

	public String getDescripcionevento() {
		return this.descripcionevento;
	}

	public void setDescripcionevento(String descripcionevento) {
		this.descripcionevento = descripcionevento;
	}

	public Date getFechaevento() {
		return this.fechaevento;
	}

	public void setFechaevento(Date fechaevento) {
		this.fechaevento = fechaevento;
	}

	public String getHoraevento() {
		return this.horaevento;
	}

	public void setHoraevento(String horaevento) {
		this.horaevento = horaevento;
	}

	public String getIpusuario() {
		return this.ipusuario;
	}

	public void setIpusuario(String ipusuario) {
		this.ipusuario = ipusuario;
	}

	public String getMetodo() {
		return this.metodo;
	}

	public void setMetodo(String metodo) {
		this.metodo = metodo;
	}

	public InvUsuario getInvUsuario() {
		return this.invUsuario;
	}

	public void setInvUsuario(InvUsuario invUsuario) {
		this.invUsuario = invUsuario;
	}

}