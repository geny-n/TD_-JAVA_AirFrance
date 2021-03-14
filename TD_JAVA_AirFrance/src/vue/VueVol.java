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

import controleur.Avion;
import controleur.Main;
import controleur.Pilote;
import controleur.Vol;
import controleur.Tableau;

public class VueVol extends JFrame implements ActionListener{

	private JButton btRetour = new JButton("Retour au menu");
	
	//d�claration du panel ajout 
	private JPanel panelAjout = new JPanel();
	private JPanel panelLister = new JPanel();
	private JTextField txtDesignation = new JTextField();
	private JTextField txtOrigine = new JTextField();
	private JTextField txtDestination = new JTextField();
	private JTextField txtDatevol = new JTextField();
	private JTextField txtHeurevol = new JTextField();
	private JComboBox<String> cbxPilote = new JComboBox<String>();
	private JComboBox<String> cbxAvion = new JComboBox<String>();
	private JButton btAnnuler = new JButton("Annuler");
	private JButton btEnregistrer = new JButton("Enregistrer");
	
	//panel lister : gestion du tableau des evenements
	private JTable uneTable;
	private Tableau unTableau; 
	private JScrollPane uneScroll;
	
	//rechercher par mot cl� 
	private JTextField txtMot = new JTextField();
	private JButton btFiltrer = new JButton("Filtrer");
	
