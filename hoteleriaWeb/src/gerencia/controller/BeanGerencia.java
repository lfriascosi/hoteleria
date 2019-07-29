package gerencia.controller;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import habitaciones.controller.JSFUtil;
import hoteleria.model.entities.FacDetalle;
import hoteleria.model.entities.InvHabitacione;
import hoteleria.model.entities.InvRolesusuario;
import hoteleria.model.entities.InvTiposhabitacione;
import hoteleria.model.entities.InvUsuario;
import hoteleria.model.manager.ManagerGerencia;
import java.io.Serializable;
import java.util.List;

@SessionScoped
@Named
public class BeanGerencia implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@EJB
	private ManagerGerencia managerGerencia;
	private List<FacDetalle> listadetalles;
	private List<InvRolesusuario> listaclientes;
	private List<InvTiposhabitacione> listaprecios;
	private List<InvHabitacione> listacprecios;
	
	private FacDetalle detalles;
	private InvRolesusuario clientes;
	private InvTiposhabitacione precios;
	private InvHabitacione habitacionSeleccionada;
	private InvHabitacione pre;
	
	@PostConstruct
	public void inicializar() {
		/**listadetalles = managerGerencia.findAllGerencia();
		detalles = new FacDetalle();**/
		listaclientes= managerGerencia.findAllClientes();
		clientes = new InvRolesusuario();
		/**listacprecios=managerGerencia.findAllPrecios();
		pre= new InvTiposhabitacione();
		listacprecios = new ManagerGerencia().findAllPrecios();
		precios = new InvHabitacione();**/
		
		
	}
	
	public void actionListenerSelecionarHabitacion(InvHabitacione habitacion) {
		habitacionSeleccionada = habitacion;
	}

	public void actionListenerActualizarPrecio() throws Exception{
		
		try {
			managerGerencia.actualizarPrecios(habitacionSeleccionada);
			JSFUtil.crearMensajeInfo("Datos actualizados");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
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



	public List<InvRolesusuario> getListaclientes() {
		return listaclientes;
	}



	public void setListaclientes(List<InvRolesusuario> listaclientes) {
		this.listaclientes = listaclientes;
	}



	public InvRolesusuario getClientes() {
		return clientes;
	}



	public void setClientes(InvRolesusuario clientes) {
		this.clientes = clientes;
	}



	public List<InvTiposhabitacione> getListaprecios() {
		return listaprecios;
	}



	public void setListaprecios(List<InvTiposhabitacione> listaprecios) {
		this.listaprecios = listaprecios;
	}



	public InvTiposhabitacione getPrecios() {
		return precios;
	}



	public void setPrecios(InvTiposhabitacione precios) {
		this.precios = precios;
	}

	public List<InvHabitacione> getListacprecios() {
		return listacprecios;
	}

	public void setListacprecios(List<InvHabitacione> listacprecios) {
		this.listacprecios = listacprecios;
	}

	public InvHabitacione getHabitacionSeleccionada() {
		return habitacionSeleccionada;
	}

	public void setHabitacionSeleccionada(InvHabitacione habitacionSeleccionada) {
		this.habitacionSeleccionada = habitacionSeleccionada;
	}





	


	
	
	
}
