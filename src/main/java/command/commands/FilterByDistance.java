package command.commands;

import collection.route.Route;
import collectionManagers.CollectionManager;
import command.CommandAbstract;

import java.util.Map;

/**
 * Выводит элементы, значение поля distance которых равно заданному
 * @author KasimovBakhtiyar
 */
public class FilterByDistance extends CommandAbstract {
    public FilterByDistance() {
        super(true, false);
    }

    @Override
    public void setArgument1(String argument1) {
        this.argument1 = Long.valueOf(argument1);
    }

    @Override
    public void execute() {
        for (Map.Entry<Integer, Route> entry : CollectionManager.getRouteMap().entrySet()) {
            if (entry.getValue().getDistance() == argument1){
                Route route = entry.getValue();

                System.out.println("name: " + route.getName() +
                        "\nid: " + route.getId() +
                        "\ncoordinates_x: " + route.getCoordinates().getX() +
                        "\ncoordinates_y: " + route.getCoordinates().getY() +
                        "\ncreationDate: " + route.getCreationDate() +

                        "\nfrom_x: " + route.getFrom().getX() +
                        "\nfrom_y: " + route.getFrom().getX() +
                        "\nto_x: " + route.getTo().getX() +
                        "\nto_y: " + route.getTo().getX() +
                        "\nfrom_x: " + route.getFrom().getX() +
                        "\ndistance: " + route.getDistance()
                );
            }
        }
    }
}