package collectionManagers;


import collection.comparators.RouteComparator;
import collection.route.Route;

import java.time.LocalDate;
import java.util.LinkedHashMap;

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
        String pathToDataFile = System.getenv(envKey);
        if (pathToDataFile == null){
            System.out.println("Переменная окружения " + envKey + " не определена в системе");
        }
        CollectionManager.setCollection(fileInteraction.read(pathToDataFile));
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
}
