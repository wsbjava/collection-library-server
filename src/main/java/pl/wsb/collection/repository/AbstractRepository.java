package pl.wsb.collection.repository;

import java.io.Serializable;

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
}
