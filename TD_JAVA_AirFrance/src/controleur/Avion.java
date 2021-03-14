package controleur;

public class Avion {
    private int idavion;
	private String designation, typeavion, constructeur, datemisecirculation;
    private int capacite, nbheuresvol;

    public Avion(int idavion, String designation, String typeavion, String constructeur, String datemisecirculation,
            int capacite, int nbheuresvol) {
        this.idavion = idavion;
        this.designation = designation;
        this.typeavion = typeavion;
        this.constructeur = constructeur;
        this.datemisecirculation = datemisecirculation;
        this.capacite = capacite;
        this.nbheuresvol = nbheuresvol;
    }

    public Avion(String designation, String typeavion, String constructeur, String datemisecirculation,
            int capacite, int nbheuresvol) {
        this.idavion = 0;
        this.designation = designation;
        this.typeavion = typeavion;
        this.constructeur = constructeur;
        this.datemisecirculation = datemisecirculation;
        this.capacite = capacite;
        this.nbheuresvol = nbheuresvol;
    }

    public Avion() {
        this.idavion = 0;
        this.designation = "";
        this.typeavion = "";
        this.constructeur = "";
        this.datemisecirculation = "";
        this.capacite = 0;
        this.nbheuresvol = 0;
    }

    public int getIdavion() {
        return idavion;
    }

    public void setIdavion(int idavion) {
        this.idavion = idavion;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getTypeavion() {
        return typeavion;
    }

    public void setTypeavion(String typeavion) {
        this.typeavion = typeavion;
    }

    public String getConstructeur() {
        return constructeur;
    }

    public void setConstructeur(String constructeur) {
        this.constructeur = constructeur;
    }

    public String getDatemisecirculation() {
        return datemisecirculation;
    }

    public void setDatemisecirculation(String datemisecirculation) {
        this.datemisecirculation = datemisecirculation;
    }

    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    public int getNbheuresvol() {
        return nbheuresvol;
    }

    public void setNbheuresvol(int nbheuresvol) {
        this.nbheuresvol = nbheuresvol;
    }
    


    
}
