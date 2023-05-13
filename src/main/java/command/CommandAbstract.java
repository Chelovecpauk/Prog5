package command;

public abstract class CommandAbstract {

    private boolean hasArgument1;
    private boolean hasArgument2;
    public Object argument1;
    public Object argument2;

    public CommandAbstract(boolean hasArgument1, boolean hasArgument2){
        this.hasArgument1 = hasArgument1;
        this.hasArgument2 = hasArgument2;
    }
    public abstract void execute();
    public void setArgument1(String argument1){
        if (hasArgument1){this.argument1 = argument1;}
        else{System.out.println("Команда не имеет аргументов");}
    };
    public void setArgument2(Object argument2){
        if (hasArgument2){this.argument2 = argument2;}
        else{System.out.println("Команда не имеет второго аргумента");}
    };

    public boolean isHasArgument1(){
        return hasArgument1;
    }

    public boolean isHasArgument2(){
        return hasArgument2;
    }

    public Object getArgument1(){
        return argument1;
    }
    public Object getArgument2(){
        return argument2;
    }
}
