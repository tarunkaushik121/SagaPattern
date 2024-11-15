package org.example.repository;

import org.example.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DriveRepository extends JpaRepository<Driver, UUID> {
}
