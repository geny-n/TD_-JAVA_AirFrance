package vue;

import java.awt.Color;

import java.awt.Font;
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
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import controleur.Main;
import controleur.Pilote;
import controleur.Tableau;

public class VuePilote extends JFrame implements ActionListener {
	
	private JButton btRetour = new JButton("Retour au menu");
	
	private JPanel panelAjout = new JPanel();
	private JTextField txtNom = new JTextField();
	private JTextField txtPrenom = new JTextField();
	private JTextField txtEmail = new JTextField();
	private JTextField txtTel = new JTextField();
	private JTextField txtNationalite = new JTextField();
    private JTextField txtDateentree = new JTextField();
    private JTextField txtNbheuresvol = new JTextField();
	private JPasswordField txtMdp = new JPasswordField();
	private int iddupilote;
	
	private JButton btAnnuler = new JButton("Annuler");
	private JButton btEnregistrer = new JButton("Enregistrer");
	
	//panel d'affichage des salari�s
	private JPanel panelLister = new JPanel();
	private JTable uneTable;
	private JScrollPane uneScroll;
	private Tableau unTableau;
	
	//rechercher par mot 
	private JTextField txtMot = new JTextField();
	private JButton btFiltrer = new JButton("Filtrer");
	
	public VuePilote () {
		this.setTitle("Gestion des Pilotes AirFranceSIOA");
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
		this.panelAjout.setLayout(new GridLayout(9,2));
		
		/*changement format text*/
		Font font = new Font("TimesRoman", Font.PLAIN, 20) ;
		JLabel l = new JLabel("Nom Pilote : ");
		l.setFont(font);
		this.panelAjout.add(l);	
		this.txtNom.setFont(font);
		this.panelAjout.add(this.txtNom);

		/*this.panelAjout.add(new JLabel("Nom pilote : "));
		this.panelAjout.add(this.txtNom);*/
		this.panelAjout.add(new JLabel("Prenom pilote : "));
		this.panelAjout.add(this.txtPrenom);
		this.panelAjout.add(new JLabel("email : "));
		this.panelAjout.add(this.txtEmail);
		this.panelAjout.add(new JLabel("mdp : "));
		this.panelAjout.add(this.txtMdp);
		this.panelAjout.add(new JLabel("tel : "));
		this.panelAjout.add(this.txtTel);
		this.panelAjout.add(new JLabel("Nationalite : "));
		this.panelAjout.add(this.txtNationalite);
        this.panelAjout.add(new JLabel("Date entree : "));
		this.panelAjout.add(this.txtDateentree);
		this.panelAjout.add(new JLabel("Nombre heure vol : "));
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
		
		String entetes [] = {"Id Pilote", "Nom", "Prenom", "Email", "Mdp", "Tel", "Nationalite", "Dateentree", "Nbheuresvol"};
		
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
					if (retour ==0) {					
						//on recupere l'id du salarie a supprimer
						int idpilote = Integer.parseInt(unTableau.getValueAt(ligne, 0).toString());
						//on supprime dans la bdd via le modele
						Main.deletetPilote(idpilote);
						//suppression de la ligne de l'affichage 
						unTableau.supprimerLigne(ligne);
					}
				}else if (e.getClickCount() == 1) {
					int ligne = uneTable.getSelectedRow();
					iddupilote = Integer.parseInt(unTableau.getValueAt(ligne, 0).toString());
					txtNom.setText(unTableau.getValueAt(ligne, 1).toString());
					txtPrenom.setText(unTableau.getValueAt(ligne, 2).toString());
					txtEmail.setText(unTableau.getValueAt(ligne, 3).toString());
					txtMdp.setText(unTableau.getValueAt(ligne, 4).toString());
					txtTel.setText(unTableau.getValueAt(ligne, 5).toString());
					txtNationalite.setText(unTableau.getValueAt(ligne, 6).toString());
                    txtDateentree.setText(unTableau.getValueAt(ligne, 7).toString());
                    txtNbheuresvol.setText(unTableau.getValueAt(ligne, 8).toString());
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
		//cette m�thode transforme l'ArrayList des salaries
		//en une matrice 
		
		ArrayList<Pilote> lesPilotes = Main.selectAllPilotes(mot);
		Object [][] matrice = new Object[lesPilotes.size()][10];
		int i = 0;
		for (Pilote unPilote : lesPilotes)
		{
			matrice [i][0] = unPilote.getIdpilote();
			matrice [i][1] = unPilote.getNom();
			matrice [i][2] = unPilote.getPrenom();
			matrice [i][3] = unPilote.getEmail();
			matrice [i][4] = unPilote.getMdp();
			matrice [i][5] = unPilote.getTel();
			matrice [i][6] = unPilote.getNationalite();
			matrice [i][7] = unPilote.getDateentree();
			matrice [i][8] = unPilote.getNbheuresvol();
			
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
			String nom = this.txtNom.getText();
			String prenom = this.txtPrenom.getText();
			String email = this.txtEmail.getText();
            String mdp = new String (this.txtMdp.getPassword());
			String tel = this.txtTel.getText();
			String nationalite = this.txtNationalite.getText();
            String dateentree = this.txtDateentree.getText();
            int nbheuresvol = Integer.parseInt(this.txtNbheuresvol.getText());
			
			
			//Instanciation du salarie 
			Pilote unPilote = new Pilote (nom, prenom, email, mdp, tel, nationalite, dateentree, nbheuresvol);
			
			//insertion dans la bdd
			Main.insertPilote(unPilote);
			
			//recuperation de l'ID salarie affecte par le SGBD
			unPilote = Main.selectWherePilote(email, mdp);
			
			//actualiser l'affichage 
			Object [] ligne = {unPilote.getIdpilote(), nom, prenom, email, mdp, tel, nationalite, dateentree, nbheuresvol};
			this.unTableau.ajouterLigne(ligne);
			
			JOptionPane.showMessageDialog(this, "Insertion reussie dans la Base de donnees");
			this.viderChamps();
		}else if (e.getSource() == this.btEnregistrer && e.getActionCommand().equals("Modifier")) {
			String nom = this.txtNom.getText();
			String prenom = this.txtPrenom.getText();
			String email = this.txtEmail.getText();
            String mdp = new String (this.txtMdp.getPassword());
			String tel = this.txtTel.getText();
			String nationalite = this.txtNationalite.getText();
            String dateentree = this.txtDateentree.getText();
            int nbheuresvol = Integer.parseInt(this.txtNbheuresvol.getText());
			
			//recup�rer l'id du salari�
			//Pilote unPiloteId = Main.selectWherePilote(email, mdp);
			
			//Instanciation du salarie 
			Pilote unPilote = new Pilote(iddupilote, nom, prenom, email, mdp, tel, nationalite, dateentree, nbheuresvol);
			
			//maj dans la bdd
			Main.updatePilote(unPilote);
			
			//recuperer numero de ligne
			int numero = this.uneTable.getSelectedRow();
			
			//actualiser l'affichage 
			Object [] ligne = {iddupilote, nom, prenom, email, mdp, tel, nationalite, dateentree, nbheuresvol};
			this.unTableau.modifierLigne(numero, ligne);
			
			JOptionPane.showMessageDialog(this, "Modification reussie dans la Base de donnees");
			this.viderChamps();
			this.btEnregistrer.setText("Enregistrer");
		}else if (e.getSource() == this.btFiltrer) {
			String mot = this.txtMot.getText();
			this.unTableau.setDonnees(this.remplirDonnees(mot));
		}
		
	}
	
	public void viderChamps() {
		this.txtNom.setText("");
		this.txtPrenom.setText("");
		this.txtMdp.setText("");
		this.txtEmail.setText("");
		this.txtTel.setText("");
		this.txtNationalite.setText("");
        this.txtDateentree.setText("");
		this.txtNbheuresvol.setText("");
		this.btEnregistrer.setText("Enregistrer");
	}
}
