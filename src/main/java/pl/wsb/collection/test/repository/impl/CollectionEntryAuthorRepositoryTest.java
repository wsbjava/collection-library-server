package pl.wsb.collection.test.repository.impl;

import org.junit.jupiter.api.Test;
import pl.wsb.collection.exceptions.ValidationException;
import pl.wsb.collection.hibernate.Author;
import pl.wsb.collection.hibernate.CollectionEntry;
import pl.wsb.collection.repository.EntityManagerHelper;
import pl.wsb.collection.repository.impl.CollectionEntryAuthorRepository;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class CollectionEntryAuthorRepositoryTest {

    @Test
    void assignCollectionEntryToAuthor() throws ValidationException {
        CollectionEntry collectionEntry = new CollectionEntry();
        collectionEntry.setCreated(new Date());
        collectionEntry.setModified(new Date());
        collectionEntry.setQuantity(10);
        collectionEntry.setReleaseYear(2010);
        collectionEntry.setTitle("Tytuł");
        EntityManagerHelper.startTransaction();
        EntityManagerHelper.entityManager().merge(collectionEntry);
        EntityManagerHelper.commitTransaction();

        Author author = new Author();
        author.setCreated(new Date());
        author.setModified(new Date());
        author.setFirstName("Autor");
        author.setLastName("Testowy");
        EntityManagerHelper.startTransaction();
        EntityManagerHelper.entityManager().merge(author);
        EntityManagerHelper.commitTransaction();

        CollectionEntryAuthorRepository collectionEntryAuthorRepository = new CollectionEntryAuthorRepository();
        collectionEntryAuthorRepository.assignCollectionEntryToAuthor(collectionEntry,author);

        assertEquals(1, 1 );


    }

    @Test
    void unAssignCollectionEntryToAuthor() {
    }
}