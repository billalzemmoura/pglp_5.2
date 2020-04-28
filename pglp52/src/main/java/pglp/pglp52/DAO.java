package pglp.pglp52;

public interface DAO <T>{
public T create(T obj);
public boolean find(T obj);
public T update(T obj);
public T delete(T obj);


}
