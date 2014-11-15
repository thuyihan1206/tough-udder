package java.toughudder;
//package toughudder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
	private final String UDDER_DAY 	 = "Udder Day";
	private final String MUDDY_UDDER = "Muddy Udder";
	private final String HOLY_COW 	 = "Holy Cow";
	private final String MAD_COW 	 = "Mad Cow";
	
	private static EventStore INSTANCE = null;
	private Map<String, Event> events;
    
   
    /**
     * Gets an instance of the EventStore singleton.
     * @return the EventStore singleton instance.
     */
    public static EventStore instance() {
    	if(INSTANCE == null) {
    		INSTANCE = new EventStore();
    	} 
    	return INSTANCE;
    }
    
    /**
     * Private singleton constructor.
     */
    private EventStore() {
       Map<String, Event> tempEvents = new HashMap<String, Event>();
       tempEvents.put(UDDER_DAY, 	createUdderDayEvent());
       tempEvents.put(MUDDY_UDDER, 	createMuddyUdderEvent());
       tempEvents.put(HOLY_COW, 	createHolyCowEvent());
       tempEvents.put(MAD_COW, 		createMadCowEvent());
       events = Collections.unmodifiableMap(tempEvents);
    }

    /**
     * Gets a list of events sorted by date.
     * 
     * @return a list of events sorted by date
     */
    public List<Event> getEvents() {
    	List<Event> eventList = new ArrayList<>();
    	eventList.addAll(events.values());
    	Collections.sort(eventList, new Comparator<Event>() {
	    	@Override
	    	public int compare(Event arg0, Event arg1) {
	    		return arg0.getDate().compareTo(arg1.getDate());
	    	}
    	});
    	return eventList;
    }
   
   public Event getEvent(String name) {
      return this.events.get(name);
   }
	
	private Event createUdderDayEvent() {
		try {
			return new Event(UDDER_DAY, "The Bahamas", dateFormat.parse("07-21-2015"), 2500.00, "just-an-udder-day.jpg");
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private Event createMuddyUdderEvent() {
		try {
			return new Event(MUDDY_UDDER, "Everglades, Florida", dateFormat.parse("07-07-2015"), 120.00, "muddy-udder.png");
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private Event createHolyCowEvent() {
		try {
			return new Event(HOLY_COW, "Wichita, Kansas", dateFormat.parse("06-19-2015"), 50.00, "holy-cow.jpg");
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private Event createMadCowEvent() {
		try {
			return new Event(MAD_COW, "Knoxville, Tennessee", dateFormat.parse("06-15-2015"), 75.00, "mad-cow-mud-run.png");
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
}