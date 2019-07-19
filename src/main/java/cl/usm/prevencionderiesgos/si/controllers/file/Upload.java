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

    // Generates a new PDF object and adds the properties from the request
    private void saveInfo(byte[] file, String name, Student student, Integer pages) {

        PDF pdf = new PDF();
        pdf.setFile(file);
        pdf.setTitle(name);
        pdf.setStudent(student);
        pdfRepository.save(pdf);

        student.setPages(student.getPages() + pages);
        studentRepository.save(student);

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
            Object type = session.getAttribute("type");

            Student student = studentRepository.findByEmail(email.toString());

            // Creates a file from the byte array request
            try (FileOutputStream fileStream = new FileOutputStream("pdf.pdf")) {
                fileStream.write(file.getBytes());
            }

            // Loads the created file to count the total of pages
            PDDocument doc = PDDocument.load(new File("pdf.pdf"));
            int pages = doc.getNumberOfPages();

            if (type.toString().equals("Regular")) {
                if (student.getPages() + pages < 250) {

                    System.out.println("entre");
                    saveInfo(file.getBytes(), file.getOriginalFilename(), student, pages);
                 /*   PDF pdf = new PDF();
                    pdf.setFile(file.getBytes());
                    pdf.setTitle(file.getOriginalFilename());
                    pdf.setStudent(student);
                    pdfRepository.save(pdf);

                    student.setPages(student.getPages() + pages);
                    studentRepository.save(student);*/

                    response.setStatus(201);

                    return new Message("File saved successfully");
                } else {
                    return new Message("Document exceeds file limit");
                }
            }
            if (type.toString().equals("Memorista")) {
                if (student.getPages() + pages < 300) {

                    saveInfo(file.getBytes(), file.getOriginalFilename(), student, pages);

                   /* PDF pdf = new PDF();
                    pdf.setFile(file.getBytes());
                    pdf.setTitle(file.getOriginalFilename());
                    pdf.setStudent(student);
                    pdfRepository.save(pdf);

                    student.setPages(student.getPages() + pages);
                    studentRepository.save(student);*/

                    response.setStatus(201);
                    return new Message("File saved successfully");
                } else {
                    return new Message("Document exceeds file limit");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new Message("Failed to saveInfo file");
        }

        return null;
    }
}