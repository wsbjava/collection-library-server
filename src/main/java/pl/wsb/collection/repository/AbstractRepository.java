package pl.wsb.collection.repository;

import pl.wsb.collection.model.Role;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;

public abstract class AbstractRepository<T, ID extends Serializable> implements IRepository<T, ID> {

    @Override
    public T merge(T entity) {
        return EntityManagerHelper.entityManager().merge(entity);
    }

    @Override
    public void delete(T entity) {
        if (entity != null) {
            EntityManagerHelper.entityManager().remove(entity);
        } //if
    }

    @Override
    public T find(ID id) {
        return EntityManagerHelper.entityManager().find(getPersistentClass(), id);
    }

    protected abstract Class<T> getPersistentClass();

    protected T getFirstResultOrNull(List<T> results){
        if(results == null){
            return null;
        }

        if(results.isEmpty()){
            return null;
        }

        return results.get(0);
    }
}
