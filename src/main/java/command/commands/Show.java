package command.commands;

import collection.route.Route;
import collectionManagers.CollectionManager;
import command.CommandAbstract;

/**
 * Выводит в стандартный поток вывода все элементы коллекции в строковом представлении
 * @author KasimovBakhtiyar
 */
public class Show extends CommandAbstract {
    public Show(){
        super(false, false);
    }

    @Override
    public void execute(){
        for (Route route: CollectionManager.getRouteMap().values()){
            System.out.println(route.toString());
        }
    }
}
