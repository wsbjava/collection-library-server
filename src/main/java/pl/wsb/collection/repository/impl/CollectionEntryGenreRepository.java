package pl.wsb.collection.repository.impl;

import pl.wsb.collection.hibernate.CollectionEntryGenre;
import pl.wsb.collection.repository.AbstractRepository;


public class CollectionEntryGenreRepository extends AbstractRepository<CollectionEntryGenre, Integer> {

    @Override
    protected Class<CollectionEntryGenre> getPersistentClass() {
        return CollectionEntryGenre.class;
    }
}