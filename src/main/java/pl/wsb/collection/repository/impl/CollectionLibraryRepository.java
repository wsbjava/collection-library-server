package pl.wsb.collection.repository.impl;

import org.apache.commons.lang.StringUtils;
import org.hibernate.cache.CacheException;
import pl.wsb.collection.exceptions.ValidationException;
import pl.wsb.collection.hibernate.*;
import pl.wsb.collection.model.ItemRentalRequest;
import pl.wsb.collection.repository.AbstractRepository;
import pl.wsb.collection.repository.EntityManagerHelper;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class CollectionLibraryRepository extends AbstractRepository<CollectionLibrary, Integer> {

    @Override
    protected Class<CollectionLibrary> getPersistentClass() {
        return CollectionLibrary.class;
    }

    /**
     *
     * Metoda zwraca wszystkie wpisy jakie zostały dodane do tabeli wypożyczeń
     *
     * @param limit
     * @param offset
     * @param search
     * @return
     */
    public List<CollectionLibrary> findAll(Integer limit, Integer offset, String search){
        CriteriaBuilder criteriaBuilder = EntityManagerHelper.entityManager().getCriteriaBuilder();
        CriteriaQuery<CollectionLibrary> criteriaQuery = criteriaBuilder.createQuery(CollectionLibrary.class);
        Root<CollectionLibrary> roles = criteriaQuery.from(CollectionLibrary.class);

        criteriaQuery.select(roles);

        return EntityManagerHelper.entityManager().createQuery(criteriaQuery).getResultList();
    }

    /**
     *
     * Metoda zwraca wszystkie wpisy wypożyczeń dla konkretnego uzytkownika
     * @param id
     * @param limit
     * @param offset
     * @param search
     * @return
     * @throws ValidationException
     */
    public List<CollectionLibrary> findAllByUser(Integer id, Integer limit, Integer offset, String search) throws ValidationException {

        UserAccount userAccount = new UserAccountRepository().find(id);

        List<CollectionLibrary> collectionLibraryList = new ArrayList<>();

        for (CollectionLibrary item : userAccount.getCollectionLibraries()){
            System.out.println(item.getId());
            System.out.println(item.getCollectionEntry().getTitle());
            collectionLibraryList.add(item);
        }


        return collectionLibraryList;

    }

    /**
     *
     * Metoda dodaje wpis mówiący o wypożyczeniu książki
     *
     * @param userId
     * @param body
     * @return
     * @throws ValidationException
     */
    public CollectionLibrary rentEntry(Integer userId, ItemRentalRequest body) throws ValidationException {

        if(userId < 1){
            throw new ValidationException("Please provide a user");
        }

        UserAccount userAccount = new UserAccountRepository().find(userId);
        if(userAccount == null){
            throw new ValidationException("Provided user doest not exist");
        }

        CollectionEntry collectionEntry = new CollectionEntryRepository().find(body.getItemId());
        if(collectionEntry == null){
            throw new ValidationException("Provided collection entry doest not exist");
        }

        CollectionLibraryStatus collectionLibraryStatus = new CollectionLibraryStatusRepository().findByAbbr("Rent");

        CollectionLibrary collectionLibrary = new CollectionLibrary();

        collectionLibrary.setCollectionEntry(collectionEntry);
        collectionLibrary.setCollectionLibraryStatus(collectionLibraryStatus);
        collectionLibrary.setCreated(new Date());
        collectionLibrary.setModified(new Date());
        collectionLibrary.setEntryReturn(new Date(new Date().getTime() + (1000 * 60 * 60 * 24)*body.getDuration()));
        collectionLibrary.setUserAccount(userAccount);

        EntityManagerHelper.startTransaction();
        collectionLibrary = this.merge(collectionLibrary);
        EntityManagerHelper.commitTransaction();
        return collectionLibrary;

    }

    /**
     *
     * Metoda zwraz wypozyczenie na podstawie uzytkownika oraz Collection entry
     * @param userAccount
     * @param collectionEntry
     * @return
     * @throws ValidationException
     */
    public CollectionLibrary findByUserAndCollectionEntry(UserAccount userAccount, CollectionEntry collectionEntry) throws ValidationException {
        if(userAccount == null){
            throw new ValidationException("Please specify a user");
        }

        if(collectionEntry == null){
            throw new ValidationException("Please specify a user");
        }

        List<CollectionLibrary> collectionLibraryList = this.findAllByUser(userAccount.getId(), 0,0,"");

        CollectionLibrary collectionLibrary = null;
        for(CollectionLibrary item : collectionLibraryList){

            if(item.getCollectionEntry().getId() == collectionEntry.getId()){
                collectionLibrary = item;
                break;
            }
        }
        return collectionLibrary;



    }

    /**
     *
     * Metoda pozwala na aktualizacje wpisu odnośnie wypożyczenia - CollectionEntry został zwrócony
     *
     * @param userId
     * @param body
     * @return
     * @throws ValidationException
     */
    public CollectionLibrary returnEntry(Integer userId, ItemRentalRequest body) throws ValidationException {
        if(userId < 1){
            throw new ValidationException("Please provide a user");
        }

        UserAccount userAccount = new UserAccountRepository().find(userId);
        if(userAccount == null){
            throw new ValidationException("Provided user doest not exist");
        }

        CollectionEntry collectionEntry = new CollectionEntryRepository().find(body.getItemId());
        if(collectionEntry == null){
            throw new ValidationException("Provided collection entry doest not exist");
        }

        CollectionLibrary collectionLibrary = this.findByUserAndCollectionEntry(userAccount, collectionEntry);

        if(collectionLibrary == null){
            throw new ValidationException("The rental item was not found");
        }

        CollectionLibraryStatus collectionLibraryStatus = new CollectionLibraryStatusRepository().findByAbbr("Returned");

        collectionLibrary.setCollectionLibraryStatus(collectionLibraryStatus);
        collectionLibrary.setModified(new Date());

        EntityManagerHelper.startTransaction();
        collectionLibrary = this.merge(collectionLibrary);
        EntityManagerHelper.commitTransaction();
        return collectionLibrary;
    }
}