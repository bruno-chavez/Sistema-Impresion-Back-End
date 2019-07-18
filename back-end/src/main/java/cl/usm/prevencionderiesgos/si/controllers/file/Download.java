package cl.usm.prevencionderiesgos.si.controllers.file;

import cl.usm.prevencionderiesgos.si.DTOs.SendFileResponse;
import cl.usm.prevencionderiesgos.si.models.PDF;
import cl.usm.prevencionderiesgos.si.models.Student;
import cl.usm.prevencionderiesgos.si.repositories.PDFRepository;
import cl.usm.prevencionderiesgos.si.repositories.StudentRepository;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@RestController
@RequestMapping("/file/download")
public class Download {

    private final PDFRepository pdfRepository;
    private final StudentRepository studentRepository;


    Download(PDFRepository pdfRepository, StudentRepository studentRepository) {
        this.pdfRepository = pdfRepository;
        this.studentRepository = studentRepository;

    }

    @PostMapping
    @ResponseBody
    public SendFileResponse SendFile(@RequestBody PDF pdf, HttpServletRequest request) {
        try {

            // Gets current session
            HttpSession session = request.getSession();
            Object email = session.getAttribute("student-email");

            Student student = studentRepository.findByEmail(email.toString());

            PDF row = pdfRepository.findByTitleAndStudentId(pdf.getTitle(), student.getId());

            return new SendFileResponse(row.getFile());

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}