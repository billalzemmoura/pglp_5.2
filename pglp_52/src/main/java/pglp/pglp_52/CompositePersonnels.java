package pglp.pglp_52;
import java.io.Serializable;
import java.util.ArrayList;
public class CompositePersonnels implements InterfacePersonnels ,Serializable{


  /**
	 * 
	 */
	private static final long serialVersionUID = -7664744119712177776L;
/**
	 * 
	 */
	
public ArrayList <InterfacePersonnels> personelles=new ArrayList<InterfacePersonnels>();
 public ArrayList<InterfacePersonnels> getPersonelles() {
	return personelles;
}
public void setPersonelles(ArrayList<InterfacePersonnels> personelles) {
	this.personelles = personelles;
}
private int  IdGroupe;
private int  IdGroupePere;
 public CompositePersonnels(int IdGroupe ) {
	 this.IdGroupe=IdGroupe;
	 
	 
 }
 public int getIDGroupe() {
	return IdGroupe;
}
  public void afficher() {
		System.out.println(" Groupe "+IdGroupe);
		System.out.println(" Groupe "+IdGroupe+" : contient le(s)  "+personelles.size() +" suivant(s)");
		
		
	}
  
  public void add(InterfacePersonnels ajouter ) {
	  if (ajouter instanceof Personnels) {
       ((Personnels)(ajouter)).setIdGRP(getIDGroupe());
	}
	  
	  personelles.add(ajouter);
	  
	  
  }
  public void remove(InterfacePersonnels ajouter ) {
	if (ajouter instanceof Personnels) {
		((Personnels) ajouter).idGRP=-1;
	}else {
	((CompositePersonnels)ajouter).IdGroupe=-1;  
	}
	
	personelles.remove(ajouter);
	  
  }


}
