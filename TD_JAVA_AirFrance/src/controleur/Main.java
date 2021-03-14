package controleur;


import java.util.ArrayList;

import modele.Modele;
import vue.VueConnexion;
import vue.VuePilote;
import vue.VueAeroport;
import vue.VueAvion;
import vue.VueVol;

public class Main {

	private static VueConnexion uneConnexion;
	private static VuePilote uneVuePilote;
	private static VueAvion uneVueAvion;
	private static VueVol uneVueVol;
	private static VueAeroport uneVueAeroport;
	
	
	public static void main(String[] args) {
		uneConnexion = new VueConnexion();
	}
	public static void instancierVuePilote () {
		uneVuePilote = new VuePilote();
	}
	public static void instancierVueAvion () {
			uneVueAvion = new VueAvion();
	}
	public static void instancierVueVol () {
		uneVueVol = new VueVol();
	}
	public static void instancierVueAeroport () {
		uneVueAeroport = new VueAeroport();
	}

	public static void rendreVisible (boolean action) {
		uneConnexion.setVisible(action);
	}
	
 /**************** Controleur de la classe salarie **************/
	
 	public static void insertPilote (Pilote unPilote) {
		Modele.insertPilote(unPilote);
	}

	public static void deletetPilote (int idpilote) {
		Modele.deletePilote(idpilote);
	}

	public static void updatePilote (Pilote unPilote) {
		Modele.updatePilote(unPilote);
	}

	public static Pilote selectWherePilote (int idpilote) {
		return Modele.selectWherePilote(idpilote);
	}

	public static Pilote selectWherePilote (String email, String mdp) {
		return Modele.selectWherePilote(email, mdp);
	}

	public static ArrayList<Pilote> selectAllPilotes (String mot) {
		return Modele.selectAllPilotes(mot);
	}
 

	/*************************************************************/

	 /**************** Controleur de la classe evenement **************/

	 public static void insertAvion (Avion unAvion) {
		Modele.insertAvion(unAvion);
	}
	
	public static void deletetAvion (int idavion) {
		Modele.deleteAvion(idavion);
	}
	
	public static void updateAvion (Avion unAvion) {
		Modele.updateAvion(unAvion);
	}
	
	public static Avion selectWhereAvion (int idavion) {
		return Modele.selectWhereAvion(idavion);
	}
	
	public static Avion selectWhereAvion (String designation, String typeavion) {
		return Modele.selectWhereAvion(designation, typeavion);
	}
	
	public static ArrayList<Avion> selectAllAvions (String mot) {
		return Modele.selectAllAvions(mot);

	}
	/*************************************************************/

	

	/**************** Controleur de la classe vol **************/
	
	public static void insertVol (Vol unVol) {
		Modele.insertVol(unVol);
	}
	
	public static void deleteVol (int idvol) {
		Modele.deleteVol(idvol);
	}
	
	public static void updateVol (Vol unVol) {
		Modele.updateVol(unVol);
	}
	
	public static Vol selectWhereVol (int idvol) {
		return Modele.selectWhereVol(idvol);
	}
	
	public static Vol selectWhereVol (String designation, String destination) {
		return Modele.selectWhereVol(designation, destination);
	}
	
	public static ArrayList<Vol> selectAllVols (String mot) {
		return Modele.selectAllVols(mot);
	}

	/*************************************************************/


	/**************** Controleur de la classe aeroport **************/
	
	 public static void insertAeroport (Aeroport unAeroport) {
		Modele.insertAeroport(unAeroport);
	}
	
	public static void deleteAeroport (int idaeroport) {
		Modele.deleteAeroport(idaeroport);
	}
	
	public static void updateAeroport (Aeroport unAeroport) {
		Modele.updateAeroport(unAeroport);
	}
	
	public static Aeroport selectWhereAeroport (int idaeroport) {
		return Modele.selectWhereAeroport(idaeroport);
	}
	
	public static Aeroport selectWhereAeroport (String designation, String adresse) {
		return Modele.selectWhereAeroport(designation, adresse);
	}
	
	public static ArrayList<Aeroport> selectAllAeroports (String mot) {
		return Modele.selectAllAeroports(mot);
	}
}
