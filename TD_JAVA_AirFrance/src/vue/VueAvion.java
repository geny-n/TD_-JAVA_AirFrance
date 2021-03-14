package vue;

import java.awt.Color;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import controleur.Main;
import controleur.Avion;
import controleur.Tableau;

public class VueAvion extends JFrame implements ActionListener {
	
	private JButton btRetour = new JButton("Retour au menu");
	
	private JPanel panelAjout = new JPanel();
	private JTextField txtDesignation = new JTextField();
	private JTextField txtTypeavion = new JTextField();
	private JTextField txtConstructeur = new JTextField();
	private JTextField txtDatemisecirculation = new JTextField();
	private JTextField txtCapacite = new JTextField();
	private JTextField txtNbheuresvol = new JTextField();
	private int idavion;

	private JButton btAnnuler = new JButton("Annuler");
	private JButton btEnregistrer = new JButton("Enregistrer");
	
	//panel d'affichage des salariés
	private JPanel panelLister = new JPanel();
	private JTable uneTable;
	private JScrollPane uneScroll;
	private Tableau unTableau;
	
	//rechercher par mot 
	private JTextField txtMot = new JTextField();
	private JButton btFiltrer = new JButton("Filtrer");
	
	public VueAvion() {
		
		this.setTitle("Gestion des Avions GestEventsIris");
		this.setBounds(200, 200, 1200, 400);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setBackground(new Color (247, 218, 40));
		this.setLayout(null);
		
		this.btRetour.setBounds(570, 330, 130, 25);
		this.add(this.btRetour);
		this.btRetour.addActionListener(this);
		
		//construction du Panel Ajout 
		this.panelAjout.setBounds(10, 50, 320, 260);
		this.panelAjout.setBackground(new Color (247, 218, 40));
		this.panelAjout.setLayout(new GridLayout(7,2));
		
		this.panelAjout.add(new JLabel("Designation : "));
		this.panelAjout.add(this.txtDesignation);
		this.panelAjout.add(new JLabel("Type : "));
		this.panelAjout.add(this.txtTypeavion);
		this.panelAjout.add(new JLabel("Constructeur : "));
		this.panelAjout.add(this.txtConstructeur);
		this.panelAjout.add(new JLabel("Date mise en circulation : "));
		this.panelAjout.add(this.txtDatemisecirculation);
		this.panelAjout.add(new JLabel("Capacite : "));
		this.panelAjout.add(this.txtCapacite);
		this.panelAjout.add(new JLabel("Nombre d'heures de vol : "));
		this.panelAjout.add(this.txtNbheuresvol);
		
		this.panelAjout.add(this.btAnnuler);
		this.panelAjout.add(this.btEnregistrer);
		this.add(this.panelAjout);
		
		//rendre les boutons cliquables
		this.btAnnuler.addActionListener(this);
		this.btEnregistrer.addActionListener(this);
		
		// ajout du panel lister 
		this.panelLister.setBounds(340, 30, 850, 600);
		this.panelLister.setBackground(new Color (247, 218, 40));
		this.panelLister.setLayout(null);
		
		String entetes [] = {"Id Avion", "Designation", "Typeavion", "Constructeur", "Datemisecirculation", "Capacite", "Nbheuresvol"};
		
		//instanciation de la classe tableau avec les entetes et les donnees
		this.unTableau = new Tableau (entetes, this.remplirDonnees(""));
	
		//instanciation de la JTable avec le tableau construit 
		this.uneTable = new JTable(this.unTableau);
		
		//je mets ma table dans ma scroll
		this.uneScroll = new JScrollPane(this.uneTable);
		this.uneScroll.setBounds(20, 20, 780, 200);
		this.panelLister.add(this.uneScroll);
		this.add(this.panelLister);
		
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
					if (retour == 0) {					
						//on recupere l'id du salarie a supprimer
						idavion = Integer.parseInt(unTableau.getValueAt(ligne, 0).toString());
						//on supprime dans la bdd via le modele
						Main.deletetAvion(idavion);
						//suppression de la ligne de l'affichage 
						unTableau.supprimerLigne(ligne);
					}
				}else if (e.getClickCount() == 1) {
					int ligne = uneTable.getSelectedRow();
					idavion = Integer.parseInt(unTableau.getValueAt(ligne, 0).toString());
					txtDesignation.setText(unTableau.getValueAt(ligne, 1).toString());
					txtTypeavion.setText(unTableau.getValueAt(ligne, 2).toString());
					txtConstructeur.setText(unTableau.getValueAt(ligne, 3).toString());
					txtDatemisecirculation.setText(unTableau.getValueAt(ligne, 4).toString());
					txtCapacite.setText(unTableau.getValueAt(ligne, 5).toString());
					txtNbheuresvol.setText(unTableau.getValueAt(ligne, 6).toString());
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
		//cette méthode transforme l'ArrayList des salaries
		//en une matrice 
		
		ArrayList<Avion> lesAvions = Main.selectAllAvions(mot);
		Object [][] matrice = new Object[lesAvions.size()][8];
		int i =0;
		for (Avion unAvion : lesAvions)
		{
			matrice [i][0] = unAvion.getIdavion();
			matrice [i][1] = unAvion.getDesignation();
			matrice [i][2] = unAvion.getTypeavion();
			matrice [i][3] = unAvion.getConstructeur();
			matrice [i][4] = unAvion.getDatemisecirculation();
			matrice [i][5] = unAvion.getCapacite();
			matrice [i][6] = unAvion.getNbheuresvol();
			
			i++;
		}
		return matrice;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.btRetour ) {
			this.dispose();//je detruis la vue actuelle
			//et je rends visible la vue connexion avec son menu
			Main.rendreVisible(true);
		}else if (e.getSource() == this.btAnnuler) {
			this.viderChamps ();
		}else if (e.getSource() == this.btEnregistrer && e.getActionCommand().equals("Enregistrer")) {
			String designation = this.txtDesignation.getText();
			String typeavion = this.txtTypeavion.getText();
			String constructeur = this.txtConstructeur.getText();
            String datemisecirculation = this.txtDatemisecirculation.getText();
            int capacite = Integer.parseInt(this.txtCapacite.getText());
            int nbheuresvol = Integer.parseInt(this.txtNbheuresvol.getText());
			
			//Instanciation du salarie 
			Avion unAvion = new Avion (designation, typeavion, constructeur, datemisecirculation, capacite, nbheuresvol);
			
			//insertion dans la bdd
			Main.insertAvion(unAvion);
			
			//recuperation de l'ID salarie affecte par le SGBD
			unAvion = Main.selectWhereAvion(designation, typeavion);
			
			//actualiser l'affichage 
			Object [] ligne = {unAvion.getIdavion(), designation, typeavion, constructeur, datemisecirculation, capacite, nbheuresvol};
			this.unTableau.ajouterLigne(ligne);
			
			JOptionPane.showMessageDialog(this, "Insertion réussie dans la Base de données");
			this.viderChamps();
		}else if (e.getSource() == this.btEnregistrer && e.getActionCommand().equals("Modifier")) {
			String designation = this.txtDesignation.getText();
			String typeavion = this.txtTypeavion.getText();
			String constructeur = this.txtConstructeur.getText();
            String datemisecirculation = this.txtDatemisecirculation.getText();
            int capacite = Integer.parseInt(this.txtCapacite.getText());
            int nbheuresvol = Integer.parseInt(this.txtNbheuresvol.getText());
			
			//recupérer l'id du salarié
			//Avion unAvionId = Main.selectWhereAvion(designation, typeavion);
			
			//Instanciation du salarie 
			Avion unAvion = new Avion (idavion, designation, typeavion, constructeur, datemisecirculation, capacite, nbheuresvol);
			
			//maj dans la bdd
			Main.updateAvion(unAvion);
			
			//recuperer numero de ligne
			int numero = this.uneTable.getSelectedRow();
			
			//actualiser l'affichage 
			Object [] ligne = {idavion, designation, typeavion, constructeur, datemisecirculation, capacite, nbheuresvol};
			this.unTableau.modifierLigne(numero, ligne);
			
			JOptionPane.showMessageDialog(this, "Modification réussie dans la Base de données");
			this.viderChamps();
			this.btEnregistrer.setText("Enregistrer");
		}else if (e.getSource() == this.btFiltrer) {
			String mot = this.txtMot.getText();
			this.unTableau.setDonnees(this.remplirDonnees(mot));
		}
		
	}
	
	public void viderChamps() {
		this.txtDesignation.setText("");
		this.txtTypeavion.setText("");
		this.txtConstructeur.setText("");
		this.txtDatemisecirculation.setText("");
		this.txtCapacite.setText("");
		this.txtNbheuresvol.setText("");
		this.btEnregistrer.setText("Enregistrer");
	}
}
