/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toughudder;

import java.io.Serializable;
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

    private List<Event> events;

    public Cart() {
        events = new ArrayList<>();
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

    public double getTotalCost() {
        double cost = 0;
        for (Event e : events) {
            cost += e.getCost();
        }
        return cost;
    }
}
