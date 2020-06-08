package pl.wsb.collection.repository.impl;

import pl.wsb.collection.hibernate.Author;
import pl.wsb.collection.repository.AbstractRepository;

public class AuthorRepository extends AbstractRepository<Author, Integer> {

    @Override
    protected Class<Author> getPersistentClass() {
        return Author.class;
    }
}

