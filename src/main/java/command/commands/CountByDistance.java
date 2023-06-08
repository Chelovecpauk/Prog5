package command.commands;

import collection.route.Route;
import collectionManagers.CollectionManager;
import command.CommandAbstract;

import java.util.Map;
import java.util.Scanner;

/**
 * выводит количество элементов, значение поля distance которых равно заданному
 * @author KasimovBakhtiyar
 */

public class CountByDistance extends CommandAbstract {
    public CountByDistance() {
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
            int count = 0;
            for (Map.Entry<Integer, Route> entry : CollectionManager.getRouteMap().entrySet()) {
                if (entry.getValue().getDistance() == argument1) {
                    count++;
                }
            }

            System.out.println("Количество элементов, значение поля distance которых равно заданному: " + count);
        }
    }
}