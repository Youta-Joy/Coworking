package org.y_lab_intensive.business.usecase;

import org.y_lab_intensive.business.domain.CoworkingSpace;
import org.y_lab_intensive.in.InputValidation;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
//import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import static org.y_lab_intensive.business.domain.CoworkingSpace.COWORKING_TYPE;
import static org.y_lab_intensive.business.usecase.CoworkingService.allPlaces;
/**
 * Класс для действий связанных с бронированием
 */
public class BookingService {
    public static final DateTimeFormatter date = DateTimeFormatter.ofPattern("dd-MM-yy HH:mm");
    private static final Scanner scanner = new Scanner(System.in);
    public static final Map<LocalDateTime,CoworkingSpace> allBookingPlacesByDate = new HashMap<>();
    /**
     * Бронирует площадку на определенные дату и время
     * @param allPlaces - список всех доступных площадок
     */
    public static void bookingByDateAndTime(Set<CoworkingSpace> allPlaces){
        String input,type,name, checkDate;
        LocalDateTime inputDate,correctDate;
        try {
            do {
                System.out.println("Введите дату и время брони в формате \"dd-MM-yy HH:mm\"");
                input = scanner.nextLine();
                if(input.isBlank()){
                    throw new Exception("Введите дату и время брони в формате \"dd-MM-yy HH:mm\"");
                }
                inputDate = LocalDateTime.parse(input, date);
                checkDate = date.format(inputDate);
            } while (!input.equals(checkDate));
            correctDate = inputDate;

            do {
                System.out.println("Введите тип желаемого коворкинга. Возможные типы:\"Рабочее место\" и \"Конференц-зал\"");
                input = scanner.nextLine();
                if(input.isBlank()){
                    System.out.println("Поле не может быть пустым и состоять из пробелов");
                }
            }while(!COWORKING_TYPE.contains(input));
            type = input;
            do {
                System.out.println("Введите имя желаемого коворкинга");
                input = scanner.nextLine();
                if(input.isBlank()){
                    System.out.println("Поле не может быть пустым и состоять из пробелов");
                }
            }while(!InputValidation.correctInput(allPlaces,input));
            name = input;
            for(CoworkingSpace place : allPlaces) {
                if (place.getType().equals(type) && place.getName().equals(name)) {
                    if(allBookingPlacesByDate.containsKey(correctDate) && allBookingPlacesByDate.containsValue(place)){
                        System.out.println("В это время данная площадка недоступна");
                    }else {
                        allBookingPlacesByDate.put(correctDate,place);
                    }
                }
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        scanner.close();
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
                checkDate = date.format(inputDate);
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
            }while(!InputValidation.correctInput(allPlaces,input));
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
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        scanner.close();
    }
    /**
     * Показывает список бронирований отсортированных по дате или площадке
     * @param allBookingPlacesByDate - список всех бронирований на определенные дату и время
     */
    /*
    public static void getAllBookingsBy(Map<LocalDateTime,CoworkingSpace> allBookingPlacesByDate){
        for (Map.Entry<LocalDateTime, CoworkingSpace> entry : allBookingPlacesByDate.entrySet()){
            Comparator<Map<LocalDateTime,CoworkingSpace>> byBookingDate = Comparator.comparing()
        }
        for(LocalDateTime keys : allBookingPlacesByDate.keySet()){
            Comparator byDate
        }
        for(CoworkingSpace values : allBookingPlacesByDate.values()){

        }
        //Comparator<Map<Booking,CoworkingSpace>> byBookingDate = Co
    }
    */

}
