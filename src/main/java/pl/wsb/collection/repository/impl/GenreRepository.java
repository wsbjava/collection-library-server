package pl.wsb.collection.repository.impl;

import pl.wsb.collection.model.Genre;
import pl.wsb.collection.repository.AbstractRepository;

public class GenreRepository extends AbstractRepository<Genre, Integer> {

    @Override
    protected Class<Genre> getPersistentClass() {
        return Genre.class;
    }
}

