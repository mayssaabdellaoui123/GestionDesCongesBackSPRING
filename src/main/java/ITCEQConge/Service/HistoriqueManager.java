package ITCEQConge.Service;

import ITCEQConge.entities.Historique;
import ITCEQConge.entities.TypeHistorique;
import ITCEQConge.repository.HistoriqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class HistoriqueManager implements HistoriqueManagerInterface{

    @Autowired
    HistoriqueRepository HR ;

    @Autowired
    HistoriqueManagerInterface hri;


    @Override
    public List<Historique> retrieveAllHistory() {
        return (List<Historique>) HR.findAll();

    }

    @Override
    public long getNombreHistorique() {
        return HR.getNombreHistorique();
    }

    @Override
    public List<Historique> RetiveHistoriqueByOwner(String owner) {
        return HR.RetiveHistoriqueByOwner(owner);
    }

    @Override
    public List<Historique> RetiveHistoriqueByType(TypeHistorique type) {
        return HR.RetiveHistoriqueByType(type);
    }

    @Override
    public List<Historique> RetiveHistoriqueByAction(String action) {
        return HR.RetiveHistoriqueByAction(action);
    }

    @Override
    public List<Historique> RetiveHistoriqueByFiltre(TypeHistorique type,String action) {
        return HR.RetiveHistoriqueByFiltre(type,action);
    }

    @Override
    public void AddHistory(String action,String username, TypeHistorique typeHistorique, String Description ) {

        Historique H = new Historique();

        H.setAction(action);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        H.setDate(now);

        H.setOwner(username);

        H.setTypehistorique(typeHistorique);

        H.setDescription(Description);

        HR.save(H);
    }

    public void DeleteHistoryAfterMonth(long id){
        Optional<Historique> optionalHistorique = HR.findById(id);
        Historique historique = optionalHistorique.get();
        LocalDateTime a  = historique.getDate();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        long differenceInDays = ChronoUnit.DAYS.between(a,now);

        long x = Math.abs(differenceInDays);
         if ( x >= 30){
             HR.deleteById(id);
         }
    }

    @Override
    public void DeleteAllHistoryAfterMonth() {
        List<Historique> list = hri.retrieveAllHistory();
        for (Historique h: list){
            DeleteHistoryAfterMonth(h.getIdHistorique());
        }
    }



}
