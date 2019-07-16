package hoteleria.controller;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import habitaciones.controller.JSFUtil;
import hoteleria.model.entities.InvTiposhabitacione;
import hoteleria.model.entities.InvUsuario;
import hoteleria.model.manager.ManagerUsuarios;

import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class BeanUsuarios implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private ManagerUsuarios managerUsuarios;
	private List<InvUsuario> listaInvUsuarios;
	private InvUsuario usuario;
	private boolean panelColapsado;
	private InvUsuario usuarioSeleccionada;

	@PostConstruct
	public void inicializar() {
		listaInvUsuarios = managerUsuarios.findAllInvUsuarios();
		usuario = new InvUsuario();
		panelColapsado = true;
	}

	public void actionListenerCOlapsarPanel() {
		panelColapsado = !panelColapsado;
	}
	public void actionListenerInsertarUsuario() {
		try {
			managerUsuarios.insertarInvUsuario(usuario);
			listaInvUsuarios = managerUsuarios.findAllInvUsuarios();
			usuario = new InvUsuario();
			JSFUtil.crearMensajeInfo("Datos de usuario insertados.");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void actionListenerELiminarUsuario(Integer idusuario) {
		managerUsuarios.EliminarInvUsuario(idusuario);
		listaInvUsuarios = managerUsuarios.findAllInvUsuarios();
		JSFUtil.crearMensajeInfo("Usuario eliminado");
	}
	
	public void actionListenerSelecionarUsuarios(InvUsuario usuario) {
		usuarioSeleccionada = usuario;
	}
	
	public void actionListenerActualizarUsuario() {
		try {
			managerUsuarios.actualizarInvUsuarios(usuarioSeleccionada);
			listaInvUsuarios = managerUsuarios.findAllInvUsuarios();
			JSFUtil.crearMensajeInfo("Datos actualizados");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}

	public List<InvUsuario> getListaInvUsuarios() {
		return listaInvUsuarios;
	}

	public void setListaInvUsuarios(List<InvUsuario> listaInvUsuarios) {
		this.listaInvUsuarios = listaInvUsuarios;
	}

	public InvUsuario getUsuario() {
		return usuario;
	}

	public void setUsuario(InvUsuario usuario) {
		this.usuario = usuario;
	}

	public boolean isPanelColapsado() {
		return panelColapsado;
	}

	public void setPanelColapsado(boolean panelColapsado) {
		this.panelColapsado = panelColapsado;
	}

	public InvUsuario getUsuarioSeleccionada() {
		return usuarioSeleccionada;
	}

	public void setUsuarioSeleccionada(InvUsuario usuarioSeleccionada) {
		this.usuarioSeleccionada = usuarioSeleccionada;
	}

}
