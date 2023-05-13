package command.commands;

import collection.route.Route;
import collectionManagers.CollectionManager;
import command.CommandAbstract;

import java.util.Map;

public class RemoveGreaterKey extends CommandAbstract {
    public RemoveGreaterKey(){
        super(true,false);
    }

    public void setArgument(Object arg){
        Integer argument1 = Integer.valueOf((String) arg);
    }

    public void execute(){
        for (Map.Entry<Integer, Route> entry : CollectionManager.getRouteMap().entrySet()){
            if(entry.getKey() > (Integer) argument1){
                CollectionManager.getRouteMap().remove(entry.getKey());
            }
        }
    }
}
