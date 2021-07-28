package ConsomiTounsi.Service;

import ConsomiTounsi.entities.Feedback;
import ConsomiTounsi.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackManager implements FeedbackManagerInterface {

    @Autowired
    FeedbackRepository fr;

    @Override
    public List<Feedback> retrieveAllFeedback() {
        return (List<Feedback>) fr.findAll();
    }

    @Override
    public Feedback addFeedback(Feedback M) {
        return fr.save(M);
    }

    @Override
    public void deleteFeedback(Long id) {
         fr.deleteById(id);
    }

    @Override
    public void deleteFeedback(String id) {
         fr.deleteById(Long.parseLong(id));
    }

    @Override
    public Feedback updateFeedback(Feedback M) {
        return fr.save(M);
    }

    @Override
    public Feedback FindFeedback(Long id) {
        return fr.findById(id).orElse(new Feedback());
    }

    @Override
    public Feedback FindFeedback(String id) {
        return fr.findById(Long.parseLong(id)).orElse(new Feedback());
    }}

