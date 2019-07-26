package hoteleria.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the inv_usuarios database table.
 * 
 */
@Entity
@Table(name="inv_usuarios")
@NamedQuery(name="InvUsuario.findAll", query="SELECT i FROM InvUsuario i")
public class InvUsuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="INV_USUARIOS_IDUSUARIO_GENERATOR", sequenceName="SEQ_INV_USUARIOS",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="INV_USUARIOS_IDUSUARIO_GENERATOR")
	@Column(unique=true, nullable=false)
	private Integer idusuario;

	@Column(nullable=false, length=50)
	private String apellidosusuario;

	@Column(nullable=false, length=300)
	private String clave;

	@Column(nullable=false, length=200)
	private String correo;

	@Column(length=100)
	private String direccion;

	@Column(nullable=false)
	private Integer estado;

	@Temporal(TemporalType.DATE)
	private Date fechaactualizacion;

	@Temporal(TemporalType.DATE)
	private Date fechacreacion;

	@Column(nullable=false, length=50)
	private String nombresusuario;

	@Column(length=20)
	private String telefono;

	//bi-directional many-to-one association to Bitacora
	@OneToMany(mappedBy="invUsuario")
	private List<Bitacora> bitacoras;

	//bi-directional many-to-one association to FacReserva
	@OneToMany(mappedBy="invUsuario")
	private List<FacReserva> facReservas;

	//bi-directional many-to-one association to InvRolesusuario
	@OneToMany(mappedBy="invUsuario",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<InvRolesusuario> invRolesusuarios;

	public InvUsuario() {
	}

	public Integer getIdusuario() {
		return this.idusuario;
	}

	public void setIdusuario(Integer idusuario) {
		this.idusuario = idusuario;
	}

	public String getApellidosusuario() {
		return this.apellidosusuario;
	}

	public void setApellidosusuario(String apellidosusuario) {
		this.apellidosusuario = apellidosusuario;
	}

	public String getClave() {
		return this.clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getCorreo() {
		return this.correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
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

	public String getNombresusuario() {
		return this.nombresusuario;
	}

	public void setNombresusuario(String nombresusuario) {
		this.nombresusuario = nombresusuario;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public List<Bitacora> getBitacoras() {
		return this.bitacoras;
	}

	public void setBitacoras(List<Bitacora> bitacoras) {
		this.bitacoras = bitacoras;
	}

	public Bitacora addBitacora(Bitacora bitacora) {
		getBitacoras().add(bitacora);
		bitacora.setInvUsuario(this);

		return bitacora;
	}

	public Bitacora removeBitacora(Bitacora bitacora) {
		getBitacoras().remove(bitacora);
		bitacora.setInvUsuario(null);

		return bitacora;
	}

	public List<FacReserva> getFacReservas() {
		return this.facReservas;
	}

	public void setFacReservas(List<FacReserva> facReservas) {
		this.facReservas = facReservas;
	}

	public FacReserva addFacReserva(FacReserva facReserva) {
		getFacReservas().add(facReserva);
		facReserva.setInvUsuario(this);

		return facReserva;
	}

	public FacReserva removeFacReserva(FacReserva facReserva) {
		getFacReservas().remove(facReserva);
		facReserva.setInvUsuario(null);

		return facReserva;
	}

	public List<InvRolesusuario> getInvRolesusuarios() {
		return this.invRolesusuarios;
	}

	public void setInvRolesusuarios(List<InvRolesusuario> invRolesusuarios) {
		this.invRolesusuarios = invRolesusuarios;
	}

	public InvRolesusuario addInvRolesusuario(InvRolesusuario invRolesusuario) {
		getInvRolesusuarios().add(invRolesusuario);
		invRolesusuario.setInvUsuario(this);

		return invRolesusuario;
	}

	public InvRolesusuario removeInvRolesusuario(InvRolesusuario invRolesusuario) {
		getInvRolesusuarios().remove(invRolesusuario);
		invRolesusuario.setInvUsuario(null);

		return invRolesusuario;
	}

}