package command.commands;

import collectionManagers.CollectionManager;
import command.CommandAbstract;
import command.CommandIdentifier;

public class Clear extends CommandAbstract {
    public Clear(){
        super(false,false);
    }

    public void execute(){
        CollectionManager.getRouteMap().clear();
    }
}
