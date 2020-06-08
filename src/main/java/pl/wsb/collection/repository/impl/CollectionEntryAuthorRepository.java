package pl.wsb.collection.repository.impl;

import pl.wsb.collection.exceptions.ValidationException;
import pl.wsb.collection.hibernate.Author;
import pl.wsb.collection.hibernate.CollectionEntry;
import pl.wsb.collection.hibernate.CollectionEntryAuthor;
import pl.wsb.collection.repository.AbstractRepository;
import pl.wsb.collection.repository.EntityManagerHelper;

import java.util.Date;


public class CollectionEntryAuthorRepository extends AbstractRepository<CollectionEntryAuthor, Integer> {

    @Override
    protected Class<CollectionEntryAuthor> getPersistentClass() {
        return CollectionEntryAuthor.class;
    }

    public void assignCollectionEntryToAuthor(CollectionEntry collectionEntry, Author author) throws ValidationException {
        if(collectionEntry == null){
            throw new ValidationException("Please specific a collection");
        }

        if(author == null){
            throw new ValidationException("Please specific a author");
        }

        CollectionEntryAuthor collectionEntryAuthor = new CollectionEntryAuthor();
        collectionEntryAuthor.setCreated(new Date());
        collectionEntryAuthor.setModified(new Date());
        collectionEntryAuthor.setAuthor(author);
        collectionEntryAuthor.setCollectionEntry(collectionEntry);
        EntityManagerHelper.startTransaction();
        this.merge(collectionEntryAuthor);
        EntityManagerHelper.commitTransaction();
    }
    public void unAssignCollectionEntryToAuthor(CollectionEntry collectionEntry, Author author) throws ValidationException {
        if(collectionEntry == null){
            throw new ValidationException("Please specific a collection");
        }

        if(author == null){
            throw new ValidationException("Please specific a author");
        }

        for(CollectionEntryAuthor collectionEntryAuthor : collectionEntry.getCollectionEntryAuthors()){
            if(collectionEntryAuthor.getAuthor() == author){
                EntityManagerHelper.startTransaction();
                this.delete(collectionEntryAuthor);
                EntityManagerHelper.commitTransaction();
            }
        }

    }


}