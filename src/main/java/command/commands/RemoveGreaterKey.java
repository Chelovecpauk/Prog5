package command.commands;

import collection.route.Route;
import collectionManagers.CollectionManager;
import command.CommandAbstract;

import java.util.Iterator;
import java.util.Map;

public class RemoveGreaterKey extends CommandAbstract {
    public RemoveGreaterKey(){
        super(true,false);
    }

    public void setArgument(Object arg){
        Integer argument1 = Integer.valueOf((String) arg);
    }

    public void execute(){
        Iterator<Map.Entry<Integer, Route>> it = CollectionManager.getRouteMap().entrySet().iterator();
        Map.Entry<Integer, Route> entry;
        while(it.hasNext()){
            entry = it.next();
            if(entry.getKey() > Integer.valueOf((String)argument1)){
                it.remove();

            }
        }
        System.out.println("Элементы с превыщающим ключом удалены");

    }
}
