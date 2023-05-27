package command.commands;

import collection.route.Route;
import collectionManagers.CollectionManager;
import command.CommandAbstract;

import java.util.Map;
import java.util.Scanner;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

/**
 * Обновляет значение элемента коллекции, id которого равен заданному
 * @author KasimovBakhtiyar
 */

public class Update extends CommandAbstract {

    public Update(){
        super(true, false);
    }

    public void execute(){
        Route searchRoute = null;
        Integer key = null;

        for (Map.Entry<Integer,Route> entry : CollectionManager.getRouteMap().entrySet()){
            if (entry.getValue().getId() == Long.valueOf( (String) getArgument1() )){
                searchRoute = entry.getValue();
                key = entry.getKey();
                break;
            }
        }

        if (searchRoute == null){System.out.println("Не существует элемента с таким id ");return;}
        else{
            Scanner scanner = new Scanner(System.in);
            System.out.println("Введите из предложенного списка то, что вы хотите заменить, или \"update\" для сохранения изменений\n" +
                    "Список:\nname\ndistance\ncoordinates_x\ncoordinates_y\nfrom_x\nfrom_y\nto_x\nto_y"
                    );
            String mes = scanner.nextLine().trim();
            try {
                label:
                while (true) {
                    switch (mes) {
                        case "name":
                            System.out.print("Введите name: ");
                            searchRoute.setName(scanner.nextLine().trim());
                            break;
                        case "distance":
                            System.out.print("Введите distance: ");
                            searchRoute.setDistance(Long.valueOf(scanner.nextLine().trim()));
                            break;
                        case "coordinates_x":
                            System.out.print("Введите coordinates_x: ");
                            searchRoute.getCoordinates().setX(Double.valueOf(scanner.nextLine().trim()));
                            break;
                        case "coordinates_y":
                            System.out.print("Введите coordinates_y: ");
                            searchRoute.getCoordinates().setY(parseLong(scanner.nextLine().trim()));
                            break;
                        case "from_x":
                            System.out.print("Введите from_x: ");
                            searchRoute.getFrom().setX(parseInt(scanner.nextLine().trim()));
                            break;
                        case "from_y":
                            System.out.print("Введите from_y: ");
                            searchRoute.getFrom().setY(Float.valueOf(scanner.nextLine().trim()));
                            break;
                        case "to_x":
                        System.out.print("Введите to_x: ");
                        searchRoute.getTo().setX(parseInt(scanner.nextLine().trim()));
                        break;
                        case "to_y":
                            System.out.print("Введите to_y: ");
                            searchRoute.getTo().setY(Float.valueOf(scanner.nextLine().trim()));
                            break;
                        case "update":
                            CollectionManager.getRouteMap().put(key,searchRoute);
                            break label;
                        default:
                            System.out.println("Ввод несоответствует предложенному списку выше");
                            break ;
                    }

                    System.out.println("Введите из предложенного списка то, что вы хотите заменить, или \"update\" для сохранения изменений");
                    mes = scanner.nextLine().trim();

                }
            }catch(NumberFormatException exc){
                System.out.println("Ошибка: недопустимый формат переменных");
                System.out.println("Допустимый формат: \nname - String\ndistance - Long\nCoordinates\n\tx - Double\n\ty - long\nLocation\n\tx - int\n\ty - Float");
            }
            //scanner.close();
        }
    }
}
