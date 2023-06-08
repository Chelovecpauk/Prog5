package command.commands;

import collectionManagers.CollectionManager;
import command.CommandAbstract;

import java.util.Scanner;

/**
 *  Удаляет элемент из коллекции по его ключу
 * @author KasimovBakhtiyar
 */

public class RemoveKey extends CommandAbstract {
    public RemoveKey(){
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
            Integer key = (Integer) argument1;
            if (CollectionManager.getRouteMap().containsKey(key)) {
                CollectionManager.getRouteMap().remove(key);
            }else{
                System.out.println("Не существует элемента с таким ключом");
            }
        }
    }
}
