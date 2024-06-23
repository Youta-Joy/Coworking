package org.y_lab_intensive.in;

import org.y_lab_intensive.business.domain.CoworkingSpace;

import java.util.Set;
/**
 * Класс для проверки корректного ввода пользователя
*/
public class InputValidation {
    /**
     * Проверяет корректность ввода
     * @param allPlaces - список всех доступных площадок
     * @param input - ввод пользователя
     * @return - возвращает true если ввод корректный и false если нет
     */
    public static boolean correctInput(Set<CoworkingSpace> allPlaces, String input){
        int space = Integer.parseInt(input);
        for(CoworkingSpace place : allPlaces){
            if(place.getType().equalsIgnoreCase(input) || place.getName().equalsIgnoreCase(input) || place.getSpace() == space){
                return true;
            }
        }
        return false;
    }
}
