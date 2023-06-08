package command.commands;

import collection.route.Route;
import collectionManagers.CollectionManager;
import command.CommandAbstract;

import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

/**
 * Удаляет из коллекции все элементы, ключ которых превышает заданный
 * @author KasimovBakhtiyar
 */
public class RemoveGreaterKey extends CommandAbstract {
    public RemoveGreaterKey(){
        super(true,false);
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
        if (CollectionManager.getRouteMap().isEmpty()){
            System.out.println("Коллекция пустая");
        }else {
            Iterator<Map.Entry<Integer, Route>> it = CollectionManager.getRouteMap().entrySet().iterator();
            Map.Entry<Integer, Route> entry;
            while (it.hasNext()) {
                entry = it.next();
                if (entry.getKey() > Integer.valueOf((String) argument1)) {
                    it.remove();

                }
            }
            System.out.println("Элементы с превыщающим ключом удалены");
        }
    }
}
