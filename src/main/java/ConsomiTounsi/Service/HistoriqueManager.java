package ConsomiTounsi.Service;

import ConsomiTounsi.entities.Departement;
import ConsomiTounsi.repository.HistoriqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoriqueManager implements HistoriqueManagerInterface{

    @Autowired
    HistoriqueRepository HR ;



}
