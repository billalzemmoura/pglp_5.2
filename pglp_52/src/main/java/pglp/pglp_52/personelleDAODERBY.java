package pglp.pglp_52;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;



public class personelleDAODERBY implements DAO<Personnels>{
	
	private static final String CREATE_TABLE_groupe="CREATE  TABLE databaseDAO.GROUPE("
            + "GRP_ID INT NOT NULL,"
            + "PRIMARY KEY (GRP_ID ))";	
	
	private static final String CREATE_TABLE_SQL="CREATE  TABLE databaseDAO.PERSONELLE ("
            + "EMP_ID INT NOT NULL,"
            + "NAME VARCHAR(45) NOT NULL,"
            + "DOB DATE NOT NULL,"
            + "FNAME VARCHAR(45) NOT NULL ,"
            + "GRP_ID INT,"
            + "FOREIGN KEY (GRP_ID) REFERENCES databaseDAO.GROUPE(GRP_ID),"
            + "PRIMARY KEY (EMP_ID ))";


private static final String CREATE_TABLE_SQL2="CREATE  TABLE databaseDAO.Telephone("
        + "EMP_ID INT NOT NULL,"
        + "Tel VARCHAR(45) NOT NULL,"
        + "FOREIGN KEY (EMP_ID) REFERENCES databaseDAO.PERSONELLE(EMP_ID),"
        + "PRIMARY KEY (Tel ))";
    

	    private Connection connect = null;
	    private Statement statement = null;
	    private ResultSet resultSet = null;
	    private ResultSet resultTel= null;
	   