	public VueVol() {
		
		this.setTitle("Gestion des Vols GestEventsIris");
		this.setBounds(200, 200, 1200, 400);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setBackground(new Color (247, 218, 40));
		this.setLayout(null);
		
		this.btRetour.setBounds(570, 330, 130, 25);
		this.add(this.btRetour);
		this.btRetour.addActionListener(this);
		
		//construction du panel Ajout 
		this.panelAjout.setBounds(10, 50, 320, 260);
		this.panelAjout.setBackground(new Color (247, 218, 40));
		this.panelAjout.setLayout(new GridLayout(8,2));
		
		this.panelAjout.add(new JLabel("Designation : "));
		this.panelAjout.add(this.txtDesignation);
		this.panelAjout.add(new JLabel("Origine : "));
		this.panelAjout.add(this.txtOrigine);
		this.panelAjout.add(new JLabel("Destination : "));
		this.panelAjout.add(this.txtDestination);
		this.panelAjout.add(new JLabel("Datevol : "));
		this.panelAjout.add(this.txtDatevol);
		this.panelAjout.add(new JLabel("Heurevol : "));
		this.panelAjout.add(this.txtHeurevol);
		this.panelAjout.add(new JLabel("Pilote : "));
		this.panelAjout.add(this.cbxPilote);
		this.panelAjout.add(new JLabel("Avion : "));
		this.panelAjout.add(this.cbxAvion);
		
		this.panelAjout.add(this.btAnnuler);
		this.panelAjout.add(this.btEnregistrer);
		this.add(this.panelAjout);
		
		//remplir le CBX Salarie 
		this.remplirCBXPilote();
        this.remplirCBXAvion();
		
		//rendre les deux boutons cliquables 
		this.btAnnuler.addActionListener(this);
		this.btEnregistrer.addActionListener(this);
		
		// ajout du panel lister 
		this.panelLister.setBounds(340, 30, 850, 600);
		this.panelLister.setBackground(new Color (247, 218, 40));
		this.panelLister.setLayout(null);
		
		String entetes [] = {"Id Vol", "Designation", "Origine", "Destination", "Datevol", "Heurevol",
            "Pilote", "Avion"};
		//instanciation de la classe tableau avec les entetes et les donnees
		this.unTableau = new Tableau (entetes, this.remplirDonnees(""));
			
		//instanciation de la JTable avec le tableau construit 
		this.uneTable = new JTable(this.unTableau);
				
		//je mets ma table dans ma scroll
		this.uneScroll = new JScrollPane(this.uneTable);
		this.uneScroll.setBounds(20, 20, 780, 200);
		this.panelLister.add(this.uneScroll);
		this.add(this.panelLister);
		
		//remplir combobox des statuts et type 
		/*this.txtStatut.addItem("programe");
		this.txtStatut.addItem("termine");
		this.txtStatut.addItem("annule");
		this.txtTypeEvent.addItem("depart");
		this.txtTypeEvent.addItem("arrivee");
		this.txtTypeEvent.addItem("anniversaire");
		this.txtTypeEvent.addItem("mariage");
		this.txtTypeEvent.addItem("autre");*/       
		
		
		//placement zone de recherche
		this.txtMot.setBounds(200, 10, 130, 25);
		this.add(this.txtMot);
		this.btFiltrer.setBounds(370, 10, 130, 25);
		this.add(this.btFiltrer);
		this.btFiltrer.addActionListener(this);
				
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
						//on recupere l'id du salarie a supprimer
						int idVol = Integer.parseInt(unTableau.getValueAt(ligne, 0).toString());
						//on supprime dans la bdd via le modele
						Main.deleteVol(idVol);
						//suppression de la ligne de l'affichage 
						unTableau.supprimerLigne(ligne);
					}
				}else if (e.getClickCount() == 1) {
					int ligne = uneTable.getSelectedRow();
                    txtDesignation.setText(unTableau.getValueAt(ligne, 1).toString());
					txtOrigine.setText(unTableau.getValueAt(ligne, 2).toString());
                    txtDestination.setText(unTableau.getValueAt(ligne, 3).toString());
					txtDatevol.setText(unTableau.getValueAt(ligne, 4).toString());
                    txtHeurevol.setText(unTableau.getValueAt(ligne, 5).toString());
					//pas de modif pour le combo statut et type et idsalarie
					btEnregistrer.setText("Modifier");
				}
				
			}
		});
				
		this.setVisible(true);
	}
	
	public Object[][] remplirDonnees (String mot) {
		ArrayList<Vol> lesVols = Main.selectAllVols(mot);
		Object [][] matrice = new Object[lesVols.size()][8];
		int i =0;
		for (Vol unVol : lesVols)
		{
			matrice [i][0] = unVol.getIdvol();
			matrice [i][1] = unVol.getDesignation();
			matrice [i][2] = unVol.getOrigine();
			matrice [i][3] = unVol.getDestination();
			matrice [i][4] = unVol.getDatevol();
			matrice [i][5] = unVol.getHeurevol();
			matrice [i][6] = unVol.getIdpilote();
            matrice [i][7] = unVol.getIdavion();
			
			i++;
		}
		return matrice;
	}
	
	public void remplirCBXPilote () {
		//cette m�thode remplit le select (combo) des salaries
		ArrayList<Pilote> lesPilotes = Main.selectAllPilotes("");
		this.cbxPilote.removeAllItems();
		for (Pilote unPilote : lesPilotes) {
			this.cbxPilote.addItem(unPilote.getIdpilote()+" - "+unPilote.getEmail()+" - "+unPilote.getNom());
		}
	}

    public void remplirCBXAvion () {
		//cette m�thode remplit le select (combo) des salaries
		ArrayList<Avion> lesAvions = Main.selectAllAvions("");
		this.cbxAvion.removeAllItems();
		for (Avion unAvion : lesAvions) {
			this.cbxAvion.addItem(unAvion.getIdavion()+" - "+unAvion.getDesignation()+" - "+unAvion.getTypeavion());
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		 if (e.getSource() == this.btRetour) {
			 this.dispose();   
			 Main.rendreVisible(true);
		 }
		 else if (e.getSource() == this.btAnnuler)
		 {
			 this.viderChamps (); 
		 }
		 else if (e.getSource() == this.btEnregistrer && e.getActionCommand().equals("Enregistrer"))
		 {
            String designation = this.txtDesignation.getText(); 
			String origine = this.txtOrigine.getText(); 
			String destination = this.txtDestination.getText(); 
			String datevol = this.txtDatevol.getText();
			int heurevol = Integer.parseInt(this.txtHeurevol.getText());
			String chaine = this.cbxPilote.getSelectedItem().toString(); 
			String tab [] = chaine.split(" - "); 
            String chaine1 = this.cbxAvion.getSelectedItem().toString(); 
			String tab1 [] = chaine1.split(" - "); 
			int idpilote = Integer.parseInt(tab[0]); 
            int idavion = Integer.parseInt(tab1[0]); 
			 
			Vol unVol = new Vol (designation, origine, destination, datevol, heurevol, idpilote, idavion);
			 
			 Main.insertVol(unVol);
			 
			 unVol = Main.selectWhereVol(designation, destination); 
			 JOptionPane.showMessageDialog(this, "Insertion reussie de l'evenement");
			 Object ligne[] = {unVol.getIdvol(), designation, origine, destination, datevol, heurevol, idpilote, idavion}; 
			 this.unTableau.ajouterLigne(ligne);
			 this.viderChamps(); 
		 }
		 else if (e.getSource() == this.btFiltrer)
		 {
			 String mot = this.txtMot.getText(); 
			 this.unTableau.setDonnees(this.remplirDonnees(mot));
		 }
		 else if (e.getSource() == this.btEnregistrer && e.getActionCommand().equals("Modifier"))
		 {
			String designation = this.txtDesignation.getText(); 
			String origine = this.txtOrigine.getText(); 
			String destination = this.txtDestination.getText(); 
			String datevol = this.txtDatevol.getText(); 
			int heurevol = Integer.parseInt(this.txtHeurevol.getText());
			String chaine = this.cbxPilote.getSelectedItem().toString(); 
			String tab [] = chaine.split(" - "); 
            String chaine1 = this.cbxAvion.getSelectedItem().toString(); 
			String tab1 [] = chaine1.split(" - "); 
			int idpilote = Integer.parseInt(tab[0]); 
            int idavion = Integer.parseInt(tab1[0]);  
			 
			int numero = this.uneTable.getSelectedRow();
			 
			int idVol = Integer.parseInt(unTableau.getValueAt(numero,0).toString()) ;
				
			Vol unVolID = Main.selectWhereVol(idVol);
			 
			 Vol unVol = new Vol (unVolID.getIdvol(), designation, origine, destination, datevol, heurevol, idpilote, idavion);
			 
			 //mise a jour dans la base de donn�es 
			 Main.updateVol(unVol);
			 //recuperer numero ligne
			 
			 
			 //actualiser l'affichage 
			 Object [] ligne = {unVolID.getIdvol(), designation, origine, destination, datevol, heurevol, idpilote, idavion};
			 this.unTableau.modifierLigne(numero, ligne);
			 
			 JOptionPane.showMessageDialog(this, "Modification reussie dans la Base de donnees");
			 this.viderChamps();
			 this.btEnregistrer.setText("Enregistrer");
		 }
	}
	
	public void viderChamps () {
		this.txtDesignation.setText("");
		this.txtOrigine.setText("");
		this.txtDestination.setText("");
		this.txtDatevol.setText("");
		this.txtHeurevol.setText("");
		this.btEnregistrer.setText("Enregistrer");
	}
	

}
