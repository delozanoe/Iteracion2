package uniandes.isis2304.parranderos.negocio;

public class PlanConsumoServicio 
{
	private Integer idPlanConsumo;
	private Integer idServicio;
	
	public PlanConsumoServicio(Integer idPlanConsumo, Integer idServicio)
	{
		this.idPlanConsumo = idPlanConsumo;
		this.idServicio = idServicio;
	}

	public Integer getIdPlanConsumo() {
		return idPlanConsumo;
	}

	public void setIdPlanConsumo(Integer idPlanConsumo) {
		this.idPlanConsumo = idPlanConsumo;
	}

	public Integer getIdServicio() {
		return idServicio;
	}

	public void setIdServicio(Integer idServicio) {
		this.idServicio = idServicio;
	}
	
	
	
	
}
