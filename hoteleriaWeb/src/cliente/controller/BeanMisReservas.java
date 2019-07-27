package cliente.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import hoteleria.controller.BeanLogin;
import hoteleria.model.entities.FacReserva;
import hoteleria.model.manager.cliente.ManagerReserva;

@Named
@SessionScoped
public class BeanMisReservas implements Serializable {
	private static final long serialVersionUID = 1L;
	@EJB
	private ManagerReserva managerReserva;
	private List<FacReserva> listaMisReservas = new ArrayList<FacReserva>();
	private BeanLogin login;
	
	@PostConstruct
	public void inicializar() {
		listaMisReservas = managerReserva.findMisReservas(1);
		// System.out.println("ESTE ES: "+login.getIdUsuario());
	}
	public BeanMisReservas() {
		// TODO Auto-generated constructor stub
	}
	
	public List<FacReserva> getListaMisReservas() {
		return listaMisReservas;
	}

	public void setListaMisReservas(List<FacReserva> listaMisReservas) {
		this.listaMisReservas = listaMisReservas;
	}
}
