package cliente.controller;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

import habitaciones.controller.JSFUtil;
import hoteleria.model.entities.InvUsuario;
import hoteleria.controller.BeanLogin;
import hoteleria.model.entities.FacReserva;
import hoteleria.model.entities.InvHabitacione;
import hoteleria.model.entities.InvTiposhabitacione;
import hoteleria.model.manager.cliente.ManagerReserva;
import hoteleria.model.manager.ManagerHabitaciones;

import java.io.Serializable;

/**
 * ManagedBean JSF para el manejo de la facturacion.
 * @author Roger Vaca
 *
 */

@Named
@SessionScoped
public class BeanReserva implements Serializable {
	private static final long serialVersionUID = 1L;
	private String cedulaCliente;
	@EJB
	private ManagerReserva managerReserva;
	@EJB
	private ManagerHabitaciones managerHabitaciones;
	
	private Integer idHabitacion = 0;
	private Integer idTipoHabitacion=0;
	private Integer cantidadProducto;
	private FacReserva facturaCabTmp;
	private InvHabitacione Habitacion;
	private List<InvHabitacione> listaHabitaciones = new ArrayList<InvHabitacione>();;
	private boolean facturaCabTmpGuardada;
	
	// Inyeccion de beans manejados:
	@Inject
	private BeanLogin beanLogin;
	
	public BeanReserva() {
		
	}
	
	
    public InvHabitacione getHabitacion() {
		return Habitacion;
	}


	public void setHabitacion(InvHabitacione habitacion) {
		Habitacion = habitacion;
	}


	public List<InvHabitacione> getListaHabitaciones() {
		return listaHabitaciones;
	}


	public void setListaHabitaciones(List<InvHabitacione> listaHabitaciones) {
		this.listaHabitaciones = listaHabitaciones;
	}


	public Integer getIdHabitacion() {
		return idHabitacion;
	}


	public void setIdHabitacion(Integer idHabitacion) {
		this.idHabitacion = idHabitacion;
	}


	/**
	 * Action para la creacion de una factura temporal en memoria.
	 * Hace uso del componente {@link facturacion.model.manager.ManagerFacturacion ManagerFacturacion} de la capa model.
	 * @return outcome para la navegacion.
	 */

     public Integer getIdTipoHabitacion() {
		return idTipoHabitacion;
	}


	public void setIdTipoHabitacion(Integer idTipoHabitacion) {
		this.idTipoHabitacion = idTipoHabitacion;
	}


	/**
	 * Devuelve un listado de componentes SelectItem a partir
	 * de un listado de {@link facturacion.model.dao.entities.Producto Producto}.
	 * 
	 * @return listado de SelectItems de productos.
	 */
	public List<SelectItem> getTipoHabitaciones(){
		List<SelectItem> listadoSI=new ArrayList<SelectItem>();
		List<InvTiposhabitacione> listadoTipoHabitaciones=managerHabitaciones.findAllHabitaciones();
		
		for(InvTiposhabitacione th:listadoTipoHabitaciones){
			SelectItem item=new SelectItem(th.getIdtipohabitacion(),th.getNombretipohabitacion());
			// System.out.println("HABITACIOOON: "+h.getIdhabitacion()+" Descripcioon: "+h.getDescripcion());
			listadoSI.add(item);
		}
		return listadoSI;
	}
	
	public List<SelectItem> getListaHabitacionesSI(){
		List<SelectItem> listadoSI=new ArrayList<SelectItem>();
		List<InvHabitacione> listadoHabitaciones=managerHabitaciones.findHabitacionesPerTipo(idTipoHabitacion);
		
		for(InvHabitacione h:listadoHabitaciones){
			SelectItem item=new SelectItem(h.getIdhabitacion(),h.getInvTiposhabitacione().getNombretipohabitacion()+" - nro: "+ h.getNumerohabitacion());
			System.out.println("HABITACIOOON: "+h.getIdhabitacion()+" Descripcioon: "+h.getDescripcion());
			listadoSI.add(item);
		}
		return listadoSI;
	} 
	
	/* public List<InvHabitacione> getListaHabitacionesSI(){
		listaHabitaciones = new ArrayList<InvHabitacione>();
		listaHabitaciones = managerHabitaciones.findHabitacionesPerTipo(idTipoHabitacion);
		
		return listaHabitaciones;
	} */

    public void verificarExistencia(){
		try {
			if(managerHabitaciones.obtenerExistencia(idHabitacion)==0)
				JSFUtil.crearMensajeError("No hay existencia");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}
    
    
}
