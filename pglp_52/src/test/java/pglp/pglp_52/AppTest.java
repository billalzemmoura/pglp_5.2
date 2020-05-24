package pglp.pglp_52;

import static org.junit.Assert.assertTrue;

import java.lang.module.FindException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;

/**
 * Unit test for simple App.
 */
public class AppTest {


CompositePersonnels p3;
CompositePersonnels p6;
DAO<CompositePersonnels> CDAO;
personelleDAODERBY dDAO;
Personnels p5;
Personnels p7;
Personnels p1;
Personnels p2;
@SuppressWarnings("unused")
	@Before
	public void create() throws Exception {
		CDAO=new CompositeDaoDerby();
		dDAO=new personelleDAODERBY();
		// creation des differents objets
		ArrayList<CompositePersonnels> perso=new ArrayList<CompositePersonnels>();
		p5=new Personnels.Builder(5, "toto", " titi",LocalDate.parse("1996-10-18", DateTimeFormatter.ISO_DATE) ).build();
		 p7=new Personnels.Builder(7, "foufou", " fifi",LocalDate.parse("1997-11-06",DateTimeFormatter.ISO_DATE)).build();
		p1=new Personnels.Builder(1, "lala", " lili",LocalDate.parse("1996-02-02", DateTimeFormatter.ISO_DATE)).build();
		ArrayList<String>Tel=new ArrayList();
		Tel.add("0756738766");
		p2=new Personnels.Builder(2, "momo", "mimi",LocalDate.parse("1980-12-13", DateTimeFormatter.ISO_DATE)).TEL(Tel).build();
	   
		p3 = new CompositePersonnels(3);
	    p6 = new CompositePersonnels(6);
	}
	
	@Test
	public void CreateCompositeDerby() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {

		CDAO.create(p3);
		assertTrue(CDAO.find(3)!= null);
	}
	
	@Test
	public void AddCompositeDerby() {
		
		System.out.println("#####Test:AddCompositeDerby#####");
		p3.add(p7);
		p6.add(p5);

		p3.add(p6);
		p3.add(p2);
		
		p3=CDAO.update(p3);
		if (p3!=null) {
			p3.afficher();
		}
		assertTrue(CDAO.find(3).personelles.size()== 3);
		System.out.println("#####FinTest:AddCompositeDerby#####");

	}
	

	@Test
	public void afficherComposite() {
		System.out.println("######### TEST:afficherComposite #########");
		CompositePersonnels a=CDAO.find(3);
		if (a!=null) {
			 AffichageGroupe ag =new AffichageGroupe((InterfacePersonnels) a);
			    
			    Iterator i=ag.getIterator();
			    while(i.hasNext()){
					
			 	  i.Next().afficher();  
				}}
		System.out.println("######### FIN TEST:afficherComposite #########");
	}
	

	@Test
	public void removeComposite() {
		System.out.println("######### TEST:RemoveComposite#########");
		CDAO.delete(CDAO.find(3));
		assertTrue(CDAO.find(3)==null);
		System.out.println("######### FIN TEST:RemoveComposite#########");
	}

	@Test
	public void CreatePersonnelleDERBY() throws Exception {
		 dDAO.delete(p1);
	        dDAO.delete(p2);
	        dDAO.delete(p5);
			
		dDAO.create(p1);
		dDAO.create(p2);
		dDAO.create(p5);
		
			   assertTrue(dDAO.find(1) != null);
	           assertTrue(dDAO.find(2) != null);
		       assertTrue(dDAO.find(5) != null);
		

	}

	

	@Test
	public void UpdatePersonnelleDERBY() {
		try {
			 dDAO.delete(p1);
		        dDAO.delete(p2);
		        dDAO.delete(p5);
				
			dDAO.create(p1);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		p1.setNom("baba");
		p1.setPrenom("bibi");
		dDAO.update(p1);
		assertTrue(dDAO.find(1).getNom()=="baba");
	}

	@Test
	public void DeletePersonnelleDERBY() {

        dDAO.delete(p1);
        dDAO.delete(p2);
        dDAO.delete(p5);
		
		assertTrue(dDAO.find(1) == null);
		assertTrue(dDAO.find(2) == null);
		assertTrue(dDAO.find(5) == null);
		
	}

	@After
	public void DeleteAll() {
        dDAO.delete(p1);
        dDAO.delete(p2);
        dDAO.delete(p7);
        dDAO.delete(p5);
	   CDAO.delete(p3);
	   CDAO.delete(p6);
	

	}
}