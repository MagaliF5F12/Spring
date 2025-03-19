package com.example.magasklad.Models;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.*;
import java.util.stream.Collectors;

public interface BaseModel {


    /**
     * Функция getColumns возвращает список названий столбцов.
     * <p>К примеру, пишем:
     * <pre>
     * return new ArrayList<>(Arrays.asList("Имя пользователя", "Логин", "Пароль"));
     * </pre>
     * <p>И получим в <code>base.html</code> следующую таблицу:
     * <pre>
     * |------------------|----------------|----------------------|
     * | Имя Пользователя |     Логин      |        Пароль        |
     * |------------------|----------------|----------------------|
     * </pre>
     */

    ArrayList<String> getColumns();

    /**
     * Функция getNewObject возвращает словарь полей модели.
     * <p>К примеру пишем:
     * <pre>
     * return new LinkedHashMap<>() {
     *     {
     *         put("username", Map.of("type", "text", "value", ""));
     *         put("password", Map.of("type", "password", "value", ""));
     *         put("roles", Map.of("type", "select-multiple", "value", List.of(RoleEnum.values())));
     *     }};
     * </pre>
     * Получим в Блоке добавления и изменения тестовые поля и поля с выбором.
     * <p>Определены следующие типы данных:
     * <ul>
     *     <li><b>date</b> - поле для даты</li>
     *     <li><b>select</b> - поле с выбором одного поля из нескольких</li>
     *     <li><b>select-multiple</b> - поле с выбором нескольких полей</li>
     *     <li><b>text</b> - текстовое поле</li>
     *     <li><b>password</b> - текстовое поле, которое скрывает введенные данные точками</li>
     * </ul>
     */

    LinkedHashMap<String, Object> getNewObject();

    /**
     * Функция getDataAttributes возвращает массив данных для заполнения таблицы.
     * <p>К примеру, пишем:
     * <pre>
     * return new ArrayList<>(Arrays.asList(username, password, roles.stream()
     *     .map(Enum::name)
     *     .collect(Collectors.joining(", "))));
     * </pre>
     * <p>Получим данные в самой таблице.
     */

    ArrayList<Object> getDataAttributes();

    default String toJson() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "{}"; // Возвращаем пустой JSON, если произошла ошибка
        }
    }

    String getStr();
}

