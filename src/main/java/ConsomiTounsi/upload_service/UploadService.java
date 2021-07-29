/*package ConsomiTounsi.upload_service;


import com.google.zxing.Reader;
import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;*/

/*@Service
public class UploadService {


    @Autowired
    ProductRepository productRepository;

    private Path fileStorageLocation = Paths.get("C:\\Users\\mouad\\uploads\\");
    private String pathlocal = "C:\\Users\\mouad\\uploads\\";
   public ResponseEntity<?> uploadeFile(MultipartFile file) throws IOException, FormatException, ChecksumException, NotFoundException {
        System.out.println(
                "'getting file"
        );
         Optional<Product> optionalProduct =  productRepository.findTopByOrderByIdDesc() ;
         if ( !optionalProduct.isPresent())
             throw new IllegalStateException("nope") ;
        //String  pictureLocationOnServer   = "C:\\Users\\EliteBook\\uploadefiles\\";
       String filName = pathlocal+optionalProduct.get().getId()+".jpg";
        File convetedFile = new File( filName);

       convetedFile.createNewFile();

        FileOutputStream fout  = new FileOutputStream(convetedFile);

        fout.write(file.getBytes()) ;

        fout.close();

       //String fileName = file.getOriginalFilename();
        // Long idfile = extractIdFromString(fileName) ;
       //StudentEntity studentEntity = studentEntityOptional.get();
       //  studentEntity.setPicsName(fileName);
       //studentRepo.save(studentEntity);

       Optional<Product> product = productRepository.findById( optionalProduct.get().getId() ) ;
       if (!product.isPresent())
           throw new IllegalStateException("can't find product ... id problem while sending the pic");

       InputStream barCodeInputStream = new FileInputStream(filName);
       BufferedImage barCodeBufferedImage = ImageIO.read(barCodeInputStream);

       LuminanceSource source = new BufferedImageLuminanceSource(barCodeBufferedImage);
       BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
       Reader reader = new MultiFormatReader();
       Result result = reader.decode(bitmap);
       String resultString = result.getText();
       System.out.println("Barcode text is " + result.getText());
       if ( Integer.parseInt(resultString.substring(0,3))!=619)
           throw new IllegalStateException("product is not tunisian");

       Product p = product.get();
       p.setCode(resultString);
       p.setImagePath(filName);

       productRepository.save(p);


       System.out.println(filName);



       return new ResponseEntity<>(null, HttpStatus.OK);
    }

    /*public String  uploadeFile(MultipartFile file ) throws IOException {
        System.out.println(
                "'getting file"
        );
        String fileName = file.getOriginalFilename();
        String  pictureLocationOnServer   = "C:\\Users\\EliteBook\\uploadefiles\\";
        File convetedFile = new File( pictureLocationOnServer+fileName);

        convetedFile.createNewFile();

        FileOutputStream fout  = new FileOutputStream(convetedFile);

        fout.write(file.getBytes()) ;

        fout.close();

        System.out.println();
        Long idfile = extractIdFromString(fileName) ;

        Optional<StudentEntity> studentEntityOptional = studentRepo.findById(idfile);
        StudentEntity studentEntity = studentEntityOptional.get();
        studentEntity.setPicsName(fileName);
        studentRepo.save(studentEntity);
        return fileName;
    }

*/

   /* public Resource loadFileAsResource(String picsName) throws MalformedURLException {


        Path filePath = this.fileStorageLocation.resolve(picsName).normalize();
        Resource resource = new UrlResource(filePath.toUri());
        if (resource.exists()) {
            return resource;

        }
        else
            return  null ;
    }


    Long extractIdFromString (String m) {
        Long id ;


       int index = m.indexOf(".") ;

       String mm = m.substring(0,index) ;
        return  Long.valueOf(mm) ;
    }


}*/
