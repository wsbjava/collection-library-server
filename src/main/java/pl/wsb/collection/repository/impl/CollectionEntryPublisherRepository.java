package pl.wsb.collection.repository.impl;

import pl.wsb.collection.model.CollectionEntryPublisher;
import pl.wsb.collection.repository.AbstractRepository;


public class CollectionEntryPublisherRepository extends AbstractRepository<CollectionEntryPublisher, Integer> {

    @Override
    protected Class<CollectionEntryPublisher> getPersistentClass() {
        return CollectionEntryPublisher.class;
    }
}