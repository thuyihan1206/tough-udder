package toughudder;

import java.io.Serializable;

public class Event implements Serializable {

    private static final long serialVersionUID = 6465002267077589606L;

    private final String name;

    private final String location;

    private final String date;

    private final double cost;

    private final String imgName;

    public Event(String name, String location, String date, double cost, String imgName) {
        this.name = name;
        this.location = location;
        this.date = date;
        this.cost = cost;
        this.imgName = imgName;
    }

    public String getImgName() {
        return imgName;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getDate() {
        return date;
    }

    public double getCost() {
        return cost;
    }

    @Override
    public int hashCode() {
        int hash = 37;
        int result = name.hashCode();
        result = result * hash + location.hashCode();
        result = result * hash + date.hashCode();
        long l = Double.doubleToLongBits(cost);
        result = result * hash + (int) (l ^ (l >>> Integer.SIZE));
        //   result = result * hash + obstacles.hashCode();
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
        Event other = (Event) o;
        return name.equals(other.name) && location.equals(other.location)
                && date.equals(other.date) && Double.compare(cost, other.cost) == 0;
    }
}
