package command.commands;

import collectionManagers.CollectionManager;
import command.CommandAbstract;
import command.CommandIdentifier;

/**
 * Команда очищает коллекцию
 * @author KasimovBakhtiyar
 */
public class Clear extends CommandAbstract {
    public Clear(){
        super(false,false);
    }

    public void execute(){
        CollectionManager.getRouteMap().clear();
        System.out.println("Коллекция очищена");
    }
}
