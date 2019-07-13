package cliente.controller;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import hoteleria.model.manager.ManagerHabitaciones;
import hoteleria.model.manager.ManagerReservas;

@Named
@SessionScoped
public class BeanFactura implements Serializable{
	private static final long serialVersionUID = 1L;
	@EJB
	private ManagerHabitaciones managerHabitaciones;
	
}
