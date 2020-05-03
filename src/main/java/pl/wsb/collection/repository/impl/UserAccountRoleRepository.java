package pl.wsb.collection.repository.impl;

import pl.wsb.collection.model.UserAccountRole;
import pl.wsb.collection.repository.AbstractRepository;

public class UserAccountRoleRepository extends AbstractRepository<UserAccountRole, Integer> {

    @Override
    protected Class<UserAccountRole> getPersistentClass() {
        return UserAccountRole.class;
    }
}

