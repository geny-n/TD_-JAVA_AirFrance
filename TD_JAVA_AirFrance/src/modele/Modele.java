package modele;



import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import controleur.Pilote;
import controleur.Aeroport;
import controleur.Avion;
import controleur.Vol;

public class Modele {
	private static BDD uneBdd = new BDD("localhost", "AirFrance_SIOA", "root", "");


	/* Pilote */
	public static void insertPilote (Pilote unPilote) {
		String requete = "insert into pilote values (null, '" + unPilote.getNom() +
														"','" + unPilote.getPrenom() +
														"','" + unPilote.getEmail()+
														"','" + unPilote.getMdp()+
														"','" +unPilote.getTel()+
														"','" + unPilote.getNationalite()+
														"','" +unPilote.getDateentree()+
														"','" +unPilote.getNbheuresvol()+
														"'); ";
		Modele.executer(requete);
	}
	
	public static void deletePilote (int idpilote) {
		String requete = "delete from pilote where idpilote =" +idpilote+";";
		Modele.executer(requete);
	}
	
	public static void updatePilote (Pilote unPilote) {
		String requete = "update pilote set nom = '" + unPilote.getNom() +
										"', prenom ='" + unPilote.getPrenom() +
										"', email ='" + unPilote.getEmail()+
										"', mdp ='"+unPilote.getMdp()+
										"', tel ='" + unPilote.getTel()+
										"', nationalite ='" + unPilote.getNationalite()+
										"', dateentree ='" + unPilote.getDateentree()+
										"', nbheuresvol ='" + unPilote.getNbheuresvol()+										
										"' where idpilote =" + unPilote.getIdpilote()+
										"; ";
		Modele.executer(requete);
	}
	
