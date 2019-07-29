package hoteleria.controller;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import hoteleria.model.dto.LoginDTO;
import hoteleria.model.entities.InvRole;
import hoteleria.model.entities.InvRolesusuario;
import hoteleria.model.manager.ManagerAuditor;
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
	private int idUsuario;
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
	private String msj = "";
	@EJB
	private ManagerSeguridad managerSeguridad;
	private LoginDTO loginDTO;

	@EJB
	private ManagerAuditor managerAuditor;
	@PostConstruct
	public void inicializar() {
		loginDTO=new LoginDTO();
	}
	/**
	 * Action que permite el acceso al sistema.
	 * @return
	 */
	
	public String encrypt(String clave) {
		String cryptd = DigestUtils.sha1Hex(clave);
		return cryptd;
	}
	
	
	
	
	public String getMsj() {
		return msj;
	}
	public void setMsj(String msj) {
		this.msj = msj;
	}
	public String accederSistema(){
		acceso=false;
		try {
			loginDTO=managerSeguridad.accederSistema(codigoUsuario, encrypt(clave));
			//verificamos el acceso del usuario:
			idUsuario = loginDTO.getIdUsuario();
			tipoUsuario=loginDTO.getTipoUsuario();
			correo = loginDTO.getCorreo(); 
			usuario = loginDTO.getUsuario(); // DigestUtils.sha256Hex(loginDTO.getUsuario());
			listaRoles = loginDTO.getRoles();
			listadoRoles = new ArrayList<String>();
			nominaRol = new ArrayList<InvRole>();
			System.out.println("CANT ROLES: "+listaRoles.size());
			for(InvRolesusuario rolUsu:listaRoles) {
				// InvRole rol = new InvRole();
				// rol=rolUsu.getInvRole();
				// rol.setNombrerolvista(rolUsu.getInvRole().getNombrerolvista());
				listadoRoles.add(rolUsu.getInvRole().getNombrerolvista());
				
				// nominaRol.add(rol);
		        }
			
			//redireccion dependiendo del tipo de usuario:
			// return loginDTO.getRutaAcceso()+"?faces-redirect=true";

			managerAuditor.crearEvento(idUsuario, this.getClass(), "accederSistema", "Acceso a login");
			if(listadoRoles.size()>1) {
				System.out.println("ROL Q TIENE:"+listadoRoles.get(0));
				return "roles?faces-redirect=true";
			}else {
				System.out.println("ROL Q TIENE: "+listadoRoles.get(0));
				this.rolSeleccionado = listadoRoles.get(0);
				return ir();
			}
			
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
	
	
	
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
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
		System.out.println("  -- IR  -- ");
		try{
			//dependiendo del tipo de usuario, configuramos la ruta de acceso a las pags web:		
			if(this.rolSeleccionado.equals("Recepcionista")) {
				//FacesContext contex = FacesContext.getCurrentInstance();
	            // contex.getExternalContext().redirect("../administrador/usuarios.xhtml" );
				loginDTO.setRutaAcceso("/recepcionista/inicio.xhtml");
			} else if(this.rolSeleccionado.equals("Cliente")) {
				loginDTO.setRutaAcceso("/cliente/inicio.xhtml");
			} else if(this.rolSeleccionado.equals("Administrador")) {
				loginDTO.setRutaAcceso("/administrador/indexadmin.xhtml");
			} else if(this.rolSeleccionado.equals("Gerente")) {
				loginDTO.setRutaAcceso("/Gerente/index.xhtml");
			} else if(this.rolSeleccionado.equals("Auditor")) {
				loginDTO.setRutaAcceso("/auditor/index.xhtml");	
			}else {
				FacesContext contex = FacesContext.getCurrentInstance();
	            contex.getExternalContext().redirect("index.html" );
				
			}
			//redireccion dependiendo del tipo de usuario:
			System.out.println("ACa vamoos:"+loginDTO.getRutaAcceso());
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
		System.out.println("SALE Y USUARIO: "+codigoUsuario);
		return "/index.html?faces-redirect=true";
	}

	public void showMessages() {
		System.out.println("SALE Y USUARIO: "+codigoUsuario);
		System.out.println("ELMENSAJE:"+msj);
		System.out.println("Ad12345678:" +encrypt("Ad12345678"));
		System.out.println("Ge12345678:" + encrypt("Ge12345678"));
		System.out.println("Re12345678:" + encrypt("Re12345678"));
		System.out.println("Au12345678:" + encrypt("Au12345678"));
		if(msj != "") {
			JSFUtil.crearMensaje(FacesMessage.SEVERITY_INFO,msj,null);
			msj="";
		}
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
				if(requestPath.contains("/recepcionista") && loginDTO.getRutaAcceso().startsWith("/recepcionista")) {
					showMessages();
					return;}
				if(requestPath.contains("/cliente") && loginDTO.getRutaAcceso().startsWith("/cliente")) {
					showMessages();
					return;}
				if(requestPath.contains("/Gerente") && loginDTO.getRutaAcceso().startsWith("/Gerente")) {
					showMessages();
					return;}
				if(requestPath.contains("/administrador") && loginDTO.getRutaAcceso().startsWith("/administrador")) {
					showMessages();
					return;}else {
				//caso contrario significa que hizo login pero intenta acceder a ruta no permitida:
				ec.redirect(ec.getRequestContextPath() + "/index.html");
					}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void actionVerificarLogin_ForLogin(){
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		String requestPath=ec.getRequestPathInfo();
		try {
			//si ya pasó por login:
			if(codigoUsuario == null || ModelUtil.isEmpty(loginDTO.getRutaAcceso())){
				showMessages();
				return;
				
			}else{
				ec.redirect(ec.getRequestContextPath() + "/faces" + loginDTO.getRutaAcceso()+"?faces-redirect=true");
				// return;
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
