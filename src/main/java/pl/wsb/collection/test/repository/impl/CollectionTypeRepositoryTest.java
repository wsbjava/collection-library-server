package pl.wsb.collection.test.repository.impl;

import org.junit.jupiter.api.Test;
import pl.wsb.collection.hibernate.CollectionType;
import pl.wsb.collection.repository.impl.CollectionTypeRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CollectionTypeRepositoryTest {

    @Test
    void findByAbbr() {
        CollectionTypeRepository collectionTypeRepository = new CollectionTypeRepository();
        CollectionType collectionType = collectionTypeRepository.findByAbbr("BOOK");

        assertEquals("Książka", collectionType.getName());
    }

    @Test
    void findAllCollectionTypes() {
        CollectionTypeRepository collectionTypeRepository = new CollectionTypeRepository();
        List<CollectionType> collectionTypeList = collectionTypeRepository.findAll();

        assertEquals(3, collectionTypeList.size());
    }
}