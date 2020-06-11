package pl.wsb.collection.repository.impl;

import pl.wsb.collection.hibernate.Messages;
import pl.wsb.collection.hibernate.Suggestion;
import pl.wsb.collection.repository.AbstractRepository;
import pl.wsb.collection.repository.EntityManagerHelper;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class SuggestionRepository extends AbstractRepository<Suggestion, Integer> {

    @Override
    protected Class<Suggestion> getPersistentClass() {
        return Suggestion.class;
    }
    

    /**
     *
     * This method is returned from DB all available Genre
     *
     * @param
     *
     * @return List<Suggestion>
     */
    public List<Suggestion> findAll(){
        CriteriaBuilder criteriaBuilder = EntityManagerHelper.entityManager().getCriteriaBuilder();
        CriteriaQuery<Suggestion> criteriaQuery = criteriaBuilder.createQuery(Suggestion.class);
        Root<Suggestion> genres = criteriaQuery.from(Suggestion.class);

        criteriaQuery.select(genres);

        return EntityManagerHelper.entityManager().createQuery(criteriaQuery).getResultList();
    }




}

