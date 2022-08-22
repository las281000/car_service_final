package com.laukhina.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;

/**
 * Класс, содержащий информацию о машине
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Car {
    private long id; //идентификатор машины в базе
    private long modelId; //Модель машины
    private String num; // Номер машины
    private long ownerId; //Владелец
    private int engineCapacity; //Объем двигателя
    private String vin; // VIN-номер (или номер кузова)    
}
