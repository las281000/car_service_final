package com.laukhina.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CarModelRequest {
    private String name; // название модели
   private String vendor; // Поставщик (он же вендор)
   private String country; // Страна производителя
}
