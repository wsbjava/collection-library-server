package pl.wsb.collection.repository.impl;

import pl.wsb.collection.hibernate.GenreCollectionType;
import pl.wsb.collection.repository.AbstractRepository;

public class GenreCollectionTypeRepository extends AbstractRepository<GenreCollectionType, Integer> {

    @Override
    protected Class<GenreCollectionType> getPersistentClass() {
        return GenreCollectionType.class;
    }
}

