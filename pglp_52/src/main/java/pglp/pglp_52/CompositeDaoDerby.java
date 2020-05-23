package pglp.pglp_52;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;



public class CompositeDaoDerby implements DAO<CompositePersonnels> {

	private static final String CREATE_TABLE_groupe="CREATE  TABLE databaseDAO.GROUPE("
            + "GRP_ID INT NOT NULL,"
            + "PRIMARY KEY (GRP_ID ))";	
	
	private static final String CREATE_TABLE_SQL="CREATE  TABLE databaseDAO.PERSONELLE ("
            + "EMP_ID INT NOT NULL,"
            + "NAME VARCHAR(45) NOT NULL,"
            + "DOB DATE NOT NULL,"
            + "FNAME VARCHAR(45) NOT NULL ,"
            + "GRP_ID INT NOT NULL,"
            + "FOREIGN KEY (GRP_ID) REFERENCES databaseDAO.GROUPE(GRP_ID),"
            + "PRIMARY KEY (EMP_ID ))";


private static final String CREATE_TABLE_SQL2="CREATE  TABLE databaseDAO.Telephone("
        + "EMP_ID INT NOT NULL,"
        + "Tel VARCHAR(45) NOT NULL,"
        + "FOREIGN KEY (EMP_ID) REFERENCES databaseDAO.PERSONELLE(EMP_ID),"
        + "PRIMARY KEY (Tel ))";
    
	
	
	private static final String CREATE_TABLE_Composite="CREATE  TABLE databaseDAO.composite("
            + "GRP_ID_P INT NOT NULL,"
            + "GRP_ID_F INT NOT NULL,"
            + "FOREIGN KEY (GRP_ID_P) REFERENCES databaseDAO.groupe(GRP_ID),"
            + "FOREIGN KEY (GRP_ID_F) REFERENCES databaseDAO.groupe(GRP_ID),"
            + "PRIMARY KEY (GRP_ID_P,GRP_ID_F ))";	
	
	 private Connection connect = null;
	    private Statement statement = null;
	    private ResultSet resultSet = null;
	    private ResultSet resultPERSO= null;
	    public CompositeDaoDerby () {
	    	
	    	 try {


		           connect = DriverManager.getConnection("jdbc:derby:databaseDAO;create=true");
		           statement=connect.createStatement();
		           statement.executeUpdate(CREATE_TABLE_groupe);
		           statement.executeUpdate(CREATE_TABLE_SQL);
		           statement.executeUpdate(CREATE_TABLE_SQL2);
		           statement.executeUpdate(CREATE_TABLE_Composite);
	    	
	    	
	    	
	    }catch (SQLException e) {
	    	if(DerbyUtils.tableAlreadyExists(e)) { //check if the exception is because of pre-existing table.
                
      	  } else {
                System.err.println(e.getMessage() + " : " + e.getStackTrace());
            }
		
		}finally {
			close();
		}
	
	    }
	    	 public  void close() {
	 	        try {
	 	            if (resultSet != null) {
	 	                resultSet.close();
	 	            }
	 	            if (statement != null) {
	 	                statement.close();
	 	            }
	 	            if (connect != null) {
	 	                connect.close();
	 	            }
	 	        } catch (Exception e) {

	 	        }
	 	    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	 		public CompositePersonnels create(CompositePersonnels obj)
	 				throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
	 			try {
	 				DAO<Personnels> pDaoDerby=DAOFactory.getPersonnelDaoDerby();
	 				connect = DriverManager.getConnection("jdbc:derby:databaseDAO;create=true");
	 				CompositePersonnels p=null;
	 				
		 			p=find(obj.getIDGroupe());						
					

	 				if (p!=null) {
	 					System.out.println(p.getIDGroupe() +" ce groupe existe dejas sur la base de données !");
	 				}else {
	 				
	 				PreparedStatement prepare =connect.prepareStatement("INSERT INTO databaseDAO.GROUPE ( GRP_ID) VALUES (?)");	 				
	 				prepare.setInt(1, obj.getIDGroupe());
                    prepare.executeUpdate();
	 				for (int i = 0; i < obj.getPersonelles().size(); i++) {
	 					if (obj.getPersonelles().get(i) instanceof Personnels) {
	 						pDaoDerby.create((Personnels) obj.getPersonelles().get(i));
						}else {
							
							create((CompositePersonnels) obj.getPersonelles().get(i));
							PreparedStatement prepare2 =connect.prepareStatement("INSERT INTO databaseDAO.COMPOSITE ( GRP_ID_P,GRP_ID_F) VALUES (?,?)");	 				
						    prepare2.setInt(1,obj.getIDGroupe() );
						    prepare2.setInt(2,((CompositePersonnels)obj.getPersonelles().get(i)).getIDGroupe());
						    prepare2.executeUpdate();
						    						
						}
							
						}
					}
	 				
	                 
	 				
	 				
	 				}
	 			 catch (SQLException e) {
	 			e.printStackTrace();
	 			} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	 			
	 			
	 			
	 			// TODO Auto-generated method stub
	 			return (CompositePersonnels) obj;
	 		}

