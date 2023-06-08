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

    public void setY(Float y){
        if(y == null){
            System.out.println("y не может быть null");
        }
        else{
            this.y = y;
        }
    }

    public void setName(String name){
        if("".equals(name)){
            System.out.println("имя не может быть пустым");
        }
        else{
            this.name = name;
        }
    }
}
