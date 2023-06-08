package command.commands;

import collection.route.Route;
import collectionManagers.CollectionManager;
import command.CommandAbstract;

import java.util.Map;
import java.util.Scanner;

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
        try {
            this.argument1 = Long.valueOf(argument1);
        }catch (NumberFormatException exc){
            System.out.println("Ошибка формата ввода, требуется целое число");
            System.out.print("Введите аргумент еще раз: ");
            Scanner scan = new Scanner(System.in);
            if (scan.hasNextLine()){
                this.setArgument1(scan.nextLine().trim());
            }
        }
    }

    @Override
    public void execute() {
        if (CollectionManager.getRouteMap().isEmpty()){
            System.out.println("Коллекция пустая");
        }else {
            boolean inStock = false;
            for (Map.Entry<Integer, Route> entry : CollectionManager.getRouteMap().entrySet()) {
                if (entry.getValue().getDistance() == argument1) {
                    inStock = true;

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
            if (!inStock) {
                System.out.println("Подходящие элементы не найдены");
            }
        }
    }
}