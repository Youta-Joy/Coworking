package org.y_lab_intensive.business.domain;
/**
 * Класс-шаблон пользователя
 */
public class User {
    private String name;
    private String password;
    private int id;
    /**
     * Конструктор класса
     * @param name - имя пользователя
     * @param password - пароль для входа в систему
     */
    public User(String name, String password) {
        this.name = name;
        this.password = password;
        this.id += 1;

    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public int getId() {
        return id;
    }

}
