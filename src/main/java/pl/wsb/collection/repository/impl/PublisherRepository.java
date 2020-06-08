package pl.wsb.collection.repository.impl;

import org.apache.commons.lang.StringUtils;
import pl.wsb.collection.hibernate.*;
import pl.wsb.collection.repository.AbstractRepository;
import pl.wsb.collection.repository.EntityManagerHelper;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PublisherRepository extends AbstractRepository<Publisher, Integer> {

    @Override
    protected Class<Publisher> getPersistentClass() {
        return Publisher.class;
    }

    /**
     *
     * With this method is available to get publisher by their name
     *
     * @param String name
     *
     * @return Publisher
     *
     */
    public Publisher findByName(String name){
        if(StringUtils.isBlank(name)){
            return null;
        }

        CriteriaBuilder criteriaBuilder = EntityManagerHelper.entityManager().getCriteriaBuilder();
        CriteriaQuery<Publisher> criteriaQuery = criteriaBuilder.createQuery(Publisher.class);
        Root<Publisher> publisher = criteriaQuery.from(Publisher.class);

        criteriaQuery.where(
                criteriaBuilder.and(
                        criteriaBuilder.equal(
                                criteriaBuilder.lower(
                                        publisher.<String>get("name")
                                ),
                                name.toLowerCase()
                        ),
                        criteriaBuilder.equal(
                                publisher.get("deleted"),
                                0
                        )
                )
        );

        return this.getFirstResultOrNull(EntityManagerHelper.entityManager().createQuery(criteriaQuery).getResultList());
    }

    /**
     *
     * This method return Publisher object by their Id
     *
     * @param int Id
     *
     * @return Publisher
     *
     */
    public Publisher findById(int Id){
        if(Id < 1){
            return null;
        }

        return EntityManagerHelper.entityManager().getReference(Publisher.class, Id);
    }

    /**
     *
     * This method get all Publisher from the db
     *
     * @return List<Publisher>
     */
    public List<Publisher> findAll(){
        CriteriaBuilder criteriaBuilder = EntityManagerHelper.entityManager().getCriteriaBuilder();
        CriteriaQuery<Publisher> criteriaQuery = criteriaBuilder.createQuery(Publisher.class);
        Root<Publisher> genres = criteriaQuery.from(Publisher.class);

        criteriaQuery.select(genres);

        return EntityManagerHelper.entityManager().createQuery(criteriaQuery).getResultList();
    }

    /**
     *
     * This method get all Publisher from database, based on deleted parameter
     *
     * @param boolean deleted
     * @return List<Publisher>
     */
    public List<Publisher> findAll(boolean deleted){
        if(!deleted){
            return this.findAll();
        }

        CriteriaBuilder criteriaBuilder = EntityManagerHelper.entityManager().getCriteriaBuilder();
        CriteriaQuery<Publisher> criteriaQuery = criteriaBuilder.createQuery(Publisher.class);
        Root<Publisher> publisher = criteriaQuery.from(Publisher.class);

        criteriaQuery.where(
                criteriaBuilder.equal(
                        publisher.get("deleted"),
                        1
                )
        );
        criteriaQuery.select(publisher);

        return EntityManagerHelper.entityManager().createQuery(criteriaQuery).getResultList();
    }


    /**
     *
     * This method get all Publisher from database based on Genre of Collection Entry
     *
     * @param Genre genre
     *
     * @return Set<Publisher>
     *
     */
    public Set<Publisher> findAllByGenre(Genre genre){

        Set<Publisher> publishers = new HashSet<Publisher>(0);
        for(CollectionEntryGenre collectionEntryGenre : genre.getCollectionEntryGenres()){
            for(CollectionEntryPublisher collectionEntryPublisher : collectionEntryGenre.getCollectionEntry().getCollectionEntryPublishers()){
                publishers.add(collectionEntryPublisher.getPublisher());
            }
        }
        return publishers;
    }

    /**
     *
     * This method get all Publisher based on Collection Type of Collection Entry
     *
     * @param collectionType
     * @return
     */
    public Set<Publisher> findAllByCollectionType(CollectionType collectionType){

        Set<Publisher> publishers = new HashSet<Publisher>(0);
        for(CollectionEntry collectionEntry : collectionType.getCollectionEntries()){
            for(CollectionEntryPublisher collectionEntryPublisher : collectionEntry.getCollectionEntryPublishers()){
                publishers.add(collectionEntryPublisher.getPublisher());
            }
        }
        return publishers;
    }

}

