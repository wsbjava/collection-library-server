package pl.wsb.collection.repository.impl;

import pl.wsb.collection.model.CollectionType;
import pl.wsb.collection.repository.AbstractRepository;


public class CollectionTypeRepository extends AbstractRepository<CollectionType, Integer> {

    @Override
    protected Class<CollectionType> getPersistentClass() {
        return CollectionType.class;
    }
}