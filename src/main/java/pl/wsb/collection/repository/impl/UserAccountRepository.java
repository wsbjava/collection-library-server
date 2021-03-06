package pl.wsb.collection.repository.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.NotImplementedException;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import pl.wsb.collection.exceptions.ValidationException;
import pl.wsb.collection.hibernate.Role;
import pl.wsb.collection.hibernate.UserAccount;
import pl.wsb.collection.hibernate.UserAccountRole;
import pl.wsb.collection.model.RegisterUserRequest;
import pl.wsb.collection.model.User;
import pl.wsb.collection.repository.AbstractRepository;
import pl.wsb.collection.repository.EntityManagerHelper;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserAccountRepository extends AbstractRepository<UserAccount, Integer> {

    @Override
    protected Class<UserAccount> getPersistentClass() {
        return UserAccount.class;
    }



    /**
     *
     * This method is used to get user by e-mail address
     *
     * @param email - unique e-mail address
     *
     * @return UserAccount
     */
    public UserAccount findByEmail(String email){
        if(StringUtils.isBlank(email)){
            return null;
        }
        CriteriaBuilder criteriaBuilder = EntityManagerHelper.entityManager().getCriteriaBuilder();
        CriteriaQuery<UserAccount> criteriaQuery = criteriaBuilder.createQuery(UserAccount.class);
        Root<UserAccount> userAccounts = criteriaQuery.from(UserAccount.class);

        criteriaQuery.where(
                criteriaBuilder.equal(
                        criteriaBuilder.lower(
                                userAccounts.<String>get("email")
                        ),
                        email.toLowerCase()
                )
        );

        return this.getFirstResultOrNull(EntityManagerHelper.entityManager().createQuery(criteriaQuery).getResultList());
    }
    /**
     *
     * With this method is available to add new User to the system
     *
     * @// TODO: 04.05.2020 dorobić aby możliwe było dodanie roli dla użytkownika
     *
     * @return UserAccount - object with User that was added to the system
     *
     * @throws ValidationException
     */
    public UserAccount registerUser(RegisterUserRequest userRequest) throws ValidationException {


        UserAccount userAccount = this.findByEmail(userRequest.getEmail());
        if(userAccount !=null){
            throw new ValidationException("The email is already registered");
        }

        userAccount = new UserAccount();
        userAccount.setCreated(new Date());
        userAccount.setModified(new Date());
        userAccount.setEmail(userRequest.getEmail()); //z requestu
        userAccount.setDateOfBirth(userRequest.getDateOfBirth());
        userAccount.setPassSalt(
                DigestUtils.sha256Hex(
                        RandomStringUtils.randomAlphanumeric(256)
                )
        );
        userAccount.setPassHash(
                userAccount.generatePassHash(userRequest.getPassword(), userAccount.getPassSalt())
        );
        userAccount.setFirstName(userRequest.getName()); //z requesta
        userAccount.setLastName(userRequest.getSurname()); //z request

        EntityManagerHelper.startTransaction();
        userAccount = this.merge(userAccount);
        EntityManagerHelper.commitTransaction();

        UserAccountRoleRepository userAccountRoleRepository = new UserAccountRoleRepository();
        RoleRepository roleRepository = new RoleRepository();

        userAccountRoleRepository.assignUserToRole(
                userAccount,
                roleRepository.findByAbbr("USER")
        );

        return userAccount;

    }

    /**
     *
     * With this method is available to get list of all users registered in system
     *
     * @return List<UserAccount>
     *
     */
    public List<UserAccount> findAll(Integer limit, Integer offset, String search) {
        CriteriaBuilder criteriaBuilder = EntityManagerHelper.entityManager().getCriteriaBuilder();
        CriteriaQuery<UserAccount> criteriaQuery = criteriaBuilder.createQuery(UserAccount.class);
        Root<UserAccount> root = criteriaQuery.from(UserAccount.class);

        criteriaQuery.select(root);

        return EntityManagerHelper.entityManager().createQuery(criteriaQuery).getResultList();
    }

    public UserAccount setRole(Integer id, String roleAbbr) throws ValidationException {
        if(id < 1){
            throw new ValidationException("Please specify a correct User");
        }

        UserAccount userAccount = this.find(id);

        if(userAccount == null){
            throw new ValidationException("The provide user does not exist!");
        }

        UserAccountRoleRepository userAccountRoleRepository = new UserAccountRoleRepository();

        for(UserAccountRole userAccountRole : userAccount.getUserAccountRoles()){
            userAccountRoleRepository.unAssingUserToRole(userAccount, userAccountRole.getRole());
        }

        for(Role role :  new RoleRepository().findAllByAbbr(roleAbbr))
        {
            userAccountRoleRepository.assignUserToRole(userAccount, role);
        }


        return userAccount;
    }
}

