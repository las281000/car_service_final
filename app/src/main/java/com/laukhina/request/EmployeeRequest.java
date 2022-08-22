package com.laukhina.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeRequest {
    private String name; //имя сотрудника
    private String surname; //фамилия сотруника
    private String phone; //телефон
}
