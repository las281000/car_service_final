package com.laukhina.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Класс, содержащий информацию о Детали
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SparePart {
    private long id; // id в базе
    private String type; //тип (название) автозапчасти
    private String serialNum; //серийный номер
    private String manufacturer; //производитель
    private long carModelId; // К какой модели автомобилей относится
    private boolean isOriginal; //если оригинальная - true, если копия - false
}