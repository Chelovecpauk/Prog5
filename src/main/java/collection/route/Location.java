package collection.route;

public class Location {
    private int x;
    private Float y;
    private String name;

    public Location(){
        this.x = 0;
        this.y = null;
    }

    public Location(int x, Float y, String name){
        this.x = x;
        this.y = y;
        this.name = name;
    }

    public int getX(){
        return x;
    }
    public Float getY(){
        return y;
    }
    public String getName(){
        return name;
    }

    public void setX(int x){
        this.x = x;
    }

    public boolean setY(Float y){
        if(y == null){
            System.out.println("y не может быть null");
            return false;
        }
        else{
            this.y = y;
            return true;
        }
    }

    public boolean setName(String name){
        if(name == ""){
            System.out.println("имя не может быть пустым");
            return false;
        }
        else{
            this.name = name;
            return true;
        }
    }
}
