package intuit.com.web.persistance.interfaces;

public interface IDAO<T> {
    void AddAllEntities(Iterable<T> entities);
    Iterable<T> getEntities();
    String AddEntity(T entity);
    T getEntityByID(String id);
}
