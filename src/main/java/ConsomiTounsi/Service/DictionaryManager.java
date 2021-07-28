package ConsomiTounsi.Service;

import ConsomiTounsi.entities.Dictionary;
import ConsomiTounsi.repository.DictionaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DictionaryManager implements DictionaryManagerInterface{

    @Autowired
    DictionaryRepository dr;

    @Override
    public List<String> retrieveAllBadWords() {
        return dr.retrieveBadWords();
    }

    @Override
    public Dictionary addBadWord(Dictionary w ){
        return dr.save(w);
    }

    @Override
    public void deleteBadWord(Long id) {
        dr.deleteById(id);
    }

    @Override
    public void deleteBadWord(String id) {
        dr.deleteById(Long.parseLong(id));
    }

    @Override
    public Dictionary updateBadWord(Dictionary w) {
        return dr.save(w);
    }

    @Override
    public Dictionary FindBadWord(Long id) {
        return dr.findById(id).orElse(new Dictionary());
    }

    @Override
    public Dictionary FindBadWord(String id) {
        return dr.findById(Long.parseLong(id)).orElse(new Dictionary());
    }
}
