package org.y_lab_intensive.business.usecase;

import org.y_lab_intensive.business.domain.CoworkingSpace;
import org.y_lab_intensive.in.InputValidation;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import static org.y_lab_intensive.business.domain.CoworkingSpace.COWORKING_TYPE;
/**
 * Класс для действий с площадками
 */
public class CoworkingService {

    public static final Set<CoworkingSpace> allPlaces = new HashSet<>();
    private static final Scanner scanner = new Scanner(System.in);
    /**
     * Заполняет список площадок базовым набором площадок
     */
    public static void defaultCoworking(){
        allPlaces.add(new CoworkingSpace("Рабочее место","Бодрое утро", 2));
        allPlaces.add(new CoworkingSpace("Рабочее место","Доброе утро", 3));
        allPlaces.add(new CoworkingSpace("Рабочее место","Уголок продуктивности", 2));

        allPlaces.add(new CoworkingSpace("Конференц-зал","МЕГА", 12));
        allPlaces.add(new CoworkingSpace("Конференц-зал","УЛЬТРА", 15));
        allPlaces.add(new CoworkingSpace("Конференц-зал","ТЕРРА", 24));
    }
    /**
     * Добавляет новые площадки в список площадок
     */
    public static void addNewCoworking(){
        try {
            String input, type, name;
            int space;
            do {
                do {
                    System.out.println("Введите тип коворкинга: \"Рабочее место\" или \"Конференц-зал\"");
                    input = scanner.nextLine();
                }while(!COWORKING_TYPE.contains(input));
                type = input;
                do {
                    System.out.println("Введите название коворкинга");
                    input = scanner.nextLine();
                    if(input.isBlank()){
                        System.out.println("Поле не может быть пустым и состоять из пробелов");
                    }
                }while(!input.equals(InputValidation.correctInput(input)));
                name = input;
                System.out.println("Введите желаемую площадь коворкинга");
                input = scanner.nextLine();
                space = Integer.parseInt(input);
                allPlaces.add(new CoworkingSpace(type, name, space));
                System.out.println("Введите 2 чтобы завершить добавление");
                input = scanner.nextLine();
            } while (!input.equals("2"));
        }catch (NullPointerException e){
            System.out.println("Поля ввода не могут быть пустыми");
        }
        scanner.close();
    }
    /**
     * Показывает список всех доступных площадок
     * @param spaces - список всех доступных площадок
     */
    public static void getListOfAvailablePlaces(Set<CoworkingSpace> spaces){
        for(CoworkingSpace space : spaces){
            System.out.println(space);
        }
    }
    /**
     * Показывает список площадок типа "Рабочее место"
     * @param workplaces - список всех доступных площадок
     */
    public static void getAllWorkplaces(Set<CoworkingSpace> workplaces){
        for(CoworkingSpace workplace : workplaces){
            if(workplace.getType().equals("Рабочее место")){
                System.out.println(workplace);
            }
        }
    }
    /**
     * Показывает список площадок типа "Конференц-зал"
     * @param confrooms - список всех доступных площадок
     */
    public static void getAllConferenceRooms(Set<CoworkingSpace> confrooms){
        for(CoworkingSpace confroom : confrooms){
            if(confroom.getType().equals("Конференц-зал")){
                System.out.println(confroom);
            }
        }
    }
    /**
     * Изменяет существующую площадку в списке всех площадок
     * @param spaces - список всех доступных площадок
     */
    public static void changeCoworking(Set<CoworkingSpace> spaces){
        String input,type,name;
        int number;
        do {
            System.out.println("Введите тип коворкинга который хотите изменить\nВозможные типы:\"Рабочее место\" и \"Конференц-зал\"");
            input = scanner.nextLine();
            if(input.isBlank()){
                System.out.println("Поле не может быть пустым и состоять из пробелов");
            }
        }while(!COWORKING_TYPE.contains(input));
        type = input;
        do {
            System.out.println("Введите название коворкинга который хотите изменить");
            input = scanner.nextLine();
            if(input.isBlank()){
                System.out.println("Поле не может быть пустым и состоять из пробелов");
            }
        }while(!input.equals(InputValidation.correctInput(input)));
        name = input;

        for (CoworkingSpace space : spaces){
            String innertype,innername;
            if(space.getType().equals(type) && space.getName().equals(name)){
                do {
                    System.out.println("Введите новый тип коворкинга\nВозможные типы:\"Рабочее место\" и \"Конференц-зал\"");
                    input = scanner.nextLine();
                    if(input.isBlank()){
                        System.out.println("Поле не может быть пустым и состоять из пробелов");
                    }
                }while(!COWORKING_TYPE.contains(input));
                innertype = input;
                space.setType(innertype);
                do {
                    System.out.println("Введите новое название коворкинга");
                    input = scanner.nextLine();
                    if(input.isBlank()){
                        System.out.println("Поле не может быть пустым и состоять из пробелов");
                    }
                }while(input.equals(InputValidation.correctInput(input)));
                innername = input;
                space.setName(innername);
                System.out.println("Введите новую площадь коворкинга");
                input = scanner.nextLine();
                number = Integer.parseInt(input);
                space.setSpace(number);
            }
        }
        scanner.close();
    }
    /**
     * Удаляет существующую площадку из списка всех площадок
     * @param spaces - список всех доступных площадок
     */
    public static void removeCoworking(Set<CoworkingSpace> spaces){
        String input,type,name;
        do {
            System.out.println("Введите тип коворкинга который хотите удалить\nВозможные типы:\"Рабочее место\" и \"Конференц-зал\"");
            input = scanner.nextLine();
            if(input.isBlank()) {
                System.out.println("Поле не может быть пустым и состоять из пробелов");
            }
        }while(!COWORKING_TYPE.contains(input));
        type = input;
        do {
            System.out.println("Введите название коворкинга который хотите удалить");
            input = scanner.nextLine();
            if(input.isBlank()){
                System.out.println("Поле не может быть пустым и состоять из пробелов");
            }
        }while(!input.equals(InputValidation.correctInput(input)));
        name = input;

        for (CoworkingSpace space : spaces) {
            if (space.getType().equals(type) && space.getName().equals(name)) {
                spaces.remove(space);
            }
        }
        scanner.close();
    }
}
