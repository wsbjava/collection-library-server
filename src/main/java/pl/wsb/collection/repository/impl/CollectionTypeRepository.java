package pl.wsb.collection.repository.impl;

import org.apache.commons.lang.StringUtils;
import pl.wsb.collection.hibernate.CollectionType;
import pl.wsb.collection.repository.AbstractRepository;
import pl.wsb.collection.repository.EntityManagerHelper;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;


public class CollectionTypeRepository extends AbstractRepository<CollectionType, Integer> {

    @Override
    protected Class<CollectionType> getPersistentClass() {
        return CollectionType.class;
    }

    public CollectionType findByAbbr(String abbr){
        if(StringUtils.isBlank(abbr)){
            return null;
        }

        CriteriaBuilder criteriaBuilder = EntityManagerHelper.entityManager().getCriteriaBuilder();
        CriteriaQuery<CollectionType> criteriaQuery = criteriaBuilder.createQuery(CollectionType.class);
        Root<CollectionType> roles = criteriaQuery.from(CollectionType.class);

        criteriaQuery.where(
                criteriaBuilder.equal(
                        criteriaBuilder.lower(
                                roles.<String>get("abbr")
                        ),
                        abbr.toLowerCase()
                )
        );

        return this.getFirstResultOrNull(EntityManagerHelper.entityManager().createQuery(criteriaQuery).getResultList());
    }

    public List<CollectionType> findAll(){
        CriteriaBuilder criteriaBuilder = EntityManagerHelper.entityManager().getCriteriaBuilder();
        CriteriaQuery<CollectionType> criteriaQuery = criteriaBuilder.createQuery(CollectionType.class);
        Root<CollectionType> collectionTypes = criteriaQuery.from(CollectionType.class);

        criteriaQuery.select(collectionTypes);

        return EntityManagerHelper.entityManager().createQuery(criteriaQuery).getResultList();
    }

}