package uniandes.isis2304.parranderos.negocio;


/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class Empleado extends Usuario
{
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private TipoEmpleado tipo;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private Hotel hotel;

	public Empleado(TipoEmpleado tipo, Hotel hotel, String nombre, String tipoDocumento, long  numeroDocumento, String correo, long id)
	{
		super(nombre, tipoDocumento, numeroDocumento, correo,id);
		this.tipo = tipo;
		this.hotel = hotel;
	}
	
	public TipoEmpleado getTipo() {
		return tipo;
	}

	public void setTipo(TipoEmpleado tipo) {
		this.tipo = tipo;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 */
	
	

}

