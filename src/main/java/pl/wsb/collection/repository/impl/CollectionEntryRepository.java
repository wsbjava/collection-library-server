package pl.wsb.collection.repository.impl;

import org.apache.commons.lang.StringUtils;
import pl.wsb.collection.exceptions.ValidationException;
import pl.wsb.collection.hibernate.*;
import pl.wsb.collection.model.ItemRequest;
import pl.wsb.collection.repository.AbstractRepository;
import pl.wsb.collection.repository.EntityManagerHelper;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class CollectionEntryRepository extends AbstractRepository<CollectionEntry, Integer> {

    @Override
    protected Class<CollectionEntry> getPersistentClass() {
        return CollectionEntry.class;
    }

    public CollectionEntry registerEntry(ItemRequest entryRequest) throws ValidationException{

        if(entryRequest.getAuthor() == null){
            throw new ValidationException("Please specify a Author of Entry!");
        }

        if(entryRequest.getPublisher() == null){
            throw new ValidationException("Please specicy a Publisher of Entry!");
        }
        CollectionTypeRepository collectionTypeRepository = new CollectionTypeRepository();
        CollectionType collectionType = collectionTypeRepository.findByAbbr(entryRequest.getType());

        if(collectionType == null){
            throw new ValidationException("Please specify a correct collection type!");
        }

        GenreRepository genreRepository = new GenreRepository();
        Genre genre = genreRepository.findByAbbr(entryRequest.getGenre());
        if(genre == null){
            throw new ValidationException("Please specify a correct abbr genre!");
        }


        CollectionEntry collectionEntry = new CollectionEntry();
        collectionEntry.setTitle(entryRequest.getTitle());
        collectionEntry.setCreated(new Date());
        collectionEntry.setModified(new Date());
        collectionEntry.setQuantity(10); //do przerobienia
        collectionEntry.setReleaseYear(entryRequest.getDateOfRelease().getYear());

        CollectionRequestStatusRepository collectionRequestStatusRepository = new CollectionRequestStatusRepository();
        CollectionRequestStatus collectionRequestStatus = collectionRequestStatusRepository.findByAbbr("Pending");
        collectionEntry.setCollectionRequestStatus(collectionRequestStatus);

        collectionEntry.setCollectionType(collectionType);


        EntityManagerHelper.startTransaction();
        collectionEntry = this.merge(collectionEntry);
        EntityManagerHelper.commitTransaction();

        CollectionEntryGenreRepository collectionEntryGenreRepository = new CollectionEntryGenreRepository();
        collectionEntryGenreRepository.assignCollectionEntryToGenre(collectionEntry, genre);

        return collectionEntry;
    }

    /**
     *
     * With this method is available to get list of all users registered in system
     *
     * @return List<CollectionEntry>
     *
     */
    public List<CollectionEntry> findAll(Integer limit, Integer offset, String search) {
        CriteriaBuilder criteriaBuilder = EntityManagerHelper.entityManager().getCriteriaBuilder();
        CriteriaQuery<CollectionEntry> criteriaQuery = criteriaBuilder.createQuery(CollectionEntry.class);
        Root<CollectionEntry> root = criteriaQuery.from(CollectionEntry.class);

        criteriaQuery.select(root);

        return EntityManagerHelper.entityManager().createQuery(criteriaQuery).getResultList();
    }


    public List<CollectionEntry> findByStatus(String abbrStatus) throws ValidationException {

        if(StringUtils.isBlank(abbrStatus)){
            throw new ValidationException("Please specify a status!");
        }

        CollectionRequestStatusRepository collectionRequestStatusRepository = new CollectionRequestStatusRepository();
        CollectionRequestStatus collectionRequestStatus = collectionRequestStatusRepository.findByAbbr(abbrStatus);

        if(collectionRequestStatus == null){
            throw new ValidationException("Please specify a correct status!");
        }

        List<CollectionEntry> collectionEntryList = new ArrayList<>(0);

        collectionEntryList.addAll(collectionRequestStatus.getCollectionEntries());

        return collectionEntryList;

    }


    public CollectionEntry acceptEntry(Integer id) throws ValidationException {

        if(id == null){
            throw new ValidationException("Please specify a entry to accept");
        }

        CollectionEntry collectionEntry = this.find(id);
        if(collectionEntry == null){
            throw new ValidationException("Please specify a correct entry to accept");
        }

        if(collectionEntry.getCollectionRequestStatus().getAbbr() == "Accepted"){
            throw new ValidationException("This entry is already accepted");
        }

        CollectionRequestStatusRepository collectionRequestStatusRepository = new CollectionRequestStatusRepository();
        CollectionRequestStatus collectionRequestStatus = collectionRequestStatusRepository.findByAbbr("Accepted");

        collectionEntry.setCollectionRequestStatus(collectionRequestStatus);

        EntityManagerHelper.startTransaction();
        collectionEntry = this.merge(collectionEntry);
        EntityManagerHelper.commitTransaction();

        return collectionEntry;


    }

    public CollectionEntry rejectEntry(Integer id) throws ValidationException {

        if(id == null){
            throw new ValidationException("Please specify a entry to reject");
        }

        CollectionEntry collectionEntry = this.find(id);
        if(collectionEntry == null){
            throw new ValidationException("Please specify a correct entry to reject");
        }

        if(collectionEntry.getCollectionRequestStatus().getAbbr() == "Rejected"){
            throw new ValidationException("This entry is already rejected");
        }

        CollectionRequestStatusRepository collectionRequestStatusRepository = new CollectionRequestStatusRepository();
        CollectionRequestStatus collectionRequestStatus = collectionRequestStatusRepository.findByAbbr("Rejected");

        collectionEntry.setCollectionRequestStatus(collectionRequestStatus);

        EntityManagerHelper.startTransaction();
        collectionEntry = this.merge(collectionEntry);
        EntityManagerHelper.commitTransaction();



        return collectionEntry;


    }

    public CollectionEntry assingGenre(Integer id, String genreAbbr) throws ValidationException{
        GenreRepository genreRepository = new GenreRepository();
        Genre genre = genreRepository.findByAbbr(genreAbbr);
        if(genre == null){
            throw new ValidationException("Please specify a correct abbr genre!");
        }

        CollectionEntry collectionEntry = this.find(id);
        if(collectionEntry == null){
            throw new ValidationException("Please specify a correct collection entry!");
        }

        System.out.println(collectionEntry.getCollectionEntryGenres().contains(genre));

        CollectionEntryGenre collectionEntryGenre = null;
        for (CollectionEntryGenre entryGenre : collectionEntry.getCollectionEntryGenres()) {
            if(entryGenre.getGenre() == genre){
                collectionEntryGenre = entryGenre;
            }
        }

        if(collectionEntry != null){
            throw new ValidationException("Genre is already assigned to the entry");
        }


        CollectionEntryGenreRepository collectionEntryGenreRepository = new CollectionEntryGenreRepository();
        collectionEntryGenreRepository.assignCollectionEntryToGenre(collectionEntry, genre);
        return this.find(id);
    }

    public CollectionEntry unAssingGenre(Integer id, String genreAbbr) throws ValidationException{
        GenreRepository genreRepository = new GenreRepository();
        Genre genre = genreRepository.findByAbbr(genreAbbr);
        if(genre == null){
            throw new ValidationException("Please specify a correct abbr genre!");
        }

        CollectionEntry collectionEntry = this.find(id);
        if(collectionEntry == null){
            throw new ValidationException("Please specify a correct collection entry!");
        }

        CollectionEntryGenre collectionEntryGenre = null;
        for (CollectionEntryGenre entryGenre : collectionEntry.getCollectionEntryGenres()) {
            if(entryGenre.getGenre() == genre){
                collectionEntryGenre = entryGenre;
            }
        }
        if(collectionEntry == null){
            throw new ValidationException("Collection entry has not specify genre");
        }

        CollectionEntryGenreRepository collectionEntryGenreRepository = new CollectionEntryGenreRepository();
        collectionEntryGenreRepository.unAssignCollectionEntryToGenre(collectionEntry, genre);
        return this.find(id);
    }

}