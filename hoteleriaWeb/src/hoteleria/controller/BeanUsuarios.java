package hoteleria.controller;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.SelectItem;
import javax.inject.Named;

import habitaciones.controller.JSFUtil;
import hoteleria.model.entities.InvRole;
import hoteleria.model.entities.InvRolesusuario;
import hoteleria.model.entities.InvUsuario;
import hoteleria.model.manager.ManagerUsuarios;

import java.io.Serializable;
import java.util.ArrayList;
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
	private InvRolesusuario usuarioSeleccionada;
	private int idrol;
	private List<InvRole> listaRoles;
	private List<InvRolesusuario> listaRolesUsuario;

	@PostConstruct
	public void inicializar() {
		listaInvUsuarios = managerUsuarios.findAllInvUsuarios();
		listaRoles = managerUsuarios.findAllInvRole();
		listaRolesUsuario = managerUsuarios.findAllInvRolesUsuario();
		usuario = new InvUsuario();
		panelColapsado = true;
	}

	public void actionListenerCOlapsarPanel() {
		panelColapsado = !panelColapsado;
	}

	public void actionListenerInsertarUsuario() {
		try {
			managerUsuarios.insertarInvUsuario(usuario, idrol);
			listaRolesUsuario = managerUsuarios.findAllInvRolesUsuario();
			
			usuario = new InvUsuario();

			JSFUtil.crearMensajeInfo("Datos de usuario insertados.");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}

	public void actionListenerInsertarRolUsuario() {
		try {
			managerUsuarios.insertarInvRolUsuario(usuarioSeleccionada, idrol);
			listaRolesUsuario = managerUsuarios.findAllInvRolesUsuario();
//			List<SelectItem> listadoSI = new ArrayList<SelectItem>();
			
			JSFUtil.crearMensajeInfo("Rol agregado.");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}

	public void actionListenerELiminarUsuario(Integer idusuario) {
		managerUsuarios.EliminarInvUsuario(idusuario);
		listaRolesUsuario = managerUsuarios.findAllInvRolesUsuario();
		JSFUtil.crearMensajeInfo("Usuario eliminado");
	}

	public void actionListenerSelecionarUsuarios(InvRolesusuario usuario) {
		usuarioSeleccionada = usuario;
	}

	public void actionListenerActualizarUsuario() {
		try {
			managerUsuarios.actualizarInvRolesusuario(usuarioSeleccionada, idrol);
			listaRolesUsuario = managerUsuarios.findAllInvRolesUsuario();
			JSFUtil.crearMensajeInfo("Datos actualizados");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}

	public List<SelectItem> getRoles() {
		List<SelectItem> listadoSI = new ArrayList<SelectItem>();
		List<InvRole> listadoRoles = managerUsuarios.findAllInvRole();

		for (InvRole th : listadoRoles) {
			SelectItem item = new SelectItem(th.getIdrol(), th.getNombrerolvista());
			// System.out.println("HABITACIOOON: "+h.getIdhabitacion()+" Descripcioon:
			// "+h.getDescripcion());
			listadoSI.add(item);
		}
		return listadoSI;
	}

//	public List<SelectItem>  getNombreRolUsuario() {
//		List<SelectItem> listadoSI = new ArrayList<SelectItem>();
//		List<InvRolesusuario> listaRol = managerUsuarios.findAllInvRolesUsuario();
//		for (InvRolesusuario ru : listaRol) {
//			SelectItem item = new SelectItem(ru.getIdrolusuario(),ru.getid);
//			// System.out.println("HABITACIOOON: "+h.getIdhabitacion()+" Descripcioon:
//			// "+h.getDescripcion());
//			listadoSI.add(item);
//		}
//		return listadoSI;
//	}

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

	public InvRolesusuario getUsuarioSeleccionada() {
		return usuarioSeleccionada;
	}

	public void setUsuarioSeleccionada(InvRolesusuario usuarioSeleccionada) {
		this.usuarioSeleccionada = usuarioSeleccionada;
	}

	public List<InvRole> getListaRoles() {
		return listaRoles;
	}

	public void setListaRoles(List<InvRole> listaRoles) {
		this.listaRoles = listaRoles;
	}

	public Integer getIdrol() {
		return idrol;
	}

	public void setIdrol(Integer idrol) {
		this.idrol = idrol;
	}

	public List<InvRolesusuario> getListaRolesUsuario() {
		return listaRolesUsuario;
	}

	public void setListaRolesUsuario(List<InvRolesusuario> listaRolesUsuario) {
		this.listaRolesUsuario = listaRolesUsuario;
	}

}
