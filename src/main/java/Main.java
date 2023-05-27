import collectionManagers.CollectionManager;
import user.UserManager;

public class Main {
    public static void main(String[] args) {
        final String ENVKEY = "p5";
        CollectionManager.loadCollection(ENVKEY);

        while (UserManager.isWorking()){
            UserManager.requestCommand();
        }
    }
}