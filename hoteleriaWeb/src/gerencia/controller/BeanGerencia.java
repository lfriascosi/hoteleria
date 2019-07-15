package gerencia.controller;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import hoteleria.model.entities.FacDetalle;
import hoteleria.model.entities.InvUsuario;
import hoteleria.model.manager.ManagerGerencia;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@SessionScoped
@Named
public class BeanGerencia implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@EJB
	
	private ManagerGerencia managerGerencia;
	private List<FacDetalle> listadetalles;
	private List<InvUsuario> listausuario;
	private InvUsuario usuarios;
	private FacDetalle detalles;
	
	@PostConstruct
	public void inicializar() {
		listadetalles = managerGerencia.findAllGerencia();
		detalles = new FacDetalle();
		
	}
	
	

	public List<FacDetalle> getListadetalles() {
		return listadetalles;
	}

	public void setListadetalles(List<FacDetalle> listadetalles) {
		this.listadetalles = listadetalles;
	}

	public FacDetalle getDetalles() {
		return detalles;
	}

	public void setDetalles(FacDetalle detalles) {
		this.detalles = detalles;
	}
	
}
