package com.bestinsurance.api.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CrudService<T> {

	T create(T obj);

    List<T> findAll();

    Optional<T> getById(UUID id);

    T update(UUID id, T obj);

    void delete(UUID id);
}
