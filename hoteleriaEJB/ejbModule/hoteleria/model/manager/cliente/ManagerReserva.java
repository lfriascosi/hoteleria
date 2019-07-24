package hoteleria.model.manager.cliente;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import hoteleria.model.entities.FacReserva;
import hoteleria.model.entities.InvHabitacione;
import hoteleria.model.manager.ManagerHabitaciones;
import hoteleria.model.entities.FacDetalle;

/**
 * Session Bean implementation class ManagerReserva
 */
@Stateless
@LocalBean
public class ManagerReserva {
    	@EJB
	    private ManagerHabitaciones managerHabitaciones;

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
	public FacReserva crearFacturaTmp(){
		FacReserva facturaCabTmp=new FacReserva();
		facturaCabTmp.setFechareserva(new Date());
		facturaCabTmp.setHorareserva(getHora());
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
	public void agregarDetalleFacturaTmp(FacReserva facturaCabTmp,Integer idHabitacion,Integer dias, Date fecha) throws Exception{
		InvHabitacione h;
		FacDetalle fd;	
		
		if(facturaCabTmp==null)
			throw new Exception("Error primero debe crear una nueva factura.");
		if(idHabitacion==null||idHabitacion.intValue()<0)
			throw new Exception("Error debe especificar el codigo de la habitación.");
		if(dias==null)
			throw new Exception("Error primero debe indicar los días de estadía.");
		if(fecha==null||idHabitacion.intValue()<0)
			throw new Exception("Error debe especificar la fecha de estadía.");
	
		
		//buscamos la habitación:
		h=managerHabitaciones.findTheHabitacionById(idHabitacion);
		//creamos un nuevo detalle y llenamos sus propiedades:
		fd=new FacDetalle();
		fd.setPrecioUnit(h.getPrecio());
		fd.setInvHabitacione(h);
		fd.setDiasestadia(dias);
		fd.setFechauso(fecha);
		facturaCabTmp.getFacDetalles().add(fd);
		
		//verificamos los campos calculados:
		calcularFacturaTmp(facturaCabTmp);
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
		
	/*	porcentajeIVA=getPorcentajeIVA();
		valorIVA=sumaSubtotales*porcentajeIVA/100;
		totalFactura=sumaSubtotales+valorIVA;
		
		facturaCabTmp.setSubtotal(new BigDecimal(sumaSubtotales));
		facturaCabTmp.setValorIva(new BigDecimal(valorIVA));
		facturaCabTmp.setBaseCero(new BigDecimal(0));//no calculamos la base cero en este ejemplo.
		facturaCabTmp.setTotal(new BigDecimal(totalFactura)); */
	}

}
