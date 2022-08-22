package com.laukhina.service;

import com.laukhina.entity.CarModel;
import com.laukhina.repository.CarModelRepository;
import com.laukhina.request.CarModelRequest;

public class CarModelService {
    private final CarModelRepository repository;

    public CarModelService(CarModelRepository repository) {
        this.repository = repository;
    }

    public CarModel getOrCreate(CarModelRequest request) {
        CarModel model = repository.findByName(request.getName()); // патаемся найти уже рарегистрированную модель

        if (model == null) { // если ничего не нашлось, вносим новую модель в базу
            model = repository.save(CarModel.builder()
                    .name(request.getName())
                    .vendor(request.getVendor())
                    .country(request.getCountry())
                    .build()); // сохраняем в базу
        }
        return model; // тут либо только что зарегистрированная машина, либо старая из базы
    }

}
