package ConsomiTounsi.Service;


import ConsomiTounsi.entities.Client;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ClientManagerInterface {
	
    List<Client> retrieveAllClient();
    void deleteClientById(Long id);
    void deleteClientById(String id);
    Client updateClient(Client Cl);
    Client FindClientById(long id);
    Client FindClientById(String id);
    long getNombreClient();
    Client SignUpClient(Client user);
    long getNBClientsbysubmonth(String Month);
    Client addClient(Client Cl);
}
