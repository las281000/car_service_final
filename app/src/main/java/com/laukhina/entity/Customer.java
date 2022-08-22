package com.laukhina.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * Класс, содержащий информацию о клиенте
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Customer {
    private long id; //id в базе
    private String name; //имя
    private String surname; //фамилия
    private String phone; //телефон
}
