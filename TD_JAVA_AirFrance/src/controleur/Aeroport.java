package controleur;

public class Aeroport {
	private int idaeroport;
	private String designation, adresse, ville, codepostal, pays, categorie;
	private int idavion;
	
	public Aeroport(int idaeroport, String designation, String adresse, String ville, String codepostal, String pays,
			String categorie, int idavion) {
		super();
		this.idaeroport = idaeroport;
		this.designation = designation;
		this.adresse = adresse;
		this.ville = ville;
		this.codepostal = codepostal;
		this.pays = pays;
		this.categorie = categorie;
		this.idavion = idavion;
	}
	
	public Aeroport(String designation, String adresse, String ville, String codepostal, String pays,
			String categorie, int idavion) {
		super();
		this.idaeroport = 0;
		this.designation = designation;
		this.adresse = adresse;
		this.ville = ville;
		this.codepostal = codepostal;
		this.pays = pays;
		this.categorie = categorie;
		this.idavion = idavion;
	}
	
	public Aeroport() {
		super();
		this.idaeroport = 0;
		this.designation = "";
		this.adresse = "";
		this.ville = "";
		this.codepostal = "";
		this.pays = "";
		this.categorie = "";
		this.idavion = 0;
	}

	public int getIdaeroport() {
		return idaeroport;
	}

	public void setIdaeroport(int idaeroport) {
		this.idaeroport = idaeroport;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getCodepostal() {
		return codepostal;
	}

	public void setCodepostal(String codepostal) {
		this.codepostal = codepostal;
	}

	public String getPays() {
		return pays;
	}

	public void setPays(String pays) {
		this.pays = pays;
	}

	public String getCategorie() {
		return categorie;
	}

	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}

	public int getIdavion() {
		return idavion;
	}

	public void setIdavion(int idavion) {
		this.idavion = idavion;
	}
	
	
	
	
	
}
