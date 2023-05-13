package command.commands;

import command.CommandAbstract;

import java.util.HashMap;
import java.util.Scanner;

import command.CommandIdentifier;
public class ExecuteScript extends CommandAbstract {

    public ExecuteScript(){
        super(true, false);
    }

    @Override
    public  void execute() {
        HashMap<String, CommandAbstract> commandMap = CommandIdentifier.getCommandMap();
        Scanner scanner = new Scanner((String) argument1);

        while (scanner.hasNextLine()) {
            String row = scanner.nextLine().trim();

            String[] input = row.split(" ");
            String command = input[0];
            String argument1;
            String argument2;

            if (input.length == 1) {
                argument1 = null;
                argument2 = null;
            } else if (input.length == 2) {
                argument1 = input[1];
                argument2 = null;
            } else if (input.length == 3) {
                argument1 = input[1];
                argument2 = input[2];
            } else {
                System.out.println("Некорректный формат ввода \nСтруктура: {команда} {аргумент1}(при наличии) {аргумент2}(при наличии)");
                return;
            }

            //execute command
            if (commandMap.containsKey(command)) {
                boolean condition1 = commandMap.get(command).isHasArgument1() == (argument1 != null);
                boolean condition2 = commandMap.get(command).isHasArgument2() == (argument2 != null);
                if (condition1 && condition2) {
                    commandMap.get(command).setArgument1(argument1);
                    commandMap.get(command).setArgument2(argument2);
                    commandMap.get(command).execute();
                } else {
                    System.out.println("Ошибка: указание несоответствующего количества аргументов");
                }
            } else {
                System.out.println("Ошибка: команды " + command + "не существует" + "\nЧтобы посмотреть список доступных команд, введите: help");
            }
            scanner.close();
        }
    }
}
