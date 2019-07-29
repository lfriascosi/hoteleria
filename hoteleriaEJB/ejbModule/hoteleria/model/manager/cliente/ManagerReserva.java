package hoteleria.model.manager.cliente;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import hoteleria.model.entities.FacReserva;
import hoteleria.model.entities.InvHabitacione;
import hoteleria.model.entities.InvRole;
import hoteleria.model.entities.InvUsuario;
import hoteleria.model.manager.ManagerDAO;
import hoteleria.model.manager.ManagerHabitaciones;
import hoteleria.model.manager.ManagerParametros;
import hoteleria.model.manager.ManagerSeguridad;
import hoteleria.model.entities.FacDetalle;
import hoteleria.model.entities.FacParametro;

/**
 * Session Bean implementation class ManagerReserva
 */
@Stateless
@LocalBean
public class ManagerReserva {
		@PersistenceContext
		private EntityManager em;
    	@EJB
	    private ManagerHabitaciones managerHabitaciones;
    	@EJB
	    private ManagerParametros managerParametros;
    	@EJB
	    private ManagerSeguridad managerSeguridad;
    	@EJB
	    private ManagerDAO managerDAO;

    Calendar calendario = new GregorianCalendar();
    /**
     * Default constructor. 
     */
    public ManagerReserva() {
        // TODO Auto-generated constructor stub
    }

    /**
	 * Crea una nueva cabecera de factura temporal, para que desde el programa
	 * cliente pueda manipularla y llenarle con la informacion respectiva.
	 * Esta informacion solo se mantiene en memoria.
	 * @return la nueva factura temporal.
	 */
	public FacReserva crearFacturaTmp(Date fechaEntrada, Date fechaSalida){
		FacReserva facturaCabTmp=new FacReserva();
		facturaCabTmp.setFechareserva(new Date());
		facturaCabTmp.setHorareserva(getHora());
		facturaCabTmp.setFechaentrada(fechaEntrada);
		facturaCabTmp.setFechasalida(fechaSalida);
		facturaCabTmp.setEstadopago(0);
		
		facturaCabTmp.setFacDetalles(new ArrayList<FacDetalle>());
		return facturaCabTmp;
	}

    private String getHora(){
            int hora, minutos, segundos;
            hora =calendario.get(Calendar.HOUR_OF_DAY);
            minutos = calendario.get(Calendar.MINUTE);
            segundos = calendario.get(Calendar.SECOND);
            return (hora + ":" + minutos + ":" + segundos);
    }

    /**
	 * Adiciona un item detalle a una factura temporal. Estos valores permanecen
	 * en memoria. 
	 * @param codigoProducto codigo del producto.
	 * @param cantidad cantidad del producto.
	 * @throws Exception problemas ocurridos al momento de insertar el item detalle.
	 */
	public void agregarDetalleFacturaTmp(FacReserva facturaCabTmp,Integer idHabitacion,Date fechaI, Date fechaS) throws Exception{
		InvHabitacione h;
		FacDetalle fd;	
		
		if(facturaCabTmp==null)
			throw new Exception("Primero debe crear una nueva factura.");
		if(idHabitacion==null||idHabitacion.intValue()<0)
			throw new Exception("Debe especificar el codigo de la habitación.");
		if(fechaI==null||idHabitacion.intValue()<0)
			throw new Exception("Debe especificar la fecha de ingreso.");
		if(fechaS==null||idHabitacion.intValue()<0)
			throw new Exception("Error debe especificar la fecha de salida.");
		if(validarFechas(fechaI,fechaS) == false || idHabitacion.intValue()<0)
			throw new Exception("Ingreso incorrecto de fechas");
		System.out.println("FECHA CAPTURADA:"+fechaI+" - " + fechaS);
		facturaCabTmp.setFechaentrada(fechaI);
		facturaCabTmp.setFechasalida(fechaS);
		//buscamos la habitación:
		h=managerHabitaciones.findTheHabitacionById(idHabitacion);
		//creamos un nuevo detalle y llenamos sus propiedades:
		fd=new FacDetalle();
		fd.setPrecioUnit(h.getPrecio());
		fd.setInvHabitacione(h);
		fd.setFechauso(new Date());
		facturaCabTmp.getFacDetalles().add(fd);
		System.out.println("AQUI SE CREARON LOS DETALLES" + fd.getDiasestadia());
		for (FacDetalle r : facturaCabTmp.getFacDetalles()) {
  	      System.out.println("TIPO HAB: "+r.getInvHabitacione().getInvTiposhabitacione().getNombretipohabitacion());
  	  }
		//verificamos los campos calculados:
		calcularFacturaTmp(facturaCabTmp);
	}

