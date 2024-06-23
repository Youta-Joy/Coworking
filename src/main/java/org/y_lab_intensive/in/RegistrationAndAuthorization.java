package org.y_lab_intensive.in;

import org.y_lab_intensive.business.domain.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
/**
 * Класс для регистрации и авторизации
 */
public class RegistrationAndAuthorization {
    private static User user;
    public static final Map<Integer, User> listOfUsers = new HashMap<>();
    private static final Scanner scanner = new Scanner(System.in);
    /**
     * Запускает вызов регистрации или авторизации
    */
    public static void start(){
        while(true) {
            System.out.println("Введите 1 - для регистрации\nВведите 2 - для авторизации");
            String input = scanner.nextLine().trim();
            if (input.equals("1")) {
                registration();
                break;
            } else if (input.equals("2")) {
                authorization();
                break;
            } else {
                System.out.println("Введите 1 из предложенных вариантов.");
            }
        }
        scanner.close();
    }
    /**
     * Запускает регистрацию
    */
    public static void registration(){
        String input,name,password;

        do{
            System.out.println("Введите имя");
            input = scanner.nextLine();
            if(!input.isBlank()){
                break;
            }else{
                System.out.println("Строка не может быть пустой и состоять из пробелов");
            }
        }while((input.isBlank()));
        name = input;
        do {
            System.out.println("Введите пароль");
            input = scanner.nextLine();
            if(!input.isBlank()){
                break;
            }else{
                System.out.println("Поле с паролем не может быть пустым и состоять из пробелов");
            }
        }while(input.isBlank());
        password = input;
        user = new User(name,password);

        if(listOfUsers.containsKey(user.getId()) && listOfUsers.containsValue(user)){
            System.out.println("Такой пользователь уже существует. Войдите в систему");
            start();
        }else{
            listOfUsers.put(user.getId(), user);
            MainUserMenu.getMainMenu();
        }
        scanner.close();
    }
    /**
     * Запускает авторизацию
     */
    public static void authorization(){
        String input, name, password;
        try {
            int id = user.getId();
            do {
                System.out.println("Введите имя");
                input = scanner.nextLine();
                if (!input.isBlank()) {
                    break;
                } else {
                    System.out.println("Строка не может быть пустой и состоять из пробелов");
                }
            } while ((input.isBlank()));
            name = input;
            do {
                System.out.println("Введите пароль");
                input = scanner.nextLine();
                if (!input.isBlank()) {
                    break;
                } else {
                    System.out.println("Поле с паролем не может быть пустым и состоять из пробелов");
                }
            } while (input.isBlank());
            password = input;
            if (listOfUsers.get(id).getName().equals(name) && listOfUsers.get(id).getPassword().equals(password)) {
                MainUserMenu.getMainMenu();
            } else {
                System.out.println("Такого пользователя не существует. Зарегистрируйтесь");
                start();
            }
        }catch (NullPointerException e){
            System.out.println("Такого пользователя не существует. Зарегистрируйтесь");
            start();
        }
        scanner.close();
    }

}
