package cl.usm.prevencionderiesgos.si.controllers.file;

import cl.usm.prevencionderiesgos.si.DTOs.Message;
import cl.usm.prevencionderiesgos.si.models.PDF;
import cl.usm.prevencionderiesgos.si.models.Student;
import cl.usm.prevencionderiesgos.si.repositories.PDFRepository;
import cl.usm.prevencionderiesgos.si.repositories.StudentRepository;

import org.apache.pdfbox.pdmodel.PDDocument;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;

@RestController
@RequestMapping("/file/upload")
public class Upload {

    private final PDFRepository pdfRepository;
    private final StudentRepository studentRepository;


    Upload(PDFRepository pdfRepository, StudentRepository studentRepository) {
        this.pdfRepository = pdfRepository;
        this.studentRepository = studentRepository;

    }


    @GetMapping
    public ResponseEntity<InputStreamResource> CheckFiles() {
        try {

            PDF pdf = pdfRepository.findById(68);

            try (FileOutputStream fos = new FileOutputStream("pdf.pdf")) {
                fos.write(pdf.getFile());
            }

            try (FileInputStream file = new FileInputStream("pdf.pdf")) {
                HttpHeaders headers = new HttpHeaders();
                headers.add("Content-Disposition", "inline; filename=pdf-test.pdf");

                return ResponseEntity
                        .ok()
                        .headers(headers)
                        .contentType(MediaType.APPLICATION_PDF)
                        .body(new InputStreamResource(file));

            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @PostMapping
    @ResponseBody
    public Message saveFile(@RequestParam("file") MultipartFile file,
                            HttpServletRequest request,
                            HttpServletResponse response) {
        try {

            // Gets current session
            HttpSession session = request.getSession();
            Object email = session.getAttribute("student-email");

            Student student = studentRepository.findByEmail(email.toString());

            // Creates a file from the byte array request
            try (FileOutputStream fileStream = new FileOutputStream("pdf.pdf")) {
                fileStream.write(file.getBytes());
            }

            // Loads the created file to count the total of pages
            PDDocument doc = PDDocument.load(new File("pdf.pdf"));
            int pages = doc.getNumberOfPages();

            // Adds the pages counted and saves it
            student.setPages(student.getPages() + pages);
            studentRepository.save(student);

            // Generates a new PDF object and adds the properties from the request
            PDF pdf = new PDF();

            pdf.setFile(file.getBytes());
            pdf.setTitle(file.getOriginalFilename());
            pdf.setStudent(student);

            pdfRepository.save(pdf);

            response.setStatus(201);

            return new Message("File saved successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return new Message("Failed to save file");
        }
    }
}