/*package ConsomiTounsi.upload_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;*/

/*@Service
public class uploadService1 {
    @Autowired
    ProductRepository productRepository;

   private String pathlocal = "C:\\Users\\mouad\\Documents\\GitHub\\Front\\src\\assets\\images\\products\\";
    public ResponseEntity<?> uploadFil (MultipartFile file ) throws IOException {
        Optional<Product> Product =  productRepository.findTopByOrderByIdDesc() ;
        if ( !Product.isPresent())
            throw new IllegalStateException("nope") ;
        String filName = pathlocal+Product.get().getId()+".jpg";
        File convetedFile = new File( filName);

        convetedFile.createNewFile();

        FileOutputStream fout  = new FileOutputStream(convetedFile);

        fout.write(file.getBytes()) ;

        fout.close();
        Product p = Product.get();
        String path="assets/images/products/"+p.getId()+".jpg";
        System.out.println(path);
        p.setImage_URL(path);
        productRepository.save(p);

        return new ResponseEntity<>(null, HttpStatus.OK);

    }

}
*/