package java.toughudder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class EventFactory {
	
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	private static final List<String> eventNames = Collections.unmodifiableList(
			Arrays.asList("Udder Day", "Muddy Udder", "Holy Cow", "Mad Cow")
    );
 
	/**
	 * Returns a list of all possible events.
	 * 
	 * @return the list of all event objects.
	 */
    public static List<Event> getAllEvents() {
    	List<Event> eventList = new ArrayList<Event>();
    	for(String eventName : eventNames) {
    		eventList.add(getEvent(eventName));
    	}
    	return eventList;
    }
	
    /**
     * Builds an event object for the given event type.
     * 
     * @param name - The name of the event
     * @return the event object for the given event name.
     */
	public static Event getEvent(String name) {
		switch(name) {
			case "Udder Day":
				return getUdderDayEvent();
			case "Muddy Udder":
				return getMuddyUdderEvent();
			case "Holy Cow":
				return getHolyCowEvent();
			case "Mad Cow":
				return getMadCowEvent();
			default:
				System.err.println("Event name was not understood: " + name);
				return null;
		}
	}
	
	private static Event getUdderDayEvent() {
		try {
			return new Event("Udder Day", "The Bahamas", dateFormat.parse("07-21-2015"), 2500.00);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private static Event getMuddyUdderEvent() {
		try {
			return new Event("Muddy Udder", "Everglades, Florida", dateFormat.parse("07-07-2015"), 120.00);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private static Event getHolyCowEvent() {
		try {
			return new Event("Holy Cow", "Wichita, Kansas", dateFormat.parse("06-19-2015"), 50.00);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private static Event getMadCowEvent() {
		try {
			return new Event("Mad Cow", "Knoxville, Tennessee", dateFormat.parse("06-15-2015"), 75.00);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
}
