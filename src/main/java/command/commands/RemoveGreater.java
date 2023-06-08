package command.commands;

import collection.comparators.RouteComparator;
import collection.route.Coordinates;
import collection.route.Location;
import collection.route.Route;
import collectionManagers.CollectionManager;
import command.CommandAbstract;

import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

/**
 * Удаляет из коллекции все элементы, превышающие заданный
 * @author KasimovBakhtiyar
 */
public class RemoveGreater extends CommandAbstract {

    public RemoveGreater(){
        super(false,false);
    }



    public void execute(){
        if (CollectionManager.getRouteMap().isEmpty()){
            System.out.println("Коллекция пустая");
        }else {
            System.out.println("Создайте объект для сравнения:");
            Route comparedRoute = CollectionManager.requestRoute();
            RouteComparator routeComparator = new RouteComparator();

            Iterator<Map.Entry<Integer, Route>> it = CollectionManager.getRouteMap().entrySet().iterator();
            Map.Entry<Integer, Route> entry;
            while (it.hasNext()) {
                entry = it.next();
                if (routeComparator.compare(entry.getValue(), comparedRoute) > 0) {
                    it.remove();

                }
            }
            System.out.println("Большие элементы удалены (при присутствии)");
        }
    }
}
