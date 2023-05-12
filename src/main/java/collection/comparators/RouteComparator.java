package collection.comparators;

import java.util.Comparator;
import collection.route.Route;

public class RouteComparator implements Comparator<Route> {
    public RouteComparator(){}
    public int compare(Route a, Route b){
        return a.getName().compareTo(b.getName());
    }
}
