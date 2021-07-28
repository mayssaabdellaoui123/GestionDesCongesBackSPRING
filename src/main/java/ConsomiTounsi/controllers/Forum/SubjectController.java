package ConsomiTounsi.controllers.Forum;

import ConsomiTounsi.Service.SubjectManagerInterface;
import ConsomiTounsi.entities.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Forum/Subject")
public class SubjectController {

    /*{
    "descriptionSubject": null,
}*/

    @Autowired
    SubjectManagerInterface subjectS;

    @PostMapping("/add")
    public void addSubject(@RequestBody Subject a){
        subjectS.addSubject(a);}

    @GetMapping("/retrieve-all")
    public List<Subject> getListSubjects(){
        return subjectS.retrieveAllSubject(); }

    @GetMapping("/retrieve-id")
    public Subject getSubjectById(@RequestParam("id") long id){
        return subjectS.FindSubject(id);
    }

    @DeleteMapping("remove-id")
    public void removeSubjectByID(@RequestParam("id")long id){
        subjectS.deleteSubject(id);
    }

    @PutMapping("/update")
    public Subject updateSubject(@RequestBody Subject a){
        return subjectS.updateSubject(a);
    }

    @PutMapping("/likeSubject-id")
    public void addLike(@RequestParam("id") long id){subjectS.addLike(id);}

    @PutMapping("/dilikeSubject-id")
    public void dislike(@RequestParam("id") long id){subjectS.dislike(id);}

    @PutMapping("/setFeauturedSubjects")
    public void FeatureSubjects(){
        subjectS.setFeaturedSubjects();
    }

    @GetMapping("/getFeaturedSubjects")
    public void ListFeauturedSubjects(){
        subjectS.getFeaturedSubjects();
    }

    @GetMapping("/getWordFilteredSubjects")
    public List<Subject> FilterByWords(@RequestParam("word")String word){
//    public List<String> FilterByWords(@RequestParam("word")String word){
        return subjectS.FilterByWords(word);
    }

    @GetMapping("/getLikesFilteredSubjects")
    public List<Subject> FilterByNbLikes(@RequestParam("minLikes")String minLikes, @RequestParam("maxLikes")String maxLikes){
        return subjectS.FilterByNbLikes(minLikes, maxLikes);
    }

    @GetMapping("/getFilteredSubjects")
    public List<Subject> Filter(@RequestParam("word")String word , @RequestParam("minLikes")String minLikes, @RequestParam("maxLikes")String maxLikes){
        return subjectS.Filter(word , minLikes, maxLikes);
    }
}

