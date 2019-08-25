package cl.usm.prevencionderiesgos.si.controllers.file;


import cl.usm.prevencionderiesgos.si.models.PDF;
import cl.usm.prevencionderiesgos.si.models.Student;
import cl.usm.prevencionderiesgos.si.repositories.PDFRepository;
import cl.usm.prevencionderiesgos.si.repositories.StudentRepository;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.FileInputStream;


@RestController
@RequestMapping("/file/fetch/{action}/{title}")
public class Show {

    private final PDFRepository pdfRepository;
    private final StudentRepository studentRepository;


    Show(PDFRepository pdfRepository, StudentRepository studentRepository) {
        this.pdfRepository = pdfRepository;
        this.studentRepository = studentRepository;

    }

    private ResponseEntity<InputStreamResource> constructResponse(Student student, PDF pdf, String action) {

        if (action.equals("print")) {
            student.setPages(student.getPages() + pdf.getPages());
            studentRepository.save(student);
        }

        String filePath = String.join("/",
                "files", student.getId().toString(), pdf.getTitle());

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=" + pdf.getTitle());

        try {
            return ResponseEntity
                    .ok()
                    .headers(headers)
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(new InputStreamResource(new FileInputStream(filePath)));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping
    public ResponseEntity<InputStreamResource> ShowFile(@PathVariable("action") String action,
                                                        @PathVariable("title") String title,
                                                        HttpServletRequest request) {

        // Spring truncates file format, so adding it back is necessary
        title += ".pdf";

        // Gets current session
        HttpSession session = request.getSession();
        Object email = session.getAttribute("student-email");
        Object type = session.getAttribute("type");

        // Searches student and pdf
        Student student = studentRepository.findByEmail(email.toString());
        PDF doc = pdfRepository.findByTitleAndStudentId(title, student.getId());
        int pages = doc.getPages();

        // Compares available pages and file pages
        if (type.toString().equals("Regular")) {
            if (student.getPages() + pages < 250) {
                return constructResponse(student, doc, action);
            }
        } else if (type.toString().equals("Memorista")) {
            if (student.getPages() + pages < 300) {
                return constructResponse(student, doc, action);
            }
        }

        // It should never get here
        return null;
    }
}