package com.laukhina.repository;

import com.laukhina.entity.CarModel;

public interface CarModelRepository extends Repository<CarModel> {
    
    //Поиск модели по ее названию
    public CarModel findByName(String name);
}
