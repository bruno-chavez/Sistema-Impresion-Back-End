package cl.usm.prevencionderiesgos.si.repositories;

import cl.usm.prevencionderiesgos.si.models.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {

}
