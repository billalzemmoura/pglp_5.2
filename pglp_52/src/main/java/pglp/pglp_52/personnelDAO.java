package pglp.pglp52;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class personnelDAO implements DAO<Personnels>{
   
	static ArrayList<Personnels> Allpersonnelles=new ArrayList<Personnels>(); 
	
	
	@SuppressWarnings("unchecked")
	public static ArrayList<Personnels> lire() {
    
    	ObjectInputStream ois = null;

    	try {
			
    		final FileInputStream fichierIn = new FileInputStream("personnel.ser");
    		ois = new ObjectInputStream(fichierIn);
Allpersonnelles = (ArrayList<Personnels>) ois.readObject();
    		

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				if (ois != null) 
					ois.close();
				}catch (final IOException ex) {
					ex.printStackTrace();
				}
    	
    	}
			
		 return Allpersonnelles;}

	public  Personnels create(Personnels personne) {
		
		Allpersonnelles=lire();
		if (!find(personne))
		Allpersonnelles.add(personne);
		
		ObjectOutputStream oos = null;
	   
 
		try {
			final FileOutputStream fichierOut = new FileOutputStream("personnel.ser");
			oos = new ObjectOutputStream(fichierOut);
	        oos.writeObject(Allpersonnelles);
			
			oos.flush();
			
			
			
			
		} catch (final java.io.IOException e) {
			e.printStackTrace();
		} finally {
			try {
				
				if (oos != null) {
					oos.close();
				}
			} catch (final IOException ex) {
				ex.printStackTrace();
			}
		}return personne;
	}

	public boolean find(Personnels obj) {
		Allpersonnelles=lire();
		for (Personnels personnels : Allpersonnelles) {
			if (personnels.getIdPersonnels()==obj.getIdPersonnels()) {
				return true;
			}
		}
		return false;
		
	}

	public Personnels update(Personnels obj) {
		Allpersonnelles=lire();
		int index=findIndex(obj);
		if (index!=-1)
		Allpersonnelles.set(index, obj);
		ObjectOutputStream oos = null;
		   
		 
		try {
			final FileOutputStream fichierOut = new FileOutputStream("personnel.ser");
			oos = new ObjectOutputStream(fichierOut);
	        oos.writeObject(Allpersonnelles);
			
			oos.flush();
			
			
			
			
		} catch (final java.io.IOException e) {
			e.printStackTrace();
		} finally {
			try {
				
				if (oos != null) {
					oos.close();
				}
			} catch (final IOException ex) {
				ex.printStackTrace();
			}
		}
		
		return obj;
	}

	public Personnels delete(Personnels obj) {
		int index;
		if ((index=findIndex(obj))==-1) {
			return obj;	
		}
		Allpersonnelles.remove(index);
		ObjectOutputStream oos = null;
		   
		 
		try {
			final FileOutputStream fichierOut = new FileOutputStream("personnel.ser");
			oos = new ObjectOutputStream(fichierOut);
	        oos.writeObject(Allpersonnelles);
			
			oos.flush();
			
			
			
			
		} catch (final java.io.IOException e) {
			e.printStackTrace();
		} finally {
			try {
				
				if (oos != null) {
					oos.close();
				}
			} catch (final IOException ex) {
				ex.printStackTrace();
			}
		}
		return obj;	
	}

	public int findIndex(Personnels obj) {
		int i=0;
		Allpersonnelles=lire();
		for (i= 0; i < Allpersonnelles.size(); i++) {
			if (Allpersonnelles.get(i).getIdPersonnels()==obj.getIdPersonnels()) 
             return i; 
	         }
		
		return -1;
	}


}
