package vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import controleur.Aeroport;
import controleur.Avion;
import controleur.Main;
//import controleur.Salarie;
//import controleur.Pilote;
//import jdk.javadoc.internal.doclets.formats.html.markup.Table;
import controleur.Tableau;

public class VueAeroport extends JFrame implements ActionListener{
    private JButton btRetour = new JButton("Retour au menu");
    
    //declaration du pannel d'ajout
    private JPanel panelAjout = new JPanel();
    private JTextField txtDesignation = new JTextField();
    private JTextField txtAdresse = new JTextField();
	private JTextField txtVille = new JTextField();
	private JTextField txtCodePostal = new JTextField();
	private JTextField txtPays = new JTextField();
    private JComboBox<String> cbxCategorie = new JComboBox<String>();
    private JComboBox<String> cbxAvion = new JComboBox<String>();//select option
    private JButton btAnnuler= new JButton("Annuler");
    private JButton btEnregistrer = new JButton("Enregistrer");
	private int iddelaeroport;//mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm
	
	//pael lister : gestion du tableau des �v�nements 
	private JTable uneTable;
	private Tableau unTableau;
	private JScrollPane uneScroll;
	private JPanel panelLister = new JPanel();
	
	//rechercher par mot cle
		private JTextField txtMot = new JTextField();
		private JButton btFiltrer = new JButton("Filtrer");
    
    public VueAeroport() {
        this.setTitle("Gestion des A�roports GestEventsIris");
		this.setBounds(200, 200, 1200, 400);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setBackground(new Color (247, 218, 40));
		this.setLayout(null);
		
		this.btRetour.setBounds(570, 330, 130, 25);
		this.add(this.btRetour);
        this.btRetour.addActionListener(this);
        
        //construction du panel ajout
        this.panelAjout.setBounds(10, 50, 320, 260);
		this.panelAjout.setBackground(new Color (247, 218, 40));
		this.panelAjout.setLayout(new GridLayout(8,2));
		
		
		this.panelAjout.add(new JLabel("Designation : "));
		this.panelAjout.add(this.txtDesignation);
		this.panelAjout.add(new JLabel("Adresse : "));
		this.panelAjout.add(this.txtAdresse);
		this.panelAjout.add(new JLabel("Ville : "));
		this.panelAjout.add(this.txtVille);
		this.panelAjout.add(new JLabel("Code postal : "));
		this.panelAjout.add(this.txtCodePostal);
		this.panelAjout.add(new JLabel("Pays : "));
		this.panelAjout.add(this.txtPays);
		this.panelAjout.add(new JLabel("Categorie : "));
		this.panelAjout.add(this.cbxCategorie);
		this.panelAjout.add(new JLabel("Avion : "));
		this.panelAjout.add(this.cbxAvion);
		
		this.panelAjout.add(this.btAnnuler);
		this.panelAjout.add(this.btEnregistrer);
		this.add(this.panelAjout);
		
		//remplir le CBX Avion 
		this.remplirCBXAvion();

		//rendre les deux boutons cliquables
		this.btAnnuler.addActionListener(this);
		this.btEnregistrer.addActionListener(this);
        
        
     // installation du panel lister 
     		this.panelLister.setBounds(340, 30, 850, 600);
     		this.panelLister.setBackground(new Color (247, 218, 40));
     		this.panelLister.setLayout(null);
     		
     		String entetes [] = {"Id aeroport", "Designation", "Adresse", "Ville", "CodePostal", "Pays", "Categorie", "Avion"};
     		
     		//instanciation de la classe tableau avec les entetes et les donnees
     		this.unTableau = new Tableau (entetes, this.remplirDonnees(""));
     	
     		//instanciation de la JTable avec le tableau construit 
     		this.uneTable = new JTable(this.unTableau);
     		
     		//je mets ma table dans ma scroll
     		this.uneScroll = new JScrollPane(this.uneTable);
     		this.uneScroll.setBounds(20, 20, 780, 200);
     		this.panelLister.add(this.uneScroll);
     		
     		this.add(this.panelLister);
     		
     		//remplir combobox des statut et type
     		this.cbxCategorie.addItem("national");
     		this.cbxCategorie.addItem("international");
     		this.cbxCategorie.addItem("fret");
     		this.cbxCategorie.addItem("autre");
     		    		
     		
     		this.uneTable.addMouseListener(new MouseListener() {
		
				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseClicked(MouseEvent e) {
					if (e.getClickCount() >= 2) {
						int ligne = uneTable.getSelectedRow();
						int retour = JOptionPane.showConfirmDialog(null, "Confirmez la suppression", "Suppression", JOptionPane.YES_NO_OPTION);
						if (retour ==0) {					
							//on recupere l'id de l'a�roport � supprimer
							int idaeroport = Integer.parseInt(unTableau.getValueAt(ligne, 0).toString());
							//on supprime dans la bdd via le modele
							Main.deleteAeroport(idaeroport);
							//suppression de la ligne de l'affichage 
							unTableau.supprimerLigne(ligne);
						}
						
					} else if (e.getClickCount() == 1) {
						int ligne = uneTable.getSelectedRow();
						iddelaeroport = Integer.parseInt(unTableau.getValueAt(ligne, 0).toString());//mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm
						txtDesignation.setText(unTableau.getValueAt(ligne, 1).toString());
						txtAdresse.setText(unTableau.getValueAt(ligne, 2).toString());
						txtVille.setText(unTableau.getValueAt(ligne, 3).toString());
						txtCodePostal.setText(unTableau.getValueAt(ligne, 4).toString());
						txtPays.setText(unTableau.getValueAt(ligne, 5).toString());
						//pas de modif pour mle combo staut et type et idsalarie
						btEnregistrer.setText("Modifier");
					}
					
				}
     		});
     		
     		
     		//placement zone de recherche
    		this.txtMot.setBounds(200, 10, 130, 25);
    		this.add(this.txtMot);
    		this.btFiltrer.setBounds(370, 10, 130, 25);
    		this.add(this.btFiltrer);
    		this.btFiltrer.addActionListener(this);
    		
    		this.setVisible(true);
        
	}   
    
