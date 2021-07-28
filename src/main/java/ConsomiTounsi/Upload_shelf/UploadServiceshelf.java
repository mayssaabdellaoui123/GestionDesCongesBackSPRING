package ConsomiTounsi.Upload_shelf;


import ConsomiTounsi.entities.Event;
import ConsomiTounsi.entities.Product;
import ConsomiTounsi.entities.Shelf;
import ConsomiTounsi.repository.EventRepository;
import ConsomiTounsi.repository.ProductRepository;
import ConsomiTounsi.repository.ShelfRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;

@Service
public class UploadServiceshelf {

    @Autowired
    ShelfRepository productRepository;
    @Autowired
    EventRepository eventRepository;

    private String pathlocal = "C:\\Users\\mayessa\\Documents\\GitHub\\Front\\src\\assets\\images\\products\\";
    public ResponseEntity<?> uploadFil (MultipartFile file ) throws IOException {
        Optional<Shelf> Shelf =  productRepository.findTopByOrderByIdShelfDesc() ;
        if ( !Shelf.isPresent())
            throw new IllegalStateException("nope") ;
        String filName = pathlocal+Shelf.get().getIdShelf()+".jpg";
        File convetedFile = new File( filName);

        convetedFile.createNewFile();

        FileOutputStream fout  = new FileOutputStream(convetedFile);

        fout.write(file.getBytes()) ;

        fout.close();
        Shelf p = Shelf.get();
        String path="assets/images/products/"+p.getIdShelf()+".jpg";
        System.out.println(path);
        p.setImage_URL(path);
        productRepository.save(p);

        return new ResponseEntity<>(null, HttpStatus.OK);

    }


    public ResponseEntity<?> uploadFilEvent (MultipartFile file ) throws IOException {
        Optional<Event> Event =  eventRepository.findTopByOrderByIdEventDesc() ;
        if ( !Event.isPresent())
            throw new IllegalStateException("nope") ;
        String filName = pathlocal+Event.get().getIdEvent()+".jpg";
        File convetedFile = new File( filName);

        convetedFile.createNewFile();

        FileOutputStream fout  = new FileOutputStream(convetedFile);

        fout.write(file.getBytes()) ;

        fout.close();
        Event p = Event.get();
        String path="assets/images/products/"+p.getIdEvent()+".jpg";
        System.out.println(path);
        p.setImage_URL(path);
        eventRepository.save(p);

        return new ResponseEntity<>(null, HttpStatus.OK);

    }


}

