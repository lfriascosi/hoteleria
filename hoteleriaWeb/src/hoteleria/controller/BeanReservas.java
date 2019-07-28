package hoteleria.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
//import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
//import javax.faces.model.SelectItem;
import javax.inject.Named;
import javax.faces.context.ExternalContext;

import habitaciones.controller.JSFUtil;
import hoteleria.model.entities.FacDetalle;
import hoteleria.model.entities.FacReserva;
import hoteleria.model.entities.InvHabitacione;
import hoteleria.model.entities.InvTiposhabitacione;
import hoteleria.model.entities.InvUsuario;
import hoteleria.model.manager.ManagerHabitaciones;
//import hoteleria.model.entities.FacReserva;
//import hoteleria.model.entities.InvUsuario;
import hoteleria.model.manager.ManagerReservas;
import hoteleria.model.manager.ManagerUsuarios;
import hoteleria.model.manager.cliente.ManagerReserva;

@Named
@SessionScoped
public class BeanReservas implements Serializable {
	private static final long serialVersionUID = 1L;
//	private String cedulaCliente;
	// @EJB
	//private ManagerReservas managerReservas;
	//private List<FacDetalle> listaFacDetalles;
//	private boolean panelColapso;

//	private Integer codigoHabitacion;
//	private FacReserva facturaCabTmp;
//	private boolean facturaCabTmpGuardada;

	//@PostConstruct
	//public void inicializar() {
		// listaFacDetalles = managerReservas.findAllFacDetalles();
//	}

//	public void actionListenerColapsarPanel() {
//		panelColapso = !panelColapso;
//	}

//	public String crearNuevaFactura() {
//		facturaCabTmp = managerReservas.crearFacturaTmp();
//		cedulaCliente = null;
//		codigoHabitacion = 0;
//		facturaCabTmpGuardada = false;
//		return "";
//	}
	public void asignarCliente(){
		if(facturaCabTmpGuardada==true){
			JSFUtil.crearMensajeInfo("id cli:" +idCliente);
		}
		try {
			managerReservas.asignarClienteFacturaTmp(facturaCabTmp,idCliente);
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
		}
	}
//
//	public List<SelectItem> getListaClientesSI() {
//		List<SelectItem> listadoSI = new ArrayList<SelectItem>();
//		List<InvUsuario> listadoClientes = managerReservas.findAllUsuarios();
//
//		for (InvUsuario c : listadoClientes) {
//			SelectItem item = new SelectItem(c.getCorreo(), c.getApellidosusuario() + " " + c.getNombresusuario());
//			listadoSI.add(item);
//		}
//		return listadoSI;
//	}

	/*
	 * public String estadoPago(int estadoPago) { String resul = ""; if (estadoPago
	 * == 1) { resul = "Pagado"; } else if (estadoPago == 0) { resul = "Por pagar";
	 * } return resul; }
	 * 
	 * public String estadoReserva(int estadoReserva) { String resul = ""; if
	 * (estadoReserva == 0) { resul = "Por ocupar"; } else if (estadoReserva == 1) {
	 * resul = "Ocupado"; } else if (estadoReserva == 2) { resul = "Finalizado"; }
	 * return resul; }
	 * 
	 * public List<FacDetalle> getListaFacDetalles() { return listaFacDetalles; }
	 * 
	 * public void setListaFacDetalles(List<FacDetalle> listaFacDetalles) {
	 * this.listaFacDetalles = listaFacDetalles; }
	 */

//	public boolean isPanelColapso() {
//		return panelColapso;
//	}
//
//	public void setPanelColapso(boolean panelColapso) {
//		this.panelColapso = panelColapso;
//	}
//
//	public String getCedulaCliente() {
//		return cedulaCliente;
//	}
//
//	public void setCedulaCliente(String cedulaCliente) {
//		this.cedulaCliente = cedulaCliente;
//	}
//
//	public Integer getCodigoHabitacion() {
//		return codigoHabitacion;
//	}
//
//	public void setCodigoHabitacion(Integer codigoHabitacion) {
//		this.codigoHabitacion = codigoHabitacion;
//	}
//
//	public FacReserva getFacturaCabTmp() {
//		return facturaCabTmp;
//	}
//
//	public void setFacturaCabTmp(FacReserva facturaCabTmp) {
//		this.facturaCabTmp = facturaCabTmp;
//	}
//
//	public boolean isFacturaCabTmpGuardada() {
//		return facturaCabTmpGuardada;
//	}
//
//	public void setFacturaCabTmpGuardada(boolean facturaCabTmpGuardada) {
//		this.facturaCabTmpGuardada = facturaCabTmpGuardada;
//	}
	
