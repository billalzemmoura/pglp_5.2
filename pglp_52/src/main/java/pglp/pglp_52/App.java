package pglp.pglp_52;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception
    { 
    	//CreateTableExample a =new CreateTableExample();
   
    	ArrayList<CompositePersonnels> perso=new ArrayList<CompositePersonnels>();
    Personnels p5=new Personnels.Builder(9, "BILL", " zemmoura",LocalDate.parse("1996-10-18", DateTimeFormatter.ISO_DATE) ).build();
    Personnels p7=new Personnels.Builder(7, "riayd", " belhabib",LocalDate.parse("1997-11-06",DateTimeFormatter.ISO_DATE)).build();
    Personnels p1=new Personnels.Builder(11, "omar", " zemmoura",LocalDate.parse("1996-02-02", DateTimeFormatter.ISO_DATE)).build();
    //Personnels p2=new Personnels.Builder(2, "mom", " belhabib").TEL().build();
    ArrayList<String>Tel=new ArrayList();
    Tel.add("0753681353");
    Personnels p2=new Personnels.Builder(2, "momo", "mimi",LocalDate.parse("1980-12-13", DateTimeFormatter.ISO_DATE)).TEL(Tel).build();
   // personelleDAODERBY dDAO=new personelleDAODERBY();
    CompositePersonnels p3 = new CompositePersonnels(3);
	CompositePersonnels p6 = new CompositePersonnels(66);
	p3.add(p7);
	p6.add(p5);

	p3.add(p6);
	p3.add(p2);
    DAO<CompositePersonnels> CDAO=new CompositeDaoDerby();
    CDAO.create(p3);
    //p3.remove(p6);
    CDAO.update(p3);
    
    CompositePersonnels a=CDAO.find(3);
     
    
    System.out.println("####");
    if (a!=null) {
    	 AffichageGroupe ag =new AffichageGroupe((InterfacePersonnels) a);
    	    
    	    Iterator i=ag.getIterator();
    	    while(i.hasNext()){
    			
    	 	  i.Next().afficher();  
    		}
    	
	}else {
    System.out.println("le tuple n'existe pas");
	}
    ((CompositeDaoDerby) CDAO).close();
	
    /*CompositePersonnels p3 = new CompositePersonnels(3);
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
    boolean a=pDAO.create(p1);
    System.out.println(a);
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
    */}
}
