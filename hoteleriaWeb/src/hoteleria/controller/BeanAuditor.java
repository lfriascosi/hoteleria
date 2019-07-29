package hoteleria.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import hoteleria.model.entities.Bitacora;
import hoteleria.model.entities.InvUsuario;
import hoteleria.model.manager.ManagerAuditor;

@Named
@SessionScoped
public class BeanAuditor implements Serializable {
	private static final long serialVersionUID = 1L;
	@EJB
	private ManagerAuditor managerAuditor;
	private List<Bitacora> listaBitacoras;
	private InvUsuario usuario;

	@PostConstruct
	public void inicializar() {
		listaBitacoras = managerAuditor.findAllBitacoras();
		usuario=managerAuditor.findInvUsuarioById();
	}

	public List<Bitacora> getListaBitacoras() {
		return listaBitacoras;
	}

	public void setListaBitacoras(List<Bitacora> listaBitacoras) {
		this.listaBitacoras = listaBitacoras;
	}

	public InvUsuario getUsuario() {
		return usuario;
	}

	public void setUsuario(InvUsuario usuario) {
		this.usuario = usuario;
	}

}
