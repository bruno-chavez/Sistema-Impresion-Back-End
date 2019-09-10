package cl.usm.prevencionderiesgos.si.controllers.file;

import cl.usm.prevencionderiesgos.si.DTOs.DocumentInfo;
import cl.usm.prevencionderiesgos.si.DTOs.Documents;
import cl.usm.prevencionderiesgos.si.models.PDF;
import cl.usm.prevencionderiesgos.si.models.Student;
import cl.usm.prevencionderiesgos.si.repositories.PDFRepository;
import cl.usm.prevencionderiesgos.si.repositories.StudentRepository;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/file/info")
public class Info {

    private final PDFRepository pdfRepository;
    private final StudentRepository studentRepository;


    Info(PDFRepository pdfRepository, StudentRepository studentRepository) {
        this.pdfRepository = pdfRepository;
        this.studentRepository = studentRepository;

    }

    @GetMapping
    @ResponseBody
    public Documents SendTitles(HttpServletRequest request) {

        // Gets current session
        HttpSession session = request.getSession();
        Object email = session.getAttribute("student-email");

        // Gets Student object from session
        Student student = studentRepository.findByEmail(email.toString());

        // Gets PDF list with all the pdfs from a Student
        List<PDF> pdfs = pdfRepository.findByStudentId(student.getId());

        // Iterates over the list to get all the titles and appends them to a list
        List<DocumentInfo> documents = new ArrayList<>();

        LocalDate lastYear = LocalDate.now().minusYears(1);

        // Compares times between creation date and last year if the difference is less than a year the pdf gets deleted
        for (PDF pdf : pdfs) {
            LocalDate creationDate = pdf.getCreated_at().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            Period period = Period.between(lastYear, creationDate);
            int diff = period.getYears();
            System.out.println(diff);
            if (diff < 1) {
                pdfRepository.delete(pdf);
                continue;
            }

            documents.add(new DocumentInfo(pdf.getTitle(), pdf.getPages()));
        }


        return new Documents(documents);
    }
}