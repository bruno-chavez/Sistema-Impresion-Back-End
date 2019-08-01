package cl.usm.prevencionderiesgos.si.controllers.file;

import cl.usm.prevencionderiesgos.si.DTOs.Message;
import cl.usm.prevencionderiesgos.si.models.PDF;
import cl.usm.prevencionderiesgos.si.models.Student;
import cl.usm.prevencionderiesgos.si.repositories.PDFRepository;
import cl.usm.prevencionderiesgos.si.repositories.StudentRepository;

import org.apache.pdfbox.pdmodel.PDDocument;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

@RestController
@RequestMapping("/file/upload")
public class Upload {

    private final PDFRepository pdfRepository;
    private final StudentRepository studentRepository;


    Upload(PDFRepository pdfRepository, StudentRepository studentRepository) {
        this.pdfRepository = pdfRepository;
        this.studentRepository = studentRepository;

    }

    @PostMapping
    @ResponseBody
    public Message saveFile(@RequestParam("file") MultipartFile file,
                            HttpServletRequest request,
                            HttpServletResponse response) {

        // Gets current session
        HttpSession session = request.getSession();
        Object email = session.getAttribute("student-email");

        Student student = studentRepository.findByEmail(email.toString());

        // Check if the file was previously added by the student
        if (pdfRepository.findByTitleAndStudentId(file.getOriginalFilename(), student.getId()) != null){
            return new Message("File with same name already uploaded");
        }

        // Creates the filePath for the new file in files/user-id/file-name
        String filePath = String.join("/", "files", student.getId().toString());

        try {

            // Necessary to create any sub folder since fileOutputStream doesnt do it
            Files.createDirectories(Paths.get(filePath));

            // After the directories are created, filePath is modified to point to the file
            filePath += "/" + file.getOriginalFilename();

            // Creates a file from the request's byte array
            try (FileOutputStream fileStream = new FileOutputStream(filePath)) {
                fileStream.write(file.getBytes());
            }

            // Loads the created file to count the total of pages
            PDDocument doc = PDDocument.load(new File(filePath));
            int pages = doc.getNumberOfPages();


            PDF pdf = new PDF();
            pdf.setTitle(file.getOriginalFilename());
            pdf.setStudent(student);
            pdf.setPages(pages);
            pdfRepository.save(pdf);

            doc.close();

        } catch (Exception e) {
            e.printStackTrace();
            return new Message("Failed to save file");
        }

        response.setStatus(201);
        return new Message("File created");
    }
}