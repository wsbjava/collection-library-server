package pl.wsb.collection.repository.impl;

import pl.wsb.collection.model.Publisher;
import pl.wsb.collection.repository.AbstractRepository;

public class PublisherRepository extends AbstractRepository<Publisher, Integer> {

    @Override
    protected Class<Publisher> getPersistentClass() {
        return Publisher.class;
    }
}

