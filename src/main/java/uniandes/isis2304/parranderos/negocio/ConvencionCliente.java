package uniandes.isis2304.parranderos.negocio;

public class ConvencionCliente 
{
	private long idConvencion;
	
	private long idCliente;
	
	
	public ConvencionCliente(long idConvencion, long idCliente)
	{
		super();
		this.idCliente = idCliente;
		this.idConvencion = idConvencion;
	}


	public long getIdConvencion() {
		return idConvencion;
	}


	public void setIdConvencion(long idConvencion) {
		this.idConvencion = idConvencion;
	}


	public long getIdCliente() {
		return idCliente;
	}


	public void setIdCliente(long idCliente) {
		this.idCliente = idCliente;
	}
	
	
	
}
