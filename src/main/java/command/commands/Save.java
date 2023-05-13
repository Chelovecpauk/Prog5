package command.commands;

import collectionManagers.CollectionManager;
import collectionManagers.FileInteractionInterface;
import collectionManagers.XmlFileInteraction;
import command.CommandAbstract;

public class Save extends CommandAbstract {

    public Save(){
        super(true,false);
    }

    public void execute(){
        FileInteractionInterface fileInteraction = new XmlFileInteraction();
        fileInteraction.write(CollectionManager.getPathToDataFile(),CollectionManager.getRouteMap());
    }
}