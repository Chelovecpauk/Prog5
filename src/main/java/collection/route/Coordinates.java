package collection.route;

public class Coordinates {
    private Double x;
    private long y;

    public Coordinates(){
        this.x = null;
        this.y = 0;
    }

    public Coordinates(Double x, long y){
        this.x = x;
        this.y = y;
    }

    public Double getX(){
        return x;
    }
    public long getY(){
        return y;
    }

    public boolean setX(Double x){
        if (x == null) {
            System.out.println("x не может быть null");
            return false;
        }
        else{
            this.x = x;
            return true;
        }
    }
    public void setY(long y){
        this.y = y;
    }

}
