package com.laukhina.repository;

import com.laukhina.entity.Car;;

/*
 * Инстерфейс репозитория для сущности Машина (Car)
 * Здесь представлены методы, специфичные именно для Машин
 */
public interface CarRepository extends Repository<Car> {

    //Поиск по номеру машины
    public Car findByNum(String num);

    //Поиск по VIN-номеру 
    public Car findByVin(String vin);
}
