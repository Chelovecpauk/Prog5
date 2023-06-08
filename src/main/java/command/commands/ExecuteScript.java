package command.commands;

import collectionManagers.CollectionManager;
import command.CommandAbstract;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import command.CommandIdentifier;

/**
 * Выполняет скрипт из файла
 * @author KasimovBakhtiyar
 */
public class ExecuteScript extends CommandAbstract {

    private ArrayList<String> fileNames;

    public ExecuteScript(){
        super(true, false);
        fileNames = new ArrayList<>();
    }

    @Override
    public void setArgument1(String argument1){
        if (isHasArgument1()) {
            this.argument1 = argument1;
        }
    }

    public ArrayList<String> getFileNames(){
        return fileNames;
    }
    @Override
    public  void execute() {
        try {
            HashMap<String, CommandAbstract> commandMap = CommandIdentifier.getCommandMap();
            Path path = Paths.get((String) argument1);
            Scanner scanner = new Scanner(path);

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
                        if ("execute_script".equals(command)){
                            ExecuteScript scr = (ExecuteScript) commandMap.get("execute_script");
                            if (scr.getFileNames().contains(argument1)){
                                System.out.println("Исполняемый файл " + argument1 + " содержит команду execute_script с ссылкой на предыдущий исполняемый файл в цепочке\n" +
                                        "Во избежание бесконечной рекурсии, команда execute_script была пропущена");
                                continue;
                            }else{
                                scr.getFileNames().add(argument1);
                            }
                        }
                        commandMap.get(command).execute();
                        System.out.println();
                    } else {
                        System.out.println("Ошибка: указание несоответствующего количества аргументов");
                    }
                } else {
                    System.out.println("Ошибка: команды " + command + " не существует" + "\nЧтобы посмотреть список доступных команд, введите: help");
                }
                //scanner.close();
            }
            fileNames.remove(argument1);
        }catch(IOException exc){
            Scanner scanner = new Scanner(System.in);
            System.out.print("Ошибка: файл не обнаружен\n" + "Введите путь к файлу повторно: ");
            CommandIdentifier.getCommandMap().get("execute_script").setArgument1(scanner.nextLine().trim());
            CommandIdentifier.getCommandMap().get("execute_script").execute();

        }
    }
}
