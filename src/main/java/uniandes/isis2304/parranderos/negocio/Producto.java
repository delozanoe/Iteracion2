package uniandes.isis2304.parranderos.negocio;

import java.util.List;

/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class Producto
{
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private String nombre;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private double costo;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	
	private long  id;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private List<Servicio> serviciosProductos;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private List<Servicio> serviciosConsumos;

	public Producto(String nombre, Double costo,long id,
			List<Servicio> serviciosProductos, List<Servicio> serviciosConsumos) {
		super();
		this.nombre = nombre;
		this.costo = costo;
		this.id = id;
		this.serviciosProductos = serviciosProductos;
		this.serviciosConsumos = serviciosConsumos;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getCosto() {
		return costo;
	}

	public void setCosto(double costo) {
		this.costo = costo;
	}


	public long getId() {
		return id;
	}

	public void setId(long  id) {
		this.id = id;
	}

	public List<Servicio> getServiciosProductos() {
		return serviciosProductos;
	}

	public void setServiciosProductos(List<Servicio> serviciosProductos) {
		this.serviciosProductos = serviciosProductos;
	}

	public List<Servicio> getServiciosConsumos() {
		return serviciosConsumos;
	}

	public void setServiciosConsumos(List<Servicio> serviciosConsumos) {
		this.serviciosConsumos = serviciosConsumos;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 */
	

}