	    @SuppressWarnings("deprecation")
		public personelleDAODERBY () throws Exception {
	        try {


	            connect = DriverManager.getConnection("jdbc:derby:databaseDAO;create=true");
	            statement=connect.createStatement();
	            statement.executeUpdate(CREATE_TABLE_groupe);
	            statement.executeUpdate(CREATE_TABLE_SQL);
	           
	           statement.executeUpdate(CREATE_TABLE_SQL2);
	           // resultSet = statement.executeQuery();
	            
	        } catch (SQLException e) {
	        	  if(DerbyUtils.tableAlreadyExists(e)) { //check if the exception is because of pre-existing table.
	                
	        	  } else {
	                  System.err.println(e.getMessage() + " :billal " + e.getStackTrace());
	              }
	        } finally {
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

		@SuppressWarnings("null")
		public Personnels create(Personnels obj)
				throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
			try {
				if (obj!=null) {
				connect = DriverManager.getConnection("jdbc:derby:databaseDAO;create=true");
				
				Personnels p=find(obj.getIdPersonnels());
				if (p!=null) {
					return null;
				}else {
				PreparedStatement prepare =connect.prepareStatement("INSERT INTO databaseDAO.PERSONELLE ( EMP_ID,NAME,FNAME,DOB,GRP_ID) VALUES (?,?,?,?,?)");
				
				
				prepare.setInt(1, obj.getIdPersonnels());
				prepare.setString(2, obj.getNom());
				prepare.setString(3, obj.getPrenom());
				prepare.setDate(4, (Date.valueOf(obj.getDate())));
				if (obj.getIdGRP()==-1) {
				prepare.setInt(5, (Integer) null);}
				else {
					prepare.setInt(5, obj.getIdGRP());
				}
				int result=prepare.executeUpdate();
                if (obj.getTel().size()>0) {
					insertNUM(obj);
				}
				assert result==1;
				close();}
				}
				} catch (SQLException e) {
			e.printStackTrace();
			}
			
			
			
			// TODO Auto-generated method stub
			return obj;
		}

		public int insertNUM(Personnels obj) {
			
			int result=0;
			try {
				
				connect = DriverManager.getConnection("jdbc:derby:databaseDAO;create=true");
				PreparedStatement prepare =connect.prepareStatement("INSERT INTO databaseDAO.Telephone ( EMP_ID,TEL) VALUES (?,?)");
			    for (int i = 0; i < obj.getTel().size(); i++) {
			    	if (!findTel(obj.getTel().get(i))) {
			    		prepare.setInt(1, obj.getIdPersonnels());
					    prepare.setString(2, obj.getTel().get(i));
					     result = prepare.executeUpdate();
					}
			    	
				}
			}catch (SQLException e) {
				e.printStackTrace();			}			    
		
		return result;
		}
		
		public Personnels find(int i) {
			Personnels p =null;
			try {
				connect = DriverManager.getConnection("jdbc:derby:databaseDAO;create=false");
				PreparedStatement preparedStatement=connect.prepareStatement("SELECT * FROM databaseDAO.PERSONELLE WHERE EMP_ID=?");
				preparedStatement.setInt(1, i);
				resultSet=preparedStatement.executeQuery();
				while(resultSet.next()) {
					preparedStatement=connect.prepareStatement("select * from databaseDAO.Telephone where EMP_ID=?");
					preparedStatement.setInt(1, i);
					resultTel=preparedStatement.executeQuery();
					ArrayList<String>Tel=new ArrayList<String>();
					while(resultTel.next()) {
					Tel.add(resultTel.getString("Tel"));
					
					}	
				p=new Personnels.Builder(resultSet.getInt("EMP_ID"),resultSet.getString("NAME") ,resultSet.getString("FNAME"), resultSet.getDate("DOB").toLocalDate() ).TEL(Tel).GRP(resultSet.getInt("GRP_ID")).build();	
			
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			
			
			return p;
		}
		public boolean findTel(String num) {
			ArrayList<String>Tel=new ArrayList<String>();
			try {
				connect = DriverManager.getConnection("jdbc:derby:databaseDAO;create=true");

					PreparedStatement 	preparedStatement=connect.prepareStatement("select * from databaseDAO.Telephone where TEL=?");
					preparedStatement.setString(1, num);
					resultTel=preparedStatement.executeQuery();
					
					if (resultTel.next()) {
					return true ;
					
					}	
				
			
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			
			
			return false;
		}

		public Personnels update(Personnels obj) {
			try {
				connect = DriverManager.getConnection("jdbc:derby:databaseDAO;create=true");
				
				Personnels p=find(obj.getIdPersonnels());
				if (p==null) {
					return null;
				}else {
				PreparedStatement prepare =connect.prepareStatement("UPDATE databaseDAO.PERSONELLE SET NAME = ? , FNAME= ? ,DOB = ? ,GRP_ID=? WHERE EMP_ID=?");
				prepare.setString(1, obj.getNom());
				prepare.setString(2, obj.getPrenom());
				prepare.setDate(3, Date.valueOf(obj.getDate()));
				prepare.setInt(4,  obj.getIdPersonnels());
				prepare.setInt(5,  obj.getIdGRP());
				int result=prepare.executeUpdate();
				
				deleteTel(obj);
				prepare =connect.prepareStatement("INSERT INTO databaseDAO.Telephone ( EMP_ID,TEL) VALUES (?,?)");
				insertNUM(obj);
				close();
				}
			} catch (SQLException e) {
			e.printStackTrace();
			}
			
			
			
			// TODO Auto-generated method stub
			return obj;
		}
		public Personnels delete( Personnels obj) {
			try {
				if (obj!=null) {
					
				
				connect = DriverManager.getConnection("jdbc:derby:databaseDAO;create=true");
				
				Personnels p=find(obj.getIdPersonnels());
				if (p==null) {
					return null;
				}else {
				PreparedStatement prepare =connect.prepareStatement("DELETE FROM  databaseDAO.TELEPHONE WHERE EMP_ID=?");
				prepare.setInt(1,  obj.getIdPersonnels());
				int result=prepare.executeUpdate();
				PreparedStatement prepare2 =connect.prepareStatement("DELETE FROM  databaseDAO.PERSONELLE WHERE EMP_ID=?");
				prepare2.setInt(1,  obj.getIdPersonnels());
				int result2=prepare2.executeUpdate();
				assert result2==1;
				close();}
				}
				} catch (SQLException e) {
			e.printStackTrace();
			}
			
			
			
			// TODO Auto-generated method stub
			return obj;
		}
		public void  deleteTel(Personnels obj) {
			try {
				connect = DriverManager.getConnection("jdbc:derby:databaseDAO;create=true");
				
				
				PreparedStatement prepare =connect.prepareStatement("DELETE FROM  databaseDAO.TELEPHONE WHERE EMP_ID=?");
				prepare.setInt(1,  obj.getIdPersonnels());
				int result=prepare.executeUpdate();
				assert result==1;
				
				
			} catch (SQLException e) {
			e.printStackTrace();
			}
			
					}
	
}
