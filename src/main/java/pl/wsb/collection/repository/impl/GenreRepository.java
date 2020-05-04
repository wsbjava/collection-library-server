package pl.wsb.collection.repository.impl;

import org.apache.commons.lang.StringUtils;
import pl.wsb.collection.exceptions.ValidationException;
import pl.wsb.collection.model.CollectionType;
import pl.wsb.collection.model.Genre;
import pl.wsb.collection.model.GenreCollectionType;
import pl.wsb.collection.repository.AbstractRepository;
import pl.wsb.collection.repository.EntityManagerHelper;

import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public class GenreRepository extends AbstractRepository<Genre, Integer> {

    @Override
    protected Class<Genre> getPersistentClass() {
        return Genre.class;
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
    public Genre findByAbbr(String abbr){
        if(StringUtils.isBlank(abbr)){
            return null;
        }

        CriteriaBuilder criteriaBuilder = EntityManagerHelper.entityManager().getCriteriaBuilder();
        CriteriaQuery<Genre> criteriaQuery = criteriaBuilder.createQuery(Genre.class);
        Root<Genre> roles = criteriaQuery.from(Genre.class);

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
     * This method is returned from DB all available Genre
     *
     * @param
     *
     * @return List<Genre>
     */
    public List<Genre> findAll(){
        CriteriaBuilder criteriaBuilder = EntityManagerHelper.entityManager().getCriteriaBuilder();
        CriteriaQuery<Genre> criteriaQuery = criteriaBuilder.createQuery(Genre.class);
        Root<Genre> genres = criteriaQuery.from(Genre.class);

        criteriaQuery.select(genres);

        return EntityManagerHelper.entityManager().createQuery(criteriaQuery).getResultList();
    }

    /**
     *
     * This method is returned from DB all available Genre associated with provide CollectionType
     *
     * @param collectionType - requestem Collection type
     *
     * @return List<Genre>
     *
     * @throws ValidationException
     */
    public List<Genre> findByCollectionType(CollectionType collectionType) throws ValidationException{

        if(collectionType == null){
            throw new ValidationException("No collection type specified");
        }

        List<Genre> genreList = new ArrayList<>(0);


        for(GenreCollectionType item : collectionType.getGenreCollectionTypes())
        {
            genreList.add(item.getGenre());
        }

        return genreList;

    }

    public List<Genre> findByCollectionType(int collectionTypeId) throws ValidationException{

        if(collectionTypeId < 1){
            throw new ValidationException("Please select a collection type");
        }

        CollectionTypeRepository collectionTypeRepository = new CollectionTypeRepository();
        return this.findByCollectionType(collectionTypeRepository.find(collectionTypeId));

    }

    public List<Genre> findByCollectionType(String collectionTypeAbbr) throws ValidationException{
        if(StringUtils.isBlank(collectionTypeAbbr)){
            return null;
        }
        CollectionTypeRepository collectionTypeRepository = new CollectionTypeRepository();
        return this.findByCollectionType(collectionTypeRepository.findByAbbr(collectionTypeAbbr));
    }
}

