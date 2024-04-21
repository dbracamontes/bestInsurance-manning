package com.bestinsurance.api.services;

import java.util.List;
import java.util.UUID;

public interface CrudService<T,U> {

	U create(T obj);

    List<U> findAll();

    U getById(UUID id);

    U update(UUID id, T obj);

    void delete(UUID id);
}
