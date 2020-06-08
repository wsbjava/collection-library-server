package pl.wsb.collection.repository.impl;

import pl.wsb.collection.hibernate.CollectionEntry;
import pl.wsb.collection.repository.AbstractRepository;


public class CollectionEntryRepository extends AbstractRepository<CollectionEntry, Integer> {

    @Override
    protected Class<CollectionEntry> getPersistentClass() {
        return CollectionEntry.class;
    }
}