	 		public CompositePersonnels find(int i) {
				CompositePersonnels p =null;
				try {
					DAO<Personnels> pDaoDerby=DAOFactory.getPersonnelDaoDerby();
					connect = DriverManager.getConnection("jdbc:derby:databaseDAO;create=true");
					PreparedStatement preparedStatement=connect.prepareStatement("SELECT * FROM databaseDAO.GROUPE WHERE GRP_ID=?");
					preparedStatement.setInt(1, i);
					resultSet=preparedStatement.executeQuery();
					if (resultSet.next()) {
						p=new CompositePersonnels(i);	
					
					
					
					PreparedStatement prepared=connect.prepareStatement("SELECT * FROM databaseDAO.PERSONELLE WHERE GRP_ID=?");
					prepared.setInt(1, i);
					resultPERSO=prepared.executeQuery();
					
					while (resultPERSO.next()) {
					p.add(pDaoDerby.find(resultPERSO.getInt("EMP_ID")));
						
					}
						
                    prepared=connect.prepareStatement("SELECT * FROM databaseDAO.COMPOSITE WHERE GRP_ID_P=?  ");
					prepared.setInt(1, i);
					
					resultPERSO=prepared.executeQuery();
						
				
					while (resultPERSO.next()) {
						p.add(find(resultPERSO.getInt("GRP_ID_F")));
							
						}
					}else {
					return null;	
					}
					
					} catch (SQLException e) {
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				
				return p;
			}
	public CompositePersonnels update(CompositePersonnels obj) {
		CompositePersonnels resutatUpdated =null;
		if (find(obj.getIDGroupe())!=null) {
			
		
		delete(obj);
		
		try {
			resutatUpdated = create(obj);
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
		}else {
			
			System.out.println("le tuple n'existe pas ");
		}
		
		return resutatUpdated;
	}

	
		public CompositePersonnels delete(CompositePersonnels obj) {
			try {
				DAO<Personnels> pDaoDerby=DAOFactory.getPersonnelDaoDerby();
				
				CompositePersonnels p=find(obj.getIDGroupe());
				if (p==null) {
					return null;
				}else {
					connect = DriverManager.getConnection("jdbc:derby:databaseDAO;create=true");
				PreparedStatement prepare1 =connect.prepareStatement("DELETE FROM  databaseDAO.COMPOSITE WHERE GRP_ID_P=?");
				prepare1.setInt(1,  obj.getIDGroupe());
				int result=prepare1.executeUpdate();
				connect = DriverManager.getConnection("jdbc:derby:databaseDAO;create=true");
				 prepare1 =connect.prepareStatement("DELETE FROM  databaseDAO.COMPOSITE WHERE GRP_ID_F=?");
				prepare1.setInt(1,  obj.getIDGroupe());
				result=prepare1.executeUpdate();
				for (int i = 0; i < p.getPersonelles().size(); i++) {
					if (p.getPersonelles().get(i)  instanceof Personnels) {
                    pDaoDerby.delete((Personnels) p.getPersonelles().get(i));
					}
					
					if (p.getPersonelles().get(i)  instanceof CompositePersonnels){
                        delete((CompositePersonnels) p.getPersonelles().get(i))	;
					}
					
				}
				 prepare1 =connect.prepareStatement("DELETE FROM  databaseDAO.GROUPE WHERE GRP_ID=?");
				prepare1.setInt(1,  p.getIDGroupe());
				result=prepare1.executeUpdate();

				
				
				
				}
			} catch (SQLException e) {
			e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			// TODO Auto-generated method stub
			return obj;
		}
		
	
}
