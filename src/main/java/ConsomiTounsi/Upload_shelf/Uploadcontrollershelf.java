package ConsomiTounsi.Upload_shelf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
@RestController
@RequestMapping( "/imageshelf")
@CrossOrigin("*")
public class Uploadcontrollershelf {

    @Autowired
    UploadServiceshelf uploadService;

    @PutMapping("/uploadshelf")
    public ResponseEntity<?> uploadFil(@RequestParam MultipartFile file ) throws IOException {
        return uploadService.uploadFil(file);
    }

    @PutMapping("/uploadevent")
    public ResponseEntity<?> uploadFil2(@RequestParam MultipartFile file ) throws IOException {
        return uploadService.uploadFilEvent(file);
    }
}
