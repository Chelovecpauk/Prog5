package command.commands;

import collection.comparators.RouteComparator;
import collection.route.Coordinates;
import collection.route.Location;
import collection.route.Route;
import collectionManagers.CollectionManager;
import command.CommandAbstract;

import java.util.Map;
import java.util.Scanner;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

public class RemoveGreater extends CommandAbstract {

    public RemoveGreater(){
        super(true,false);
    }

    public void setArgument1(Object argument1){
        this.argument1 = CollectionManager.requestRoute();
    }

    public void execute(){
        RouteComparator routeComparator = new RouteComparator();
        for (Map.Entry<Integer, Route> entry : CollectionManager.getRouteMap().entrySet()){
            if (routeComparator.compare(entry.getValue(), (Route) argument1) > 0){
                CollectionManager.getRouteMap().remove(entry.getKey());
            }
        }
    }
}
