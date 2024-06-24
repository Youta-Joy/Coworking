package org.y_lab_intensive.business.usecase;

import org.y_lab_intensive.business.domain.CoworkingSpace;
import org.y_lab_intensive.in.InputValidation;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Stream;

import static org.y_lab_intensive.business.domain.CoworkingSpace.COWORKING_TYPE;
import static org.y_lab_intensive.business.usecase.CoworkingService.allPlaces;
/**
 * Класс для действий связанных с бронированием
 */
public class BookingService{
    public static final DateTimeFormatter date = DateTimeFormatter.ofPattern("dd-MM-yy HH:mm");
    private static final Scanner scanner = new Scanner(System.in);
    public static final Map<LocalDateTime,CoworkingSpace> allBookingPlacesByDate = new HashMap<>();
    /**
     * Бронирует площадку на определенные дату и время
     * @param allPlaces - список всех доступных площадок
     */
    public static void bookingByDateAndTime(Set<CoworkingSpace> allPlaces){
        String input,type,name, checkDate;
        checkDate = "Check";
        LocalDateTime inputDate,correctDate;
        inputDate = LocalDateTime.now();
        try {
            do {
                System.out.println("Введите дату и время брони в формате \"dd-MM-yy HH:mm\"");
                input = scanner.nextLine();
                if (input.isBlank()) {
                    throw new Exception("Введите дату и время брони в формате \"dd-MM-yy HH:mm\"");
                }
                try {
                    inputDate = LocalDateTime.parse(input, date);
                    checkDate = inputDate.format(date);
                } catch (Exception e) {
                    System.out.println("Введите дату и время брони в формате \"dd-MM-yy HH:mm\"");
                }
            } while (!input.equals(checkDate));
            correctDate = inputDate;
            do {
                System.out.println("Введите тип желаемого коворкинга. Возможные типы:\"Рабочее место\" и \"Конференц-зал\"");
                input = scanner.nextLine();
                if (input.isBlank()) {
                    System.out.println("Поле не может быть пустым и состоять из пробелов");
                }
            } while (!COWORKING_TYPE.contains(input));
            type = input;
            do {
                System.out.println("Введите имя желаемого коворкинга");
                input = scanner.nextLine();
                if (input.isBlank()) {
                    System.out.println("Поле не может быть пустым и состоять из пробелов");
                }
            } while(!input.equals(InputValidation.correctInput(input)));
            name = input;
            for (CoworkingSpace place : allPlaces) {
                if (place.getType().equals(type) && place.getName().equals(name)) {
                    if (allBookingPlacesByDate.containsKey(correctDate) && allBookingPlacesByDate.containsValue(place)) {
                        System.out.println("В это время данная площадка недоступна");
                        break;
                    } else {
                        allBookingPlacesByDate.put(correctDate, place);
                        System.out.println("Бронь добавлена");
                        break;
                    }
                }else {
                    System.out.println("Такой площадки не существует");
                    break;
                }
            }
            scanner.close();
        }catch (Exception e){
            System.out.println("Что то пошло не так");
        }
    }
    /**
     * Предоставляет список доступных слотов для бронирования на конкретную дату
     * @param date - интересующая дата для брони
     * @param bookingList - список всех бронирований на определенные дату и время
     * @param places - список всех доступных площадок
     */
    public static void getListOfAvailableBookingByDate(LocalDateTime date, Map<LocalDateTime, CoworkingSpace> bookingList, Set<CoworkingSpace> places){
        if (bookingList.containsKey(date)) {
            for (CoworkingSpace place : places) {
                if (!bookingList.containsValue(place)) {
                    System.out.println("Свободный коворкинг на эту дату " + place);
                }
            }
        } else {
            System.out.println("Все коворкинги свободны для бронирования");
        }
    }
    /**
     * Позволяет изменить бронь
     * @param allBookingPlacesByDate - список всех бронирований на определенные дату и время
     */
    public static void changeBooking(Map<LocalDateTime,CoworkingSpace> allBookingPlacesByDate){
        String input, type, name, checkDate;
        LocalDateTime inputDate, correctDate;
        try {
            do {
                System.out.println("Введите дату и время брони которую хотите изменить в формате \"dd-MM-yy HH:mm\"");
                input = scanner.nextLine();
                inputDate = LocalDateTime.parse(input, date);
                checkDate = inputDate.format(date);
            } while (!input.equals(checkDate));
            correctDate = inputDate;
            do {
                System.out.println("Введите тип коворкинга который хотите изменить. Возможные типы:\"Рабочее место\" и \"Конференц-зал\"");
                input = scanner.nextLine();
                if (input.isBlank()) {
                    System.out.println("Поле не может быть пустым и состоять из пробелов");
                }
            } while (!COWORKING_TYPE.contains(input));
            type = input;
            do {
                System.out.println("Введите имя коворкинга который хотите изменить");
                input = scanner.nextLine();
                if(input.isBlank()){
                    System.out.println("Поле не может быть пустым и состоять из пробелов");
                }
            }while(!input.equals(InputValidation.correctInput(input)));
            name = input;
            if (allBookingPlacesByDate.containsKey(correctDate)){
                if (allBookingPlacesByDate.get(correctDate).getType().equalsIgnoreCase(type) && allBookingPlacesByDate.get(correctDate).getName().equalsIgnoreCase(name)){
                    System.out.println("Добавьте новую бронь:");
                    bookingByDateAndTime(allPlaces);
                }else{
                    System.out.println("На эту дату и время нет брони на этот коворкинг");
                }
            }else{
                System.out.println("На эти дату и время нет брони");
            }
            scanner.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    /**
     * Отменяет бронирование
     * @param allBookingPlacesByDate - список всех бронирований на определенные дату и время
     */
    public static void removeBooking(Map<LocalDateTime,CoworkingSpace> allBookingPlacesByDate) {
        String input, type, name, checkDate;
        LocalDateTime inputDate, correctDate;
        try {
            do {
                System.out.println("Введите дату и время брони которую хотите удалить в формате \"dd-MM-yy HH:mm\"");
                input = scanner.nextLine();
                inputDate = LocalDateTime.parse(input, date);
                checkDate = inputDate.format(date);
            } while (!input.equals(checkDate));
            correctDate = inputDate;
            do {
                System.out.println("Введите тип коворкинга который хотите удалить. Возможные типы:\"Рабочее место\" и \"Конференц-зал\"");
                input = scanner.nextLine();
                if (input.isBlank()) {
                    System.out.println("Поле не может быть пустым и состоять из пробелов");
                }
            } while (!COWORKING_TYPE.contains(input));
            type = input;
            do {
                System.out.println("Введите имя коворкинга который хотите удалить");
                input = scanner.nextLine();
                if(input.isBlank()){
                    System.out.println("Поле не может быть пустым и состоять из пробелов");
                }
            }while(!input.equals(InputValidation.correctInput(input)));
            name = input;
            if (allBookingPlacesByDate.containsKey(correctDate)){
                if (allBookingPlacesByDate.get(correctDate).getType().equalsIgnoreCase(type) && allBookingPlacesByDate.get(correctDate).getName().equalsIgnoreCase(name)){
                    allBookingPlacesByDate.remove(correctDate);
                }else{
                    System.out.println("На эту дату и время нет брони на этот коворкинг");
                }
            }else{
                System.out.println("На эти дату и время нет брони");
            }
            scanner.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    /**
     * Показывает список бронирований
     * @param allBookingPlacesByDate - список всех бронирований на определенные дату и время
     */
    public static void getAllBookings(Map<LocalDateTime,CoworkingSpace> allBookingPlacesByDate){
        for (Map.Entry<LocalDateTime, CoworkingSpace> entry : allBookingPlacesByDate.entrySet()){
            System.out.println("Список всех бронирований:");
            System.out.println(entry);
        }
    }
    /**
     * Показывает список бронирований отсортированных по дате
     * @param allBookingPlacesByDate - список всех бронирований на определенные дату и время
     */
    public static void getAllBookingsByDate(Map<LocalDateTime,CoworkingSpace> allBookingPlacesByDate){
        Map<LocalDateTime,CoworkingSpace> sortedByKey = new HashMap<>();
        List<LocalDateTime> sortedByDate = new ArrayList<>(allBookingPlacesByDate.keySet());
        Collections.sort(sortedByDate);
        for(int i = 0; i < sortedByDate.size(); i++){
            sortedByKey.put(sortedByDate.get(i), allBookingPlacesByDate.get(sortedByDate.get(i)));
        }
        System.out.println(sortedByKey);
    }
    /**
     * Показывает список бронирований отсортированных по названию прощадок
     */
    public static void  getAllBookingsByCoworkingName(){
        allBookingPlacesByDate.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .forEach(System.out::println);
    }
}
