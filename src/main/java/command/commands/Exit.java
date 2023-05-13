package command.commands;

import command.CommandAbstract;
import user.UserManager;

public class Exit extends CommandAbstract {
    public Exit(){
        super(false,false);
    }

    @Override
    public void execute(){
        UserManager.setIsWorking(false);
    }
}
