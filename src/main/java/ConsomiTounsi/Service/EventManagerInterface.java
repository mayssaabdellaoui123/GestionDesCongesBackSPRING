package ConsomiTounsi.Service;


import ConsomiTounsi.entities.Event;

import java.util.List;
import java.util.Optional;

public interface EventManagerInterface {
    List<Event> retrieveAllEvent();
    Event addEvent(Event E);
    Event addEvent1(Event E);
    void deleteEvent(long id);
    void deleteEvent(String id);
    Event updateEvent(Event E);
    public Event FindEventById(Long id);
    public Event FindEventById(String id);
    List<Event> getEventsByLevelorder();
    List<Event> getEventsByLevelorder2();
}
