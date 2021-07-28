package ConsomiTounsi.controllers.Forum;

import ConsomiTounsi.Service.CommentManagerInterface;
import ConsomiTounsi.entities.Comment;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/Forum/Comment")
public class CommentController {

    @Autowired
    CommentManagerInterface commentS;

    @PutMapping("/add")
    public void addComment(@RequestBody Comment a, @RequestParam("id") long  id){ commentS.addComment(a , id);}

    @GetMapping("/retrieve-all")
    public List<Comment> getListComments(){ return commentS.retrieveAllComment(); }

    @GetMapping("/retrieve-id")
    public Comment getCommentById(@RequestParam("id") long id){
        return commentS.FindComment(id);
    }

    @DeleteMapping("remove-id")
    public void removeCommentByID(@RequestParam("id")long id){
        commentS.deleteComment(id);
    }

    @PutMapping("/update")
    public Comment updateComment(@RequestBody Comment a){
        return commentS.updateComment(a);
    }

    @PutMapping("/likeComment-id")
    public void addLike(@RequestParam("id") long id){commentS.addLike(id);}

    @PutMapping("/dislikeComment-id")
    public void dislike(@RequestParam("id") long id){commentS.dislike(id);}

    @GetMapping("/mostPertinentComments")
    public Comment GetPertinentCommentsBySubject(@RequestParam("id") long id){
        return commentS.retrieveCommentByPertinence(id); }

    @PutMapping("/pertinentComments")
    public void updatePertinent(){
        commentS.setPertinentComments();
    }

    @GetMapping("/retrieveSubjectComments-idSubject")
    public Set<Comment> retrieveSubjectComments(@RequestParam("id") long id){
        return commentS.retrieveSubjectComments(id);
    }

}
