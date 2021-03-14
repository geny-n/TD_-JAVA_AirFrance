package vue;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controleur.Main;
import controleur.Pilote;

public class VueConnexion extends JFrame implements ActionListener, KeyListener{

	//panel connexion
	private JPanel panelCon =new JPanel();
	private JTextField txtEmail = new JTextField();
	private JPasswordField txtMdp = new JPasswordField();
	private JButton btAnnuler = new JButton("Annuler");
	private JButton btSeConnecter = new JButton("Se Connecter");
	
	//panel menu 
	private JPanel panelMenu =new JPanel();
	private JButton btPilotes = new JButton("Gerer les Pilotes");
	private JButton btAvions = new JButton("Gerer les Avions");
	private JButton btVols = new JButton("Gerer les Vols");
	private JButton btAeroports = new JButton("Gerer les Aeroports");
	private JButton btQuitter = new JButton("Quitter");

	
	public VueConnexion () {
		this.setTitle("Connexion a l'application AirFranceSIOA");
		this.setBounds(200, 200, 1150, 500);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setBackground(new Color (247, 218, 40));
		this.setLayout(null);
		
		//insertion du logo 
		ImageIcon uneImage = new ImageIcon("src/images/logo.jpg");
		JLabel monLogo = new JLabel(uneImage);
		monLogo.setBounds(10, 20, 430, 420);
		this.add(monLogo);
		
		//insertion du pannel connexion 
		this.panelCon.setBounds(470, 40, 500, 350);
		this.panelCon.setBackground(new Color(247, 218, 40));
		this.panelCon.setLayout(new GridLayout(3, 2));
		
		this.panelCon.add(new JLabel("Email : "));
		this.panelCon.add(this.txtEmail);
		this.panelCon.add(new JLabel("MDP : "));
		this.panelCon.add(this.txtMdp);
		this.panelCon.add(this.btAnnuler);
		this.panelCon.add(this.btSeConnecter);
		
		//insertion du pannel 
		this.add(this.panelCon);
		
		//rendre les boutons cliquables
		this.btAnnuler.addActionListener(this);
		this.btSeConnecter.addActionListener(this);
		
		//rendre les TXT ecoutables sur frappe de touche
		this.txtEmail.addKeyListener(this);
		this.txtMdp.addKeyListener(this);
		
		//contruction du panel menu
		this.panelMenu.setBounds(470, 40, 520, 200);
		this.panelMenu.setBackground(new Color(247, 218, 40));
		this.panelMenu.setLayout(new GridLayout(2, 2));
		this.panelMenu.add(this.btPilotes);
		this.panelMenu.add(this.btAvions);
		this.panelMenu.add(this.btVols);
		this.panelMenu.add(this.btAeroports);
		this.panelMenu.add(this.btQuitter);
		this.panelMenu.setVisible(false);
		this.add(this.panelMenu);
		
		//rendre les boutons du panel menu ecoutables 
		this.btPilotes.addActionListener(this);
		this.btAvions.addActionListener(this);
		this.btVols.addActionListener(this);
		this.btAeroports.addActionListener(this);
		this.btQuitter.addActionListener(this);
		
		
		this.setVisible(true);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource()== this.btAnnuler) {
			this.txtEmail.setText("");
			this.txtMdp.setText("");
		}else if (e.getSource() == this.btSeConnecter) {
			this.traitement();
		}else if (e.getSource() == this.btQuitter) {
			int retour = JOptionPane.showConfirmDialog(this, "Voulez-vous vraiment quitter l'application ?", "Quitter l'application", JOptionPane.YES_NO_OPTION);
			if (retour == 0) {
			this.txtEmail.setText("");
			this.txtMdp.setText("");
			this.panelCon.setVisible(true);
			this.panelMenu.setVisible(false);
			}
		}else if (e.getSource()== this.btPilotes) {
			//on cache la fenetre de connexion et on ouvre la fenetre des salaries
			Main.rendreVisible(false);
			Main.instancierVuePilote();
			
		}else if (e.getSource()== this.btAvions) {
			//on cache la fenetre de connexion et on ouvre la fenetre des salaries
			Main.rendreVisible(false);
			
			Main.instancierVueAvion();
		}else if (e.getSource()== this.btVols) {
			//on cache la fenetre de connexion et on ouvre la fenetre des salaries
			Main.rendreVisible(false);
			
			Main.instancierVueVol();
		}else if (e.getSource()== this.btAeroports) {
			//on cache la fenetre de connexion et on ouvre la fenetre des salaries
			Main.rendreVisible(false);
			
			Main.instancierVueAeroport();
		}

	}

	public void traitement () {
		String email = this.txtEmail.getText();
		String mdp = new String (this.txtMdp.getPassword());
		//on verifie dans la base de donn�es en passant par le controleur
		Pilote unPilote = Main.selectWherePilote(email, mdp);
		if (unPilote == null) {
			JOptionPane.showMessageDialog(this, "Veuillez verifier vos identifiants !", "Erreur de connexion", JOptionPane.ERROR_MESSAGE);
		}else {
			JOptionPane.showMessageDialog(this, " Bienvenue M/Mde "+unPilote.getNom()+" "+unPilote.getPrenom(), "Connexion reussie a GestIrisEvents", JOptionPane.INFORMATION_MESSAGE);
			this.panelCon.setVisible(false);
			this.panelMenu.setVisible(true);
		}
	}
	@Override
	public void keyTyped(KeyEvent e) {
	
		
	}


	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode()== KeyEvent.VK_ENTER) {
			this.traitement();
		}
		
	}


	@Override
	public void keyReleased(KeyEvent e) {
	
		
	}
}
