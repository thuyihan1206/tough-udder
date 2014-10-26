package java.toughudder;

import java.io.Serializable;

public class Obstacle implements Serializable {

   private static final long serialVersionUID = 6689651406241906509L;

   private final String name;
   
   private final String description;
   
   /** 
    * Just the name of the image; whatever is using this class is responsible
    * for knowing the location of the images.
    */
   private final String imageName;

   public Obstacle(String name, String description, String imageName) {
      this.name = name;
      this.description = description;
      this.imageName = imageName;
   }

   public String getName() {
      return name;
   }

   public String getDescription() {
      return description;
   }

   public String getImageName() {
      return imageName;
   }
   
   @Override
   public int hashCode() {
      int hash = 37;
      int result = name.hashCode();
      result = result * hash + description.hashCode();
      result = result * hash + imageName.hashCode();
      return result;
   }
   
   @Override
   public boolean equals(Object o) {
      if (!(o instanceof Obstacle)) {
         return false; 
      }
      if (this == o) {
         return true;
      }
      Obstacle other = (Obstacle)o;
      return name.equals(other.getName()) && 
            description.equals(other.getDescription()) &&
            imageName.equals(other.getImageName());
   }
}
