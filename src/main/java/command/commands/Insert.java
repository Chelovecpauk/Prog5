package command.commands;

import collection.route.Coordinates;
import collection.route.Location;
import collection.route.Route;
import collectionManagers.CollectionManager;
import command.CommandAbstract;

import java.util.Scanner;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

/**
 * Добавляет новый элемент с заданным ключом
 * @author KasimovBakhtiyar
 */

public class Insert extends CommandAbstract {
    public Insert(){
        super(true, false);
    }

    @Override
    public void setArgument1(String arg){
        try {
            this.argument1 = Integer.valueOf(arg);
        }catch (NumberFormatException exc){
            System.out.println("Ошибка формата ввода, требуется целое число");
            System.out.print("Введите аргумент еще раз: ");
            Scanner scan = new Scanner(System.in);
            if (scan.hasNextLine()){
                this.setArgument1(scan.nextLine().trim());
            }
        }
    }


    public void execute(){
        Route route = new Route();
        route = CollectionManager.requestRoute();

        CollectionManager.getRouteMap().put(Integer.valueOf((String)argument1),route);
        CollectionManager.sorteCollection();
        System.out.println("Элемент добавлен");
        //scanner.close();
    }
}
