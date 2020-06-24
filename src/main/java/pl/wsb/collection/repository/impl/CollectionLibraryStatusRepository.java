package pl.wsb.collection.repository.impl;

import org.apache.commons.lang.StringUtils;
import pl.wsb.collection.hibernate.CollectionLibraryStatus;
import pl.wsb.collection.hibernate.Role;
import pl.wsb.collection.repository.AbstractRepository;
import pl.wsb.collection.repository.EntityManagerHelper;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;


public class CollectionLibraryStatusRepository extends AbstractRepository<CollectionLibraryStatus, Integer> {

    @Override
    protected Class<CollectionLibraryStatus> getPersistentClass() {
        return CollectionLibraryStatus.class;
    }

    public CollectionLibraryStatus findByAbbr(String abbr){
        if(StringUtils.isBlank(abbr)){
            return null;
        }

        CriteriaBuilder criteriaBuilder = EntityManagerHelper.entityManager().getCriteriaBuilder();
        CriteriaQuery<CollectionLibraryStatus> criteriaQuery = criteriaBuilder.createQuery(CollectionLibraryStatus.class);
        Root<CollectionLibraryStatus> roles = criteriaQuery.from(CollectionLibraryStatus.class);

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
}