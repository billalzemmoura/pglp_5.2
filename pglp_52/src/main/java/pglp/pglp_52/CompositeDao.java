package pglp.pglp_52;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class CompositeDao implements DAO<CompositePersonnels>{
	static ArrayList<CompositePersonnels > compositePersonnel=new ArrayList<CompositePersonnels>(); 
	@SuppressWarnings("unchecked")
	public static ArrayList<CompositePersonnels> lire() {
	    
    	ObjectInputStream ois = null;

    	try {
			
    		final FileInputStream fichierIn = new FileInputStream("Compositepersonnel.ser");
    		ois = new ObjectInputStream(fichierIn);
compositePersonnel = (ArrayList<CompositePersonnels>) ois.readObject();
    		

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
			
		 return compositePersonnel;}
	
	public CompositePersonnels create(CompositePersonnels Compositepersonne) {

		compositePersonnel=lire();
		if (!find(Compositepersonne))
			compositePersonnel.add(Compositepersonne);
		
		ObjectOutputStream oos = null;
	   
 
		try {
			final FileOutputStream fichierOut = new FileOutputStream("Compositepersonnel.ser");
			oos = new ObjectOutputStream(fichierOut);
	        oos.writeObject(compositePersonnel);
			
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
		}return Compositepersonne;
	}
	
	

	public boolean find(CompositePersonnels obj) {
		compositePersonnel=lire();
		for (CompositePersonnels groupepersonnels : compositePersonnel) {
			if (groupepersonnels.getIDGroupe()==obj.getIDGroupe()) {
				return true;
			}
		}
		
		return false;
	}

	public CompositePersonnels update(CompositePersonnels obj) {
		compositePersonnel=lire();
		int index=findIndex(obj);
		if (index!=-1)
			compositePersonnel.set(index, obj);
		ObjectOutputStream oos = null;
		   
		 
		try {
			final FileOutputStream fichierOut = new FileOutputStream("Compositepersonnel.ser");
			oos = new ObjectOutputStream(fichierOut);
	        oos.writeObject(compositePersonnel);
			
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
	private int findIndex(CompositePersonnels obj) {
		int i=0;
		compositePersonnel=lire();
		for (i= 0; i < compositePersonnel.size(); i++) {
			if (compositePersonnel.get(i).getIDGroupe()==obj.getIDGroupe()) 
             return i; 
	         }
		
		return -1;
	}
	public CompositePersonnels delete(CompositePersonnels obj) {
		int index;
		if ((index=findIndex(obj))==-1) {
			return obj;	
		}
		compositePersonnel.remove(index);
		ObjectOutputStream oos = null;
		   
		 
		try {
			final FileOutputStream fichierOut = new FileOutputStream("Compositepersonnel.ser");
			oos = new ObjectOutputStream(fichierOut);
	        oos.writeObject(compositePersonnel);
			
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

	public CompositePersonnels find(int id) {
		// TODO Auto-generated method stub
		return null;
	}



	
	
	
}
