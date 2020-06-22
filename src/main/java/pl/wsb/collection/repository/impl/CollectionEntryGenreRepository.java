package pl.wsb.collection.repository.impl;

import pl.wsb.collection.exceptions.ValidationException;
import pl.wsb.collection.hibernate.*;
import pl.wsb.collection.repository.AbstractRepository;
import pl.wsb.collection.repository.EntityManagerHelper;

import java.util.Date;


public class CollectionEntryGenreRepository extends AbstractRepository<CollectionEntryGenre, Integer> {

    @Override
    protected Class<CollectionEntryGenre> getPersistentClass() {
        return CollectionEntryGenre.class;
    }


    public void assignCollectionEntryToGenre(CollectionEntry collectionEntry, Genre genre) throws ValidationException {
        if(collectionEntry == null){
            throw new ValidationException("Please provide a collection entry");
        }

        if(genre == null){
            throw new ValidationException("Please provide a genre");
        }

        CollectionEntryGenre collectionEntryGenre = new CollectionEntryGenre();
        collectionEntryGenre.setCreated(new Date());
        collectionEntryGenre.setModified(new Date());
        collectionEntryGenre.setCollectionEntry(collectionEntry);
        collectionEntryGenre.setGenre(genre);
        EntityManagerHelper.startTransaction();
        this.merge(collectionEntryGenre);
        EntityManagerHelper.commitTransaction();
    }
    public void unAssignCollectionEntryToGenre(CollectionEntry collectionEntry, Genre genre) throws ValidationException {
        if(collectionEntry == null){
            throw new ValidationException("Please provide a collection entry");
        }

        if(genre == null){
            throw new ValidationException("Please provide a genre");
        }

        for(CollectionEntryGenre collectionEntryGenre : collectionEntry.getCollectionEntryGenres()){
            if(collectionEntryGenre.getGenre() == genre){
                EntityManagerHelper.startTransaction();
                this.delete(collectionEntryGenre);
                EntityManagerHelper.commitTransaction();
            }
        }
    }


}