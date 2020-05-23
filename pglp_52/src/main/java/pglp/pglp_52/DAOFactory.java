package pglp.pglp_52;

public class DAOFactory <T>{
	public  static  DAO <Personnels> getPersonnelDAO() {
		return new personnelDAO();
	}
    public static  DAO <CompositePersonnels> getCompositeDao(){
    	
    	return new CompositeDao();
    } 

public static  DAO <Personnels> getPersonnelDaoDerby() throws Exception{
    	
    	return new personelleDAODERBY();
    } 

}
