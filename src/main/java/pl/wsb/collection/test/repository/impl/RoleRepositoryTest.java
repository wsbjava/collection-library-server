package pl.wsb.collection.test.repository.impl;

import org.junit.jupiter.api.Test;
import pl.wsb.collection.hibernate.Role;
import pl.wsb.collection.repository.impl.RoleRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RoleRepositoryTest {

    @Test
    void findByAbbr() {
        RoleRepository roleRepository = new RoleRepository();
        Role role = roleRepository.findByAbbr("USER");

        assertEquals("Użytkownik", role.getName());
    }

    @Test
    void selectAllRoles(){
        RoleRepository roleRepository = new RoleRepository();
        List<Role> roles = roleRepository.findAll(100, 0, "");

        assertEquals(3, roles.size());
    }
}