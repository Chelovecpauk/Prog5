package command.commands;

import collection.route.Coordinates;
import collection.route.Location;
import collection.route.Route;
import collectionManagers.CollectionManager;
import command.CommandAbstract;

import java.util.Scanner;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

public class Insert extends CommandAbstract {
    public Insert(){
        super(true, false);
    }

  /*  @Override
    public void setArgument1(String arg){
        this.argument1 = Integer.valueOf(arg);
    }*/


    public void execute(){
        Route route = new Route();
        Coordinates coordinates = new Coordinates();
        Location from = new Location();
        Location to = new Location();
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Введите name: ");
            route.setName(scanner.nextLine().trim());
            System.out.print("Введите distance: ");
            route.setDistance(Long.valueOf(scanner.nextLine().trim()));
            System.out.print("Введите Coordinates_x: ");
            coordinates.setX(Double.valueOf(scanner.nextLine().trim()));
            System.out.print("Введите Coordinates_y: ");
            coordinates.setY(parseLong(scanner.nextLine().trim()));
            System.out.print("Введите Location_from_x: ");
            from.setX(parseInt(scanner.nextLine().trim()));
            System.out.print("Введите Location_from_y: ");
            from.setY(Float.valueOf(scanner.nextLine().trim()));
            System.out.print("Введите Location_to_x: ");
            to.setX(parseInt(scanner.nextLine().trim()));
            System.out.print("Введите Location_to_y: ");
            to.setY(Float.valueOf(scanner.nextLine().trim()));
            route.setCoordinates(coordinates);
            route.setFrom(from);
            route.setTo(to);

            CollectionManager.getRouteMap().put((Integer)argument1,route);
            CollectionManager.sorteCollection();
            scanner.close();

        }catch(NumberFormatException exc){
            System.out.println("Ошибка: недопустимый формат переменных");
            System.out.println("Допустимый формат: \nname - String\ndistance - Long\nCoordinates\n\tx - Double\n\ty - long\nLocation\n\tx - int\n\ty - Float");
        }
    }
}
