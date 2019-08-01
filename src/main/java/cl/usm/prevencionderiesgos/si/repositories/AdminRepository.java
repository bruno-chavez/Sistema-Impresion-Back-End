package cl.usm.prevencionderiesgos.si.repositories;

import cl.usm.prevencionderiesgos.si.models.Admin;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends CrudRepository<Admin, Long> {
    Admin findByEmail(String email);
}
