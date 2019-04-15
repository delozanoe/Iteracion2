package uniandes.isis2304.parranderos.negocio;

import java.sql.Date;
import java.util.ArrayList;

public class Convencion
{
	private Integer id;

	private String tematica;

	private Integer numeroParticipantes;

	private Date fechaInicio;

	private Date fechaFin;

	private double cuenta;

	private boolean pazYSalvo;

	private String estado;

	private ArrayList<Hotel> hotelesConvencion;

	private ArrayList<ReservaHabitacion> reservasHabitaciones;

	private ArrayList<Cliente> clientes;

	private PlanConsumo planConsumo;

	private ArrayList<ReservaServicio> reservasServicio;

	public Convencion(Integer id, String tematica, Integer numeroParticipantes, Date fechaInicio, Date fechaFin,
			double cuenta, boolean pazYSalvo, String estado, ArrayList<Hotel> hotelesConvencion,
			ArrayList<ReservaHabitacion> reservasHabitaciones, ArrayList<Cliente> clientes, PlanConsumo planConsumo,
			ArrayList<ReservaServicio> reservasServicio) {
		super();
		this.id = id;
		this.tematica = tematica;
		this.numeroParticipantes = numeroParticipantes;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.cuenta = cuenta;
		this.pazYSalvo = pazYSalvo;
		this.estado = estado;
		this.hotelesConvencion = hotelesConvencion;
		this.reservasHabitaciones = reservasHabitaciones;
		this.clientes = clientes;
		this.planConsumo = planConsumo;
		this.reservasServicio = reservasServicio;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTematica() {
		return tematica;
	}

	public void setTematica(String tematica) {
		this.tematica = tematica;
	}

	public Integer getNumeroParticipantes() {
		return numeroParticipantes;
	}

	public void setNumeroParticipantes(Integer numeroParticipantes) {
		this.numeroParticipantes = numeroParticipantes;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public double getCuenta() {
		return cuenta;
	}

	public void setCuenta(double cuenta) {
		this.cuenta = cuenta;
	}

	public boolean isPazYSalvo() {
		return pazYSalvo;
	}

	public void setPazYSalvo(boolean pazYSalvo) {
		this.pazYSalvo = pazYSalvo;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public ArrayList<Hotel> getHotelesConvencion() {
		return hotelesConvencion;
	}

	public void setHotelesConvencion(ArrayList<Hotel> hotelesConvencion) {
		this.hotelesConvencion = hotelesConvencion;
	}

	public ArrayList<ReservaHabitacion> getReservasHabitaciones() {
		return reservasHabitaciones;
	}

	public void setReservasHabitaciones(ArrayList<ReservaHabitacion> reservasHabitaciones) {
		this.reservasHabitaciones = reservasHabitaciones;
	}

	public ArrayList<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(ArrayList<Cliente> clientes) {
		this.clientes = clientes;
	}

	public PlanConsumo getPlanConsumo() {
		return planConsumo;
	}

	public void setPlanConsumo(PlanConsumo planConsumo) {
		this.planConsumo = planConsumo;
	}

	public ArrayList<ReservaServicio> getReservasServicio() {
		return reservasServicio;
	}

	public void setReservasServicio(ArrayList<ReservaServicio> reservasServicio) {
		this.reservasServicio = reservasServicio;
	}





}
