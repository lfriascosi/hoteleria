package hoteleria.controller;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import hoteleria.model.dto.LoginDTO;
import hoteleria.model.entities.InvRole;
import hoteleria.model.entities.InvRolesusuario;
import hoteleria.model.manager.ManagerSeguridad;
import hoteleria.model.util.ModelUtil;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.codec.digest.DigestUtils;

@Named
@javax.enterprise.context.SessionScoped
public class BeanLogin implements Serializable{
	private static final long serialVersionUID = 1L;
	private String codigoUsuario;
	private String clave;
	private String tipoUsuario;
	private String usuario;
	private String correo;
	private List<InvRolesusuario> listaRoles;
	private List<String> listadoRoles = new ArrayList<String>();
	private String rolSeleccionado;
	private InvRole rol;
	private List<InvRole> nominaRol = new ArrayList<InvRole>();
	private boolean acceso=false;
	@EJB
	private ManagerSeguridad managerSeguridad;
	private LoginDTO loginDTO;

	@PostConstruct
	public void inicializar() {
		loginDTO=new LoginDTO();
	}
	/**
	 * Action que permite el acceso al sistema.
	 * @return
	 */
	public String accederSistema(){
		acceso=false;
		try {
			loginDTO=managerSeguridad.accederSistema(codigoUsuario, clave);
			//verificamos el acceso del usuario:
			tipoUsuario=loginDTO.getTipoUsuario();
			correo = loginDTO.getCorreo(); 
			usuario = loginDTO.getUsuario(); // DigestUtils.sha256Hex(loginDTO.getUsuario());
			listaRoles = loginDTO.getRoles();
			listadoRoles = new ArrayList<String>();
			nominaRol = new ArrayList<InvRole>();
			for(InvRolesusuario rolUsu:listaRoles) {
				// InvRole rol = new InvRole();
				// rol=rolUsu.getInvRole();
				// rol.setNombrerolvista(rolUsu.getInvRole().getNombrerolvista());
				listadoRoles.add(rolUsu.getInvRole().getNombrerolvista());
				
				// nominaRol.add(rol);
		        }
			
			//redireccion dependiendo del tipo de usuario:
			// return loginDTO.getRutaAcceso()+"?faces-redirect=true";
			return "roles?faces-redirect=true";
		} catch (Exception e) {
			e.printStackTrace();
			JSFUtil.crearMensajeERROR(e.getMessage());
		}
		return "";
	}
	
	public InvRole getInvRoleBean(Integer id) {
        if (id == null){
            throw new IllegalArgumentException("no id provided");
        }
        for (InvRole rol : nominaRol ){
            if (id.equals(rol.getIdrol())){
                return rol;
            }
        }
        return null;
    }
	
	public String irLogin() {
		return "/login.xhtml?faces-redirect=true";
	}
	public String irInicio() {
		return "/index.html?faces-redirect=true";
	}
	public String irRegistro() {
		return "/registro.xhtml?faces-redirect=true";
	}
	
	public String ir() {
		try{
			//dependiendo del tipo de usuario, configuramos la ruta de acceso a las pags web:		
			if(this.rolSeleccionado.equals("Recepcionista")) {
				//FacesContext contex = FacesContext.getCurrentInstance();
	            // contex.getExternalContext().redirect("../administrador/usuarios.xhtml" );
				loginDTO.setRutaAcceso("/recepcionista/reservas.xhtml");
			} else if(this.rolSeleccionado.equals("Cliente")) {
				loginDTO.setRutaAcceso("/cliente/index.xhtml");
			} else if(this.rolSeleccionado.equals("Administrador")) {
				loginDTO.setRutaAcceso("/administrador/indexadmin.xhtml");
			} else if(this.rolSeleccionado.equals("Gerente")) {
				loginDTO.setRutaAcceso("/Gerente/index.xhtml");	
			}else {
				FacesContext contex = FacesContext.getCurrentInstance();
	            contex.getExternalContext().redirect("index.html" );
				
			}
			//redireccion dependiendo del tipo de usuario:
			 return loginDTO.getRutaAcceso()+"?faces-redirect=true";
            
		}catch(  Exception e ){
			JSFUtil.crearMensajeERROR( "Seleccione un rol por favor" );
		}
		return "";
	}
	
	/**
	 * Finaliza la sesion web del usuario.
	 * @return
	 */
	public String salirSistema(){
		System.out.println("salirSistema");
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/index.html?faces-redirect=true";
	}
	
	public void actionVerificarLogin(){
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		String requestPath=ec.getRequestPathInfo();
		try {
			//si no paso por login:
			if(loginDTO==null || ModelUtil.isEmpty(loginDTO.getRutaAcceso())){
				ec.redirect(ec.getRequestContextPath() + "/index.html");
			}else{
				//validar las rutas de acceso:
				if(requestPath.contains("/recepcionista") && loginDTO.getRutaAcceso().startsWith("/recepcionista"))
					return;
				if(requestPath.contains("/cliente") && loginDTO.getRutaAcceso().startsWith("/cliente"))
					return;
				if(requestPath.contains("/Gerente") && loginDTO.getRutaAcceso().startsWith("/Gerente"))
					return;
				if(requestPath.contains("/administrador") && loginDTO.getRutaAcceso().startsWith("/administrador"))
					return;
				//caso contrario significa que hizo login pero intenta acceder a ruta no permitida:
				ec.redirect(ec.getRequestContextPath() + "/index.html");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	

	public List<String> getListadoRoles() {
		return listadoRoles;
	}
	public void setListadoRoles(List<String> listadoRoles) {
		this.listadoRoles = listadoRoles;
	}
	
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	
	

	public InvRole getRol() {
		return rol;
	}
	public void setRol(InvRole rol) {
		this.rol = rol;
	}
	public List<InvRole> getNominaRol() {
		return nominaRol;
	}
	public void setNominaRol(List<InvRole> nominaRol) {
		this.nominaRol = nominaRol;
	}
	public String getRolSeleccionado() {
		return rolSeleccionado;
	}
	public void setRolSeleccionado(String rolSeleccionado) {
		this.rolSeleccionado = rolSeleccionado;
	}
	public List<InvRolesusuario> getListaRoles() {
		return listaRoles;
	}
	public void setListaRoles(List<InvRolesusuario> listaRoles) {
		this.listaRoles = listaRoles;
	}
	public String getCodigoUsuario() {
		return codigoUsuario;
	}

	public void setCodigoUsuario(String codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public boolean isAcceso() {
		return acceso;
	}

	public String getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String usuario) {
		this.correo = usuario;
	}
	
}
