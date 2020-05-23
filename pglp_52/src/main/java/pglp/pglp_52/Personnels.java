package pglp.pglp_52;


import java.io.Serializable;
import java.util.ArrayList;
import java.sql.Date;
import java.time.LocalDate;

public class Personnels implements InterfacePersonnels,Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int getIdPersonnels() {
		return IdPersonnels;
	}
	public int idGRP;
	public int getIdGRP() {
		return idGRP;
	}
	public void setIdGRP(int idGRP) {
		this.idGRP = idGRP;
	}
	public LocalDate getDate() {return dateDeNaissance;}
	public String IDPersoToString() {
		return Integer.toString(IdPersonnels);
	}
	public void setIdPersonnels(int idPersonnels) {
		this.IdPersonnels = idPersonnels;
	}
	public String getNom() {
		return Nom;
	}
	public void AddNum(String tel) {
		Tel.add(tel);
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
	public ArrayList<String> getTel() {
		return Tel;
	}
	public void setTel(ArrayList<String> tel) {
		this.Tel = tel;
	}
	/**
	 * 
	 */
	
	int IdPersonnels;
	String Nom;
	String prenom;
	LocalDate dateDeNaissance;
	ArrayList<String> Tel;
	public Personnels(Builder builder) {
		IdPersonnels=builder.IdPersonnels;
		Nom=builder.Nom;
		prenom=builder.prenom;
		dateDeNaissance=builder.dateDeNaissance;
		Tel=builder.Tel;
		idGRP=builder.idGRP;
	}
	public void afficher() {
		System.out.println(" personnelle:"+IdPersonnels);
		
	}
	public static class Builder{
		

		public int idGRP;
		private final int IdPersonnels;
		private final String Nom;
		private final String prenom;
		LocalDate dateDeNaissance;
		ArrayList<String>Tel=new ArrayList<String>();
		
		public Builder(int IdPersonnels,String Nom,String prenom ,LocalDate localDate) 
		{
			this.IdPersonnels=IdPersonnels;
			this.Nom=Nom;
			this.prenom=prenom;
			this.dateDeNaissance=localDate;
			this.idGRP=IdPersonnels;
		}
		public int getIdGRP() {
			return idGRP;
		}
		public void setIdGRP(int idGRP) {
			this.idGRP = idGRP;
		}
		public Builder TEL(ArrayList<String> Tel ) 
		{
			this.Tel=Tel;
			return this;
			
			
		}
		public Builder GRP(int idGRP ) 
		{
			this.idGRP=idGRP;
			return this;
			
			
		}
	public Personnels build() {
		return new Personnels(this);
	}
	
	
	}
	

}
