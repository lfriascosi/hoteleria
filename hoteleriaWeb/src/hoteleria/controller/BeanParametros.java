package hoteleria.controller;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import habitaciones.controller.JSFUtil;
import hoteleria.model.entities.FacParametro;
import hoteleria.model.manager.ManagerParametros;

import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class BeanParametros implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private ManagerParametros managerParametros;
	private List<FacParametro> listaParametros;
	private FacParametro parametro;
	private boolean panelColapsado;
	
	@PostConstruct
	public void inicializar() {
		listaParametros = managerParametros.findAllFacParametro();
		parametro = new FacParametro();
		panelColapsado = true;
	}
	
	public void actionListenerCOlapsarPanel() {
		panelColapsado = !panelColapsado;
	}
	
	public void actionListenerInsertarParametros() {
		try {
			managerParametros.insertarFacParametro(parametro);
			listaParametros = managerParametros.findAllFacParametro();
			parametro = new FacParametro();
			JSFUtil.crearMensajeInfo("Datos de parametros insertados.");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void actionListenerELiminarParametro(Integer idparametro) {
		managerParametros.EliminarFacParametro(idparametro);
		listaParametros = managerParametros.findAllFacParametro();
		JSFUtil.crearMensajeInfo("Parametro eliminado");
	}

	public List<FacParametro> getListaParametros() {
		return listaParametros;
	}

	public void setListaParametros(List<FacParametro> listaParametros) {
		this.listaParametros = listaParametros;
	}

	public FacParametro getParametro() {
		return parametro;
	}

	public void setParametro(FacParametro parametro) {
		this.parametro = parametro;
	}

	public boolean isPanelColapsado() {
		return panelColapsado;
	}

	public void setPanelColapsado(boolean panelColapsado) {
		this.panelColapsado = panelColapsado;
	}
	
	

}
