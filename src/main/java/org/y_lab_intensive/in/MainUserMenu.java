package org.y_lab_intensive.in;

import org.y_lab_intensive.business.usecase.BookingService;
import org.y_lab_intensive.business.usecase.CoworkingService;

import java.time.LocalDateTime;
import java.util.Scanner;

import static org.y_lab_intensive.business.usecase.BookingService.allBookingPlacesByDate;
import static org.y_lab_intensive.business.usecase.BookingService.date;
import static org.y_lab_intensive.business.usecase.CoworkingService.allPlaces;
/**
 * Класс для взаимодействия с пользователем
 */
public class MainUserMenu {
    private static final Scanner scanner = new Scanner(System.in);
    /**
     * Предоставляет пользователю список доступных действий
     */
    public static void getMainMenu() {
        CoworkingService.defaultCoworking();
        String input,innerInput;
        do {
            System.out.println("Выберите действие: ");
            System.out.println("1 - для просмотра списка всех доступных рабочих мест и конференц-залов");
            System.out.println("2 - для просмотра доступных слотов для бронирования на конкретную дату; ");
            System.out.println("3 - для бронирования рабочего места или конференц-зала на определённое время и дату;");
            System.out.println("4 - для изменения бронирования;");
            System.out.println("5 - для отмены бронирования;");
            System.out.println("6 - для добавления новых рабочих мест и конференц-залов, а также управление существующими;");
            System.out.println("7 - для просмотра всех бронирований и их фильтрация по дате, пользователю или ресурсу.");
            System.out.println("8 - чтобы завершить программу ");
            input = scanner.nextLine();
            switch (input) {
                case "1":
                    CoworkingService.getListOfAvailablePlaces(allPlaces);
                    break;
                case "2":
                    try {
                        LocalDateTime inputDate, correctDate;
                        String checkDate;
                        do {
                            System.out.println("Введите желаемые дату и время в формате \"dd-MM-yy HH:mm\"");
                            innerInput = scanner.nextLine();
                            if (innerInput.isBlank()) {
                                System.out.println("Ввод не может быть пустым и состоять из пробелов");
                            }
                            inputDate = LocalDateTime.parse(innerInput, date);
                            checkDate = date.format(inputDate);
                        } while (!innerInput.equals(checkDate));
                        correctDate = inputDate;
                        BookingService.getListOfAvailableBookingByDate(correctDate, allBookingPlacesByDate, allPlaces);
                    }catch (Exception e){
                        System.out.println("Введите желаемые дату и время в формате \"dd-MM-yy HH:mm\"");
                    }
                    break;
                case "3":
                    BookingService.bookingByDateAndTime(allPlaces);
                    break;
                case "4":
                    BookingService.changeBooking(allBookingPlacesByDate);
                    break;
                case "5":
                    BookingService.removeBooking(allBookingPlacesByDate);
                    break;
                case "6":
                    do {
                        System.out.println("Выберите действие:");
                        System.out.println("1 - Добавить коворкинг");
                        System.out.println("2 - Изменить коворкинг");
                        System.out.println("3 - Удалить коворкинг");
                        System.out.println("4 - Выйти из этого меню");
                        innerInput = scanner.nextLine();
                        switch (innerInput){
                            case "1":
                                CoworkingService.addNewCoworking();
                                break;
                            case "2":
                                CoworkingService.changeCoworking(allPlaces);
                                break;
                            case "3":
                                CoworkingService.removeCoworking(allPlaces);
                                break;
                            case "4":
                                break;
                            default:
                                System.out.println("Выберите действие:");
                        }
                    }while(!innerInput.equals("4"));
                    break;
                case "7":
                    do {
                        System.out.println("Выберите действие:");
                        System.out.println("1 - Посмотреть список всех забронированных места");
                        System.out.println("2 - Посмотреть список всех забронированных мест отсортированных по дате");
                        System.out.println("3 - Посмотреть список всех забронированных мест отсортированных по названию площадки");
                        System.out.println("4 - Выйти из этого меню");
                        innerInput = scanner.nextLine();
                        switch (innerInput){
                            case "1":
                                BookingService.getAllBookings(allBookingPlacesByDate);
                                break;
                            case "2":
                                BookingService.getAllBookingsByDate(allBookingPlacesByDate);
                                break;
                            case "3":
                                BookingService.getAllBookingsByCoworkingName();
                                break;
                            case "4":
                                break;
                            default:
                                System.out.println("Выберите действие:");
                        }
                    }while(!innerInput.equals("4"));
                    break;
                case "8":
                    break;
                default:
                    System.out.println("Выберите действие: ");
            }
        }while (!input.equals("8"));
        scanner.close();
    }
}
