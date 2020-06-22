package pl.wsb.collection.repository.impl;

import org.apache.commons.lang.StringUtils;
import pl.wsb.collection.hibernate.CollectionRequestStatus;
import pl.wsb.collection.hibernate.Genre;
import pl.wsb.collection.repository.AbstractRepository;
import pl.wsb.collection.repository.EntityManagerHelper;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;


public class CollectionRequestStatusRepository extends AbstractRepository<CollectionRequestStatus, Integer> {

    @Override
    protected Class<CollectionRequestStatus> getPersistentClass() {
        return CollectionRequestStatus.class;
    }

    /**
     *
     * This methos is used to fetch from DB a Role object based on unique abbr value
     *
     * @param abbr - an unique CollectionRequestStatus abbr name
     *
     * @return CollectionRequestStatus
     *
     */
    public CollectionRequestStatus findByAbbr(String abbr){
        if(StringUtils.isBlank(abbr)){
            return null;
        }

        CriteriaBuilder criteriaBuilder = EntityManagerHelper.entityManager().getCriteriaBuilder();
        CriteriaQuery<CollectionRequestStatus> criteriaQuery = criteriaBuilder.createQuery(CollectionRequestStatus.class);
        Root<CollectionRequestStatus> root = criteriaQuery.from(CollectionRequestStatus.class);

        criteriaQuery.where(
                criteriaBuilder.equal(
                        criteriaBuilder.lower(
                                root.<String>get("abbr")
                        ),
                        abbr.toLowerCase()
                )
        );

        return this.getFirstResultOrNull(EntityManagerHelper.entityManager().createQuery(criteriaQuery).getResultList());
    }
}