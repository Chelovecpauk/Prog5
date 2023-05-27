package collectionManagers;

import collection.route.Coordinates;
import collection.route.Route;
import collection.route.Location;

import java.io.*;
import java.util.AbstractMap;
import java.util.LinkedHashMap;
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
    private String pathToExampleFile = "C:\\Users\\Бахтияр\\IdeaProjects\\Prog5\\src\\main\\java\\data\\xmlStructure";

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
                                            xmlEvent = reader.nextEvent();
                                            coordinates.setX(Double.valueOf(xmlEvent.asCharacters().getData()));
                                        } else {
                                            System.out.println("Исходный файл не должен содержать повторяющихся тегов внутри тега coordinates");
                                            break;
                                        }
                                    } else if (startElement.getName().getLocalPart().equals("y")) {
                                        if (coordinates.getY() == 0) {
                                            xmlEvent = reader.nextEvent();
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
                                        break;
                                    }else if( !endElement.getName().getLocalPart().equals("x") &&  !endElement.getName().getLocalPart().equals("y")){
                                        System.out.println("Ошибочный конечный тег");
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
                                            xmlEvent = reader.nextEvent();
                                            from.setX(Integer.parseInt(xmlEvent.asCharacters().getData()));
                                        } else {
                                            System.out.println("Исходный файл не должен содержать повторяющихся тегов внутри тега location_from");
                                            break;
                                        }
                                    } else if (startElement.getName().getLocalPart().equals("y")) {
                                        if (from.getY() == null) {
                                            xmlEvent = reader.nextEvent();
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
                                        break;
                                    } else if( !endElement.getName().getLocalPart().equals("x") &&  !endElement.getName().getLocalPart().equals("y")){
                                        System.out.println("Ошибочный конечный тег");
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
                                            xmlEvent = reader.nextEvent();
                                            to.setX(Integer.parseInt(xmlEvent.asCharacters().getData()));
                                        } else {
                                            System.out.println("Исходный файл не должен содержать повторяющихся тегов внутри тега location_to");
                                            break;
                                        }
                                    } else if (startElement.getName().getLocalPart().equals("y")) {
                                        if (to.getY() == null) {
                                            xmlEvent = reader.nextEvent();
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
                                        break;
                                    } else if( !endElement.getName().getLocalPart().equals("x") &&  !endElement.getName().getLocalPart().equals("y")){
                                        System.out.println("Ошибочный конечный тег");
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

                    else if(!startElement.getName().getLocalPart().equals("Routes")){
                        System.out.println("Неопознанный тег, редактируйте исходный файл ");
                        System.out.println(getStructureFile());
                        break;
                    }

                }

                if (xmlEvent.isEndElement()) {
                    EndElement endElement = xmlEvent.asEndElement();
                    if (endElement.getName().getLocalPart().equals("Route")) {
                        routeMap.put(routeMap.size(), route);
                        route = null;
                        coordinates = null;
                        from = null;
                        to = null;
                    }
                }
            }
            CollectionManager.setCollection(routeMap);
            reader.close();

        } catch (FileNotFoundException exc ) {
            exc.printStackTrace();
            System.out.println("Ошибка: файл не обнаружен");

            /*Scanner scanner = new Scanner(System.in);
            System.out.print("Ошибка: файл не обнаружен\n" + "Введите путь к исходному xml файлу: ");
            CollectionManager.setPathToDataFile(scanner.nextLine().trim());
            this.read(CollectionManager.getPathToDataFile());*/
        } catch(XMLStreamException exc){
            exc.printStackTrace();
        }catch(NumberFormatException exc){
            System.out.println("Файл содержит недопустимые значения переменных");
            System.out.println("Допустимый формат: \nname - String\ndistance - Long\nCoordinates\n\tx - Double\n\ty - long\nLocation\n\tx - int\n\ty - Float");
        }
        return CollectionManager.sorteCollection();
    }

    @Override
    public void write(String pathToDataFile, AbstractMap<Integer,Route> routeMap){
        try {
            OutputStream os = new FileOutputStream(pathToDataFile);
            Writer osr = new OutputStreamWriter(os);
            osr.write("<?xml version=\"1.1\" encoding=\"UTF-8\" ?>\n" + "<Routes>\n");
            for (Route route : CollectionManager.getRouteMap().values()) {
                osr.write("\t<Route>\n" +
                        "\t\t<name>" + route.getName() + "</name>\n" +
                        "\t\t<distance>" + route.getDistance() + "</distance>\n" +
                        "\t\t<coordinates>\n" +
                        "\t\t\t<x>" + route.getCoordinates().getX() + "</x>\n" +
                        "\t\t\t<y>" + route.getCoordinates().getY() + "</y>\n" +
                        "\t\t</coordinates>\n" +
                        "\t\t<location_from>\n" +
                        "\t\t\t<x>" + route.getFrom().getX() + "</x>\n" +
                        "\t\t\t<y>" + route.getFrom().getY() + "</y>\n" +
                        "\t\t</location_from>\n" +
                        "\t\t<location_to>\n" +
                        "\t\t\t<x>" + route.getTo().getX() + "</x>\n" +
                        "\t\t\t<y>" + route.getTo().getY() + "</y>\n" +
                        "\t\t</location_to>\n" +
                        "\t</Route>");
            }
            osr.write("</Routes>");
            if (osr != null) {
                osr.close();
            }
        }catch(IOException e){
        e.printStackTrace();
        }
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
