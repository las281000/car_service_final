package com.laukhina.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * Класс, содержащий информацию о сотруднике
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Employee {
    private long id; //id в базе
    private String name; //имя сотрудника
    private String surname; //фамилия сотруника
    private String phone; //телефон
}
