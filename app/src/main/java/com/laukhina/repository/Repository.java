package com.laukhina.repository;

import java.sql.ResultSet;

/*
 * Базовые методы для всех репозиториев
 */
public interface Repository <E> {
   
   public E save(E entity); //сохранение в базу
   public E findById(long id); //поиск по id в базе
   public E mapResult(ResultSet result); //конвертация результата в entity
}
