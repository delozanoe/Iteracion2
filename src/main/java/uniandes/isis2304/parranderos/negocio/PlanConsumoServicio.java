package uniandes.isis2304.parranderos.negocio;

public class PlanConsumoServicio 
{
	private long idPlanConsumo;
	private long idServicio;
	
	public PlanConsumoServicio(long idPlanConsumo, long idServicio)
	{
		this.idPlanConsumo = idPlanConsumo;
		this.idServicio = idServicio;
	}

	public long getIdPlanConsumo() {
		return idPlanConsumo;
	}

	public void setIdPlanConsumo(long idPlanConsumo) {
		this.idPlanConsumo = idPlanConsumo;
	}

	public long getIdServicio() {
		return idServicio;
	}

	public void setIdServicio(long idServicio) {
		this.idServicio = idServicio;
	}
	
	
	
	
}
