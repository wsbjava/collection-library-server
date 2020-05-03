package pl.wsb.collection.repository.impl;

import pl.wsb.collection.model.CollectionEntryAuthor;
import pl.wsb.collection.repository.AbstractRepository;


public class CollectionEntryAuthorRepository extends AbstractRepository<CollectionEntryAuthor, Integer> {

    @Override
    protected Class<CollectionEntryAuthor> getPersistentClass() {
        return CollectionEntryAuthor.class;
    }
}