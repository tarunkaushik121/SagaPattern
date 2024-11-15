package org.example.repository;

import org.example.dtos.CommonStatus;
import org.example.entity.Cab;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CabRepository extends JpaRepository<Cab, UUID> {
    boolean existsByRegistrationNumberAndCabStatus(String registrationNumber, CommonStatus cabStatus);
}
