package hoteleria.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import hoteleria.model.entities.FacDetalle;
import hoteleria.model.entities.FacReserva;
import hoteleria.model.entities.InvUsuario;
import hoteleria.model.manager.ManagerReservas;

@Named
@SessionScoped
public class BeanReservas implements Serializable {
	private static final long serialVersionUID = 1L;
	@EJB
	private ManagerReservas managerReservas;
	private List<FacReserva> listaFacReservas;
	private List<FacDetalle> listaFacDetalles;
	private List<InvUsuario> listaInvUsuarios;
	
	@PostConstruct
	public void inicializar() {
		listaFacReservas=managerReservas.findAllFacReservas();
		listaFacDetalles=managerReservas.findAllFacDetalles();
		listaInvUsuarios=managerReservas.findAllInvUsuarios();
	}

	public List<FacReserva> getListaFacReservas() {
		return listaFacReservas;
	}

	public void setListaFacReservas(List<FacReserva> listaFacReservas) {
		this.listaFacReservas = listaFacReservas;
	}

	public List<FacDetalle> getListaFacDetalles() {
		return listaFacDetalles;
	}

	public void setListaFacDetalles(List<FacDetalle> listaFacDetalles) {
		this.listaFacDetalles = listaFacDetalles;
	}

	public List<InvUsuario> getListaInvUsuarios() {
		return listaInvUsuarios;
	}

	public void setListaInvUsuarios(List<InvUsuario> listaInvUsuarios) {
		this.listaInvUsuarios = listaInvUsuarios;
	}
}
