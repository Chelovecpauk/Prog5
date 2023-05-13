package command.commands;

import collection.route.Route;
import collectionManagers.CollectionManager;
import command.CommandAbstract;

public class Show extends CommandAbstract {
    public Show(){
        super(false, false);
    }

    @Override
    public void execute(){
        for (Route route: CollectionManager.getRouteMap().values()){
            System.out.println(route);
        }
    }
}
