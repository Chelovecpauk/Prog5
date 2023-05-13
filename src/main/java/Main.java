import collectionManagers.CollectionManager;
import user.UserManager;

public class Main {
    public static void main(String[] args) {
        String envKey = "p5";
        CollectionManager.loadCollection(envKey);

        while (UserManager.isWorking()){
            UserManager.requestCommand();
        }
    }
}