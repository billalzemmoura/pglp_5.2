package pglp.pglp52;

public class DAOFactory <T>{
	public  static  DAO <Personnels> getPersonnelDAO() {
		return new personnelDAO();
	}
    public static  DAO <CompositePersonnels> getCompositeDao(){
    	
    	return new CompositeDao();
    } 


}
