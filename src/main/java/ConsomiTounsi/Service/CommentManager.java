package ConsomiTounsi.Service;

import ConsomiTounsi.entities.Comment;
import ConsomiTounsi.entities.Subject;
import ConsomiTounsi.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CommentManager implements CommentManagerInterface{

    @Autowired
    SubjectManagerInterface subjectS;

    @Autowired
    CommentRepository cr;

    @Autowired
    DictionaryManagerInterface dictionaryS;

    @Override
    public List<Comment> retrieveAllComment() {
        return (List<Comment>) cr.findAll();
    }

    @Override
    public void addComment(Comment Co , long id) {
        boolean prohibited = false;
        List<String> prohibitedDict = dictionaryS.retrieveAllBadWords();
        prohibitedDict.replaceAll(String::toUpperCase);
        List<String> List = new ArrayList<String>(Arrays.asList(Co.getTextComment().split("\\s+")));
        for (String word : List ) {
            if (prohibitedDict.contains(word.toUpperCase())){ prohibited = true ;}}
        if (prohibited == false )
        { Co.setLikesComment(0);
        Co.setMostPertinentComment(false);
        Subject subj = subjectS.FindSubject(id);
        Co.setSubject(subj);
        cr.save(Co);} }

    @Override
    public void deleteComment(Long id) {
        cr.deleteById(id);
    }

    @Override
    public void deleteComment(String id) {
        cr.deleteById(Long.parseLong(id));
    }

    @Override
    public Comment updateComment(Comment Co) {
        return cr.save(Co);
    }

    @Override
    public Comment FindComment(Long id) {
        return  cr.findById(id).orElse(new Comment());    }

    @Override
    public Comment FindComment(String id) {
        return  cr.findById(Long.parseLong(id)).orElse(new Comment());    }


    @Override
    public Comment retrieveCommentByPertinence(long id) {
        Subject s = subjectS.FindSubject(id);
        long idC = 0;
        Set<Comment> com = s.getComment();
        for (Comment c : com) {
            if (c.isMostPertinentComment() == true) {
                 idC = c.getIdComment();}}
        Comment cc = FindComment(idC);
        cc.setSubject(null);
        return cc; }


    @Override
    public Set<Comment> retrieveSubjectComments(long id) {
        Subject s = subjectS.FindSubject(id);
        Set<Comment> com = s.getComment();
        for (Comment c : com){ c.setSubject(null);}
        return com;
    }

    @Override
    public void setPertinentComments() {
        int MaxLikes=0;
        long idMax = 0 ;
        long id = 0;
        List<Comment> ListComment =  retrieveAllComment();
        for ( Comment c : ListComment){
            id = c.getIdComment();
            cr.notPertinentComment(id);
        }
        List<Subject> ListSubjects = subjectS.retrieveAllSubject();
        for ( Subject subj : ListSubjects) {
            Set<Comment> ListComments = subj.getComment();
            for ( Comment c : ListComments) {
                if ( c.getLikesComment() > MaxLikes )
                    {
                        MaxLikes = c.getLikesComment();
                        idMax = c.getIdComment();
                    }
            }
                cr.setPertinentComment(idMax);
        }
    }

    @Override
    public int addLike(long id) {
        Comment a = cr.findById(id).orElse(new Comment());
        int nb = a.getLikesComment() + 1;
        return cr.addLike(nb , id) ; }

    @Override
    public int dislike(long id) {
        Comment a = cr.findById(id).orElse(new Comment());
        int nb = a.getLikesComment() -1 ;
        return cr.addLike(nb , id) ; }

}
