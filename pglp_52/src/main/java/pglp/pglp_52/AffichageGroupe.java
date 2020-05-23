package pglp.pglp52;

import java.util.ArrayList;


public class AffichageGroupe {

	ArrayList<InterfacePersonnels>arr=new ArrayList<InterfacePersonnels>() ;
	
	public AffichageGroupe(InterfacePersonnels root) {
		
		arr.add(root);
		
		
		
	}
	
	private class GPIterator implements Iterator{
		int cpt=0;
		
		public GPIterator() {
		int verif=0;
		while (verif<arr.size()) {
			if (arr.get(verif) instanceof CompositePersonnels) {
			for (InterfacePersonnels interfacePersonnels : ((CompositePersonnels)arr.get(verif)).personelles) {
				arr.add(interfacePersonnels);
			}
				
				
			}
			verif++;
			
		}
		
		
		}
	
	
		
		public boolean hasNext() {
			
			if(cpt>=arr.size())
			return false;
		    
			return true;
		
		}

		public InterfacePersonnels Next() {
		
			if (this.hasNext()) {
				return arr.get(cpt++);				
			}

		return null;	
			
		}
		
	
	}

	
	public Iterator getIterator()
	{
		
		return new GPIterator();
	}
	
	
}
