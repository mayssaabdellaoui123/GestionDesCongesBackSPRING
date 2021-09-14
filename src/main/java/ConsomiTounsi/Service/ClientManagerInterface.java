package ConsomiTounsi.Service;


import ConsomiTounsi.entities.Client;
import ConsomiTounsi.entities.User;

import java.util.List;

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
    List<User> retrieveAllEmployees();
    Client addClient(Client Cl);

}
