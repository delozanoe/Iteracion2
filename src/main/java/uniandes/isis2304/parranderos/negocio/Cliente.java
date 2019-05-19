package uniandes.isis2304.parranderos.negocio;
import java.math.BigDecimal;
import java.util.LinkedList;
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
	
	public Cliente(String nombre, BigDecimal id, String TipoDoc,String numeroDoc, String correo )
	{
		super(nombre, TipoDoc, numeroDoc, correo, id);
		this.pazYSalvo="F";
	}

	public Cliente()
	{
		super("", "", "", "", new BigDecimal(0));
		this.nombre = "";
		this.pazYSalvo = "F";
		this.planConsumo = null;
		this.habitacion = null;
		this.reservaHabitacion = new LinkedList<>();
		this.reservas = new LinkedList<>();
		this.convenciones = new LinkedList<>();
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
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}
	
	
	
	
	


}
