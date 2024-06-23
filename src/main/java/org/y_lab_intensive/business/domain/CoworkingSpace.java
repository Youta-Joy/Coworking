package org.y_lab_intensive.business.domain;

import java.util.Set;
/**
 * Класс-шаблон площадок
*/
public class CoworkingSpace {
    private String type;
    private String name;
    private int space;

    public static final Set<String> COWORKING_TYPE = Set.of("Рабочее место","Конференц-зал");
    /**
     * Конструктор класса
     * @param type - тип площадки
     * @param name - название площадки
     * @param space - площадь площадки
     */
    public CoworkingSpace(String type, String name, int space) {
        if(COWORKING_TYPE.contains(type)) {
            this.type = type;
            this.name = name;
            this.space = space;
        }else{
            throw new IllegalArgumentException("Типом коворкинга может быть только \"Рабочее место\" или \"Конференц-зал\"");
        }
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSpace() {
        return space;
    }

    public void setSpace(int space) {
        this.space = space;
    }

    @Override
    public String toString() {
        return "CoworkingSpace{" +
                "type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", space=" + space +
                '}';
    }
}
