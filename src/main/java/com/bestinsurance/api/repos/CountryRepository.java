package com.bestinsurance.api.repos;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bestinsurance.api.model.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, UUID> {

}
