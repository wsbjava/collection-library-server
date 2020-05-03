package pl.wsb.collection.repository.impl;

import pl.wsb.collection.model.CollectionLibrary;
import pl.wsb.collection.repository.AbstractRepository;


public class CollectionLibraryRepository extends AbstractRepository<CollectionLibrary, Integer> {

    @Override
    protected Class<CollectionLibrary> getPersistentClass() {
        return CollectionLibrary.class;
    }
}