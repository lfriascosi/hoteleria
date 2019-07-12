package habitaciones.controller;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;


import hoteleria.model.entities.InvTiposhabitacione;
import hoteleria.model.manager.ManagerHabitaciones;

import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class BeanHabitaciones implements Serializable {
	private static final long serialVersionUID = 1L;
	@EJB
	private ManagerHabitaciones managerHabitaciones;
	private List<InvTiposhabitacione> listaHabitacion;
	private InvTiposhabitacione habitacion;
	private boolean panelColapsado;
	private InvTiposhabitacione habitacionSeleccionada;
	
	@PostConstruct
	public void inicializar() {
		listaHabitacion = managerHabitaciones.findAllHabitaciones();
		habitacion = new InvTiposhabitacione();
		panelColapsado = true;
	}

	public void actionListenerCOlapsarPanel() {
		panelColapsado = !panelColapsado;
	}

	public void actionListenerInsertarHabitacion() {
		try {
			managerHabitaciones.InsertarHabitacion(habitacion);
			listaHabitacion = managerHabitaciones.findAllHabitaciones();
			habitacion = new InvTiposhabitacione();
			JSFUtil.crearMensajeInfo("Datos de habitacion insertados.");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}
	

	public void actionListenerELiminarHabitaciones(Integer idtipohabitacion) {
		managerHabitaciones.eliminarHabitacion(idtipohabitacion);
		listaHabitacion = managerHabitaciones.findAllHabitaciones();
		JSFUtil.crearMensajeInfo("Habitacion eliminada");
	}

	public void actionListenerSelecionarHabitacion(InvTiposhabitacione habitacion) {
		habitacionSeleccionada = habitacion;
	}

	public void actionListenerActualizarHabitacion() {
		try {
			managerHabitaciones.actualizarHabitacion(habitacionSeleccionada);
			listaHabitacion = managerHabitaciones.findAllHabitaciones();
			JSFUtil.crearMensajeInfo("Datos actualizados");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}

	

	
	public List<InvTiposhabitacione> getListaHabitacion() {
		return listaHabitacion;
	}

	public void setListaHabitacion(List<InvTiposhabitacione> listaHabitacion) {
		this.listaHabitacion = listaHabitacion;
	}

	public InvTiposhabitacione getHabitacion() {
		return habitacion;
	}

	public void setHabitacion(InvTiposhabitacione habitacion) {
		this.habitacion = habitacion;
	}

	public boolean isPanelColapsado() {
		return panelColapsado;
	}

	public void setPanelColapsado(boolean panelColapsado) {
		this.panelColapsado = panelColapsado;
	}

	public InvTiposhabitacione getHabitacionSeleccionada() {
		return habitacionSeleccionada;
	}

	public void setHabitacionSeleccionada(InvTiposhabitacione habitacionSeleccionada) {
		this.habitacionSeleccionada = habitacionSeleccionada;
	}

}
