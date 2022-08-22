package com.laukhina.service;

import com.laukhina.repository.CustomerRepository;
import com.laukhina.request.CustomerRequest;
import com.laukhina.entity.Customer;

/*
 * Бизнес-логика для сущности Клиента
 * CustomerService обращается к базе через репозиторий
 */
public class CustomerService {
    private final CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    // находит старого клиента по телефону или регистрирует нового
    public Customer getOrCreate(CustomerRequest request) {
        Customer customer = repository.findByPhone(request.getPhone());// пытаемся найти по телефону

        if (customer == null) { // если нет номера в базе, значит клиент пришел впервые
            customer = repository.save(Customer.builder()// сохраняем в базу
                    .name(request.getName())
                    .surname(request.getSurname())
                    .phone(request.getPhone())
                    .build());
        }
        return customer; // тут либо только что зарегистрированнй Клиент, либо старый из базы
    }
}
