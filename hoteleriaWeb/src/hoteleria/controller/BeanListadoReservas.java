package hoteleria.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import hoteleria.model.entities.FacDetalle;
import hoteleria.model.entities.FacReserva;
import hoteleria.model.manager.ManagerReservas;
import hoteleria.model.manager.cliente.ManagerReserva;
@Named
@SessionScoped
public class BeanListadoReservas implements Serializable {
	private static final long serialVersionUID = 1L;
	@EJB
	private ManagerReservas managerReservas;
	@EJB
	private ManagerReserva managerReserva;
	private List<FacReserva> listaMisReservas = new ArrayList<FacReserva>();
	private List<FacDetalle> listaDetallesReservas = new ArrayList<FacDetalle>();
	@Inject
	private BeanLogin beanLogin;
	
	
	@PostConstruct
	public void inicializar() {
		// listaMisReservas = managerReservas.findAllReservas();
		//listaDetallesReservas = new ArrayList<FacDetalle>();
		// System.out.println("ESTE ES: "+login.getIdUsuario());
	}
	
	public void obtenerFacturas() {
		listaMisReservas = managerReservas.findAllReservas();
	}
	
	public List<FacDetalle> extraerDetalles(Integer IdReserva) {
		System.out.println("extraerDetalles: "+IdReserva);
		listaDetallesReservas = managerReserva.findDetallesReservas(IdReserva);
		for (FacDetalle facDetalle : listaDetallesReservas) {
			System.out.println("aquiiii estaa: "+facDetalle.getInvHabitacione().getDescripcion());
		}
		return  listaDetallesReservas;
	}
	
	public String estadoPago(Integer e) {
		String r="";
		if(e == 0)
			r="Por pagar";
		else if(e ==1)
			r="Pagado";
		return r;
	}
	
	public BeanListadoReservas() {
		// TODO Auto-generated constructor stub
	}
	
	public List<FacReserva> getListadoReservas() {
		return listaMisReservas;
	}

	public void setListaMisReservas(List<FacReserva> listaMisReservas) {
		this.listaMisReservas = listaMisReservas;
	}
	public List<FacDetalle> getListaDetallesReservas() {
		return listaDetallesReservas;
	}
	public void setListaDetallesReservas(List<FacDetalle> listaDetallesReservas) {
		this.listaDetallesReservas = listaDetallesReservas;
	}
	
	
 
}
