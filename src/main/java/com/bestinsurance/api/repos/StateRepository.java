package com.bestinsurance.api.repos;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bestinsurance.api.model.State;

@Repository
public interface StateRepository extends JpaRepository<State, UUID>{

}
