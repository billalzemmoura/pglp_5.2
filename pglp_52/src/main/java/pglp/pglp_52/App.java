package pglp.pglp52;

import java.util.ArrayList;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    { 
    
    ArrayList<CompositePersonnels> perso=new ArrayList<CompositePersonnels>();
    	Personnels p5=new Personnels.Builder(5, "BILL", " zemmoura").build();
      Personnels p7=new Personnels.Builder(7, "riayd", " belhabib").build();
      Personnels p1=new Personnels.Builder(1, "BILL", " zemmoura").build();
      Personnels p2=new Personnels.Builder(2, "riayd", " belhabib").build();
  	
	CompositePersonnels p3 = new CompositePersonnels(3);
	CompositePersonnels p6 = new CompositePersonnels(6);
	p3.add(p7);
	p6.add(p5);

	p3.add(p6);
	p3.add(p2);
    DAO<CompositePersonnels> CDAO=new CompositeDao();
    CDAO.create(p3); 
    CompositePersonnels p10 = new CompositePersonnels(10);
    CDAO.create(p10);
    p10.add(p1);
    CDAO.update(p10);
    CDAO.delete(p3);
    perso=CompositeDao.lire();
    
    DAO<Personnels> pDAO=DAOFactory.getPersonnelDAO();
    pDAO.create(p1);
    pDAO.create(p2);
    pDAO.create(p5);
    pDAO.create(p7);
   p5.setNom("meriem");
   pDAO.update(p5);
   pDAO.delete(p7);
  // perso=personnelDAO.lire();
  
	  AffichageGroupe ag = new AffichageGroupe(perso.get(0));
		pglp.pglp52.Iterator i =  ag.getIterator();
		while (i.hasNext()) {

			 i.Next().afficher();
		}



   System.out.println(perso.size());
    }
}
