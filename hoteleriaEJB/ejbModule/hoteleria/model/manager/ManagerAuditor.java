package hoteleria.model.manager;

import java.net.Inet4Address;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import hoteleria.model.entities.Bitacora;
import hoteleria.model.entities.InvUsuario;


/**
 * Session Bean implementation class ManagerAuditor
 */
@Stateless
@LocalBean
public class ManagerAuditor {
	@PersistenceContext
	private EntityManager em;
	
	Calendar calendario = new GregorianCalendar();
    public ManagerAuditor() {
        // TODO Auto-generated constructor stub
    }

    public List<Bitacora> findAllBitacoras() {
		String consulta2 = "select d from Bitacora d";
		Query q = em.createQuery(consulta2, Bitacora.class);
		return q.getResultList();
	}
    public InvUsuario findInvUsuarioById() {
    	int id=19;
		return em.find(InvUsuario.class, id);
	}
    
    @SuppressWarnings("rawtypes")
	public void mostrarLog(Class clase, String nombreMetodo, String mensaje) {
		SimpleDateFormat format=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		System.out.println(format.format(new Date())+ " [" + clase.getSimpleName() + "/" + nombreMetodo + "]: " + mensaje);
	}
    
    public void insertar(Object pObjeto) throws Exception {		
		if (pObjeto == null)
			throw new Exception("No se puede insertar un objeto null.");
		try {
			em.persist(pObjeto);
			mostrarLog(this.getClass(),"insertar", "Objeto insertado: " + pObjeto.getClass().getSimpleName() + " : " + pObjeto);
		} catch (Exception e) {
			mostrarLog(this.getClass(),"insertar",
					"No se pudo insertar el objeto especificado: " + pObjeto.getClass().getSimpleName() + " : " + pObjeto);
			throw new Exception("No se pudo insertar el objeto especificado: " + e.getMessage());
		}
	}
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public Object findById(Class clase, Object pID) throws Exception {
		mostrarLog(this.getClass(),"findById", clase.getSimpleName() + " : " + pID);
		if (pID == null)
			throw new Exception("Debe especificar el codigo para buscar el dato.");
		Object o;
		try {
			o = em.find(clase, pID);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error al buscar la informacion especificada (" + pID + ") : " + e.getMessage());
		}
		return o;
	}
    private String getHora(){
        int hora, minutos, segundos;
        hora =calendario.get(Calendar.HOUR_OF_DAY);
        minutos = calendario.get(Calendar.MINUTE);
        segundos = calendario.get(Calendar.SECOND);
        return (hora + ":" + minutos + ":" + segundos);
}
    public void crearEvento(Integer codigoUsuario,Class clase,String metodo,String descripcion) throws Exception{
		Bitacora evento=new Bitacora();
		//cambio para probar git
		
		if(codigoUsuario==null)
			throw new Exception("Error auditoria: debe indicar el idusuario del usuario.");
		if(metodo==null||metodo.length()==0)
			throw new Exception("Error auditoria: debe indicar el metodo que genera el evento.");

		InvUsuario usuario=(InvUsuario)findById(InvUsuario.class, codigoUsuario);
		if(usuario==null)
			throw new Exception("Error auditoria: no existe el usuario indicado.");
		
		evento.setInvUsuario(usuario);
		evento.setMetodo(clase.getSimpleName()+"/"+metodo);
		evento.setDescripcionevento(descripcion);
		evento.setFechaevento(new Date());
		evento.setHoraevento(getHora());
		evento.setIpusuario(Inet4Address.getLocalHost().getHostAddress());
		
		insertar(evento);
	}
}
