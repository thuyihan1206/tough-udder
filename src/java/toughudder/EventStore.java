package java.toughudder;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * I'm assuming that we're not using a DB here - this will just be a static 
 * read-only set of events.
 * 
 * @author jjungha1
 *
 */
public class EventStore {
   
   private static EventStore INSTANCE = new EventStore();
   
   public static EventStore instance() {
      return INSTANCE;
   }
   
   private Map<String, Event> eventMap;
   
   private EventStore() {
      Map<String, Event> tempMap = new HashMap<>();
      
      String name = "Tough Udder MD";
      List<Obstacle> obstacles = new ArrayList<>();
      obstacles.addAll(ObstacleStore.instance().getObstacles());
      Calendar date = Calendar.getInstance();
      date.set(Calendar.MONTH, Calendar.NOVEMBER);
      date.set(Calendar.DAY_OF_MONTH, 5);
      tempMap.put(name, new Event(name, "MD", date, 100.0, obstacles));
      
      name = "Tough Udder DC";
      obstacles = new ArrayList<>();
      obstacles.addAll(ObstacleStore.instance().getObstacles());
      date = Calendar.getInstance();
      date.set(Calendar.MONTH, Calendar.DECEMBER);
      date.set(Calendar.DAY_OF_MONTH, 10);
      tempMap.put(name, new Event(name, "DC", date, 120.0, obstacles));
      
      eventMap = Collections.unmodifiableMap(tempMap);
   }

   /**
    * Gets a list of events sorted by date.
    * 
    * @return a list of events sorted by date
    */
   public List<Event> getEvents() {
      List<Event> events = new ArrayList<>();
      events.addAll(eventMap.values());
      Collections.sort(events, new Comparator<Event>() {
         @Override
         public int compare(Event arg0, Event arg1) {
            return arg0.getDate().compareTo(arg1.getDate());
         }
      });
      return events;
   }
   
   public Event getEvent(String name) {
      return eventMap.get(name);
   }
}
