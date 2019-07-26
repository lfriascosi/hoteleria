package hoteleria.controller;

import java.io.Serializable;
//import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
//import javax.faces.model.SelectItem;
import javax.inject.Named;

import hoteleria.model.entities.FacDetalle;
//import hoteleria.model.entities.FacReserva;
//import hoteleria.model.entities.InvUsuario;
import hoteleria.model.manager.ManagerReservas;

@Named
@SessionScoped
public class BeanReservas implements Serializable {
	private static final long serialVersionUID = 1L;
//	private String cedulaCliente;
	@EJB
	private ManagerReservas managerReservas;
	private List<FacDetalle> listaFacDetalles;
//	private boolean panelColapso;

//	private Integer codigoHabitacion;
//	private FacReserva facturaCabTmp;
//	private boolean facturaCabTmpGuardada;

	@PostConstruct
	public void inicializar() {
		listaFacDetalles = managerReservas.findAllFacDetalles();
	}

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
//	public void asignarCliente(){
//		if(facturaCabTmpGuardada==true){
//			JSFUtil.crearMensajeWARN("La factura ya fue guardada.");
//		}
//		try {
//			managerReservas.asignarClienteFacturaTmp(facturaCabTmp,cedulaCliente);
//		} catch (Exception e) {
//			JSFUtil.crearMensajeERROR(e.getMessage());
//		}
//	}
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
		} else if (estadoReserva == 2) {
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

}
