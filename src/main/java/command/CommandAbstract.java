package command;

public abstract class CommandAbstract {

    private boolean hasArgument1;
    private boolean hasArgument2;
    private Object argument1;
    private Object argument2;

    public CommandAbstract(boolean hasArgument1, boolean hasArgument2){
        this.hasArgument1 = hasArgument1;
        this.hasArgument2 = hasArgument2;
    }
    public abstract void execute();
    public abstract void setArgument1(Object argument1);
    public abstract void setArgument2(Object argument2);

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
