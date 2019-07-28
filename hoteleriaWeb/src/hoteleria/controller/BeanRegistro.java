package hoteleria.controller;

import java.io.Serializable;
import java.util.Date;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.codec.digest.DigestUtils;
import org.wildfly.security.mechanism.digest.DigestUtil;

import habitaciones.controller.JSFUtil;
import hoteleria.model.entities.InvUsuario;
import hoteleria.model.manager.ManagerSeguridad;
import hoteleria.model.manager.ManagerUsuarios;

@Named
@SessionScoped
public class BeanRegistro implements Serializable{
	private static final long serialVersionUID = 1L;
	@EJB
	private ManagerSeguridad managerSeguridad;
	private String nombres;
	private String apellidos;
	private String domicilio;
	private String telefono;
	private String correo;
	private String clave;
	private InvUsuario usuario = new InvUsuario(); 
	private boolean statusForm = false;
	@Inject
	private BeanLogin beanLogin;
	
	public String encrypt(String clave) {
		String cryptd = DigestUtils.sha1Hex(clave);
		return cryptd;
	}
	public String registrarUsuario() {
		try {
			usuario.setNombresusuario(nombres);
			usuario.setApellidosusuario(apellidos);
			usuario.setDireccion(domicilio);
			usuario.setTelefono(telefono);
			usuario.setCorreo(correo);
			usuario.setClave(encrypt(clave));
			usuario.setEstado(1);
			usuario.setFechacreacion(new Date());
			usuario.setFechaactualizacion(new Date());
			usuario = managerSeguridad.registrarUsuario(usuario);
			if(usuario==null) {
			//	throw new Exception("");
				JSFUtil.crearMensajeError("No se encuentran llenos todos los datos");
			}
			else {
						// JSFUtil.crearMensajeInfo("Usuario registrado ahora Inicie la Sesión.");
						nombres="";
						apellidos="";
						domicilio="";
						telefono="";
						correo="";
						clave="";
						beanLogin.setMsj("Usuario Registrado");
						return "/login.xhtml?faces-redirect=true";
				}
			
			
		} catch (Exception e) {
			JSFUtil.crearMensajeError("Error al registrar, verifique el formato de los valores");
			e.printStackTrace();
		}
		return "";
	}
	
	public void validatePass(String p){
		try {
			if (((String)p).length()<8) {
		    	 // throw new Exception("No se encuentran llenos todos los datos");
		    	  // JSFUtil.crearMensajeError("La contraseña debe tener al menos 8 caracteres ");
		      }else {
		    	  statusForm = true;
		      }
		}catch (Exception e) {
			      JSFUtil.crearMensajeError("La contraseña debe tener minimo 8 digitos");
			//e.printStackTrace();
		}
	}
	
	
	
	public boolean isStatusForm() {
		return statusForm;
	}
	public void setStatusForm(boolean statusForm) {
		this.statusForm = statusForm;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getDomicilio() {
		return domicilio;
	}
	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	
	
}
