package ConsomiTounsi.Service;


import ConsomiTounsi.entities.Subject;

import java.util.List;
import java.util.Optional;

public interface SubjectManagerInterface {
    List<Subject> retrieveAllSubject();

    void addSubject(Subject Su);

    void deleteSubject(Long id);

    void deleteSubject(String id);

    Subject updateSubject(Subject Su);

    Subject FindSubject(Long id);

    Subject FindSubject(String id);

    int addLike(long id);
    int dislike(long id);

    void setFeaturedSubjects();
    List<Subject> Filter(String word , String minLikes, String maxLikes );
    List<Subject> getFeaturedSubjects();

    List<Subject> FilterByWords(String word);
    List<Subject> FilterByNbLikes(String minLikes, String maxLikes);
}