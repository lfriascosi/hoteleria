package hoteleria.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import habitaciones.controller.JSFUtil;
import hoteleria.model.entities.InvObjeto;
import hoteleria.model.manager.ManagerAdministrador;

@Named
@RequestScoped
public class BeanObjeto implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<InvObjeto> listaObjetos;
	@EJB
	private ManagerAdministrador managerAdministrador;

	private InvObjeto objeto;
	private InvObjeto ObjetoSeleccionada;
	private boolean panelColapsado;

	@PostConstruct
	public void inicializar() {
		listaObjetos = managerAdministrador.findAllObjetos();
		objeto = new InvObjeto();
		panelColapsado = true;
	}

	public void actionListenerCOlapsarPanel2() {
		panelColapsado = !panelColapsado;
	}

	public void actionListenerInsertarObjeto() {
		try {
			managerAdministrador.insertarObjeto(objeto);
			listaObjetos = managerAdministrador.findAllObjetos();
			objeto = new InvObjeto();
			JSFUtil.crearMensajeInfo("Datos de objeto insertados.");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}

	public void actionListenerELiminarObjeto(Integer idobjeto) {
		managerAdministrador.eliminarObjeto(idobjeto);
		listaObjetos = managerAdministrador.findAllObjetos();
		JSFUtil.crearMensajeInfo("Objeto eliminado");
	}

	public void actionListenerSelecionarObjeto(InvObjeto objeto) {
		ObjetoSeleccionada = objeto;
	}

	public void actionListenerActualizarObjeto() {
		try {
			managerAdministrador.actualizarObjeto(ObjetoSeleccionada);
			listaObjetos = managerAdministrador.findAllObjetos();
			JSFUtil.crearMensajeInfo("Datos actualizados");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}

	public List<InvObjeto> getListaObjetos() {
		return listaObjetos;
	}

	public void setListaObjetos(List<InvObjeto> listaObjetos) {
		this.listaObjetos = listaObjetos;
	}

	public InvObjeto getObjeto() {
		return objeto;
	}

	public void setObjeto(InvObjeto objeto) {
		this.objeto = objeto;
	}

	public InvObjeto getObjetoSeleccionada() {
		return ObjetoSeleccionada;
	}

	public void setObjetoSeleccionada(InvObjeto objetoSeleccionada) {
		ObjetoSeleccionada = objetoSeleccionada;
	}

	public boolean isPanelColapsado() {
		return panelColapsado;
	}

	public void setPanelColapsado(boolean panelColapsado) {
		this.panelColapsado = panelColapsado;
	}


}
