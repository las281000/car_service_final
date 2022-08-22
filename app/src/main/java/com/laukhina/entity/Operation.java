package com.laukhina.entity;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * Класс, содержащий информацию о конкретной операции над машиной
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Operation {
    private long id; //id в базе
    private long employeeId; // сотрудник, выполнивший операцию
    private long carId; //машина
    private long partId; //установленная деталь
    private Instant date; //Дата и время операции
    private int price; //стоимость
    private String description; //описание операции
}
