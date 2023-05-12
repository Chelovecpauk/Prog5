package collectionManagers;

import collection.route.Coordinates;
import collection.route.Route;
import collection.route.Location;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;


import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

public class XmlFileInteraction implements FileInteractionInterface{
    private String pathToExampleFile = "..\\Data\\xmlStructure";

    public XmlFileInteraction(){
        this.pathToExampleFile = adaptPathToOS(pathToExampleFile);
    }

    public String getPathToExampleFile() {
        return pathToExampleFile;
    }

    public void setPathToExampleFile(String pathToExampleFile) {
        this.pathToExampleFile = pathToExampleFile;
    }

    @Override
    public LinkedHashMap<Integer, Route> read(String pathToDataFile){
        pathToDataFile = adaptPathToOS(pathToDataFile);
        LinkedHashMap <Integer, Route> routeMap = new LinkedHashMap<Integer, Route>();
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        Route route = null;
        String name = null;
        Coordinates coordinates = null;
        Location from = null;
        Location to = null;
        Long distance = null;


        try {
            XMLEventReader reader = xmlInputFactory.createXMLEventReader(new FileReader(pathToDataFile));
            while (reader.hasNext()) {
                XMLEvent xmlEvent = reader.nextEvent();
                if (xmlEvent.isStartElement()) {
                    StartElement startElement = xmlEvent.asStartElement();
                    if (startElement.getName().getLocalPart().equals("Route")) {
                        route = new Route();

                    } else if (startElement.getName().getLocalPart().equals("name")) {
                        xmlEvent = reader.nextEvent();
                        if(name == null){
                            route.setName(xmlEvent.asCharacters().getData());
                        }else{
                            System.out.println("Исходный файл не должен содержать повторяющихся тегов внутри тега Route");
                            break;
                        }
                    } else if (startElement.getName().getLocalPart().equals("distance")) {
                        xmlEvent = reader.nextEvent();
                        if (distance == null){
                            route.setDistance(Long.valueOf(xmlEvent.asCharacters().getData()));
                        }else{
                            System.out.println("Исходный файл не должен содержать повторяющихся тегов внутри тега Route");
                            break;
                        }

                    } else if (startElement.getName().getLocalPart().equals("coordinates")) {
                        if (coordinates == null) {
                            coordinates = new Coordinates();

                            while (reader.hasNext()) {
                                xmlEvent = reader.nextEvent();
                                if (xmlEvent.isStartElement()) {
                                    startElement = xmlEvent.asStartElement();
                                    if (startElement.getName().getLocalPart().equals("x")) {
                                        if (coordinates.getX() == null) {
                                            coordinates.setX(Double.valueOf(xmlEvent.asCharacters().getData()));
                                        } else {
                                            System.out.println("Исходный файл не должен содержать повторяющихся тегов внутри тега coordinates");
                                            break;
                                        }
                                    } else if (startElement.getName().getLocalPart().equals("y")) {
                                        if (coordinates.getY() == 0) {
                                            coordinates.setY(Long.parseLong(xmlEvent.asCharacters().getData()));
                                        } else {
                                            System.out.println("Исходный файл не должен содержать повторяющихся тегов внутри тега coordinates");
                                            break;
                                        }
                                    } else {
                                        System.out.println("Неопознанный тег, редактируйте исходный файл");
                                        System.out.println(getStructureFile());
                                        break;
                                    }
                                }
                                if (xmlEvent.isEndElement()) {
                                    EndElement endElement = xmlEvent.asEndElement();
                                    if (endElement.getName().getLocalPart().equals("coordinates")) {
                                        route.setCoordinates(coordinates);
                                    }else{
                                        System.out.println("Ошибочный конечный тег, должно быть </coordinates>");
                                        System.out.println(getStructureFile());
                                        break;
                                    }
                                }
                            }
                        } else {
                            System.out.println("Исходный файл не должен содержать повторяющихся тегов внутри тега Route");
                            System.out.println(getStructureFile());
                            break;
                        }

                    }else if (startElement.getName().getLocalPart().equals("location_from")) {
                        if (from == null) {
                            from = new Location();

                            while (reader.hasNext()) {
                                xmlEvent = reader.nextEvent();
                                if (xmlEvent.isStartElement()) {
                                    startElement = xmlEvent.asStartElement();
                                    if (startElement.getName().getLocalPart().equals("x")) {
                                        if (from.getX() == 0) {
                                            from.setX(Integer.parseInt(xmlEvent.asCharacters().getData()));
                                        } else {
                                            System.out.println("Исходный файл не должен содержать повторяющихся тегов внутри тега location_from");
                                            break;
                                        }
                                    } else if (startElement.getName().getLocalPart().equals("y")) {
                                        if (from.getY() == 0) {
                                            from.setY(Float.valueOf(xmlEvent.asCharacters().getData()));
                                        } else {
                                            System.out.println("Исходный файл не должен содержать повторяющихся тегов внутри тега location_from");
                                            break;
                                        }
                                    } else {
                                        System.out.println("Неопознанный тег, редактируйте исходный файл");
                                        System.out.println(getStructureFile());
                                        break;
                                    }
                                }

                                if (xmlEvent.isEndElement()) {
                                    EndElement endElement = xmlEvent.asEndElement();
                                    if (endElement.getName().getLocalPart().equals("location_from")) {
                                        route.setFrom(from);
                                    } else {
                                        System.out.println("Ошибочный конечный тег, должно быть </location_from>");
                                        System.out.println(getStructureFile());
                                        break;
                                    }
                                }
                            }
                        } else {
                            System.out.println("Исходный файл не должен содержать повторяющихся тегов внутри тега Route");
                            System.out.println(getStructureFile());
                            break;
                        }

                    }else if (startElement.getName().getLocalPart().equals("location_to")) {
                        if (to == null) {
                            to = new Location();

                            while (reader.hasNext()) {
                                xmlEvent = reader.nextEvent();
                                if (xmlEvent.isStartElement()) {
                                    startElement = xmlEvent.asStartElement();
                                    if (startElement.getName().getLocalPart().equals("x")) {
                                        if (to.getX() == 0) {
                                            to.setX(Integer.parseInt(xmlEvent.asCharacters().getData()));
                                        } else {
                                            System.out.println("Исходный файл не должен содержать повторяющихся тегов внутри тега location_to");
                                            break;
                                        }
                                    } else if (startElement.getName().getLocalPart().equals("y")) {
                                        if (to.getY() == 0) {
                                            to.setY(Float.valueOf(xmlEvent.asCharacters().getData()));
                                        } else {
                                            System.out.println("Исходный файл не должен содержать повторяющихся тегов внутри тега locatiom_to");
                                            System.out.println(getStructureFile());
                                            break;
                                        }
                                    } else {
                                        System.out.println("Неопознанный тег, редактируйте исходный файл");
                                        System.out.println(getStructureFile());
                                        break;
                                    }
                                }
                                if (xmlEvent.isEndElement()) {
                                    EndElement endElement = xmlEvent.asEndElement();
                                    if (endElement.getName().getLocalPart().equals("location_to")) {
                                        route.setTo(to);
                                    } else {
                                        System.out.println("Ошибочный конечный тег, должно быть </location_to>");
                                        System.out.println(getStructureFile());
                                        break;
                                    }
                                }
                            }
                        } else {
                            System.out.println("Исходный файл не должен содержать повторяющихся тегов внутри тега Route");
                            System.out.println(getStructureFile());
                            break;
                        }
                    }

                    else {System.out.println("Неопознанный тег, редактируйте исходный файл");System.out.println(getStructureFile());break;}

                }

                if (xmlEvent.isEndElement()) {
                    EndElement endElement = xmlEvent.asEndElement();
                    if (endElement.getName().getLocalPart().equals("Route")) {
                        routeMap.put(routeMap.size(), route);
                    }
                }
            }
            CollectionManager.setCollection(routeMap);
            reader.close();

        } catch (FileNotFoundException exc ) {
            exc.printStackTrace();

            Scanner scanner = new Scanner(System.in);
            System.out.print("Ошибка: файл не обнаружен\n" + "Введите путь к исходному xml файлу: ");
            CollectionManager.setPathToDataFile(scanner.nextLine().trim());
            this.read(CollectionManager.getPathToDataFile());

        } catch(XMLStreamException exc){
            exc.printStackTrace();
        }
        return CollectionManager.sorteCollection();
    }

    @Override
    public String getStructureFile() {
        String example;
        try {
             example = new String(Files.readAllBytes(Paths.get(pathToExampleFile)));
             return "Файл должен иметь структуру\n" + example;
        }catch(IOException exc){
            return exc.getMessage();
        }
    }


}
