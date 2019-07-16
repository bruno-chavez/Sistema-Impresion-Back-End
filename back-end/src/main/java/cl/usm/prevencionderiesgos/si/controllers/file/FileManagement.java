package cl.usm.prevencionderiesgos.si.controllers.file;

import cl.usm.prevencionderiesgos.si.models.PDF;
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
import javax.servlet.http.HttpSession;
import java.io.*;

@RestController
@RequestMapping("/file")
public class FileManagement {

    private final PDFRepository pdfRepository;
    private final StudentRepository studentRepository;


    FileManagement(PDFRepository pdfRepository, StudentRepository studentRepository) {
        this.pdfRepository = pdfRepository;
        this.studentRepository = studentRepository;

    }


    /*@GetMapping
    public ResponseEntity<InputStreamResource> loadFile() {
        try {

            PDF pdf = pdfRepository.findById(36);

            try (FileOutputStream fos = new FileOutputStream("pdf.pdf")) {
                fos.write(pdf.getFile());
            }

            FileInputStream file = new FileInputStream("pdf.pdf");
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "inline; filename=pdf-test.pdf");

            return ResponseEntity
                    .ok()
                    .headers(headers)
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(new InputStreamResource(file));

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }*/

    @PostMapping
    public String saveFile(@RequestParam("file") MultipartFile file) {

        /*HttpSession session = request.getSession();
        Object email = session.getAttribute("student-email");

        Student student = studentRepository.findByEmail(email.toString());
        pdf.setStudent(student);*/

        try {

            try (FileOutputStream fileStream = new FileOutputStream("pdf.pdf")) {
                fileStream.write(file.getBytes());
            }

            PDDocument doc = PDDocument.load(new File("pdf.pdf"));
            int pages = doc.getNumberOfPages();

            PDF pdf = new PDF();
            pdf.setFile(file.getBytes());
            pdf.setPages(pages);

            pdfRepository.save(pdf);
            return "File saved successfully";
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to save file";
        }
    }
}