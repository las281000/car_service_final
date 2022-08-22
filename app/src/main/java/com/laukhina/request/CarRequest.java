package com.laukhina.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CarRequest {
    private String num; // Номер машины
    private int engineCapacity; // Пробег (км)
    private String vin; // VIN-номер (или номер кузова)

    private String modelName; // название модели
    private String vendor; // Поставщик (он же вендор)
    private String country; // Страна производителя

}
