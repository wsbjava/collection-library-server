package pl.wsb.collection.repository.impl;

import org.apache.commons.lang.StringUtils;
import pl.wsb.collection.exceptions.ValidationException;
import pl.wsb.collection.hibernate.Author;
import pl.wsb.collection.hibernate.Role;
import pl.wsb.collection.hibernate.UserAccount;
import pl.wsb.collection.model.AuthorRequest;
import pl.wsb.collection.repository.AbstractRepository;
import pl.wsb.collection.repository.EntityManagerHelper;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.util.List;

public class AuthorRepository extends AbstractRepository<Author, Integer> {

    @Override
    protected Class<Author> getPersistentClass() {
        return Author.class;
    }

    /**
     *
     * With this method is available to add author to the database. Method check by the
     * author name and last name is author is already added to the database. If yes - throw
     * an exception
     *
     * @param AuthorRequest body
     *
     * @return Author
     *
     * @throws ValidationException
     *
     */
    public Author registerBody(AuthorRequest body) throws ValidationException {

        Author author = this.findByNameAndSurname(body.getFirstName(), body.getLastName());
        if(author != null){
            throw new ValidationException("This author exist in DataBase");
        }

        author = new Author();
        author.setFirstName(body.getFirstName());
        author.setLastName(body.getLastName());
        author.setCreated(new Date());
        author.setModified(new Date());


        EntityManagerHelper.startTransaction();
        author = this.merge(author);
        EntityManagerHelper.commitTransaction();

        return author;
    }

    /**
     *
     * With this method is available to find author by their Name and Surname
     *
     * @param String name
     * @param String surname
     *
     * @return Author
     */
    public Author findByNameAndSurname(String name, String surname){
        if(StringUtils.isBlank(name)){
            return null;
        }
        if(StringUtils.isBlank(surname)){
            return null;
        }

        CriteriaBuilder criteriaBuilder = EntityManagerHelper.entityManager().getCriteriaBuilder();
        CriteriaQuery<Author> criteriaQuery = criteriaBuilder.createQuery(Author.class);
        Root<Author> authorRoot = criteriaQuery.from(Author.class);

        criteriaQuery.where(
                criteriaBuilder.and(
                        criteriaBuilder.equal(
                                criteriaBuilder.lower(
                                        authorRoot.<String>get("firstName")
                                ),
                                name.toLowerCase()
                        ),
                        criteriaBuilder.equal(
                                criteriaBuilder.lower(
                                        authorRoot.<String>get("lastName")
                                ),
                                surname.toLowerCase()
                        )
                )
        );

        return this.getFirstResultOrNull(EntityManagerHelper.entityManager().createQuery(criteriaQuery).getResultList());
    }

    /**
     *
     * With this method is available to get list of all users registered in system
     *
     * @return List<Author>
     *
     */
    public List<Author> findAll(Integer limit, Integer offset, String search) {
        CriteriaBuilder criteriaBuilder = EntityManagerHelper.entityManager().getCriteriaBuilder();
        CriteriaQuery<Author> criteriaQuery = criteriaBuilder.createQuery(Author.class);
        Root<Author> root = criteriaQuery.from(Author.class);

        criteriaQuery.select(root);

        return EntityManagerHelper.entityManager().createQuery(criteriaQuery).getResultList();
    }
    @Override
    public Author find(Integer id) throws ValidationException {
        Author author = super.find(id);
        if(author == null)
            throw new ValidationException("The following author doest not exist!");
        return author;
    }
}