	public static void executer (String requete) {
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			unStat.execute(requete);
			unStat.close();
			uneBdd.seDeConnecter();
		}catch (SQLException exp) {
			System.out.println("Erreur d'execution de la requete :" + requete);
		}
	}
	
	public static Pilote selectWherePilote (int idpilote) {
		
			String requete = "select * from pilote where idpilote = "+idpilote +";";
			Pilote unPilote = null;
			try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			ResultSet unRes = unStat.executeQuery(requete);
			if (unRes.next()){
				unPilote = new Pilote(
						unRes.getInt("idpilote"), unRes.getString("nom"), unRes.getString("prenom"),
						unRes.getString("email"), unRes.getString("mdp"), unRes.getString("tel"),
						unRes.getString("nationalite"), unRes.getString("dateentree"),
						unRes.getInt("nbheuresvol"));
			}
			unStat.close();
			uneBdd.seDeConnecter();
		}catch (SQLException exp) {
			System.out.println("Erreur d'execution de la requete :" + requete);
		}
			return unPilote;
	}
	
	//surcharge de la m�thode selectWhereSalarie avec different arguments 
	public static Pilote selectWherePilote (String email, String mdp) {
		
		String requete = "select * from pilote where email = '"+email +"' and mdp ='"+mdp+"' ;";
		Pilote unPilote = null;
		try {
		uneBdd.seConnecter();
		Statement unStat = uneBdd.getMaConnexion().createStatement();
		ResultSet unRes = unStat.executeQuery(requete);
		if (unRes.next()){
			unPilote = new Pilote(
				unRes.getInt("idpilote"), unRes.getString("nom"), unRes.getString("prenom"),
				unRes.getString("email"), unRes.getString("mdp"), unRes.getString("tel"),
				unRes.getString("nationalite"), unRes.getString("dateentree"),
				unRes.getInt("nbheuresvol"));
		}
		unStat.close();
		uneBdd.seDeConnecter();
		}catch (SQLException exp) {
			System.out.println("Erreur d'execution de la requete :" + requete);
		}
		return unPilote;
	}
	
	public static ArrayList<Pilote> selectAllPilotes(String mot) {
		
		String requete ="";
		if (mot.equals("")) {
			requete ="select * from pilote ;" ;
		}else {
			requete ="select * from pilote where nom like '%"+mot+"%' or prenom like '%"+mot
					+ "%' or email like '%"+mot+"%' or mdp like '%" +mot+
					"%' or tel like '%"+mot+"%' or nationalite like '%"+mot+"%';";
		}
		ArrayList<Pilote> lesPilotes = new ArrayList<Pilote>();
		try {
		uneBdd.seConnecter();
		Statement unStat = uneBdd.getMaConnexion().createStatement();
		ResultSet desRes = unStat.executeQuery(requete);
		while (desRes.next()){
			Pilote unPilote = new Pilote(
				desRes.getInt("idpilote"), desRes.getString("nom"), desRes.getString("prenom"),
				desRes.getString("email"), desRes.getString("mdp"), desRes.getString("tel"),
				desRes.getString("nationalite"), desRes.getString("dateentree"),
				desRes.getInt("nbheuresvol"));
				lesPilotes.add(unPilote);
		}
		unStat.close();
		uneBdd.seDeConnecter();
	}catch (SQLException exp) {
		System.out.println("Erreur d'execution de la requete :" + requete);
	}
		return lesPilotes;
}

	/* Avion */

	public static void insertAvion(Avion unAvion) {
		String requete = "insert into avion values (null, '" + unAvion.getDesignation() +
			"','" + unAvion.getTypeavion() +
			"','" + unAvion.getConstructeur() +
			"','" + unAvion.getDatemisecirculation() +
			"','" + unAvion.getCapacite() +
			"','" + unAvion.getNbheuresvol() + "'); ";
		Modele.executer(requete);
	}

	public static void deleteAvion(int idavion) {
		String requete = "delete from avion where idavion =" + idavion + ";";
		Modele.executer(requete);
	}

	public static void updateAvion(Avion unAvion) {
		String requete = "update avion set designation = '" + unAvion.getDesignation() +
			"', typeavion ='" + unAvion.getTypeavion() +
			"', constructeur ='" + unAvion.getConstructeur() +
			"', datemisecirculation ='" + unAvion.getDatemisecirculation() +
			"', capacite ='" + unAvion.getCapacite() +
			"', nbheuresvol ='" + unAvion.getNbheuresvol() +
			"' where idavion =" + unAvion.getIdavion() + "; ";
		Modele.executer(requete);
	}

	public static Avion selectWhereAvion(int idavion) {

		String requete = "select * from avion where idavion = " + idavion + ";";
		Avion unAvion = null;
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			ResultSet unRes = unStat.executeQuery(requete);
			if (unRes.next()) {
				unAvion = new Avion(
					unRes.getInt("idavion"), unRes.getString("designation"), unRes.getString("typeavion"),
					unRes.getString("constructeur"), unRes.getString("datemisecirculation"), unRes.getInt("capacite"),
					unRes.getInt("nbheuresvol"));
			}
			unStat.close();
			uneBdd.seDeConnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur d'éxécution de la requete :" + requete);
		}
		return unAvion;
	}

	//surcharge de la méthode selectWhereSalarie avec different arguments 
	public static Avion selectWhereAvion(String designation, String typeavion) {

		String requete = "select * from avion where designation = '" + designation + "' and typeavion ='" + typeavion + "' ;";
		Avion unAvion = null;
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			ResultSet unRes = unStat.executeQuery(requete);
			if (unRes.next()) {
				unAvion = new Avion(
					unRes.getInt("idavion"), unRes.getString("designation"), unRes.getString("typeavion"),
					unRes.getString("constructeur"), unRes.getString("datemisecirculation"), unRes.getInt("capacite"),
					unRes.getInt("nbheuresvol"));
			}
			unStat.close();
			uneBdd.seDeConnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur d'éxécution de la requete :" + requete);
		}
		return unAvion;
	}

	public static ArrayList < Avion > selectAllAvions(String mot) {

		String requete = "";
		if (mot.equals("")) {
			requete = "select * from avion ;";
		} else {
			requete = "select * from avion where designation like '%" + mot + "%' or typeavion like '%" + mot +
				"%' or constructeur like '%" + mot + "%' or datemisecirculation like '%" + mot + "%' ;";
		}
		ArrayList < Avion > lesAvions = new ArrayList < Avion > ();
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			ResultSet desRes = unStat.executeQuery(requete);
			while (desRes.next()) {
				Avion unAvion = new Avion(
					desRes.getInt("idavion"), desRes.getString("designation"), desRes.getString("typeavion"),
					desRes.getString("constructeur"), desRes.getString("datemisecirculation"), desRes.getInt("capacite"),
					desRes.getInt("nbheuresvol"));

				lesAvions.add(unAvion);
			}
			unStat.close();
			uneBdd.seDeConnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur d'éxécution de la requete :" + requete);
		}
		return lesAvions;
	}


		/* Vol */

		public static void insertVol (Vol unVol) {
			String requete = "insert into vol values (null, '" + unVol.getDesignation() +
														"','" + unVol.getOrigine() +
														"','" + unVol.getDestination()+
														"','" + unVol.getDatevol()+
														"','" +unVol.getHeurevol()+
														"','" + unVol.getIdpilote()+
														"','" + unVol.getIdavion()+
														"'); ";
			Modele.executer(requete);
		}
		
		public static void deleteVol (int idvol) {
			String requete = "delete from vol where idvol =" +idvol+";";
			Modele.executer(requete);
		}
		
		
		public static void updateVol (Vol unVol) {
			String requete = "update vol set designation = '" + unVol.getDesignation() +
										"', origine ='" + unVol.getOrigine() +
										"', destination ='" + unVol.getDestination()+
										"', datevol ='" + unVol.getDatevol()+
										"', heurevol ='"+unVol.getHeurevol()+
										"', idpilote ='" + unVol.getIdpilote()+
										"', idavion ='" + unVol.getIdavion()+
								"' where idvol =" + unVol.getIdvol()+ "; ";
			Modele.executer(requete);
		}

		public static Vol selectWhereVol (int idvol) {
			
				String requete = "select * from vol where idvol = "+idvol +";";
				Vol unVol = null;
				try {
				uneBdd.seConnecter();
				Statement unStat = uneBdd.getMaConnexion().createStatement();
				ResultSet unRes = unStat.executeQuery(requete);
				if (unRes.next()){
					unVol = new Vol (
							unRes.getInt("idvol"), unRes.getString("designation"), unRes.getString("origine"),
							unRes.getString("destination"), unRes.getString("datevol"), unRes.getInt("heurevol"),
							unRes.getInt("idpilote"), unRes.getInt("idavion"));
				}
				unStat.close();
				uneBdd.seDeConnecter();
			}catch (SQLException exp) {
				System.out.println("Erreur d'execution de la requete :" + requete);
			}
				return unVol;
		}
		
		//surcharge de la m�thode selectWhereSalarie avec different arguments 
		public static Vol selectWhereVol (String designation, String destination) {
			
			String requete = "select * from vol where designation = '"+designation +"' and destination ='"+destination+"' ;";
			Vol unVol = null;
			try {
				uneBdd.seConnecter();
				Statement unStat = uneBdd.getMaConnexion().createStatement();
				ResultSet unRes = unStat.executeQuery(requete);
				if (unRes.next()){
					unVol = new Vol(
						unRes.getInt("idvol"), unRes.getString("designation"), unRes.getString("origine"),
						unRes.getString("destination"), unRes.getString("datevol"), unRes.getInt("heurevol"),
						unRes.getInt("idpilote"), unRes.getInt("idavion"));
				}
				unStat.close();
				uneBdd.seDeConnecter();
			}catch (SQLException exp) {
				System.out.println("Erreur d'execution de la requete :" + requete);
			}
			return unVol;
		}
		
		public static ArrayList<Vol> selectAllVols (String mot) {
			
			String requete ="";
			if (mot.equals("")) {
				requete ="select * from vol ;" ;
			}else {
				requete ="select * from vol where designation like '%"+mot+"%' or origine like '%"+mot
						+ "%' or destination like '%"+mot+"%' or datevol like '%" +mot+"%';";
			}
			ArrayList<Vol> lesVols = new ArrayList<Vol>();
			try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			ResultSet desRes = unStat.executeQuery(requete);
			while (desRes.next()){
				Vol unVol = new Vol(
					desRes.getInt("idvol"), desRes.getString("designation"), desRes.getString("origine"),
					desRes.getString("destination"), desRes.getString("datevol"), desRes.getInt("heurevol"),
					desRes.getInt("idpilote"), desRes.getInt("idavion"));
				lesVols.add(unVol);
			}
			unStat.close();
			uneBdd.seDeConnecter();
		}catch (SQLException exp) {
			System.out.println("Erreur d'execution de la requete :" + requete);
		}
			return lesVols;
	}

	/*Aeroport */


	public static void insertAeroport(Aeroport unAeroport) {
		String requete = "insert into aeroport values (null, '" + unAeroport.getDesignation() +
			"','" + unAeroport.getAdresse() + "','" + unAeroport.getVille() + "','" + unAeroport.getCodepostal() +
			"','" + unAeroport.getPays() + "','" + unAeroport.getCategorie() + "','" + unAeroport.getIdavion() + "'); ";
		Modele.executer(requete);
	}

	public static void deleteAeroport(int idaeroport) {
		String requete = "delete from aeroport where idaeroport =" + idaeroport + ";";
		Modele.executer(requete);
	}

	public static void updateAeroport(Aeroport unAeroport) {
		String requete = "update aeroport set designation = '" + unAeroport.getDesignation() +
			"', adresse ='" + unAeroport.getAdresse() +
			"', ville ='" + unAeroport.getVille() +
			"', codepostal ='" + unAeroport.getCodepostal() +
			"', pays ='" + unAeroport.getPays() +
			"', categorie ='" + unAeroport.getCategorie() +
			"', idavion ='" + unAeroport.getIdavion() +
			"' where idaeroport =" + unAeroport.getIdaeroport() + "; ";
		Modele.executer(requete);
	}


	public static Aeroport selectWhereAeroport(int idaeroport) {

		String requete = "select * from aeroport where idaeroport = " + idaeroport + ";";
		Aeroport unAeroport = null;
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			ResultSet unRes = unStat.executeQuery(requete);
			if (unRes.next()) {
				unAeroport = new Aeroport(
					unRes.getInt("idaeroport"), unRes.getString("designation"), unRes.getString("adresse"),
					unRes.getString("ville"), unRes.getString("codepostal"), unRes.getString("pays"),
					unRes.getString("categorie"), unRes.getInt("idavion"));
			}
			unStat.close();
			uneBdd.seDeConnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur d'execution de la requete :" + requete);
		}
		return unAeroport;
	}

	//surcharge de la methode selectWhereSalarie avec different arguments 
	public static Aeroport selectWhereAeroport(String designation, String adresse) {

		String requete = "select * from aeroport where designation = '" + designation + "' and adresse ='" + adresse + "' ;";
		Aeroport unAeroport = null;
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			ResultSet unRes = unStat.executeQuery(requete);
			if (unRes.next()) {
				unAeroport = new Aeroport(
					unRes.getInt("idaeroport"), unRes.getString("designation"), unRes.getString("adresse"),
					unRes.getString("ville"), unRes.getString("codepostal"), unRes.getString("pays"),
					unRes.getString("categorie"), unRes.getInt("idavion"));
			}
			unStat.close();
			uneBdd.seDeConnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur d'execution de la requete :" + requete);
		}
		return unAeroport;
	}

	public static ArrayList < Aeroport > selectAllAeroports(String mot) {
		String requete = "";
		if (mot.equals("")) {
			requete = "select * from aeroport ;";
		} else {
			requete = "select * from aeroport where designation like '%" + mot +
				"%' or adresse like '%" + mot +
				"%' or ville like '%" + mot +
				"%' or codepostal like '%" + mot +
				"%' or pays like '%" + mot +
				"%' or categorie like '%" + mot + "%' ;";
		}
		ArrayList < Aeroport > lesAeroports = new ArrayList < Aeroport > ();
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			ResultSet desRes = unStat.executeQuery(requete);
			while (desRes.next()) {
				Aeroport unAeroport = new Aeroport(
					desRes.getInt("idaeroport"), desRes.getString("designation"), desRes.getString("adresse"),
					desRes.getString("ville"), desRes.getString("codepostal"), desRes.getString("pays"),
					desRes.getString("categorie"), desRes.getInt("idavion"));
				lesAeroports.add(unAeroport);
			}
			unStat.close();
			uneBdd.seDeConnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur d'execution de la requete :" + requete);
		}
		return lesAeroports;
	}

}