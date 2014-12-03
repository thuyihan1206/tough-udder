/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toughudder;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Evan Chan
 */
public class Cart implements Serializable {

    private static final long serialVersionUID = 8600291804865739176L;

    private final List<Event> events;

    private final NumberFormat costFormat;

    private final DateFormat dateFormat;

    public Cart() {
        events = new ArrayList<>();
        costFormat = NumberFormat.getCurrencyInstance();
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    }

    public void addEvent(Event event) {
        events.add(event);
    }

    public boolean removeEvent(Event event) {
        return events.remove(event);
    }

    /**
     * Completely empties the cart of all events.
     */
    public void clear() {
        events.clear();
    }

    public List<Event> getEvents() {
        return events;
    }

    public NumberFormat getCostFormat() {
        return costFormat;
    }

    public DateFormat getDateFormat() {
        return dateFormat;
    }

    public double getTotalCost() {
        double cost = 0;
        for (Event e : events) {
            cost += e.getCost();
        }
        return cost;
    }
    
    /**
     * Helper function that checks to see if the cart currently 
     * holds the event given by eventName.
     * 
     * @param eventName - The name of the event to check for.
     * @return true if the cart currently holds this event, false otherwise.
     */
    public boolean hasEvent(String eventName) {
        for(Event event : this.getEvents()) {
            if(event.getName().equals(eventName)) {
                return true;
            }
        }
        return false;
    }
}
