package pglp.pglp_52;

import java.sql.SQLException;

public interface DAO <T>{
public  T create(T obj) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException;
public T find(int id);
public T update(T obj);
public T delete(T interfacePersonnels);


}
