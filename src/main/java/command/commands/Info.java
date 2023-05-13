package command.commands;

import command.CommandAbstract;
import collectionManagers.CollectionManager;

public class Info extends CommandAbstract {

    public Info(){
        super(false, false);
    }

    @Override
    public void execute(){
        System.out.println(
                "Тип коллекции: " + CollectionManager.getType() + "\n" +
                "Дата инициализации: " + CollectionManager.getInitialDAte() + "\n" +
                "Количество элементов: " + CollectionManager.getRouteMap().size()
        );
    }
}
