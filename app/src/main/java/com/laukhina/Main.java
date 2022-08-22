package com.laukhina;

import com.laukhina.repository.CarModelRepositoryImpl;
import com.laukhina.repository.CarRepositoryImpl;
import com.laukhina.repository.CustomerRepositoryImpl;
import com.laukhina.repository.EmployeeRepositoryImpl;
import com.laukhina.repository.OperationRepositoryImpl;
import com.laukhina.repository.SparePartRepositoryImpl;
import com.laukhina.request.CarRequest;
import com.laukhina.request.CustomerRequest;
import com.laukhina.request.EmployeeRequest;
import com.laukhina.request.OperationRequest;
import com.laukhina.request.SparePartRequest;
import com.laukhina.service.CarModelService;
import com.laukhina.service.CarService;
import com.laukhina.service.CustomerService;
import com.laukhina.service.EmployeeService;
import com.laukhina.service.OperationService;
import com.laukhina.service.SparePartService;
import com.laukhina.util.JdbcConnection;

public class Main {
    public static void main(String[] args) {
        JdbcConnection connection = new JdbcConnection();
        connection.makeConnection();

        var carModelRep = new CarModelRepositoryImpl(connection.getConnection());
        var carRep = new CarRepositoryImpl(connection.getConnection());
        var customerRep = new CustomerRepositoryImpl(connection.getConnection());
        var employeeRep = new EmployeeRepositoryImpl(connection.getConnection());
        var partRep = new SparePartRepositoryImpl(connection.getConnection());
        var operRep = new OperationRepositoryImpl(connection.getConnection());

        var carModelService = new CarModelService(carModelRep);
        var customerService = new CustomerService(customerRep);
        var carService = new CarService(carRep, carModelService, customerService);
        var employeeService = new EmployeeService(employeeRep);
        var partService = new SparePartService(partRep);
        var operationService = new OperationService(operRep, carService, partService, employeeService);

        employeeService.getOrCreate(EmployeeRequest.builder()
                .name("Alex")
                .surname("Black")
                .phone("9191033323")
                .build());

        employeeService.getOrCreate(EmployeeRequest.builder()
                .name("Sasha")
                .surname("Laukhina")
                .phone("9191033324")
                .build());

        operationService.createOperation(OperationRequest.builder()
                .carRequest(CarRequest.builder()
                        .num("A123AA")
                        .engineCapacity(3)
                        .vin("1KLBN52TWXM186109")
                        .modelName("S 350d")
                        .vendor("Mercedes Benz")
                        .country("Germany")
                        .build())

                .customerRequest(CustomerRequest.builder()
                        .name("Grigory")
                        .surname("Martirosov")
                        .phone("9152399696")
                        .build())

                .partRequest(SparePartRequest.builder()
                        .type("wheel")
                        .serialNum("12345678")
                        .manufacturer("China")
                        .isOriginal(false)
                        .build())

                .description("Снятие колеса")
                .price(1000000)
                .build());

                operationService.createOperation(OperationRequest.builder()
                .carRequest(CarRequest.builder()
                        .num("A123AA")
                        .engineCapacity(3)
                        .vin("1KLBN52TWXM186109")
                        .modelName("S 350d")
                        .vendor("Mercedes Benz")
                        .country("Germany")
                        .build())

                .customerRequest(CustomerRequest.builder()
                        .name("Grigory")
                        .surname("Martirosov")
                        .phone("9152399696")
                        .build())

                .partRequest(SparePartRequest.builder()
                        .type("chair")
                        .serialNum("12345678")
                        .manufacturer("Germany")
                        .isOriginal(true)
                        .build())

                .description("Снятие кресла")
                .price(100)
                .build());



        connection.closeConnection();
    }
}
