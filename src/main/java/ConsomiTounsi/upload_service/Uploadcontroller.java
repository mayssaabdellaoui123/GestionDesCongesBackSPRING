/*package ConsomiTounsi.upload_service;


import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;*/

/*@RestController
@RequestMapping( "/image")
@CrossOrigin("*")
public class Uploadcontroller {

    @Autowired
    UploadService uploadService;
    @Autowired
    uploadService1 uploadService1;

    @GetMapping(
            value = "/{picture}",
            produces = MediaType.IMAGE_JPEG_VALUE
    )


    public Resource loadFileAsResource(@PathVariable String picture ) throws MalformedURLException {


        return  uploadService.loadFileAsResource(picture) ;


    }
    @PutMapping  ("/upload")
    public ResponseEntity<?> uploadFil(@RequestParam MultipartFile file ) throws IOException {
        return uploadService1.uploadFil(file);
    }





    @PostMapping
 public ResponseEntity<?> uploadFile(@RequestParam MultipartFile file) throws IOException, FormatException, ChecksumException, NotFoundException {

        return uploadService.uploadeFile(file  );
    }
   /* @GetMapping
    public  ResponseEntity<?> getStudentImage ( )
    {
        return  uploadService.getStudentImage() ;
    }*/



/*}*/
