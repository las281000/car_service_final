package com.laukhina.repository;

import com.laukhina.entity.Customer;

/*
 * Инстерфейс репозитория для сущности Клиента (Customer)
 * Здесь представлены методы, специфичные именно для Клиентов
 */
public interface CustomerRepository extends Repository<Customer>{

    //Поиск клиента по номеру телефона
    public Customer findByPhone(String phone);
}
 