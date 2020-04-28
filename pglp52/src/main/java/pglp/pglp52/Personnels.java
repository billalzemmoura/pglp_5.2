package pglp.pglp52;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Personnels implements InterfacePersonnels,Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int getIdPersonnels() {
		return IdPersonnels;
	}
	public void setIdPersonnels(int idPersonnels) {
		this.IdPersonnels = idPersonnels;
	}
	public String getNom() {
		return Nom;
	}
	public void setNom(String nom) {
		this.Nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public ArrayList<Integer> getTel() {
		return Tel;
	}
	public void setTel(ArrayList<Integer> tel) {
		this.Tel = tel;
	}
	/**
	 * 
	 */
	
	int IdPersonnels;
	String Nom;
	String prenom;
	Date dateDeNaissance;
	ArrayList<Integer>Tel;
	public Personnels(Builder builder) {
		IdPersonnels=builder.IdPersonnels;
		Nom=builder.Nom;
		prenom=builder.prenom;
		dateDeNaissance=builder.dateDeNaissance;
		Tel=builder.Tel;
		
	}
	public void afficher() {
		System.out.println("lidentifiant de personelle:"+IdPersonnels);
		
	}
	public static class Builder{
		

		private final int IdPersonnels;
		private final String Nom;
		private final String prenom;
		Date dateDeNaissance;
		ArrayList<Integer>Tel=new ArrayList<Integer>();
		
		public Builder(int IdPersonnels,String Nom,String prenom ) 
		{
			this.IdPersonnels=IdPersonnels;
			this.Nom=Nom;
			this.prenom=prenom;
			this.dateDeNaissance=dateDeNaissance;
			
		}
		public Builder TEL(Integer tel) 
		{
			this.Tel.add(tel);
			return this;
			
			
		}
	public Personnels build() {
		return new Personnels(this);
	}
	
	
	}
	

}