	 public boolean validarFechas(Date fechaEntrada, Date fechaSalida){
			Date fa = new Date();
			System.out.println("Fecha entrada: "+fechaEntrada+ " Fecha salida: "+fechaSalida);
			if(fechaEntrada== null || fechaSalida==null) {
				System.out.println("Obligatorio ingresar fechas");
				return false;
			}else {
				if(fechaEntrada.equals(fa) && fechaSalida.after(fechaEntrada)) {
					return true;
				}
				if(fechaEntrada.before(fechaSalida) && (fechaEntrada.equals(fa) || fechaEntrada.after(fa))) 
					return true;
				else 
					return false;
			}
			
		} 
	/**
	 * Realiza los calculos de subtotales, impuestos y totales.
	 * @param facturaCabTmp Factura temporal creada en memoria.
	 * @throws Exception 
	 */
	private void calcularFacturaTmp(FacReserva facturaCabTmp) throws Exception{
		double sumaSubtotales;
		double porcentajeIVA,valorIVA,totalFactura;
		//verificamos los campos calculados:
		sumaSubtotales=0;
		for(FacDetalle det:facturaCabTmp.getFacDetalles()){
			sumaSubtotales+= det.getPrecioUnit();
		}
		
		porcentajeIVA=getPorcentajeIVA();
		valorIVA=sumaSubtotales*porcentajeIVA/100;
		totalFactura=sumaSubtotales+valorIVA;
		

		facturaCabTmp.setSubtotal(new BigDecimal(sumaSubtotales));
		facturaCabTmp.setValorIva(new BigDecimal(valorIVA));
		facturaCabTmp.setTotal(new BigDecimal(totalFactura)); 
		facturaCabTmp.setFacParametro(getPorcentajeDesc());
		
	}

	/**
  	 * Obtiene el valor actual para el porcentaje de impuesto IVA.
  	 * @return valor del IVA
	 * @throws Exception 
  	 */
  	public double getPorcentajeIVA() throws Exception{
  		return managerParametros.getValorParametroDouble("valor_iva");
  	}
  	
  	public FacParametro getPorcentajeDesc() throws Exception{
  		return managerParametros.findParametroName("valor_desc");
  	}
  	
  	/**
	 * Guarda en la base de datos una factura.
	 * @param IdUsuario Codigo del usuario que genera la factura.
	 * @param facturaCabTmp factura temporal creada en memoria.
	 * @throws Exception problemas ocurridos en la insercion.
	 */
	public void guardarFacturaTemporal(Integer IdUsuario,FacReserva facturaCabTmp) throws Exception{
		
		if(facturaCabTmp==null)
			throw new Exception("Primero habilite la reservación.");
		if(facturaCabTmp.getFacDetalles()==null || facturaCabTmp.getFacDetalles().size()==0)
			throw new Exception("Debe seleccionar habitaciones");
		//obtenemos el numero de la nueva factura:
		
		//asignacion del usuario que crea la factura
		//obtenemos el numero de la nueva factura:
		InvUsuario usuario=managerSeguridad.findUsuarioPerId(IdUsuario);
		//obtenemos el numero de la nueva factura:
		facturaCabTmp.setInvUsuario(usuario);
		
		facturaCabTmp.setFechareserva(new Date());

		//obtenemos el numero de la nueva factura:
		//obtenemos el numero de la nueva factura:
		int contFacturas;
		contFacturas=getContadorFacturas();
		contFacturas++;
		facturaCabTmp.setCodigoreserva(Integer.toString(contFacturas));
		
		//verificamos los campos calculados:
		calcularFacturaTmp(facturaCabTmp);
		
		
		for(FacDetalle det:facturaCabTmp.getFacDetalles()){
			System.out.println("Detalle Reserva: "+det.getInvHabitacione().getDescripcion());
			det.setFacReserva(facturaCabTmp);
			// managerDAO.insertar(det);
		}
		
		System.out.println("----------------------------------------------------------------");
		System.out.println("ANTES DE INSERTAR");
		
		//guardamos la factura completa en la bdd:
		managerDAO.insertar(facturaCabTmp);
		System.out.println("LUEGO DE INSERTAR");
		System.out.println("----------------------------------------------------------------");
		//actualizamos los parametros contadores de facturas:
		actualizarContFacturas(contFacturas);
		
		facturaCabTmp=null;		
		
	}
	
	/**
	 * Retorna el valor actual del contador de facturas. 
	 * Este contador es un parametro del sistema.
	 * @return ultimo valor del contador de facturas
	 * @throws Exception
	 */
	private int getContadorFacturas() throws Exception{
		return managerParametros.getValorParametroInteger("cont_reservas");
	}

		/**
	 * Actualiza el valor del contador de facturas.
	 * @param nuevoContadorFacturas nuevo valor del contador.
	 * @throws Exception
	 */
	private void actualizarContFacturas(int nuevoContadorFacturas) throws Exception{
		managerParametros.actualizarParametro("cont_reservas", nuevoContadorFacturas);
	}

	public List<FacReserva> findMisReservas(Integer IdUsuario) {
		String consulta = "select d from FacReserva d where idusuario='"+IdUsuario+"'";
		Query q = em.createQuery(consulta, FacReserva.class);
		return q.getResultList();
	}
	
	public List<FacDetalle> findDetallesReservas(Integer IdReserva) {
		String consulta = "select d from FacDetalle d where idreserva='"+IdReserva+"'";
		Query q = em.createQuery(consulta, FacDetalle.class);
		return q.getResultList();
	}
}
