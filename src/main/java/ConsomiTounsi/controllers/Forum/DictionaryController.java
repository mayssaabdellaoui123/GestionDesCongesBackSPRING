package ConsomiTounsi.controllers.Forum;

import ConsomiTounsi.Service.DictionaryManagerInterface;
import ConsomiTounsi.entities.Dictionary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Forum/Dictionary")
public class DictionaryController {
    @Autowired
    DictionaryManagerInterface dictionaryS;

    @PostMapping("/add")
    public void addBadWord(@RequestBody Dictionary a){ dictionaryS.addBadWord(a);}

    @GetMapping("/retrieve-all")
    public List<String> getListDictionary(){ return dictionaryS.retrieveAllBadWords(); }

    @DeleteMapping("remove-id")
    public void removeBadWordByID(@RequestParam("id")long id){
        dictionaryS.deleteBadWord(id);
    }
}
