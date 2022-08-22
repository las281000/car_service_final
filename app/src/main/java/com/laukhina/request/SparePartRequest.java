package com.laukhina.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SparePartRequest {
    private String type; //тип (название) автозапчасти
    private String serialNum; //серийный номер
    private String manufacturer; //производитель
    private boolean isOriginal; //если оригинальная - true, если копия - false
}
