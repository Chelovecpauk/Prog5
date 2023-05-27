package command.commands;

import collection.route.Route;
import collectionManagers.CollectionManager;
import command.CommandAbstract;

import java.util.Map;

public class CountByDistance extends CommandAbstract {
    public CountByDistance() {
        super(true, false);
    }

    @Override
    public void setArgument1(String argument1) {
        this.argument1 = Long.valueOf(argument1);
    }

    @Override
    public void execute() {
        int count = 0;
        for (Map.Entry<Integer, Route> entry : CollectionManager.getRouteMap().entrySet()) {
            if (entry.getValue().getDistance() == argument1){
                count++;
            }
        }

        System.out.println("Количество элементов, значение поля distance которых равно заданному: " + count);
    }
}