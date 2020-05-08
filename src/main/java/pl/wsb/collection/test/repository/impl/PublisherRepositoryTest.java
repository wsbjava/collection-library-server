package pl.wsb.collection.test.repository.impl;

import org.junit.jupiter.api.Test;
import pl.wsb.collection.model.Publisher;
import pl.wsb.collection.repository.impl.PublisherRepository;

import static org.junit.jupiter.api.Assertions.*;

class PublisherRepositoryTest {

    @Test
    void findByName() {

        PublisherRepository publisherRepository = new PublisherRepository();
        Publisher result = publisherRepository.findByName("Wydawnictwo Agora");

        assertEquals(4, result.getId());

    }
}