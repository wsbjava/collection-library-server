package pl.wsb.collection.repository;

import pl.wsb.collection.exceptions.ValidationException;

import java.io.Serializable;

public interface IRepository<T, PK extends Serializable> {
    T merge(T entity);
    void delete(T entity);
    T find(PK id) throws ValidationException;
}
