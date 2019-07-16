package cl.usm.prevencionderiesgos.si.repositories;

import cl.usm.prevencionderiesgos.si.models.PDF;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PDFRepository extends CrudRepository<PDF, Long> {
    PDF findById(Integer id);
}
