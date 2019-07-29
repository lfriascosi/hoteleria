package hoteleria.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.SelectItem;
import javax.inject.Named;

import habitaciones.controller.JSFUtil;
import hoteleria.model.entities.InvHabitacione;
import hoteleria.model.entities.InvTiposhabitacione;
import hoteleria.model.manager.ManagerAdministrador;

@Named
@SessionScoped
public class BeanHabitacion implements Serializable {
	private static final long serialVersionUID = 1L;
	@EJB
	private ManagerAdministrador managerAdministrador;

	private List<InvHabitacione> listaHabitacion;

	private InvHabitacione habitacion;
	private boolean panelColapsado;
	private InvHabitacione habitacionSeleccionada;
	private int idtipohabitacion;

	@PostConstruct
	public void inicializar() {
		listaHabitacion = managerAdministrador.findAllInvHabitacione();

		habitacion = new InvHabitacione();
		panelColapsado = true;
	}

	public void actionListenerCOlapsarPanel() {
		panelColapsado = !panelColapsado;
	}
	

	public void actionListenerInsertarHabitacion() {
		try {
			managerAdministrador.insertarHabitacion(habitacion,idtipohabitacion);
			listaHabitacion = managerAdministrador.findAllInvHabitacione();
			habitacion = new InvHabitacione();
			JSFUtil.crearMensajeInfo("Datos de habitacion insertados.");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}
	

	public void actionListenerELiminarHabitaciones(Integer idhabitacion) {
		managerAdministrador.eliminarhabitacion(idhabitacion);
		listaHabitacion = managerAdministrador.findAllInvHabitacione();
		JSFUtil.crearMensajeInfo("Habitacion eliminado");
	}

	public void actionListenerSelecionarHabitacion(InvHabitacione habitacion) {
		habitacionSeleccionada = habitacion;
	}

	public void actionListenerActualizarHabitacion() {
		try {
			managerAdministrador.actualizarHabitacion(habitacionSeleccionada,idtipohabitacion);
			listaHabitacion = managerAdministrador.findAllInvHabitacione();
			JSFUtil.crearMensajeInfo("Datos actualizados");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}
	public List<SelectItem> getTiposHabitacion() {
		List<SelectItem> listadoSI = new ArrayList<SelectItem>();
		List<InvTiposhabitacione> listadoTiposHabitacion = managerAdministrador.finAllTiposhabitaciones();

		for (InvTiposhabitacione th : listadoTiposHabitacion) {
			SelectItem item = new SelectItem(th.getIdtipohabitacion(), th.getNombretipohabitacion());
			// System.out.println("HABITACIOOON: "+h.getIdhabitacion()+" Descripcioon:
			// "+h.getDescripcion());
			listadoSI.add(item);
		}
		return listadoSI;
	}

	public List<InvHabitacione> getListaHabitacion() {
		return listaHabitacion;
	}

	public void setListaHabitacion(List<InvHabitacione> listaHabitacion) {
		this.listaHabitacion = listaHabitacion;
	}

	public InvHabitacione getHabitacion() {
		return habitacion;
	}

	public void setHabitacion(InvHabitacione habitacion) {
		this.habitacion = habitacion;
	}

	public boolean isPanelColapsado() {
		return panelColapsado;
	}

	public void setPanelColapsado(boolean panelColapsado) {
		this.panelColapsado = panelColapsado;
	}

	public int getIdtipohabitacion() {
		return idtipohabitacion;
	}

	public void setIdtipohabitacion(int idtipohabitacion) {
		this.idtipohabitacion = idtipohabitacion;
	}

	public InvHabitacione getHabitacionSeleccionada() {
		return habitacionSeleccionada;
	}

	public void setHabitacionSeleccionada(InvHabitacione habitacionSeleccionada) {
		this.habitacionSeleccionada = habitacionSeleccionada;
	}
	
}
