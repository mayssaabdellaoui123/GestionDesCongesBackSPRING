package ConsomiTounsi.Service;


import ConsomiTounsi.entities.Comment;

import java.util.List;
import java.util.Set;

public interface CommentManagerInterface {
    List<Comment> retrieveAllComment();
    void addComment(Comment Co , long id);
    void deleteComment(Long id);
    void deleteComment(String id);
    Comment updateComment(Comment Co);
    Comment FindComment(Long id);
    Comment FindComment(String id);

    int addLike(long id);
    int dislike(long id);
    Comment retrieveCommentByPertinence(long id);
    Set<Comment> retrieveSubjectComments(long id);

    void setPertinentComments();

}
