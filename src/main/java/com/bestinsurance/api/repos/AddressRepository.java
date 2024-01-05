package com.bestinsurance.api.repos;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.bestinsurance.api.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, UUID>{

}
