package user;

import command.CommandAbstract;
import command.CommandIdentifier;

import java.util.HashMap;
import java.util.Scanner;

/**
* Класс взаимодействия с пользователем в интерактивном режиме
*/
public class UserManager {
    private static HashMap<String, CommandAbstract> commandMap;
    private static Scanner scanner;
    private static boolean isWorking;

    static{
        commandMap = CommandIdentifier.getCommandMap();
        scanner = new Scanner(System.in);
        isWorking = true;
    }

    public static void requestCommand(){
        System.out.print("Введите команду: ");
        String userManager = scanner.nextLine().trim();

        String[] input = userManager.split(" ");
        String command = input[0];
        String argument1;
        String argument2;

        if (input.length == 1) {
            argument1 = null;
            argument2 = null;
        }else if (input.length == 2) {
            argument1 = input[1];
            argument2 = null;
        }else if (input.length == 3) {
            argument1 = input[1];
            argument2 = input[2];
        }else {
            System.out.println("Некорректный формат ввода \nВведите: {команда} {аргумент1}(при наличии) {аргумент2}(при наличии)");
            return;
        }

        //execute command
        if (commandMap.containsKey(command)){
            boolean condition1 = commandMap.get(command).isHasArgument1() ==  (argument1 != null);
            boolean condition2 = commandMap.get(command).isHasArgument2() == (argument2 != null);
            if (condition1 && condition2){
                commandMap.get(command).setArgument1(argument1);
                commandMap.get(command).setArgument2(argument2);
                commandMap.get(command).execute();
            }else{System.out.println("Вы ввели несоответствующее количество аргуметов");}
        }else{
            System.out.println("Ошибка: команды " + command + " не существует" + "\nЧтобы посмотреть список доступных команд, введите: help");
        }

    }

    public static void setIsWorking(boolean isWorking) {
        UserManager.isWorking = isWorking;
    }
    public static boolean isWorking(){
        return isWorking;
    }
}
