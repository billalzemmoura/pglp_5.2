package pglp.pglp52;
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
 private int  IdGroupe;
 public CompositePersonnels(int IdGroupe ) {
	 this.IdGroupe=IdGroupe;
	 
	 
 }
 public int getIDGroupe() {
	return IdGroupe;
}
  public void afficher() {
		System.out.println("lidentifiant du groupe :"+IdGroupe);
		
		
	}
  
  public void add(InterfacePersonnels ajouter ) {
	  personelles.add(ajouter);
	  
	  
  }
  public void remove(InterfacePersonnels ajouter ) {
	  
	  personelles.remove(ajouter);
	  
  }



}