	private Integer idCliente=0;
	@EJB
	private ManagerReservas managerReservas;
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
	private boolean activGuardar=false;
	private boolean verDeshacer = false;
	
	
	private Integer adultos = 0;	
	private Integer niños = 0;				
	private List<Integer> numeroAdultos = new ArrayList<Integer>(); 								
	private List<Integer> numeroNiños = new ArrayList<Integer>();																		
	private Integer maxNiños = 0;	
	private Integer maxAdultos = 4;
	
	public void resetParams() {
		activForm = false;
		activGuardar=false;
		verDeshacer = false;
		facturaCabTmpGuardada=false;
		listaHabitaciones = new ArrayList<InvHabitacione>();
		listaHabitacionesRESP = new ArrayList<InvHabitacione>();
		numeroAdultos = new ArrayList<Integer>(); 								
		numeroNiños = new ArrayList<Integer>();	
		maxNiños = 0;	
		maxAdultos = 4;
		adultos = 0;	
		niños = 0;	
		fechaEntrada = null;
		fechaSalida = null;
		
	}
	
	private List<InvUsuario> listaUsuarios = new ArrayList<InvUsuario>();
	// Inyeccion de beans manejados:
	//@Inject
	//private BeanLogin beanLogin;
	
	public BeanReservas() {
		
	}
	
	@PostConstruct
	public void inicializar() {
		//this.crearNuevaReserva();
		llenarListaAdultos();llenarListaNiños();
		listaUsuarios = managerUsuarios.findAllInvUsuarios();
	}
	
	public String crearNuevaReserva() {
		// if(validarFechas() == true) {
			facturaCabTmp=managerReservas.crearFacturaTmp(fechaEntrada, fechaSalida);
			idCliente=0;
			idHabitacion=0;
			dias=0;
			fecha=null;
			activGuardar = true;
			// fechaEntrada = null;
			// fechaSalida = null;
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
		System.out.println("VAMOS A RESERVAR");
		if(facturaCabTmpGuardada==true){
			JSFUtil.crearMensajeWaring("La reserva ya está realizada.");
			
		}
		try {
			managerReservas.agregarDetalleFacturaTmp(facturaCabTmp,idHabitacion,fechaEntrada,fechaSalida);
			
			this.updStock(idHabitacion);
			this.verDeshacer=true;
			idHabitacion=0;
			dias=0;
			fecha=null;
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
		}		
		
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
		JSFUtil.crearMensajeInfo("Detalles de habitaciones eliminados");
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
			
			// JSFUtil.crearMensajeInfo("CLIENTE: "+idCliente);
			managerReservas.guardarFacturaTemporalRecep(idCliente,facturaCabTmp);
			facturaCabTmpGuardada=true;
			resetParams();
			JSFUtil.crearMensajeInfo("Reserva Guardada");
			return "./reservas.xhtml?faces-redirect=true";
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
		}
		
		return "";
	}
	
	public String irReservar() {
		return "/reservacion.html?faces-redirect=true";
	}
	
	public void buscarHabitacion() {	
		int c = adultos + niños;
		
		if(c == 3) {
			c = 4;
		}
		listaHabitaciones = managerHabitaciones.findHabitacionesPerCapacidad(c);
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
	
	public boolean isActivGuardar() {
		return activGuardar;
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
		List<InvUsuario> listadoUsuarios=listaUsuarios;
		
		for(InvUsuario u:listadoUsuarios){
			SelectItem item=new SelectItem(u.getIdusuario(),u.getNombresusuario()+" "+u.getApellidosusuario());
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
