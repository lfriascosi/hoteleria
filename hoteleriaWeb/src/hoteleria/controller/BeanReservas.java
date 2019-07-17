package hoteleria.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import hoteleria.model.entities.FacDetalle;
import hoteleria.model.manager.ManagerReservas;

@Named
@SessionScoped
public class BeanReservas implements Serializable {
	private static final long serialVersionUID = 1L;
	@EJB
	private ManagerReservas managerReservas;
	private List<FacDetalle> listaFacDetalles;

	@PostConstruct
	public void inicializar() {
		listaFacDetalles = managerReservas.findAllFacDetalles();
	}

	public String estadoPago(int estadoPago) {
		String resul = "";
		if (estadoPago == 1) {
			resul = "Pagado";
		} else if (estadoPago == 0) {
			resul = "Por pagar";
		}
		return resul;
	}

	public String estadoReserva(int estadoReserva) {
		String resul = "";
		if (estadoReserva == 0) {
			resul = "Por ocupar";
		} else if (estadoReserva == 1) {
			resul = "Ocupado";
		}else if (estadoReserva == 2) {
			resul = "Finalizado";
		}
		return resul;
	}

	public List<FacDetalle> getListaFacDetalles() {
		return listaFacDetalles;
	}

	public void setListaFacDetalles(List<FacDetalle> listaFacDetalles) {
		this.listaFacDetalles = listaFacDetalles;
	}

}
