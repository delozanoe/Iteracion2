package uniandes.isis2304.parranderos.negocio;

public class ConvencionCliente 
{
	private Integer idConvencion;
	
	private Integer idCliente;
	
	
	public ConvencionCliente(Integer idConvencion, Integer idCliente)
	{
		super();
		this.idCliente = idCliente;
		this.idConvencion = idConvencion;
	}


	public Integer getIdConvencion() {
		return idConvencion;
	}


	public void setIdConvencion(Integer idConvencion) {
		this.idConvencion = idConvencion;
	}


	public Integer getIdCliente() {
		return idCliente;
	}


	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}
	
	
	
}
