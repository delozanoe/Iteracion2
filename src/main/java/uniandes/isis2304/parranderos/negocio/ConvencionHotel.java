package uniandes.isis2304.parranderos.negocio;

public class ConvencionHotel 
{

	private long idConvencion;

	private long idHotel;


	public ConvencionHotel(long idConvencion, long idHotel)
	{
		super();
		this.idHotel = idHotel;
		this.idConvencion = idConvencion;
	}


	public long getIdConvencion() {
		return idConvencion;
	}


	public void setIdConvencion(long idConvencion) {
		this.idConvencion = idConvencion;
	}


	public long getIdHotel() {
		return idHotel;
	}


	public void setIdHotel(long idHotel) {
		this.idHotel = idHotel;
	}


}
