package hoteleria.model.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the inv_rolesusuarios database table.
 * 
 */
@Entity
@Table(name="inv_rolesusuarios")
@NamedQuery(name="InvRolesusuario.findAll", query="SELECT i FROM InvRolesusuario i")
public class InvRolesusuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="INV_ROLESUSUARIOS_IDROLUSUARIO_GENERATOR", sequenceName="SEQ_INV_ROLESUSUARIOS",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="INV_ROLESUSUARIOS_IDROLUSUARIO_GENERATOR")
	@Column(unique=true, nullable=false)
	private Integer idrolusuario;

	//bi-directional many-to-one association to InvRole
	@ManyToOne
	@JoinColumn(name="idrol", nullable=false)
	private InvRole invRole;

	//bi-directional many-to-one association to InvUsuario
	@ManyToOne
	@JoinColumn(name="idusuario", nullable=false)
	private InvUsuario invUsuario;

	public InvRolesusuario() {
	}

	public Integer getIdrolusuario() {
		return this.idrolusuario;
	}

	public void setIdrolusuario(Integer idrolusuario) {
		this.idrolusuario = idrolusuario;
	}

	public InvRole getInvRole() {
		return this.invRole;
	}

	public void setInvRole(InvRole invRole) {
		this.invRole = invRole;
	}

	public InvUsuario getInvUsuario() {
		return this.invUsuario;
	}

	public void setInvUsuario(InvUsuario invUsuario) {
		this.invUsuario = invUsuario;
	}

}