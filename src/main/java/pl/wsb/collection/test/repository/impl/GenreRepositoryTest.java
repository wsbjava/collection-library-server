package pl.wsb.collection.test.repository.impl;

import org.junit.jupiter.api.Test;
import pl.wsb.collection.exceptions.ValidationException;
import pl.wsb.collection.hibernate.CollectionType;
import pl.wsb.collection.hibernate.Genre;
import pl.wsb.collection.repository.impl.CollectionTypeRepository;
import pl.wsb.collection.repository.impl.GenreRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GenreRepositoryTest {

    @Test
    void findByAbbr() {
        GenreRepository genreRepository = new GenreRepository();
        Genre genre = genreRepository.findByAbbr("Comedy");

        assertEquals("Komedia", genre.getName());
    }

    @Test
    void findAllRoles() {
        GenreRepository genreRepository = new GenreRepository();
        List<Genre> genreList = genreRepository.findAll();

        assertEquals(13, genreList.size());
    }

    @Test
    void findByCollectionType() throws ValidationException {

        CollectionTypeRepository collectionTypeRepository = new CollectionTypeRepository();
        CollectionType collectionType = collectionTypeRepository.findByAbbr("BOOK");

        GenreRepository genreRepository = new GenreRepository();
        List<Genre> genreList = genreRepository.findByCollectionType(collectionType);

        assertEquals(4, genreList.size());

    }
}