package controleur;

import javax.swing.table.AbstractTableModel;

public class Tableau extends AbstractTableModel {

	private String [] entetes; //liste des colonnes entetes
	private Object [] [] donnees; //la matrice des donnees
	
	public Tableau (String entetes[], Object donnees[][]) {
		this.entetes = entetes;
		this.donnees = donnees;
	}
	
	@Override
	public int getRowCount() {
	//nombre de lignes
		return this.donnees.length;
	}

	@Override
	public int getColumnCount() {
	//nombre de colonnes
		return this.entetes.length;
	}

	@Override
	public Object getValueAt(int ligne, int colonne) {
		//objet se trouvant au croisement de la ligne et de la colonne
		return this.donnees[ligne][colonne];
	}

	@Override
	public String getColumnName(int colonne) {
		//retourner le nom de la colonne
		return this.entetes[colonne];
	}

	public void ajouterLigne(Object ligne[]) {
		Object [] [] matrice = new Object [this.donnees.length +1] [this.entetes.length];
		//parcours recopie des donnees
		for (int i = 0; i< this.donnees.length; i++) {
			matrice[i] = this.donnees[i];
		}
		//on ajoute la derni�re ligne
		matrice [this.donnees.length] = ligne;
		//on actualise la donn�e
		this.donnees = matrice;
		this.fireTableDataChanged();
	}
	
	public void supprimerLigne(int numero) {
		Object [] [] matrice = new Object [this.donnees.length -1] [this.entetes.length];
		//parcours recopie des donnees
		int j = 0;
		for (int i = 0; i< this.donnees.length; i++) {
			if (numero != i) {
			matrice[j] = this.donnees[i];
			j++;
			}
		}
		//on actualise la donn�e
		this.donnees = matrice;
		this.fireTableDataChanged();
	}
	
	public void modifierLigne (int numero, Object ligne[]) {
		Object [] [] matrice = new Object [this.donnees.length] [this.entetes.length];
		//parcours recopie des donnees
		int j = 0;
		for (int i = 0; i< this.donnees.length; i++) {
			if (numero != i) {
			matrice[j] = this.donnees[i];
			j++;
			}else {
				matrice [j] = ligne;
				j++;
			}
		}
		//on actualise la donn�e
		this.donnees = matrice;
		this.fireTableDataChanged();
	}
	
	
	public void setDonnees (Object [][] newDonnees) {
		//changement complet de toute la matrice des donn�es 
		this.donnees = newDonnees;
		this.fireTableDataChanged();
	}
}
