package pl.wsb.collection.repository.impl;

import org.apache.commons.lang.StringUtils;
import pl.wsb.collection.model.Role;
import pl.wsb.collection.repository.AbstractRepository;
import pl.wsb.collection.repository.EntityManagerHelper;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class RoleRepository extends AbstractRepository<Role, Integer> {

    @Override
    protected Class<Role> getPersistentClass() {
        return Role.class;
    }

    /**
     *
     * This methos is used to fetch from DB a Role object based on unique abbr value
     *
     * @param abbr - an uniqu role abbr name
     *
     * @return Role
     *
     */
    public Role findByAbbr(String abbr){
        if(StringUtils.isBlank(abbr)){
            return null;
        }

        CriteriaBuilder criteriaBuilder = EntityManagerHelper.entityManager().getCriteriaBuilder();
        CriteriaQuery<Role> criteriaQuery = criteriaBuilder.createQuery(Role.class);
        Root<Role> roles = criteriaQuery.from(Role.class);

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

    /**
     *
     * This method is returned to fetch from DB all available roles
     *
     * @param
     *
     * @return List<Role>
     */
    public List<Role> findAll(){
        CriteriaBuilder criteriaBuilder = EntityManagerHelper.entityManager().getCriteriaBuilder();
        CriteriaQuery<Role> criteriaQuery = criteriaBuilder.createQuery(Role.class);
        Root<Role> roles = criteriaQuery.from(Role.class);

        criteriaQuery.select(roles);

        return EntityManagerHelper.entityManager().createQuery(criteriaQuery).getResultList();
    }
}

