package pl.wsb.collection.test.repository.impl;

import org.junit.jupiter.api.Test;
import pl.wsb.collection.exceptions.ValidationException;
import pl.wsb.collection.hibernate.Role;
import pl.wsb.collection.repository.impl.RoleRepository;

import java.util.ArrayList;
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


    @Test
    void getAdminrRoleList(){

        try {
            RoleRepository roleRepository = new RoleRepository();
            List<Role> roleList = roleRepository.findAllByAbbr("ADMIN");
            List<Role> expectedRoleList = new ArrayList<>();
            expectedRoleList.add(roleRepository.findByAbbr("ADMIN"));
            expectedRoleList.add(roleRepository.findByAbbr("EMPLOYEE"));
            expectedRoleList.add(roleRepository.findByAbbr("USER"));

            assertEquals(expectedRoleList, roleList);
        }
        catch (ValidationException ex){
            System.out.println(ex.getMessage());
        }
    }

    @Test
    void getEmployeeRoleList(){
        try {
            RoleRepository roleRepository = new RoleRepository();
            List<Role> roleList = roleRepository.findAllByAbbr("EMPLOYEE");
            List<Role> expectedRoleList = new ArrayList<>();
            expectedRoleList.add(roleRepository.findByAbbr("EMPLOYEE"));
            expectedRoleList.add(roleRepository.findByAbbr("USER"));

            assertEquals(expectedRoleList, roleList);
        }
        catch (ValidationException ex){
            System.out.println(ex.getMessage());
        }
    }

    @Test
    void getUserRoleList(){
        try {
            RoleRepository roleRepository = new RoleRepository();
            List<Role> roleList = roleRepository.findAllByAbbr("USER");
            List<Role> expectedRoleList = new ArrayList<>();
            expectedRoleList.add(roleRepository.findByAbbr("USER"));

            assertEquals(expectedRoleList, roleList);
        }
        catch (ValidationException ex){
            System.out.println(ex.getMessage());
        }
    }
}