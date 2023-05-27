package command.commands;

import command.CommandAbstract;
import user.UserManager;

/**
 * Завершает программу
 * @author KasimovBakhtiyar
 */

public class Exit extends CommandAbstract {
    public Exit(){
        super(false,false);
    }

    @Override
    public void execute(){
        UserManager.setIsWorking(false);
    }
}
