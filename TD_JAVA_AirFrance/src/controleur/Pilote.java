package controleur;

public class Pilote {
    private int idpilote;
    private String nom, prenom, email, mdp, tel, nationalite, dateentree;
    private int nbheuresvol;

    public Pilote(int idpilote, String nom, String prenom, String email, String mdp, String tel, String nationalite,
            String dateentree, int nbheuresvol) {
        this.idpilote = idpilote;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.mdp = mdp;
        this.tel = tel;
        this.nationalite = nationalite;
        this.dateentree = dateentree;
        this.nbheuresvol = nbheuresvol;
    }

    public Pilote(String nom, String prenom, String email, String mdp, String tel, String nationalite,
            String dateentree, int nbheuresvol) {
        this.idpilote = 0;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.mdp = mdp;
        this.tel = tel;
        this.nationalite = nationalite;
        this.dateentree = dateentree;
        this.nbheuresvol = nbheuresvol;
    }

    public Pilote() {
        this.idpilote = 0;
        this.nom = "";
        this.prenom = "";
        this.email = "";
        this.mdp = "";
        this.tel = "";
        this.nationalite = "";
        this.dateentree = "";
        this.nbheuresvol = 0;
    }

    public int getIdpilote() {
        return idpilote;
    }

    public void setIdpilote(int idpilote) {
        this.idpilote = idpilote;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getNationalite() {
        return nationalite;
    }

    public void setNationalite(String nationalite) {
        this.nationalite = nationalite;
    }

    public String getDateentree() {
        return dateentree;
    }

    public void setDateentree(String dateentree) {
        this.dateentree = dateentree;
    }

    public int getNbheuresvol() {
        return nbheuresvol;
    }

    public void setNbheuresvol(int nbheuresvol) {
        this.nbheuresvol = nbheuresvol;
    }

    
    
    
}
