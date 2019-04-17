package uniandes.isis2304.parranderos.negocio;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;
import java.sql.Timestamp;


public class Convencion
{
	private long id;

	private String tematica;

	private long numeroParticipantes;

	private Timestamp fechaInicio;

	private Timestamp fechaFin;

	private BigDecimal cuenta;

	private String pazYSalvo;

	private String estado;

	private List<Hotel> hotelesConvencion;

	private List<ReservaHabitacion> reservasHabitaciones;

	private List<Cliente> clientes;

	private long idPlanConsumo;

	private List<ReservaServicio> reservasServicio;
	
	public Convencion() {
		
		this.id = 0;
		this.tematica = "";
		this.numeroParticipantes = 0;
		this.fechaInicio =  new Timestamp (0);
		this.fechaFin = new Timestamp (0);
		this.cuenta = new BigDecimal(0);
		this.pazYSalvo = " ";
		this.estado = " ";
		this.hotelesConvencion = new LinkedList<>();
		this.reservasHabitaciones = new LinkedList<>();
		this.clientes = new LinkedList<>();;
		//TODO MIRAR PLAN CINSUMO
		this.idPlanConsumo =0;
		this.reservasServicio = new LinkedList<>();
	}

	public Convencion(String tematica, long numeroParticipantes,
			Timestamp fechaInicio, Timestamp fechaFin, BigDecimal cuenta
			, String pazYSalvo, String estado, long id, long idPlanConsumo) {
		super();
		this.id = id;
		this.tematica = tematica;
		this.numeroParticipantes = numeroParticipantes;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.cuenta = cuenta;
		this.pazYSalvo = pazYSalvo;
		this.estado = estado;
		this.hotelesConvencion = new LinkedList<>();
		this.reservasHabitaciones =new LinkedList<>();
		this.clientes =new LinkedList<>();
	//TODO plan consumo
		this.idPlanConsumo= idPlanConsumo;
		
		this.reservasServicio = new LinkedList<>();
	}

	public long getIdPlanConsumo() {
		return idPlanConsumo;
	}

	public void setIdPlanConsumo(long idPlanConsumo) {
		this.idPlanConsumo = idPlanConsumo;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTematica() {
		return tematica;
	}

	public void setTematica(String tematica) {
		this.tematica = tematica;
	}

	public long getNumeroParticipantes() {
		return numeroParticipantes;
	}

	public void setNumeroParticipantes(long numeroParticipantes) {
		this.numeroParticipantes = numeroParticipantes;
	}

	public Timestamp getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Timestamp fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Timestamp getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Timestamp fechaFin) {
		this.fechaFin = fechaFin;
	}

	public BigDecimal getCuenta() {
		return cuenta;
	}

	public void setCuenta(BigDecimal cuenta) {
		this.cuenta = cuenta;
	}

	public String getPazYSalvo() {
		return pazYSalvo;
	}

	public void setPazYSalvo(String pazYSalvo) {
		this.pazYSalvo = pazYSalvo;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String  estado) {
		this.estado = estado;
	}

	public List<Hotel> getHotelesConvencion() {
		return hotelesConvencion;
	}

	public void setHotelesConvencion(List<Hotel> hotelesConvencion) {
		this.hotelesConvencion = hotelesConvencion;
	}

	public List<ReservaHabitacion> getReservasHabitaciones() {
		return reservasHabitaciones;
	}

	public void setReservasHabitaciones(List<ReservaHabitacion> reservasHabitaciones) {
		this.reservasHabitaciones = reservasHabitaciones;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

//	public PlanConsumo getPlanConsumo() {
//		return planConsumo;
//	}
//
//	public void setPlanConsumo(PlanConsumo planConsumo) {
//		this.planConsumo = planConsumo;
//	}

	public List<ReservaServicio> getReservasServicio() {
		return reservasServicio;
	}

	public void setReservasServicio(List<ReservaServicio> reservasServicio) {
		this.reservasServicio = reservasServicio;
	}





}
