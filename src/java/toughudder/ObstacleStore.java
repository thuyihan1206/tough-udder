package java.toughudder;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ObstacleStore {

   private static ObstacleStore INSTANCE = new ObstacleStore();
   
   public static ObstacleStore instance() {
      return INSTANCE;
   }
   
   private final Map<String, Obstacle> obstacleMap;
   
   private ObstacleStore() {
      Map<String, Obstacle> tempMap = new HashMap<>();
      
      // Now create our data.
      String name = "Udder Madness";
      tempMap.put(name, new Obstacle(
            name, "Crawl under a sea of cow udders!", "udder_madness.jpg"));
      name = "Cow Pull";
      tempMap.put(name, new Obstacle(
            name, "Pull your cow 50 meters!", "cow_pull.jpg"));
      name = "Barnstormer";
      tempMap.put(name, new Obstacle(
            name, "Climb up the side of a barn and slide down the other side.",
            "barnstormer.jpg"));
      
      obstacleMap = Collections.unmodifiableMap(tempMap);
   }
   
   public Collection<Obstacle> getObstacles() {
      return obstacleMap.values();
   }
   
   public Obstacle getObstacle(String name) {
      return obstacleMap.get(name);
   }
}
