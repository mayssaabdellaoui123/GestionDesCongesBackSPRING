package ConsomiTounsi.Service;

import ConsomiTounsi.entities.DictionaryWord;
import ConsomiTounsi.entities.Subject;
import ConsomiTounsi.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class SubjectManager implements SubjectManagerInterface{

    @Autowired
    SubjectRepository sr;

    @Autowired
    DictionaryManagerInterface dictionaryS;

    @Override
    public List<Subject> retrieveAllSubject() {
        return (List<Subject>) sr.findAll();
    }

    @Override
    public void addSubject(Subject Su) {
        boolean redundant = false;
        boolean prohibited = false;
        List<Subject> allSubjects = retrieveAllSubject();
        List<String> prohibitedDict = dictionaryS.retrieveAllBadWords();
        prohibitedDict.replaceAll(String::toUpperCase);
        List<String> List = new ArrayList<String>(Arrays.asList(Su.getDescriptionSubject().split("\\s+")));
        for (String word : List ) {
            if (prohibitedDict.contains(word.toUpperCase())){ prohibited = true ;}
        }
        for(Subject s : allSubjects){
            if(Su.getDescriptionSubject().toUpperCase().equals(s.getDescriptionSubject().toUpperCase())){
                redundant = true ;
            }
        }
        if (prohibited == false && redundant==false )
        { Su.setLikesSubject(0);
          Su.setFeaturedSubject(false);
            sr.save(Su);
        }
    }


    @Override
    public void deleteSubject(Long id) {
        sr.deleteById(id);
    }

    @Override
    public void deleteSubject(String id) {
        sr.deleteById(Long.parseLong(id));
    }

    @Override
    public Subject updateSubject(Subject Su) {
        return sr.save(Su);
    }

    @Override
    public Subject FindSubject(Long id) {
        return sr.findById(id).orElse(new Subject());
    }

    @Override
    public Subject FindSubject(String id) {
        return sr.findById(Long.parseLong(id)).orElse(new Subject());
    }

    @Override
    public int addLike(long id) {
        Subject a = sr.findById(id).orElse(new Subject());
        int nb = a.getLikesSubject() + 1;
        return sr.addLike(nb , id) ; }

    @Override
    public int dislike(long id) {
        Subject a = sr.findById(id).orElse(new Subject());
        int nb = a.getLikesSubject() - 1;
        return sr.addLike(nb , id) ; }

    @Override
    public void setFeaturedSubjects() {
        int i = 0;
        int MaxLikes=0;
        long idMax = 0 ;
        long id=0;
        List<Subject> ListSubjects = retrieveAllSubject();
        for ( Subject subj : ListSubjects){
            id = subj.getIdSubject();
            sr.NotFeatured(id);
        }
        while(i<3)
        {
        for ( Subject subj : ListSubjects) {
                if ( subj.getLikesSubject() > MaxLikes )
                {
                    MaxLikes = subj.getLikesSubject();
                    idMax = subj.getIdSubject();
                }
            }
            sr.setFeautured(idMax);
            ListSubjects.remove(FindSubject(idMax));
            MaxLikes=0;
            i++;
        }
    }

    @Override
    public List<Subject> Filter(String word, String minLikes, String maxLikes) {
        List<Subject> FilteredByWordsSubjects = FilterByWords( word);
        List<Subject> FilteredByLikesSubjects = FilterByNbLikes( minLikes, maxLikes);
        boolean redundant = false;
        for (Subject s1 : FilteredByLikesSubjects){
            for (Subject s2 : FilteredByWordsSubjects ){
                if (s2.equals(s1)){ redundant = true;} }
            if (redundant == false ){FilteredByWordsSubjects.add(s1);}
        }
        return FilteredByWordsSubjects ;
    }

    @Override
    public List<Subject> getFeaturedSubjects() {
        return sr.getFeaturedSubjects();
    }

    @Override
    public List<Subject> FilterByNbLikes(String minLikes, String maxLikes) {
        List<Subject> allSubjects= retrieveAllSubject();
        List<Subject> filteredSubjects = new ArrayList<>();
        for ( Subject s : allSubjects){
            if((minLikes!="") && (maxLikes !="")) {
                if ((s.getLikesSubject() >= Integer.parseInt(minLikes)) && (s.getLikesSubject() <= Integer.parseInt(maxLikes))) {
                    filteredSubjects.add(s);
                }
            }
            if((minLikes!="") && (maxLikes =="")) {
             if (s.getLikesSubject() >= Integer.parseInt(minLikes)) {
                    filteredSubjects.add(s);
                }
            }
            if((minLikes=="") && (maxLikes !="")) {
             if (s.getLikesSubject() <= Integer.parseInt(maxLikes)) {
                 filteredSubjects.add(s);
             }
        }
        }
        return filteredSubjects;
    }

    @Override
    public List<Subject> FilterByWords(String word) {
        List<Subject> allSubjects = retrieveAllSubject();
        List<Subject> filteredSubjects = new ArrayList<>();
        List<String> words = new ArrayList<String>(Arrays.asList(word.split("\\s+")));
        if (word.equals("")){return allSubjects;}
        for (Subject s : allSubjects){
            List<String> listTest = new ArrayList<String>(Arrays.asList(s.getDescriptionSubject().split("\\s+")));
            for (String e : words){
            if(listTest.contains(e)){
                filteredSubjects.add(s);
            }}
        }
        return filteredSubjects;
    }



}

