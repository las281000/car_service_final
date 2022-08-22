package com.laukhina.service;

import java.time.Instant;

import com.laukhina.entity.Car;
import com.laukhina.entity.Employee;
import com.laukhina.entity.Operation;
import com.laukhina.entity.SparePart;
import com.laukhina.repository.OperationRepository;
import com.laukhina.request.OperationRequest;

public class OperationService {
    private final OperationRepository repository;
    private final CarService carService;
    private final SparePartService partService;
    private final EmployeeService employeeService;

    public OperationService(OperationRepository repository,
            CarService carService,
            SparePartService partService,
            EmployeeService employeeService) {

        this.repository = repository;
        this.carService = carService;
        this.partService = partService;
        this.employeeService = employeeService;
    }

    public Operation createOperation(OperationRequest request) {

        Car car = carService.getOrCreate(request.getCarRequest(), request.getCustomerRequest());
        SparePart part = partService.createSparePart(car.getModelId(), request.getPartRequest());
        Employee assignedEmployee = employeeService.chooseEmployee();

        Operation operation = Operation.builder()
                .employeeId(assignedEmployee.getId())
                .carId(car.getId())
                .partId(part.getId())
                .date(Instant.now())
                .price(request.getPrice())
                .description(request.getDescription())
                .build();

        operation = repository.save(operation);
        return operation;
    }

}
