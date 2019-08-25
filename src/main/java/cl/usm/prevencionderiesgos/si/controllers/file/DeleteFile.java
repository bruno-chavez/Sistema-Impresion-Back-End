package cl.usm.prevencionderiesgos.si.controllers.file;


import cl.usm.prevencionderiesgos.si.DTOs.DocumentInfo;
import cl.usm.prevencionderiesgos.si.DTOs.Message;
import cl.usm.prevencionderiesgos.si.DTOs.Pages;
import cl.usm.prevencionderiesgos.si.models.PDF;
import cl.usm.prevencionderiesgos.si.models.Student;
import cl.usm.prevencionderiesgos.si.repositories.PDFRepository;
import cl.usm.prevencionderiesgos.si.repositories.StudentRepository;

import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.io.File;


@RestController
@RequestMapping("/file/delete/{title}")
public class DeleteFile {

    private final StudentRepository studentRepository;
    private final PDFRepository pdfRepository;


    DeleteFile(PDFRepository pdfRepository, StudentRepository studentRepository) {
        this.pdfRepository = pdfRepository;
        this.studentRepository = studentRepository;
    }

    @DeleteMapping
    @ResponseBody
    public ResponseEntity<Message> DeleteDoc(@PathVariable("title") String title, HttpServletRequest request) {

        // Gets current session
        HttpSession session = request.getSession();
        Object email = session.getAttribute("student-email");

        // Searches student
        Student student = studentRepository.findByEmail(email.toString());

        String filePath = String.join("/",
                "files",
                student.getId().toString(),
                title);

        File f = new File(filePath);

        if (f.delete()) {
            PDF pdf = pdfRepository.findByTitle(title);
            pdfRepository.delete(pdf);
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(new Message("file deleted"));
        } else {
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(new Message("file not found"));
        }
    }
}