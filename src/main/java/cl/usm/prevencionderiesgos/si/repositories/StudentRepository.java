package cl.usm.prevencionderiesgos.si.repositories;

import cl.usm.prevencionderiesgos.si.models.Student;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {
    Student findByEmail(String email);
    List<Student> findAll();
}
