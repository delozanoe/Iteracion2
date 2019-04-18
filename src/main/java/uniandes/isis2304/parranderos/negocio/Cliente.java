package uniandes.isis2304.parranderos.negocio;
import java.math.BigDecimal;
import java.util.List;



/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class Cliente extends Usuario
{
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */



	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */

	private String pazYSalvo;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */

	private List<String> planConsumo;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */

	private Habitacion habitacion;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */

	private List<ReservaHabitacion> reservaHabitacion;


	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */

	private List<ReservaServicio> reservas;

	private List<Convencion> convenciones;

	public Cliente( long id, String pazYSalvo, List<String> planConsumo, Habitacion habitacion,
			List<ReservaHabitacion> reserva, List<ReservaServicio> reservas, String nombre, String tipoDocumento, long numeroDocumento, String correo, List<Convencion> convenciones)
			 {

		super(nombre, nombre, numeroDocumento, nombre, id);
		this.id = id;
		this.nombre = nombre;
		this.pazYSalvo = pazYSalvo;
		this.planConsumo = planConsumo;
		this.habitacion = habitacion;
		this.reservaHabitacion = reserva;
		this.reservas = reservas;
		this.convenciones = convenciones;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String isPazYSalvo() {
		if(habitacion.getCuenta() == new BigDecimal(0))
		{
			return "T";
		}
		else
		{
			return "F";
		}
	}

	public void setPazYSalvo(String pazYSalvo) {
		this.pazYSalvo = pazYSalvo;
	}

	public List<String> getPlanConsumo() {
		return planConsumo;
	}

	public void setPlanConsumo(List<String> planConsumo) {
		this.planConsumo = planConsumo;
	}

	public Habitacion getHabitacion() {
		return habitacion;
	}

	public void setHabitacion(Habitacion habitacion) {
		this.habitacion = habitacion;
	}

	public List<ReservaHabitacion> getReserva() {
		return reservaHabitacion;
	}

	public void setReserva(List<ReservaHabitacion> reserva) {
		this.reservaHabitacion = reserva;
	}

	public List<ReservaServicio> getReservas() {
		return reservas;
	}

	public void setReservas(List<ReservaServicio> reservas) {
		this.reservas = reservas;
	}

	public List<Convencion> getConvencion()
	{
		return convenciones;
	}

	public void setConvenciones(List<Convencion> convenciones)
	{
		this.convenciones = convenciones;
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 */



}
