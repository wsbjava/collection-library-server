package pl.wsb.collection.repository.impl;

import pl.wsb.collection.exceptions.ValidationException;
import pl.wsb.collection.model.Role;
import pl.wsb.collection.model.UserAccount;
import pl.wsb.collection.model.UserAccountRole;
import pl.wsb.collection.repository.AbstractRepository;
import pl.wsb.collection.repository.EntityManagerHelper;

import java.util.Date;

public class UserAccountRoleRepository extends AbstractRepository<UserAccountRole, Integer> {

    @Override
    protected Class<UserAccountRole> getPersistentClass() {
        return UserAccountRole.class;
    }

    public void assignUserToRole(UserAccount userAccount, Role role) throws ValidationException{
        if(role == null){
            throw new ValidationException("Please provide a role");
        }

        if(userAccount == null){
            throw new ValidationException("Please provide a user");
        }

        UserAccountRole userAccountRole = new UserAccountRole();
        userAccountRole.setCreated(new Date());
        userAccountRole.setModified(new Date());
        userAccountRole.setRole(role);
        userAccountRole.setUserAccount(userAccount);
        EntityManagerHelper.startTransaction();
        this.merge(userAccountRole);
        EntityManagerHelper.commitTransaction();
    }

    public void unAssingUserToRole(UserAccount userAccount, Role role) throws ValidationException{
        if(role == null){
            throw new ValidationException("Please provide a role");
        }

        if(userAccount == null){
            throw new ValidationException("Please provide a user");
        }

        for(UserAccountRole userAccountRole : userAccount.getUserAccountRoles()){
            if(userAccountRole.getRole() == role){
                EntityManagerHelper.startTransaction();
                this.delete(userAccountRole);
                EntityManagerHelper.commitTransaction();
            }
        }
    }

}

