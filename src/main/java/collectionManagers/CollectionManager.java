package collectionManagers;


import collection.comparators.RouteComparator;
import collection.route.Coordinates;
import collection.route.Location;
import collection.route.Route;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Scanner;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;
import static java.util.stream.Collectors.*;
import static java.util.Map.Entry.*;

public class CollectionManager {
    private static String type = "LinkedHashMap";
    private static LinkedHashMap<Integer, Route> routeMap;
    private static String pathToDataFile;
    private static LocalDate initialDate;
    private static FileInteractionInterface fileInteraction = new XmlFileInteraction();

    public static String getType(){
        return type;
    }
    public static LinkedHashMap<Integer, Route> getRouteMap(){
        return routeMap;
    }
    public static String getPathToDataFile(){
        return pathToDataFile;
    }
    public static LocalDate getInitialDAte(){
        return initialDate;
    }
    public static void setCollection(LinkedHashMap<Integer,Route> routeMap){
        CollectionManager.routeMap = routeMap;
        setInitialDate();
        sorteCollection();
    }
    public static void setPathToDataFile(String pathToDataFile){
        CollectionManager.pathToDataFile = pathToDataFile;
    }

    public static void setInitialDate(){
        CollectionManager.initialDate = LocalDate.now();}

    public static void writeCollection(){
        fileInteraction.write(pathToDataFile, CollectionManager.routeMap);
    }

    public static void loadCollection(String envKey){
        try{ String pathToDataFile = System.getenv(envKey);}
        catch (NullPointerException exc){
            System.out.println("Переменная окружения " + envKey + " не определена в системе");
        }
        //CollectionManager.setCollection(fileInteraction.read(pathToDataFile));

        CollectionManager.setCollection(fileInteraction.read("C:\\Users\\Бахтияр\\IdeaProjects\\Prog5\\src\\main\\java\\data\\xmlMainFile"));

    }

    public static LinkedHashMap<Integer, Route> sorteCollection(){
        routeMap = routeMap.entrySet()
                .stream()
                .sorted(comparingByValue(new RouteComparator()))
                .collect(
                        toMap(e -> e.getKey(), e -> e.getValue(), (e1,e2) -> e2,
                                LinkedHashMap::new));
        return routeMap;
    }
    public static Route requestRoute(){
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

            //scanner.close();

            return route;

        }catch(NumberFormatException exc){
            System.out.println("Ошибка: недопустимый формат переменных");
            System.out.println("Допустимый формат: \nname - String\ndistance - Long\nCoordinates\n\tx - Double\n\ty - long\nLocation\n\tx - int\n\ty - Float");

            System.out.println("Попробуйте еще раз");
            return requestRoute();
        }
    }
}
