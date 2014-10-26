package java.toughudder;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class Event implements Serializable {
   
   private static final long serialVersionUID = 6465002267077589606L;

   private final String name;
   
   private final String location;
   
   private final Calendar date;

   private final double cost;
   
   /** Ordered List of Obstacles encountered in this event. */
   private final List<Obstacle> obstacles;

   public Event(
         String name, String location, Calendar date, double cost, 
         List<Obstacle> obstacles) {
      
      this.name = name;
      this.location = location;
      this.date = date;
      this.cost = cost;
      this.obstacles = Collections.unmodifiableList(obstacles);
   }

   public String getName() {
      return name;
   }

   public String getLocation() {
      return location;
   }

   public Calendar getDate() {
      return date;
   }

   public double getCost() {
      return cost;
   }
   
   public List<Obstacle> getObstacles() {
      return obstacles;
   }
   
   @Override
   public int hashCode() {
      int hash = 37;
      int result = name.hashCode();
      result = result * hash + location.hashCode();
      result = result * hash + date.hashCode();
      long l = Double.doubleToLongBits(cost);
      result = result * hash + (int)(l ^ (l >>> Integer.SIZE));
      result = result * hash + obstacles.hashCode();
      return result;
   }
   
   @Override
   public boolean equals(Object o) {
      if (!(o instanceof Event)) {
         return false; 
      }
      if (this == o) {
         return true;
      }
      Event other = (Event)o;
      return name.equals(other.name) && location.equals(other.location) &&
            date.equals(other.date) && Double.compare(cost, other.cost) == 0 &&
            obstacles.containsAll(other.obstacles) && 
            other.obstacles.containsAll(obstacles);
   }
}
