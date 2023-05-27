package command.commands;

import collectionManagers.CollectionManager;
import command.CommandAbstract;

/**
 *  Удаляет элемент из коллекции по его ключу
 * @author KasimovBakhtiyar
 */

public class RemoveKey extends CommandAbstract {
    public RemoveKey(){
        super(true,false);
    }

    public void execute(){
        CollectionManager.getRouteMap().remove(Integer.valueOf((String) argument1));
    }
}
