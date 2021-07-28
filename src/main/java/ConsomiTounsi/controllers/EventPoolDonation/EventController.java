package ConsomiTounsi.controllers.EventPoolDonation;

import ConsomiTounsi.Service.EventManager;
import ConsomiTounsi.Service.EventManagerInterface;
import ConsomiTounsi.Service.ProductManagerInterface;
import ConsomiTounsi.entities.*;
import ConsomiTounsi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/event")
@CrossOrigin("*")

public class EventController {
    @Autowired
    EventManagerInterface eventmanager ;
    @Autowired
    EventManager manager;
    @Autowired
    UserRepository Ur;


    //@PostMapping("/add")
   //ublic void addEvent(@RequestBody Event e){ eventmanager.addEvent1(e);}


    @PostMapping("/add-id")
    public void addEvent(@RequestBody Event e){ eventmanager.addEvent(e);}

    @GetMapping("/retrieve-all")
    public List<Event> getListEvents(){ return eventmanager.retrieveAllEvent(); }
    //@PutMapping ("/AddClienttoevent/{id}")
    //public void addclientstoevent(@PathVariable("id") Long id,@RequestBody Client c){
     //   manager.addClientToEvent(id,c);
    //}

    //@PutMapping ("/AddClienttoevent/{id}")
   // public void addclientstoevent(@RequestBody Client c,@PathVariable("id") Long id){
     //   manager.AddEventToClient(c,id);
   // }
    @DeleteMapping ("/AddClienttoevent/{idclient}/{idevent}")
    public void addclienttoevent(@PathVariable("idclient") long idClient , @PathVariable("idevent") long idevent){
        manager.AddEventToClient(idClient,idevent);
    }

    @DeleteMapping("removeeventafteryear/{idevent}")
    public void DeletEventAfterAYear (@PathVariable("idevent")long idevent){
        manager.DeleteEventAfterAYear(idevent);

    }

    @DeleteMapping("removealleventafteryear")
    public void DeleteAllEventAfterAYear (){
        manager.DeleteAllEventAfterAYear();

    }

    @DeleteMapping("nombreP/{id}")
    public int Nombredeparticipants (@PathVariable("id") long id){
       int x=  manager.Nombredeparticipants(id);
        return x;
    }

    @GetMapping("List/{id}")
    public List<User> ListePup(@PathVariable("id") long id){
        return manager.ListePup(id);
    }


    @DeleteMapping("remove-id/{id}")
    public void removeAdminByID(@PathVariable("id")long id){
        eventmanager.deleteEvent(id);
    }

    @DeleteMapping("/RemoveClientFromEvent/{idclient}/{idevent}")
    public void RemoveClientFromEvent (@PathVariable("idclient") long idclient , @PathVariable("idevent") long idevent){
        manager.RemoveClientFromEvent(idclient,idevent);
    }

    @GetMapping("/getEventsByLevelorder")
    public List<Event> getEventsByLevelorder() { return eventmanager.getEventsByLevelorder(); }

    @GetMapping("/getEventsByLevelorder2")
    public List<Event> getEventsByLevelorder2() { return eventmanager.getEventsByLevelorder2(); }

    @PutMapping("/update")
    public Event updateShelf(@RequestBody Event p){
        return eventmanager.updateEvent(p);
    }

    @GetMapping("/iduser/{username}")
    public long retrieveIdClientByUsername(@PathVariable("username") String username) {
        return  Ur.retrieveIdClientByUsername(username);
    }


}


