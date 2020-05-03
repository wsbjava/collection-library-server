package pl.wsb.collection.repository.impl;

import pl.wsb.collection.model.CollectionRequestStatus;
import pl.wsb.collection.repository.AbstractRepository;


public class CollectionRequestStatusRepository extends AbstractRepository<CollectionRequestStatus, Integer> {

    @Override
    protected Class<CollectionRequestStatus> getPersistentClass() {
        return CollectionRequestStatus.class;
    }
}