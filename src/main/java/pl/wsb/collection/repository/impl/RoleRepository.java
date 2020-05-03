package pl.wsb.collection.repository.impl;

import pl.wsb.collection.model.Role;
import pl.wsb.collection.repository.AbstractRepository;

public class RoleRepository extends AbstractRepository<Role, Integer> {

    @Override
    protected Class<Role> getPersistentClass() {
        return Role.class;
    }
}

