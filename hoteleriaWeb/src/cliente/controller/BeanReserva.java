package cliente.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

import habitaciones.controller.JSFUtil;
import hoteleria.model.entities.InvUsuario;
import hoteleria.controller.BeanLogin;
import hoteleria.model.dto.LoginDTO;
import hoteleria.model.entities.FacReserva;
import hoteleria.model.entities.InvHabitacione;
import hoteleria.model.entities.InvTiposhabitacione;
import hoteleria.model.manager.cliente.ManagerReserva;
import hoteleria.model.manager.ManagerHabitaciones;
import hoteleria.model.manager.ManagerSeguridad;
import hoteleria.model.manager.ManagerUsuarios;

import java.io.Serializable;
import java.text.SimpleDateFormat;

/**
 * ManagedBean JSF para el manejo de la facturacion.
 * @author Roger Vaca
 *
 */

@Named
@SessionScoped
public class BeanReserva implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer idCliente;
	@EJB
	private ManagerReserva managerReserva;
	@EJB
	private ManagerHabitaciones managerHabitaciones;
	@EJB
	private ManagerUsuarios managerUsuarios;  // recepcionista

	private Integer idHabitacion = 0;
	private Integer idTipoHabitacion=0;
	private Integer dias;
	private Date fecha; // no se usa
	private Date fechaEntrada;
	private Date fechaSalida;
	private FacReserva facturaCabTmp;
	private InvHabitacione Habitacion;
	private List<InvHabitacione> listaHabitaciones = new ArrayList<InvHabitacione>();
	private List<InvHabitacione> listaHabitacionesRESP = new ArrayList<InvHabitacione>();
	private boolean facturaCabTmpGuardada;
	private boolean activForm=false;
	private boolean verDeshacer = false;
	private boolean activGuardar=false;
	
	
	
	private Integer adultos = 0;	
	private Integer niños = 0;				
	private List<Integer> numeroAdultos = new ArrayList<Integer>(); 								
	private List<Integer> numeroNiños = new ArrayList<Integer>();																		
	private Integer maxNiños = 0;	
	private Integer maxAdultos = 4;
	// Inyeccion de beans manejados:
	@Inject
	private BeanLogin beanLogin;
	
	public BeanReserva() {
		
	}
	
	public void resetParams() {
		System.out.println("Reset params");
		activForm = false;
		activGuardar=false;
		verDeshacer = false;
		facturaCabTmpGuardada=false;
		listaHabitaciones = new ArrayList<InvHabitacione>();
		listaHabitacionesRESP = new ArrayList<InvHabitacione>();
		// numeroAdultos = new ArrayList<Integer>(); 								
		// numeroNiños = new ArrayList<Integer>();	
		maxNiños = 0;	
		maxAdultos = 4;
		adultos = 0;	
		niños = 0;	
		fechaEntrada = null;
		fechaSalida = null;
		
	}
	
	@PostConstruct
	public void inicializar() {
		// System.out.println(facturaCabTmp.getFacDetalles().size());
		//this.crearNuevaReserva();
		llenarListaAdultos();llenarListaNiños();
	}
	
	public String crearNuevaReserva() {
		// if(validarFechas() == true) {
			facturaCabTmp=managerReserva.crearFacturaTmp(fechaEntrada, fechaSalida);
			idCliente=0;
			idHabitacion=0;
			dias=0;
			fecha=null;
			activGuardar = true;
			//fechaEntrada = null;
			//fechaSalida = null;
			facturaCabTmpGuardada=false;
			this.activForm = true;
			System.out.println("ACTIVEEE:" +this.activForm);
			return "";
		/* }else {
			JSFUtil.crearMensajeWaring("Ingrese correctamente las fechas");
			System.out.println("Las fechas ingresadas son incorrectas");
		}
			return ""; */
	}
	

	
	public void llenarListaNiños() {
		if(this.maxNiños>=0) {
			numeroNiños = new ArrayList<Integer>();
			for (int i = 0; i <= this.maxNiños; i++) {
				this.numeroNiños.add(i);
				System.out.println("indice Niños: "+i);
			}
		}else{
			numeroNiños = new ArrayList<Integer>();
		}
	}
	
	public void llenarListaAdultos() {
		if(this.maxAdultos>=0) {
			numeroAdultos = new ArrayList<Integer>();
			for (int i = 0; i <= this.maxAdultos; i++) {
				this.numeroAdultos.add(i);
				System.out.println("indice Adultos: "+i);
			}
		}else {
			numeroAdultos = new ArrayList<Integer>();
		}
	}
	
		/**
	 * Action que adiciona un item a una factura temporal.
	 * Hace uso del componente {@link model.manager.ManagerFacturacion ManagerFacturacion} de la capa model.
	 * @return
	 */
	public void insertarDetalle(Integer idHabitacion){
		System.out.println("insertarDetalle()");
		if(facturaCabTmpGuardada==true){
			JSFUtil.crearMensajeWaring("La reserva ya está realizada.");
			
		}
		try {
			managerReserva.agregarDetalleFacturaTmp(facturaCabTmp,idHabitacion,fechaEntrada,fechaSalida);
			this.updStock(idHabitacion);
			this.verDeshacer=true;
			idHabitacion=0;
			dias=0;
			fecha=null;
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
		}		
		
	}
	
	
	
	public boolean isActivGuardar() {
			return activGuardar;
		}

		public void setActivGuardar(boolean activGuardar) {
			this.activGuardar = activGuardar;
		}

	public void updStock(Integer idHabitacion) {
		System.out.println("ACTUALIZANDO -----");
		
		List<InvHabitacione> listaHabitacionesAUX = new ArrayList<InvHabitacione>();
		for (InvHabitacione h : this.listaHabitaciones) {
			System.out.println("idH BDD: "+h.getIdhabitacion()+" ID ACTUAL: "+idHabitacion);
			if(h.getIdhabitacion()!= idHabitacion) {
				listaHabitacionesAUX.add(h);
				System.out.println("Id habit:" +h.getNumerohabitacion());
			}
		}
		this.listaHabitacionesRESP = this.listaHabitaciones;
		this.listaHabitaciones = listaHabitacionesAUX;
	}
	
	
	public void deshacerReservas() {
		System.out.println("DESHACER");
		this.listaHabitaciones = this.listaHabitacionesRESP;
		this.crearNuevaReserva();
		this.verDeshacer = false;
	}
	
	
	/**
	 * Action que almacena en la base de datos una factura temporal creada en memoria.
	 * Hace uso del componente {@link facturacion.model.manager.ManagerFacturacion ManagerFacturacion} de la capa model.
	 * @return outcome para la navegacion.
	 */
	public String guardarFactura(){
		if(facturaCabTmpGuardada==true){
			JSFUtil.crearMensajeWaring("La reserva ya está realizada.");
			return "";
		}
		try {
			managerReserva.guardarFacturaTemporal(beanLogin.getIdUsuario(),facturaCabTmp);
			facturaCabTmpGuardada=true;
			this.resetParams();
			beanLogin.setMsj("Reserva Guardada");
			return "./reservaciones.xhtml?faces-redirect=true";
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
		}
		
		return "";
	}
	
	public void buscarHabitacion() {	
		if(adultos == 0 && niños >0) {
			JSFUtil.crearMensajeError("Los niños deben ser acompañados por una persona adulta");
		}else {
			int c = adultos + niños;
			
			if(c == 3) {
				c = 4;
		}
		listaHabitaciones = managerHabitaciones.findHabitacionesPerCapacidad(c);
		}
	}
	
	public String irReservar() {
			return "/index.xhtml?faces-redirect=true";
	}
	
	
	

	public boolean isVerDeshacer() {
		return verDeshacer;
	}

	public void setVerDeshacer(boolean verDeshacer) {
		this.verDeshacer = verDeshacer;
	}

	public Date getFechaEntrada() {
		return fechaEntrada;
	}

	public void setFechaEntrada(Date fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}

	public Date getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public List<Integer> getNumeroAdultos() {
		return numeroAdultos;
	}

	public void setNumeroAdultos(List<Integer> numeroAdultos) {
		this.numeroAdultos = numeroAdultos;
	}

	public List<Integer> getNumeroNiños() {
		return numeroNiños;
	}

	public void setNumeroNiños(List<Integer> numeroNiños) {
		this.numeroNiños = numeroNiños;
	}

	public void actCuposNiños() {
		System.out.println("agregar limite NIÑOS");
		this.maxNiños = 4 - this.adultos;
		System.out.println(this.maxNiños);
		llenarListaNiños();
	}
	public void actCuposAdultos() {
		System.out.println("agregar limite Adultos");
		this.maxAdultos = 4 - this.niños;
		System.out.println(this.maxAdultos);
		llenarListaAdultos();
	}
	
	public Integer getNiños() {
			return niños;
	}

	public void setNiños(Integer niños) {
		
		this.niños = niños;
	}

	public Integer getAdultos() {
		
		return adultos;
	}

	public void setAdultos(Integer adultos) {
	
		this.adultos = adultos;
	}

	public Integer getMaxNiños() {
		return maxNiños;
	}

	public void setMaxNiños(Integer maxNiños) {
		this.maxNiños = maxNiños;
	}

	public Integer getMaxAdultos() {
		return maxAdultos;
	}

	public void setMaxAdultos(Integer maxAdultos) {
		this.maxAdultos = maxAdultos;
	}

	public FacReserva getFacturaCabTmp() {
			return facturaCabTmp;
		}

		public void setFacturaCabTmp(FacReserva facturaCabTmp) {
			this.facturaCabTmp = facturaCabTmp;
		}

		public boolean isFacturaCabTmpGuardada() {
			return facturaCabTmpGuardada;
		}

		public void setFacturaCabTmpGuardada(boolean facturaCabTmpGuardada) {
			this.facturaCabTmpGuardada = facturaCabTmpGuardada;
		}

		
		
	public Integer getIdCliente() {
			return idCliente;
		}

		public void setIdCliente(Integer idCliente) {
			this.idCliente = idCliente;
		}

		public void setDias(Integer dias) {
			this.dias = dias;
		}

		public void setFecha(Date fecha) {
			this.fecha = fecha;
		}

		public void setActivForm(boolean activForm) {
			this.activForm = activForm;
		}

	public Date getFecha() {
		return fecha;
	}
	
	public Integer getDias() {
		return dias;
	}
	
	public boolean isActivForm() {
		return activForm;
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
	
	public List<SelectItem> getClientes(){
		List<SelectItem> listadoSI=new ArrayList<SelectItem>();
		List<InvUsuario> listadoUsuarios=managerUsuarios.findAllInvUsuarios();
		
		for(InvUsuario u:listadoUsuarios){
			SelectItem item=new SelectItem(u.getIdusuario(),u.getNombresusuario());
			// System.out.println("HABITACIOOON: "+h.getIdhabitacion()+" Descripcioon: "+h.getDescripcion());
			listadoSI.add(item);
		}
		return listadoSI;
	}
	
	public List<SelectItem> getListaHabitacionesSI(){
		List<SelectItem> listadoSI=new ArrayList<SelectItem>();
		List<InvHabitacione> listadoHabitaciones=managerHabitaciones.findHabitacionesPerTipo(idTipoHabitacion);
		
		for(InvHabitacione h:listadoHabitaciones){
			SelectItem item=new SelectItem(h.getIdhabitacion(),"Nro: "+ h.getNumerohabitacion()+",Piso: "+h.getNumeropiso());
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
