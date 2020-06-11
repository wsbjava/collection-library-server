package pl.wsb.collection.repository.impl;

import org.apache.commons.lang.StringUtils;
import pl.wsb.collection.exceptions.ValidationException;
import pl.wsb.collection.hibernate.CollectionType;
import pl.wsb.collection.hibernate.Genre;
import pl.wsb.collection.hibernate.GenreCollectionType;
import pl.wsb.collection.hibernate.Messages;
import pl.wsb.collection.repository.AbstractRepository;
import pl.wsb.collection.repository.EntityManagerHelper;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class MessagesRepository extends AbstractRepository<Messages, Integer> {

    @Override
    protected Class<Messages> getPersistentClass() {
        return Messages.class;
    }


    /**
     *
     * This method is returned from DB all available Genre
     *
     * @param
     *
     * @return List<Genre>
     */
    public List<Messages> findAll(){
        CriteriaBuilder criteriaBuilder = EntityManagerHelper.entityManager().getCriteriaBuilder();
        CriteriaQuery<Messages> criteriaQuery = criteriaBuilder.createQuery(Messages.class);
        Root<Messages> genres = criteriaQuery.from(Messages.class);

        criteriaQuery.select(genres);

        return EntityManagerHelper.entityManager().createQuery(criteriaQuery).getResultList();
    }




}

