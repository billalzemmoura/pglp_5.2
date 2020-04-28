package pglp.pglp52;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


import org.junit.Before;
import org.junit.Test;

public class test

{
	private Personnels personne;

	@Before
	public void setUp() {
		personne = new Personnels.Builder(4, "BILL", " zemmoura").build();

	}

	
	@Test
	public void testSerealise() {
		
		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;

		try {
			final FileOutputStream fichierOut = new FileOutputStream("personne.ser");
			oos = new ObjectOutputStream(fichierOut);
			oos.writeObject(personne);
			oos.flush();
			final FileInputStream fichierIn = new FileInputStream("personne.ser");
			ois = new ObjectInputStream(fichierIn);
			Personnels personneDeserealisable = (Personnels) ois.readObject();
			
			assertEquals(personne.getNom(), (personneDeserealisable).getNom());
			assertEquals(personne.getPrenom(), ( personneDeserealisable).getPrenom());
			assertEquals(personne.getIdPersonnels(), personneDeserealisable.getIdPersonnels());
			
			
		} catch (final java.io.IOException e) {
			e.printStackTrace();
		} catch (final ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ois != null) {
					ois.close();
				}
				if (oos != null) {
					oos.close();
				}
			} catch (final IOException ex) {
				ex.printStackTrace();
			}
		}

	}

	/**
	 * Create the test case
	 *
	 * @param testName name of the test case
	 */
	@Test
	public void TestCreate(){
		Personnels p5=new Personnels.Builder(5, "BILL", " zemmoura").build();
		DAO<Personnels> pDAO=DAOFactory.getPersonnelDAO();
		pDAO.create(p5);
		assertTrue(pDAO.find(p5));
	
	}
	
	@Test
	public void TestDelete(){
		Personnels p5=new Personnels.Builder(5, "BILL", " zemmoura").build();
		DAO<Personnels> pDAO=DAOFactory.getPersonnelDAO();
		pDAO.create(p5);
		pDAO.delete(p5);
		assertFalse(pDAO.find(p5));
	
	}
	
	@Test
	public void Testfind(){
		Personnels p8=new Personnels.Builder(8, "BILL", " zemmoura").build();
		Personnels p4=new Personnels.Builder(4, "bob", " bob").build();
		DAO<Personnels> pDAO=DAOFactory.getPersonnelDAO();
		pDAO.create(p4);
		assertFalse(pDAO.find(p8));
		assertTrue(pDAO.find(p4));
		
	
	}

	@Test
	public void Testupdate(){
		boolean isUpdate=false;
		Personnels p9=new Personnels.Builder(9, "BILL", " zemmoura").build();
		ArrayList<Personnels> load=new ArrayList<Personnels>();
		DAO<Personnels> pDAO=DAOFactory.getPersonnelDAO();
		pDAO.create(p9);
		p9.setNom("ze");;
		//System.out.println(p9.IdPersonnels);
		pDAO.update(p9);
		
		load=personnelDAO.lire();
		for (Personnels personnels : load) {
		   if (personnels.getIdPersonnels()==9) {
			isUpdate=true;
		}
		   System.out.println(personnels.getNom());
		
		}
		//find ne doit pas trouver l'objet p9 car elle l'es détecte par leurs identifaints 
		
		assertTrue(isUpdate);
	
	}

	
	@Test
	public void TestPersonelDAO() {
		

	    ArrayList<Personnels> load=new ArrayList<Personnels>();
	    	Personnels p5=new Personnels.Builder(5, "BILL", " zemmoura").build();
	      Personnels p7=new Personnels.Builder(7, "riayd", " belhabib").build();
	      Personnels p1=new Personnels.Builder(1, "BILL", " zemmoura").build();
	      Personnels p2=new Personnels.Builder(2, "riayd", " belhabib").build();
	      DAO<Personnels> pDAO=DAOFactory.getPersonnelDAO();
		    pDAO.create(p1);
		    pDAO.create(p2);
		    pDAO.create(p5);
		    pDAO.create(p7);
		   p5.setNom("meriem");
		   pDAO.update(p5);
		   pDAO.delete(p7);
		   load=personnelDAO.lire();
	    
	   System.out.println(load.size());
	 
	assertTrue(pDAO.find(p1)&&pDAO.find(p2)&&pDAO.find(p5));
		 



	   System.out.println("le nombre de composite est :"+load.size());
	    }

		
	
	@Test
	public void afficheTest() {
		Personnels p4 = new Personnels.Builder(4, "BILL", " zemmoura").build();
		Personnels p1 = new Personnels.Builder(1, " BILL", "zemmoura").build();
		Personnels p5 = new Personnels.Builder(5, "BILL", "zemmoura").build();
		CompositePersonnels p3 = new CompositePersonnels(3);
		CompositePersonnels p2 = new CompositePersonnels(2);
		p2.add(p4);
		p2.add(p5);

		p3.add(p1);
		p3.add(p2);

		/*AffichageGroupe a = new AffichageGroupe(p3);
		Iterator i = a.getIterator();
		while (i.hasNext()) {

			i.Next().afficher();
		}*/

	}

}
