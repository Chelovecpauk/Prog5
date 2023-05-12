package collection.route;

import java.time.LocalDateTime;

public class Route {
    private Long id;
    private String name;
    private Coordinates coordinates;
    private java.time.LocalDateTime creationDate;
    private Location from;
    private Location to;
    private Long distance;

    private static Long idGenerator = (long) 1;

    public Route(){
        this.id = idGenerator ++;
        this.creationDate  = LocalDateTime.now();
    }

    public Route(String name, Coordinates coordinates, Location from, Location to, Long distance){
        this.name = name;
        this.coordinates = coordinates;
        this.from = from;
        this.to = to;
        this.distance = distance;
        this.id = idGenerator ++;
        this.creationDate  = LocalDateTime.now();
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public Location getFrom() {
        return from;
    }

    public Location getTo() {
        return to;
    }

    public Long getDistance() {
        return distance;
    }

    public Long getId() {
        return id;
    }

    public boolean setCoordinates(Coordinates coordinates) {
        if(coordinates == null){
            System.out.println("coordinates не может быть null");
            return false;
        }
        else{
            this.coordinates = coordinates;
            return true;
        }
    }

    public boolean setDistance(Long distance) {
        if (distance < 2){
            System.out.println("значение distance должно быть больше 1");
            return false;
        }
        else{
            this.distance = distance;
            return true;
        }
    }

    public boolean setFrom(Location from) {
        if (from == null){
            System.out.println("параметр from не может быть null");
            return false;
        }
        else{
            this.from = from;
            return true;
        }
    }

    public boolean setName(String name) {
        if(name == null || name == ""){
            if (name == null){System.out.println("name не может быть null");}
            if (name == ""){System.out.println("name не может быть пустым");}
            return false;
        }
        else{
            this.name = name;
            return true;
        }
    }

    public boolean setTo(Location to) {
        if (to == null){
            System.out.println("параметр to не может быть null");
            return false;
        }
        else{
            this.to = to;
            return true;
        }
    }

}