    public Object [][] remplirDonnees (String mot){
		//cette methode transforme l'ArrayList des �v�nements
		//en une matrice 
		
		ArrayList<Aeroport> lesAeroports= Main.selectAllAeroports(mot);
		Object [][] matrice = new Object[lesAeroports.size()][8];
		int i =0;
		for (Aeroport unAeroport : lesAeroports)
		{
			matrice [i][0] = unAeroport.getIdaeroport();
			matrice [i][1] = unAeroport.getDesignation();
			matrice [i][2] = unAeroport.getAdresse();
			matrice [i][3] = unAeroport.getVille();
			matrice [i][4] = unAeroport.getCodepostal();
			matrice [i][5] = unAeroport.getPays();
			matrice [i][6] = unAeroport.getCategorie();
			matrice [i][7] = unAeroport.getIdavion();
			
			i++;
		}
		return matrice;
    }
	
	public void remplirCBXAvion () {
		// cette methode remplit le select (combp) des avions
		ArrayList<Avion> lesAvions = Main.selectAllAvions("");
		this.cbxAvion.removeAllItems();
		for (Avion unAvion : lesAvions) {
			this.cbxAvion.addItem(unAvion.getIdavion()+	" - " + unAvion.getDesignation()+" - " + unAvion.getTypeavion());
		}
 	}
	
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btRetour) {
            this.dispose();
            Main.rendreVisible(true);
        } else if (e.getSource() == this.btAnnuler) {
        	this.viderChamps ();
        } else if (e.getSource() == this.btEnregistrer && e.getActionCommand().equals("Enregistrer")) {
			String description = this.txtDesignation.getText();
			String adresse = this.txtAdresse.getText();
			String ville = this.txtVille.getText();
			String codepostal = this.txtCodePostal.getText();
			String pays = this.txtPays.getText();
			String categorie = this.cbxCategorie.getSelectedItem().toString();
			String chaine = this.cbxAvion.getSelectedItem().toString();
			String tab [] = chaine.split(" - ");
			int idavion = Integer.parseInt(tab [0]);

			//Instanciation de l'a�roport
			Aeroport unAeroport = new Aeroport (description, adresse, ville, codepostal, pays, categorie, idavion);
			
			//insertion dans la bdd
			Main.insertAeroport(unAeroport);

			//recuperation de l'ID a�roport affecte par le SGBD			
			unAeroport = Main.selectWhereAeroport(description, adresse);
			
			//actualiser l'affichage 
			Object ligne [] = {unAeroport.getIdaeroport(), description, adresse, ville, codepostal, pays, categorie, idavion};
				this.unTableau.ajouterLigne(ligne);				
				JOptionPane.showMessageDialog(this, "insertion reussie de l'aeroport");
				this.viderChamps();
        }else if (e.getSource() == this.btEnregistrer && e.getActionCommand().equals("Modifier")) {
        	String designation = this.txtDesignation.getText();
			String adresse = this.txtAdresse.getText();
			String ville = this.txtVille.getText();
			String codepostal = this.txtCodePostal.getText();
			String pays = this.txtPays.getText();
			String categorie = this.cbxCategorie.getSelectedItem().toString();
			String chaine = this.cbxAvion.getSelectedItem().toString();
			String tab [] = chaine.split(" - ");
			int idavion = Integer.parseInt(tab [0]);
			
			//recuperer l'id de l'a�roport		
			//Aeroport unAeroportID = Main.selectWhereAeroport(idAeroport);
			
			//Instanciation de l'a�roport
			Aeroport unAeroport = new Aeroport (iddelaeroport, designation, adresse, ville, codepostal, pays, categorie, idavion);	
			
			//maj dans la bdd			
			Main.updateAeroport(unAeroport);		
			
			//recuperer numero ligne			
			int numero = this.uneTable.getSelectedRow();		
			
			//actualiser l'affichage
			Object [] ligne = {iddelaeroport, designation, adresse, ville, codepostal, pays, categorie, idavion};
			this.unTableau.modifierLigne(numero, ligne);
			
			JOptionPane.showMessageDialog(this, "Modification reussie dans la Base de donn�es");
			this.viderChamps();
			this.btEnregistrer.setText("Enregistrer");
		}else if (e.getSource() == this.btFiltrer) {
			String mot = this.txtMot.getText();
			this.unTableau.setDonnees(this.remplirDonnees(mot));
        }
    }
    
    
    public void viderChamps () {
    	this.txtDesignation.setText("");
    	this.txtAdresse.setText("");
    	this.txtVille.setText("");
		this.txtCodePostal.setText("");
		this.txtPays.setText("");
    }
    
  
}
