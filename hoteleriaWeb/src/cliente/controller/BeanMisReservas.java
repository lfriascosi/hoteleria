package cliente.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import hoteleria.controller.BeanLogin;
import hoteleria.model.entities.FacDetalle;
import hoteleria.model.entities.FacReserva;
import hoteleria.model.manager.cliente.ManagerReserva;

@Named
@SessionScoped
public class BeanMisReservas implements Serializable {
	private static final long serialVersionUID = 1L;
	@EJB
	private ManagerReserva managerReserva;
	private List<FacReserva> listaMisReservas = new ArrayList<FacReserva>();
	private List<FacDetalle> listaDetallesReservas = new ArrayList<FacDetalle>();
	@Inject
	private BeanLogin beanLogin;
	
	@PostConstruct
	public void inicializar() {
		// listaMisReservas = managerReserva.findMisReservas(beanLogin.getIdUsuario());
		//listaDetallesReservas = new ArrayList<FacDetalle>();
		// System.out.println("ESTE ES: "+login.getIdUsuario());
	}
	
	public void obtenerMisReservas() {
		listaMisReservas = managerReserva.findMisReservas(beanLogin.getIdUsuario());
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
	
	public BeanMisReservas() {
		// TODO Auto-generated constructor stub
	}
	
	public List<FacReserva> getListaMisReservas() {
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
