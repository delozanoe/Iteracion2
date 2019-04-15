package uniandes.isis2304.parranderos.negocio;
import java.util.ArrayList;



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
	
	private char pazYSalvo;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private ArrayList<String> planConsumo;

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
	
	private ArrayList<ReservaHabitacion> reservaHabitacion;
	

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private ArrayList<ReservaServicio> reservas;

	public Cliente( Integer id, char pazYSalvo, ArrayList<String> planConsumo, Habitacion habitacion,
			ArrayList<ReservaHabitacion> reserva, ArrayList<ReservaServicio> reservas, String nombre, String tipoDocumento, long numeroDocumento, String correo) {
		
		super(nombre, nombre, numeroDocumento, nombre, id);
		this.id = id;
		this.nombre = nombre;
		this.pazYSalvo = pazYSalvo;
		this.planConsumo = planConsumo;
		this.habitacion = habitacion;
		this.reservaHabitacion = reserva;
		this.reservas = reservas;
	}

	
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public char isPazYSalvo() {
		if(habitacion.getCuenta() == 0)
		{
			return 'T';
		}
		else 
		{
			return 'F';
		}
	}

	public void setPazYSalvo(char pazYSalvo) {
		this.pazYSalvo = pazYSalvo;
	}

	public ArrayList<String> getPlanConsumo() {
		return planConsumo;
	}

	public void setPlanConsumo(ArrayList<String> planConsumo) {
		this.planConsumo = planConsumo;
	}

	public Habitacion getHabitacion() {
		return habitacion;
	}

	public void setHabitacion(Habitacion habitacion) {
		this.habitacion = habitacion;
	}

	public ArrayList<ReservaHabitacion> getReserva() {
		return reservaHabitacion;
	}

	public void setReserva(ArrayList<ReservaHabitacion> reserva) {
		this.reservaHabitacion = reserva;
	}

	public ArrayList<ReservaServicio> getReservas() {
		return reservas;
	}

	public void setReservas(ArrayList<ReservaServicio> reservas) {
		this.reservas = reservas;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 */
	
	

}

