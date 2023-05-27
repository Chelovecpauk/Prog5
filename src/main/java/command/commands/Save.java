package command.commands;

import collectionManagers.CollectionManager;
import collectionManagers.FileInteractionInterface;
import collectionManagers.XmlFileInteraction;
import command.CommandAbstract;

public class Save extends CommandAbstract {

    public Save(){
        super(false,false);
    }

    public void execute(){
        FileInteractionInterface fileInteraction = new XmlFileInteraction();
        fileInteraction.write(CollectionManager.getPathToDataFile(),CollectionManager.getRouteMap());
        System.out.println("Коллекция сохранена");
    }
}
