package command.commands;

import collection.route.Route;
import collectionManagers.CollectionManager;
import command.CommandAbstract;

import java.util.Map;

/**
 * Выводит сумму значений поля distance для всех элементов коллекции
 * @author KasimovBakhtiyar
 */

public class SumOfDistance extends CommandAbstract {
    public SumOfDistance(){
        super(false,false);
    }

    public void execute(){
        if (CollectionManager.getRouteMap().isEmpty()){
            System.out.println("Коллекция пустая");
        }else {
            Long sum = Long.valueOf(0);
            for (Map.Entry<Integer, Route> entry : CollectionManager.getRouteMap().entrySet()) {
                sum += entry.getValue().getDistance();
            }
            System.out.println("Сумма значений поля distance: " + sum);
        }
    }
}
