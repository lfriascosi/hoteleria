package hoteleria.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Time;
import java.util.Date;


/**
 * The persistent class for the bitacora database table.
 * 
 */
@Entity
@NamedQuery(name="Bitacora.findAll", query="SELECT b FROM Bitacora b")
public class Bitacora implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer idbitacora;

	private String descripcionevento;

	@Temporal(TemporalType.DATE)
	private Date fechaevento;

	private Time horaevento;

	private String ipusuario;

	//bi-directional many-to-one association to InvUsuario
	@ManyToOne
	@JoinColumn(name="idusuario")
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

	public Time getHoraevento() {
		return this.horaevento;
	}

	public void setHoraevento(Time horaevento) {
		this.horaevento = horaevento;
	}

	public String getIpusuario() {
		return this.ipusuario;
	}

	public void setIpusuario(String ipusuario) {
		this.ipusuario = ipusuario;
	}

	public InvUsuario getInvUsuario() {
		return this.invUsuario;
	}

	public void setInvUsuario(InvUsuario invUsuario) {
		this.invUsuario = invUsuario;
	}

}