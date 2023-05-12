package collectionManagers;

import java.util.AbstractMap;
import java.util.LinkedHashMap;

import collection.route.Route;

public interface FileInteractionInterface {
    LinkedHashMap<Integer,Route> read(String pathToDataFile);

    void write(String pathToDataFile, AbstractMap<Integer,Route> routeMap);
    String getStructureFile();

    public default String adaptPathToOS(String path){
        if(System.getProperty("oc.name") == "FreeBSD"){
            return path.replace("\\","/");
        }
        else{return path;}
    }
}
