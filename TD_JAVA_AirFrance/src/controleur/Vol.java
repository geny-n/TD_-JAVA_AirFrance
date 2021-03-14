package controleur;

public class Vol {
    private int idvol;
	private String designation, origine, destination, datevol;
    private int heurevol, idpilote, idavion;

    public Vol(int idvol, String designation, String origine, String destination, String datevol, int heurevol,
            int idpilote, int idavion) {
        this.idvol = idvol;
        this.designation = designation;
        this.origine = origine;
        this.destination = destination;
        this.datevol = datevol;
        this.heurevol = heurevol;
        this.idpilote = idpilote;
        this.idavion = idavion;
    }

    public Vol(String designation, String origine, String destination, String datevol, int heurevol,
            int idpilote, int idavion) {
        this.idvol = 0;
        this.designation = designation;
        this.origine = origine;
        this.destination = destination;
        this.datevol = datevol;
        this.heurevol = heurevol;
        this.idpilote = idpilote;
        this.idavion = idavion;
    }

    public Vol() {
        this.idvol = 0;
        this.designation = "";
        this.origine = "";
        this.destination = "";
        this.datevol = "";
        this.heurevol = 0;
        this.idpilote = 0;
        this.idavion = 0;
    }

    public int getIdvol() {
        return idvol;
    }

    public void setIdvol(int idvol) {
        this.idvol = idvol;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getOrigine() {
        return origine;
    }

    public void setOrigine(String origine) {
        this.origine = origine;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDatevol() {
        return datevol;
    }

    public void setDatevol(String datevol) {
        this.datevol = datevol;
    }

    public int getHeurevol() {
        return heurevol;
    }

    public void setHeurevol(int heurevol) {
        this.heurevol = heurevol;
    }

    public int getIdpilote() {
        return idpilote;
    }

    public void setIdpilote(int idpilote) {
        this.idpilote = idpilote;
    }

    public int getIdavion() {
        return idavion;
    }

    public void setIdavion(int idavion) {
        this.idavion = idavion;
    }
}
