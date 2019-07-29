package hoteleria.controller;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import habitaciones.controller.JSFUtil;
import hoteleria.model.entities.InvTiposhabitacione;
import hoteleria.model.manager.ManagerAdministrador;

import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class BeanTipoHabitacio implements Serializable {
	private static final long serialVersionUID = 1L;
	@EJB
	private ManagerAdministrador managerAdministrador;
	private List<InvTiposhabitacione> listaTipoHabitacion;
	
	private InvTiposhabitacione tipohabitacion;
	private boolean panelColapsado;
	private InvTiposhabitacione tipohabitacionSeleccionada;
	
	@PostConstruct
	public void inicializar() {
		listaTipoHabitacion = managerAdministrador.finAllTiposhabitaciones();
		
		tipohabitacion = new InvTiposhabitacione();
		panelColapsado = true;
	}

	public void actionListenerCOlapsarPanel() {
		panelColapsado = !panelColapsado;
	}

	public void actionListenerInsertarTipoHabitacion() {
		try {
			managerAdministrador.insertarTipoHabitacion(tipohabitacion);
			listaTipoHabitacion = managerAdministrador.finAllTiposhabitaciones();
			tipohabitacion = new InvTiposhabitacione();
			JSFUtil.crearMensajeInfo("Datos de tipo habitacion insertados.");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}
	

	public void actionListenerELiminarTipoHabitaciones(Integer idtipohabitacion) {
		managerAdministrador.eliminarTipohabitacion(idtipohabitacion);
		listaTipoHabitacion = managerAdministrador.finAllTiposhabitaciones();
		JSFUtil.crearMensajeInfo("Tipo de Habitacion eliminado");
	}

	public void actionListenerSelecionarTipoHabitacion(InvTiposhabitacione tipohabitacion) {
		tipohabitacionSeleccionada = tipohabitacion;
	}

	public void actionListenerActualizarTipoHabitacion() {
		try {
			managerAdministrador.actualizarTipoHabitacion(tipohabitacionSeleccionada);
			listaTipoHabitacion = managerAdministrador.finAllTiposhabitaciones();
			JSFUtil.crearMensajeInfo("Datos actualizados");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}

	public boolean isPanelColapsado() {
		return panelColapsado;
	}

	public void setPanelColapsado(boolean panelColapsado) {
		this.panelColapsado = panelColapsado;
	}

	public List<InvTiposhabitacione> getListaTipoHabitacion() {
		return listaTipoHabitacion;
	}

	public void setListaTipoHabitacion(List<InvTiposhabitacione> listaTipoHabitacion) {
		this.listaTipoHabitacion = listaTipoHabitacion;
	}

	public InvTiposhabitacione getTipohabitacion() {
		return tipohabitacion;
	}

	public void setTipohabitacion(InvTiposhabitacione tipohabitacion) {
		this.tipohabitacion = tipohabitacion;
	}

	public InvTiposhabitacione getTipohabitacionSeleccionada() {
		return tipohabitacionSeleccionada;
	}

	public void setTipohabitacionSeleccionada(InvTiposhabitacione tipohabitacionSeleccionada) {
		this.tipohabitacionSeleccionada = tipohabitacionSeleccionada;
	}

	

}
