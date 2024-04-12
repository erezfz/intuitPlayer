package intuit.com.web.persistance.interfaces;

import intuit.com.exceptions.IntuitDAOException;

import java.util.List;

public interface IDAO<T> {
    void AddAllEntities(List<T> entities) throws IntuitDAOException;
    List<T> getEntities();
    String AddEntity(T entity) throws IntuitDAOException;
    T getEntityByID(String id);
}
