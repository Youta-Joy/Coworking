package org.y_lab_intensive.in;

import org.y_lab_intensive.business.domain.CoworkingSpace;

import java.util.Set;

import static org.y_lab_intensive.business.usecase.CoworkingService.allPlaces;

/**
 * Класс для проверки корректного ввода пользователя
*/
public class InputValidation {
    /**
     * Проверяет корректность ввода
     * @param input - ввод пользователя
     * @return - возвращает true если ввод корректный и false если нет
     */
    public static String correctInput(String input){
        String check = "";
        int space = Integer.parseInt(input);
        for(CoworkingSpace place : allPlaces){
            if(place.getType().equalsIgnoreCase(input) || place.getName().equalsIgnoreCase(input) || place.getSpace() == space){
                check = input;
            }else{
                check = "";
            }
        }
        return check;
    }
}
