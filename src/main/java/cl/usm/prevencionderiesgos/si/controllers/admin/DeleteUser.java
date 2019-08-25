package cl.usm.prevencionderiesgos.si.controllers.admin;

import cl.usm.prevencionderiesgos.si.DTOs.Message;
import cl.usm.prevencionderiesgos.si.models.PDF;
import cl.usm.prevencionderiesgos.si.models.Student;
import cl.usm.prevencionderiesgos.si.repositories.PDFRepository;
import cl.usm.prevencionderiesgos.si.repositories.StudentRepository;

import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.List;


@RestController
@RequestMapping("/admin/delete/{user}")
public class DeleteUser {

    private final StudentRepository studentRepository;
    private final PDFRepository pdfRepository;


    DeleteUser(StudentRepository studentRepository, PDFRepository pdfRepository) {
        this.studentRepository = studentRepository;
        this.pdfRepository = pdfRepository;
    }

    @DeleteMapping
    @ResponseBody
    public Message Delete(@PathVariable("user") String user) {

        Student student = studentRepository.findByEmail(user);

        // Deletes every database's entry related to the user
        List<PDF> pdfs = pdfRepository.findByStudentId(student.getId());
        for (PDF pdf : pdfs) {
            pdfRepository.delete(pdf);
        }

        // Deletes actual files related to the user
        String filePath = String.join("/", "files", student.getId().toString());
        File f = new File(filePath);
        f.delete();

        studentRepository.delete(student);

        return new Message("user deleted");
    }
}
