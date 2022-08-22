package com.laukhina.service;

import com.laukhina.entity.Car;
import com.laukhina.entity.Customer;
import com.laukhina.entity.CarModel;
import com.laukhina.repository.CarRepository;
import com.laukhina.request.CarModelRequest;
import com.laukhina.request.CarRequest;
import com.laukhina.request.CustomerRequest;

/*
 * Бизнес-логика для сущности Машина
 * CarService обращается к базе через репозиторий
 */
public class CarService {
    private final CarRepository repository;
    private final CarModelService modelService;
    private final CustomerService customerService;

    public CarService(CarRepository repository, CarModelService modelService, CustomerService customerService) {
        this.repository = repository;
        this.modelService = modelService;
        this.customerService = customerService;
    }

    // поиск или регистрация машины в сервисе
    public Car getOrCreate(CarRequest carRequest, CustomerRequest ownerRequest) {
        // Создаем обертку модели для дальнейшей передачи в CarModelService
        CarModelRequest modelRequest = CarModelRequest.builder()
                .name(carRequest.getModelName())
                .vendor(carRequest.getVendor())
                .country(carRequest.getCountry())
                .build();

        CarModel model = modelService.getOrCreate(modelRequest);

        Customer owner = customerService.getOrCreate(ownerRequest);

        Car car = repository.findByNum(carRequest.getNum()); // пробуем найти в базе по номеру

        if (car == null) { // если нет номера в базе, значит магишину привезли впервые
            car = repository.save(Car.builder()
                    .modelId(model.getId())
                    .num(carRequest.getNum())
                    .ownerId(owner.getId())
                    .engineCapacity(carRequest.getEngineCapacity())
                    .engineCapacity(carRequest.getEngineCapacity())
                    .build()); // сохраняем в базу
        }
        return car; // тут либо только что зарегистрированная машина, либо старая из базы

    }

}
