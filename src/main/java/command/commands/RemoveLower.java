package command.commands;

import collection.comparators.RouteComparator;
import collection.route.Route;
import collectionManagers.CollectionManager;
import command.CommandAbstract;

import java.util.Iterator;
import java.util.Map;

/**
 * Удаляет из коллекции все элементы, меньшие, чем заданный
 * @author KasimovBakhtiyar
 */

public class RemoveLower extends CommandAbstract {

    public RemoveLower(){
        super(false,false);
    }


    public void execute(){
        System.out.println("Создайте объект для сравнения:");
        Route comparedRoute = CollectionManager.requestRoute();
        RouteComparator routeComparator = new RouteComparator();

        Iterator<Map.Entry<Integer, Route>> it = CollectionManager.getRouteMap().entrySet().iterator();
        Map.Entry<Integer, Route> entry;
        while(it.hasNext()) {
            entry = it.next();
            if (routeComparator.compare(entry.getValue(), comparedRoute) < 0) {
                it.remove();

            }
        }
        System.out.println("Меньшие элементы удалены (при присутствии)");

    }
}