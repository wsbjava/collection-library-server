package pl.wsb.collection.repository.impl;

import pl.wsb.collection.model.UserAccount;
import pl.wsb.collection.repository.AbstractRepository;

public class UserAccountRepository extends AbstractRepository<UserAccount, Integer> {

    @Override
    protected Class<UserAccount> getPersistentClass() {
        return UserAccount.class;
    }
}

