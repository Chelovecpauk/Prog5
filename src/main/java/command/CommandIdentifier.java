package command;

import command.commands.*;

import java.util.HashMap;

public class CommandIdentifier {
    private final static HashMap<String,CommandAbstract> commandMap = new HashMap<>();

    static{
        commandMap.put("help", new Help());
        commandMap.put("info", new Info());
        commandMap.put("show", new Show());
        commandMap.put("insert", new Insert());
        commandMap.put("update", new Update());
        commandMap.put("remove_key", new RemoveKey());
        commandMap.put("clear", new Clear());
        commandMap.put("save", new Save());
        commandMap.put("execute_script", new ExecuteScript());
        commandMap.put("exit", new Exit());
        commandMap.put("remove_greater", new RemoveGreater());
        commandMap.put("remove_lower", new RemoveLower());
        commandMap.put("remove_greater_key", new RemoveGreaterKey());
        commandMap.put("sum_of_distance", new SumOfDistance());
        commandMap.put("count_by_distance", new Distance());
        commandMap.put("filter_by_distance", new FilterByDistance());

    }

    public static HashMap<String, CommandAbstract> getCommandMap(){
        return commandMap;
    }
}
