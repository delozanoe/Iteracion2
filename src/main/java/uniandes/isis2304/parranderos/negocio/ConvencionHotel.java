package uniandes.isis2304.parranderos.negocio;

public class ConvencionHotel 
{

	private Integer idConvencion;

	private Integer idHotel;


	public ConvencionHotel(Integer idConvencion, Integer idHotel)
	{
		super();
		this.idHotel = idHotel;
		this.idConvencion = idConvencion;
	}


	public Integer getIdConvencion() {
		return idConvencion;
	}


	public void setIdConvencion(Integer idConvencion) {
		this.idConvencion = idConvencion;
	}


	public Integer getIdHotel() {
		return idHotel;
	}


	public void setIdHotel(Integer idHotel) {
		this.idHotel = idHotel;
	}


}
