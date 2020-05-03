package pl.wsb.collection.repository.impl;

import pl.wsb.collection.model.CollectionLibraryStatus;
import pl.wsb.collection.repository.AbstractRepository;


public class CollectionLibraryStatusRepository extends AbstractRepository<CollectionLibraryStatus, Integer> {

    @Override
    protected Class<CollectionLibraryStatus> getPersistentClass() {
        return CollectionLibraryStatus.class;
    }
}