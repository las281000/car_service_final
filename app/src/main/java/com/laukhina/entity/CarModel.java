package com.laukhina.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * Класс, содержащий информацию о модели машины
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CarModel {
   private long id; //id в базе
   private String name; // название модели
   private String vendor; // Поставщик (он же вендор)
   private String country; // Страна производителя
}
