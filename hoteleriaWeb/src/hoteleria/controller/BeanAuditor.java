package hoteleria.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import hoteleria.model.entities.Bitacora;
import hoteleria.model.manager.ManagerAuditor;

@Named
@SessionScoped
public class BeanAuditor implements Serializable {
	private static final long serialVersionUID = 1L;
	@EJB
	private ManagerAuditor managerAuditor;
	private List<Bitacora> listaBitacoras;

	@PostConstruct
	public void inicializar() {
		listaBitacoras = managerAuditor.findAllBitacoras();
	}

	public List<Bitacora> getListaBitacoras() {
		return listaBitacoras;
	}

	public void setListaBitacoras(List<Bitacora> listaBitacoras) {
		this.listaBitacoras = listaBitacoras;
	}

}
