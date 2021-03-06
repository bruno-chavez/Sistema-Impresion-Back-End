package cl.usm.prevencionderiesgos.si.repositories;

import cl.usm.prevencionderiesgos.si.models.PDF;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PDFRepository extends CrudRepository<PDF, Long> {
    List<PDF> findByStudentId(Integer id);
    PDF findByTitle(String title);
    PDF findByTitleAndStudentId(String title, Integer id);
}
