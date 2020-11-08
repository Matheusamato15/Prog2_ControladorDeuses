package Modelo;

public class ModeloDeus {
	
	private Integer id;
	private String nomeDeus;
	private String regiaoDeus;
	private String representacaoDeus;
	private String personalidadeDeus;
	private String hierarquiaDeus;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getNomeDeus() {
		return nomeDeus;
	}
	
	public void setNomeDeus(String nomeDeus) {
		this.nomeDeus = nomeDeus;
	}
	
	public String getRegiaoDeus() {
		return regiaoDeus;
	}
	
	public void setRegiaoDeus(String regiaoDeus) {
		this.regiaoDeus = regiaoDeus;
	}
	
	public String getRepresentacaoDeus() {
		return representacaoDeus;
	}
	
	public void setRepresentacaoDeus(String representacaoDeus) {
		this.representacaoDeus = representacaoDeus;
	}
	
	public String getPersonalidadeDeus() {
		return personalidadeDeus;
	}
	
	public void setPersonalidadeDeus(String personalidadeDeus) {
		this.personalidadeDeus = personalidadeDeus;
	}
	
	public String getHierarquiaDeus() {
		return hierarquiaDeus;
	}
	
	public void setHierarquiaDeus(String hierarquiaDeus) {
		this.hierarquiaDeus = hierarquiaDeus;
	}

	public String toString() {
		return "ModeloDeus [id=" + id + ", nomeDeus=" + nomeDeus + ", regiaoDeus=" + regiaoDeus + ", representacaoDeus="
				+ representacaoDeus + ", personalidadeDeus=" + personalidadeDeus + ", hierarquiaDeus=" + hierarquiaDeus
				+ "]";
	}
}
